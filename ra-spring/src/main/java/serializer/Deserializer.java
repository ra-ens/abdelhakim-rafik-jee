package serializer;

import appContextConfigFile.ContextConfig;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;

public class Deserializer implements IDeserializer<ContextConfig>{

    /**
     * Read config file from the given InputStream.
     * @param inputStream the input stream
     * @return the deserialized object
     * @throws IOException in case of errors reading from the stream
     */
    @Override
    public ContextConfig deserialize(InputStream inputStream) {
        Yaml yaml = new Yaml(new Constructor(ContextConfig.class));
        ContextConfig config = yaml.load(inputStream);
        return config;
    }
}
