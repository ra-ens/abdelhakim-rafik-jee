package presentation;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class mainAnnotation {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ext", "dao", "metier");
        IMetier metier = context.getBean(IMetier.class);

        System.out.println("WITH ANNOTATION RESULT :> " + metier.calc());
    }
}
