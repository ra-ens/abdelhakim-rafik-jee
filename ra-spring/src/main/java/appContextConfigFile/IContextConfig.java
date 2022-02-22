package appContextConfigFile;

import java.io.InputStream;
import java.util.Map;

public interface IContextConfig {

    Map<String, Object> readConfigFile(InputStream stream);
}
