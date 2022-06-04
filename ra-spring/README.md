# Mini Framework d'injection de dépendance similaire à Spring IOC

L’objectif de ce projet est de développer un Framework d'injection de dépendance similaire à Spring IOC  
Le Framework doit permettre à un programmeur de faire l'injection des dépendances entre les différents composant de son application respectant les possibilités suivantes :
- A travers un fichier YAML de configuration en utilisant Snakeyaml
- En utilisant les annotations
- Possibilité d'injection via :
  - Le constructeur
  - Le Setter
  - Attribut (accès direct à l'attribut : Field)
## Conditions préalables

- [JAVA](https://www.oracle.com/java/technologies/downloads/)

## Structure de projet
- annotations
  - Autowired
  - Component
- appContextConfigFile
  - Bean
  - ConstrucotrArg
  - Property
  - IContextConfig
  - ContextConfig
- exceptions
  - BeanNotFoundException
  - ConfigFileInvalideException
  - ConfigFileNotSupportedException
- processors
  - ComponentProcessor
- serializer
  - IDeserializer
  - Deserializer
  - ISerializer
  - Serializer
- RaSpring

# Fonctionnement
Pour utilise le Framework, le programmeur choisi entre deux méthodes soit le fichier de configuration ou les annotations.
Pour le fichier de configuration doit être en YAML comme suite :  
```yaml
beans:
  - id: dao
    className: ext.DaoImpVW
  - id: metier
    className: metier.MetierImp
    constructorArgs:
      - ref: dao
```
Pour le Main principale est comme suite :
```java
public class YamlTest {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        RaSpring context = null;

        try {
            context = new RaSpring(YamlTest.class.getResource("ApplicationContext.yml").getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        IMetier metier = (IMetier) context.getBean("metier");
        System.out.println(metier.calc());
    }
}
```
Pour les annotations les composants doivent utilise l’annotation @Component
```java
@Component("dao")
public class DaoImp  implements IDao{
    @Override
    public double getData() {
        System.out.println("Version Database");
        return Math.PI * Math.random() * 501;
    }
}
```
Puis le main doit être comme suite
```java
public class AnnotationTest {

    public static void main(String[] args) {
        RaSpring context = null;
        context = new RaSpring("dao", "ext", "metier");

    }
}
```