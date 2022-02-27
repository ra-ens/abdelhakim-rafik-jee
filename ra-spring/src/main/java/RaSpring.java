import appContextConfigFile.Bean;
import appContextConfigFile.ConstructorArg;
import appContextConfigFile.ContextConfig;
import appContextConfigFile.Property;
import serializer.Deserializer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class RaSpring {

    private Map<String, Object> beanRegistry = new HashMap<>();

    public RaSpring() {
    }

    public RaSpring(String configFilePath) throws FileNotFoundException, ClassNotFoundException {
        Deserializer deserializer = new Deserializer();
        try {
            FileInputStream file = new FileInputStream(new File(configFilePath));
            ContextConfig config = deserializer.deserialize(file);
            this.init(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(ContextConfig config) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        for (Bean bean: config.getBeans()) {
            System.out.println(">> " + bean.getClassName());
            Class bClass = Class.forName(bean.getClassName());
            Object bInstance = bClass.newInstance();
            beanRegistry.put(bean.getId(), bInstance);

            // if has constructor args
            if(bean.getConstructorArgs() != null) {
                for (ConstructorArg arg: bean.getConstructorArgs()) {

                }
            }
            // if has properties
            System.out.println();
            if(bean.getProperties() != null) {
                for(Property prop: bean.getProperties()) {
                    System.out.println(">>>>> " + prop.getName() + " | " + prop.getRef());
                    Object refInstance = beanRegistry.get(prop.getRef());
                    PropertyDescriptor descriptor = new PropertyDescriptor(prop.getName(), bClass);
                    descriptor.getWriteMethod().invoke(bInstance, refInstance);
                }
            }
        }
    }

    public Object getBean(String name) {
        return this.beanRegistry.get(name);
    }
}
