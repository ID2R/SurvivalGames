package dev.iiprocraft.sg.base.config;

import dev.iiprocraft.sg.base.plugin.SGPluginBootstrap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author DirectPlan
 */
public class ConfigurationAdapter {

    private FileConfiguration configuration;
    private final SGPluginBootstrap plugin;
    private final File file;

    public ConfigurationAdapter(SGPluginBootstrap plugin, String file) {
        this.file = new File(plugin.getLoader().getDataFolder(), file);
        this.plugin = plugin;
        loadKeys();
    }

    public void loadConfiguration() {
        if(!file.exists()){
            plugin.getLoader().saveResource(file.getName(), false);
        }
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    private void loadKeys() {
        loadConfiguration();
        for(ConfigKeys configKey : ConfigKeys.values()) {
            if(!configKey.getConfigFile().equals(file.getName())) continue;
            String key = configKey.getKey();
            if(!configuration.contains(key)) {
                configuration.set(key, configKey.getDefaultValue());
                continue;
            }
            Object value = configuration.get(key);
            configKey.setDefaultValue(value);
        }
    }

    public void saveConfiguration() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        loadKeys();
    }
}
