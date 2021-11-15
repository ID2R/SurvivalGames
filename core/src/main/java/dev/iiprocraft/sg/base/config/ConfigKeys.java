package dev.iiprocraft.sg.base.config;

import dev.iiprocraft.sg.base.config.replacement.Replacement;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;

/**
 * @author DirectPlan
 */

@Getter
public enum ConfigKeys {

    /* Storage Credentials */

    STORAGE_METHOD("storage-method", "config.yml", "MYSQL"),

    STORAGE_HOST("storage.host", "config.yml", "localhost"),
    STORAGE_PORT("storage.port", "config.yml", 0, false),
    STORAGE_USERNAME("storage.username", "config.yml", "username"),
    STORAGE_PASSWORD("storage.password", "config.yml", "password"),
    STORAGE_DATABASE("storage.database", "config.yml", "SurvivalGames"),
    STORAGE_MAXIMUM_POOL_SIZE("storage.maximumPoolSize", "config.yml", 10);


    private final String key, configFile;
    @Setter private Object defaultValue;
    private final boolean valueOverride;

    ConfigKeys(String key, String configFile, Object defaultValue, boolean valueOverride) {
        this.key = key;
        this.configFile = configFile;
        this.defaultValue = defaultValue;
        this.valueOverride = valueOverride;
    }


    ConfigKeys(String key, String configFile, Object defaultValue) {
        this(key, configFile, defaultValue, true);
    }

    ConfigKeys(String key, Object defaultValue) {
        this(key, "messages.yml", defaultValue);
    }

    // TODO: Add similar data types methods such as boolean, double, float, etc.

    public String getString(Replacement... replacements) {
        if(!(defaultValue instanceof String)) {
            throw new IllegalStateException("The specified value's data type cannot be cast as String");
        }

        String value = defaultValue.toString();
        for(Replacement replacement : replacements) {
            value = replacement.replace(value);
        }
        return value;
    }

    public int getInteger() {
        if(!(defaultValue instanceof Integer)) {
            throw new IllegalStateException("The specified value's data type cannot be cast as Integer");
        }
        return (int) defaultValue;
    }

    public void sendMessage(CommandSender sender, Replacement... replacements) {
        String message = getString(replacements);
        sender.sendMessage(message);
    }
}
