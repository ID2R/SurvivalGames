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
    private final PlayerManager playerManager = new PlayerManager(this);;
    private final ConfigHandler configHandler = new ConfigHandler();

    private final Storage storage = new Storage();
    private final PluginManager pluginManager = new SGPluginManager();

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
        loader.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "This server is running " + ChatColor.BLUE + "SurvivalGames" + ChatColor.YELLOW
                + " version " + ChatColor.GOLD + loader.getDescription().getVersion()
        );

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
}

