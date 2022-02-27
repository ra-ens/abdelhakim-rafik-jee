import appContextConfigFile.Bean;
import appContextConfigFile.ContextConfig;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import serializer.Deserializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Deserializer deserializer = new Deserializer();
        try {
            ContextConfig config = deserializer.deserialize(new FileInputStream(new File(Main.class.getResource("ApplicationContext.yml").getPath())));
            System.out.println(config.getBeans().get(0).getClassName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
