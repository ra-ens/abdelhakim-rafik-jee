package appContextConfigFile;

import java.util.List;

public class Bean {

    private String id;
    private String className;
    private List<ConstructorArg> constructorArgs;

    public Bean() {}

    public Bean(String id, String className, List<ConstructorArg> constructorArgs) {
        this.id = id;
        this.className = className;
        this.constructorArgs = constructorArgs;
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
}
