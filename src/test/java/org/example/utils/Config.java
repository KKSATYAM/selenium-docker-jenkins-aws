package org.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static final Logger log = LoggerFactory.getLogger(Config.class);
    public static final String DEFAULT_PROPERTY = "config/default.properties";

    public static Properties properties;

    public static void Initialize() {
        //load property
        properties = loadProperties();

        //check for any override through command line
        for (String key : properties.stringPropertyNames()) {
            if (System.getProperties().containsKey(key)) {
                properties.setProperty(key, System.getProperty(key));
            }
        }

        log.info("----------Test Properties----------");

        //print updated property
        for (String key : properties.stringPropertyNames()) {
            log.info("{} = {}", key, properties.getProperty(key));

        }
        log.info("------------------------------------------");
    }

    public static String get(String key){
        return properties.getProperty(key);
    }


    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTY)) {
            properties.load(stream);
        } catch (Exception e) {
            log.info("unable to read property file {}", DEFAULT_PROPERTY, e);
        }
        return properties;
    }

}
