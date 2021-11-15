package dev.iiprocraft.sg.base.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class ConfigHandler {

    private final Map<String, ConfigurationAdapter> configurations = new HashMap<>();

    @Deprecated
    public ConfigurationAdapter get(String fileName) {
        return configurations.get(fileName);
    }

    public void addConfiguration(String fileName, ConfigurationAdapter configurationAdapter) {
        configurations.put(fileName, configurationAdapter);
    }

    public void saveConfigurations() {
        configurations.values().forEach(ConfigurationAdapter::saveConfiguration);
    }

    public void reloadConfigurations() {
        configurations.values().forEach(ConfigurationAdapter::reloadConfig);
    }
}
