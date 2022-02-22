package serializer;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Deserializer implements IDeserializer<Map<String, Object>>{

    /**
     * Read config file from the given InputStream.
     * @param inputStream the input stream
     * @return the deserialized object
     * @throws IOException in case of errors reading from the stream
     */
    @Override
    public Map<String, Object> deserialize(InputStream inputStream) throws IOException {
        Yaml yaml = new Yaml();
        Map<String, Object> fileContent = yaml.load(inputStream);
        return fileContent;
    }
}
