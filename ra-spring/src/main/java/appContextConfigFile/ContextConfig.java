package appContextConfigFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ContextConfig implements IContextConfig {

    private List<Bean> beans;

    public ContextConfig() {}

    public ContextConfig(List<Bean> beans) {
        this.beans = beans;
    }

    @Override
    public Map<String, Object> readConfigFile(InputStream stream) {
        return null;
    }

    public List<Bean> getBeans() {
        return beans;
    }

    public void setBeans(List<Bean> beans) {
        this.beans = beans;
    }
}
