 /*
 * MIT License
 *
 * Copyright (c) 2021 iiProCraft & contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.iiprocraft.sg.base.plugin;

import dev.iiprocraft.sg.api.SurvivalGamesProvider;
import dev.iiprocraft.sg.base.SurvivalGamesApiImpl;
import dev.iiprocraft.sg.base.config.ConfigHandler;
import dev.iiprocraft.sg.base.config.ConfigurationAdapter;
import dev.iiprocraft.sg.base.management.ArenaManager;
import dev.iiprocraft.sg.base.management.PlayerManager;
import dev.iiprocraft.sg.base.storage.Storage;
import dev.iiprocraft.sg.common.plugin.loader.PluginLoader;
import dev.iiprocraft.sg.common.plugin.manager.PluginManager;
import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

 /**
 * @author DirectPlan
 */
@Data
public class SGPluginBootstrap implements PluginLoader {

    private final JavaPlugin loader;

    private final ArenaManager arenaManager = new ArenaManager();
    private final PlayerManager playerManager = new PlayerManager(this);
    private final ConfigHandler configHandler = new ConfigHandler();
    private final Storage storage;
    private final PluginManager pluginManager;
    private final String COLOR_CHAR;

     /**
      * This constructor initialize the variables of
      * this class.
      *
      * @param loader The main class of the plugin
      */
    public SGPluginBootstrap(JavaPlugin loader) {
        this.loader         = loader;
        this.storage        = new Storage(loader);
        this.pluginManager  = new SGPluginManager();
        this.COLOR_CHAR     = "§";
    }

    @Override
    public void enable() {

        String[] startupMessage = {
                "   _____                  _            _  _____                           ",
                "  / ____|                (_)          | |/ ____|                          ",
                " | (___  _   _ _ ____   _____   ____ _| | |  __  __ _ _ __ ___   ___  ___ ",
                "  \\___ \\| | | | '__\\ \\ / / \\ \\ / / _` | | | |_ |/ _` | '_ ` _ \\ / _ \\/ __|",
                "  ____) | |_| | |   \\ V /| |\\ V / (_| | | |__| | (_| | | | | | |  __/\\__ \\",
                " |_____/ \\__,_|_|    \\_/ |_| \\_/ \\__,_|_|\\_____|\\__,_|_| |_| |_|\\___||___/",
                "                                                                          "
        };
        Arrays.asList(startupMessage).forEach(string -> {
            loader.getServer().getConsoleSender().sendMessage(ChatColor.GOLD+string);
        });
        loader.getServer().getConsoleSender().sendMessage(
                (COLOR_CHAR+'e')+"This server is running " + (COLOR_CHAR+'9') + "SurvivalGames" + (COLOR_CHAR+'e')
                + " version " + (COLOR_CHAR+'6')+loader.getDescription().getVersion()
        );

        /*
         * It will generate the default configuration
         */
        loader.saveDefaultConfig();

        configHandler.addConfiguration("config", new ConfigurationAdapter(this, "config.yml"));
        configHandler.addConfiguration("messages", new ConfigurationAdapter(this, "messages.yml"));
        configHandler.addConfiguration("chests", new ConfigurationAdapter(this, "chests.yml"));

        storage.connect();

        SurvivalGamesProvider.registerApi(new SurvivalGamesApiImpl(this));
    }
    @Override
    public void disable() {
        configHandler.saveConfigurations();
        storage.close();
    }

    public Storage getStorage() {
        return storage;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public JavaPlugin getLoader() {
        return loader;
    }

    public PluginManager getPluginManager() { return pluginManager; }
}

