package appContextConfigFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class ContextConfig implements IContextConfig {

    ArrayList<Bean> beans = new ArrayList<>();

    @Override
    public Map<String, Object> readConfigFile(InputStream stream) {
        return null;
    }
}
