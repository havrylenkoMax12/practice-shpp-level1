package com.havrylenko;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyLoader {

    private static final String DEFAULT_MINIMUM = "1";
    private static final String DEFAULT_MAXIMUM = "10";
    private static final String DEFAULT_INCREMENT = "1";
    private static final String DEFAULT_TYPE = "int";


    private static PropertyLoader instance;
    private Map<String,String> allProperties;

    private PropertyLoader(String filename) {
        loadFromProperties(filename);
    }

    private void loadFromProperties(String filename) {
        Properties properties = new Properties();
        allProperties = new HashMap<>();
        allProperties.put("type", System.getProperty("type", DEFAULT_TYPE));

        try (InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                System.err.println(filename + " not found");
            }
            properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
            allProperties.put("minimum", properties.getProperty("minimum", DEFAULT_MINIMUM));
            allProperties.put("maximum", properties.getProperty("maximum", DEFAULT_MAXIMUM));
            allProperties.put("increment", properties.getProperty("increment", DEFAULT_INCREMENT));
        } catch (IOException | NullPointerException e) {
            allProperties.put("minimum", DEFAULT_MINIMUM);
            allProperties.put("maximum", DEFAULT_MAXIMUM);
            allProperties.put("increment", DEFAULT_INCREMENT);
        }
    }

    public static PropertyLoader getInstance(String filename) {
        if(instance == null){
            instance = new PropertyLoader(filename);
        }
        return instance;
    }

    public String getProperty(String key){
        return allProperties.get(key);
    }
}
