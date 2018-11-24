package com.project.cucumber.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YAMLLoader {

    private static final String PROPERTY_ROOT = "src/main/resources/";
    private static final Logger LOGGER = LogManager.getLogger(YAMLLoader.class);
    private static Map<String, Object> OBJECT_REPO = new HashedMap();
    private static Map<String, List<TestConfig>> CONFIG_REPO = new HashedMap();

    Object getObject(String fileName, Class objectClass) {
        Object object = OBJECT_REPO.get(fileName);
        if (object != null) {
            return object;
        }
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        final File file = FileUtils.getFile(PROPERTY_ROOT + fileName);
        LOGGER.info("Loading the YAML file: " + file.getAbsolutePath());
        try {
            object = mapper.readValue(file, objectClass);
            OBJECT_REPO.put(fileName, object);
            return object;
        } catch (IOException e) {
            LOGGER.info("May be not able to map the class " + objectClass + " with the YAML file " + file.getAbsolutePath());
            LOGGER.error(e.fillInStackTrace());
            return null;
        }
    }

    List<TestConfig> getAllConfig(String fileName) {
        List<TestConfig> configs = CONFIG_REPO.get(fileName);
        if (configs != null) {
            return configs;
        }
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        final File file = FileUtils.getFile(PROPERTY_ROOT + fileName);
        LOGGER.info("Loading the config file: " + file.getAbsolutePath());
        try {
            //configs = mapper.readValue(file, List.class);
            configs = mapper.readValue(file, new TypeReference<List<TestConfig>>(){});
            System.out.println(configs.get(0).getLoginUrl());
            CONFIG_REPO.put(fileName, configs);
            return configs;
        } catch (FileNotFoundException ex) {
            LOGGER.error("Unable to find the file: " + file.getAbsolutePath());
            LOGGER.error(ex.fillInStackTrace());
        } catch (Exception e) {
            LOGGER.info("Not able to map the list of TestConfig classes with the YAML file " + file.getAbsolutePath());
            LOGGER.error(e.fillInStackTrace());
            e.printStackTrace();
        }
        return null;
    }
}
