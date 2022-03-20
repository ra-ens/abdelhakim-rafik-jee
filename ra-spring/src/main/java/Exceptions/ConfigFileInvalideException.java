package Exceptions;

public class ConfigFileInvalideException extends Exception {

    public ConfigFileInvalideException() {
        super("Config file has non valide formate");
    }
}
