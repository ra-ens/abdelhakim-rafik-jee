# Inversion de contrôle et injection de dépendance

Dans ce projet nous avons utilisé le Framework Spring pour test l’inversion de contrôle et injection de dépendance.

## Conditions préalables

- [JAVA](https://www.oracle.com/java/technologies/downloads/)
- [Spring](https://spring.io/)

## Frameworks et outils

- **Spring**

## Structure de projet
- dao
  - **IDao** : Interface de DAO
  - **DaoImp** : Implémentation de DAO
- ext
  - **DaoImpVW** : Implémentation de DAO avec version web
- metier
  - **IMetier** : Interface Metier
  - **MetierImp** : Implémentation de l'interface Metier
- presentation
  - **mainAnnotation** : Main avec l'utilisation des annotation
  - **mainXml** : Main avec l'utilisation de XML

## Implémentations
La plus important dans l’implémentation est l’utilisation des liaisons faible, c’est l’utilisation des interfaces au lieux des classes.
```java
@Component
public class MetierImp implements IMetier{

    private IDao dao;

    public MetierImp(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calc() {
        return dao.getData() * 5;
    }
}
```
Suite est l'exploitions avec les annotations

```java
public class mainAnnotation {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ext", "dao", "metier");
        IMetier metier = context.getBean(IMetier.class);

        System.out.println("WITH ANNOTATION RESULT :> " + metier.calc());
    }
}
```
Exploitation avec un fichier XML

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dao"  class="dao.DaoImp"/>
    <bean id="metier" class="metier.MetierImp">
        <constructor-arg ref="dao"/>
    </bean>
</beans>
```
```java
public class mainXml {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/ApplicationContext.xml");
        IMetier metier = (IMetier) context.getBean("metier");
        System.out.println("WITH XML RESULT :> " + metier.calc());
    }
}
```