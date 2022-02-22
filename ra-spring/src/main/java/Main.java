import appContextConfigFile.Bean;
import appContextConfigFile.ContextConfig;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import serializer.Deserializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Deserializer deserializer = new Deserializer();
        try {
            System.out.println(">>>" + Main.class.getResource("ApplicationContext.yml").toString());
            FileInputStream file = new FileInputStream(new File(Main.class.getResource("ApplicationContext.yml").getPath()));
            Yaml yaml = new Yaml(new Constructor(Bean.class));
            Bean config = yaml.load(file);
            System.out.println(config);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
