package appContextConfigFile;

import java.util.List;

public class Bean {

    private String id;
    private String className;
    private List<ConstructorArg> constructorArgs;
    private List<Property> properties;

    public Bean() {}

    public Bean(String id, String className, List<ConstructorArg> constructorArgs, List<Property> properties) {
        this.id = id;
        this.className = className;
        this.constructorArgs = constructorArgs;
        this.properties = properties;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
