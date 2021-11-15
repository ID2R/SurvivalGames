package dev.iiprocraft.sg.base;

import dev.iiprocraft.sg.api.SurvivalGames;
import dev.iiprocraft.sg.api.arena.ArenaManager;
import dev.iiprocraft.sg.api.player.PlayerManager;
import dev.iiprocraft.sg.base.plugin.SGPluginBootstrap;
import lombok.Data;

/**
 * @author DirectPlan
 */
@Data
public class SurvivalGamesApiImpl implements SurvivalGames {
    
    private final SGPluginBootstrap plugin;

    @Override
    public ArenaManager getArenaManager() {
        return plugin.getArenaManager();
    }

    @Override
    public PlayerManager getPlayerManager() {
        return plugin.getPlayerManager();
    }
}
