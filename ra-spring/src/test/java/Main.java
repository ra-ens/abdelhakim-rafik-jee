import test.metier.IMetier;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        RaSpring context = new RaSpring("/D:/Study/S4/JEE/ra-spring/target/classes/ApplicationContext.yml");

        IMetier metier = (IMetier) context.getBean("metier");
        System.out.println(metier.calc());
    }
}
