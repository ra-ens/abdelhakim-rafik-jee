
import metier.IMetier;

import java.io.FileNotFoundException;
import java.io.IOException;

public class XmlTest {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        RaSpring context = null;

        try {
            context = new RaSpring(XmlTest.class.getResource("ApplicationContext.yml").getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        IMetier metier = (IMetier) context.getBean("metier");
        System.out.println(metier.calc());
    }
}
