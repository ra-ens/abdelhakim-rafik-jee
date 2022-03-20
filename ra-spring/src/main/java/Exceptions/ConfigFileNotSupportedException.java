package Exceptions;

public class ConfigFileNotSupportedException extends Exception{

    public ConfigFileNotSupportedException(){
        super("Config file extension not supported, try use (xml or yaml) file");
    }
}
