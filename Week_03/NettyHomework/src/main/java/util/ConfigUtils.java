package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigUtils {

    private static  Properties properties ;
    static{
        try {
            properties = new Properties();
            InputStream in = ConfigUtils.class.getClass().getResourceAsStream("/config.properties");
            properties.load(in);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public static String getValueByKey(String key){
        return properties.getProperty(key);
    }
}
