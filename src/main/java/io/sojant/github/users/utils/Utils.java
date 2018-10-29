package io.sojant.github.users.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Sojant on 2018-10-29.
 */
public class Utils {

    public static String env(String key, String def){
        String value = System.getenv(key);
        return (value != null) ? value :def;
    }

    public static String env_strict(String key){
        String value = System.getenv(key);

        if (value == null)
        throw new IllegalStateException(String.format("Env var %s must exist.",key));

        return value;
    }

    public static String env(String key){
        return System.getenv(key);
    }

    public static String property(String key){
        Properties prop = new Properties();
        try {
            prop.load(Utils.class.getResourceAsStream("/application.properties"));
            return prop.getProperty(key);
        } catch (IOException e) {
            return null;
        }
    }

}
