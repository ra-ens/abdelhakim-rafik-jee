import annotations.Component;
import appContextConfigFile.Bean;
import appContextConfigFile.ConstructorArg;
import appContextConfigFile.ContextConfig;
import appContextConfigFile.Property;
import org.reflections.Reflections;
import serializer.Deserializer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RaSpring {

    private Map<String, Object> beanRegistry = new HashMap<>();
    private Map<String, Class> classesRegistry = new HashMap<>();

    /**
     * Framework constructor with packages list
     * @param args
     */
    public RaSpring(String ...args) {

        for (String arg : args) {
            Reflections reflections = new Reflections(arg);
            Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Component.class);
            System.out.println(arg + " >> " + classes.size());
        }
    }

    /**
     * Framework constructor with file configuration
     * @param configFilePath
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public RaSpring(String configFilePath) throws IOException, ClassNotFoundException {
        Deserializer deserializer = new Deserializer();
            FileInputStream file = new FileInputStream(new File(configFilePath));
            ContextConfig config = deserializer.deserialize(file);
        try {
            this.init(config);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialise the framework with given context configuration
     * @param config
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     */
    private void init(ContextConfig config) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        for (Bean bean: config.getBeans()) {
            // get class
            Class bClass = Class.forName(bean.getClassName());
            classesRegistry.put(bean.getId(), bClass);

            // Class Instance
            Object bInstance = null;

            // Create instance with constructor args
            if(bean.getConstructorArgs() != null) {
                List<ConstructorArg> args = bean.getConstructorArgs();

                // prepare constructor parameters types
                Class[] paramsType = new Class[args.size()];
                Object[] params = new Object[args.size()];
                for(int i=0; i<args.size(); i++) {
                    paramsType[i] = this.classesRegistry.get(args.get(i).getRef());
                    params[i] = this.beanRegistry.get(args.get(i).getRef());
                }

                // get compatible constructor
                Constructor<?> constructor = this.getCompatibleConstructor(bClass, paramsType);
                if(constructor == null) throw new RuntimeException("No constructor Found for: "+ bean.getId());

                // instantiate the class with founded constructor
                bInstance = constructor.newInstance(params);
            }
            // create instance without constructor args
            else {
                bInstance = bClass.newInstance();
                // if has properties
                if (bean.getProperties() != null) {
                    for (Property prop : bean.getProperties()) {
                        Object refInstance = beanRegistry.get(prop.getRef());
                        PropertyDescriptor descriptor = new PropertyDescriptor(prop.getName(), bClass);
                        descriptor.getWriteMethod().invoke(bInstance, refInstance);
                    }
                }
            }

            if(bInstance == null) throw new RuntimeException(bean.getClassName() + " Can't be instantiate");
            // register created bean
            beanRegistry.put(bean.getId(), bInstance);
        }
    }

    /**
     * Get compatible constructor from given paramsTypes
     * @param pClass
     * @param parameterTypes
     * @return
     */
    private Constructor<?> getCompatibleConstructor(Class<?> pClass, Class<?>... parameterTypes) {
        Constructor<?>[] constructors = pClass.getConstructors();
        outer : for (Constructor<?> ctr : constructors) {
            if (ctr.getParameterCount() == parameterTypes.length) {
                for (int i = 0 ; i < parameterTypes.length ; i++) {
                    if (!ctr.getParameterTypes()[i].isAssignableFrom(parameterTypes[i])) {
                        continue outer;
                    }
                }
                return ctr;
            }
        }
        return null;
    }

    /**
     * Get bean by its id
     * @param name
     * @return
     */
    public Object getBean(String name) {
        return this.beanRegistry.get(name);
    }
}
