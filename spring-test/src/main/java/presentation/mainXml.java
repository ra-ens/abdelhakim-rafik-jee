package presentation;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class mainXml {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/ApplicationContext.xml");
        IMetier metier = (IMetier) context.getBean("metier");
        System.out.println("WITH XML RESULT :> " + metier.calc());
    }
}
