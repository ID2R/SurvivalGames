package dev.iiprocraft.sg.base.management;

import dev.iiprocraft.sg.base.player.SGPlayer;
import dev.iiprocraft.sg.base.plugin.SGPluginBootstrap;
import lombok.Data;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class PlayerManager implements dev.iiprocraft.sg.api.player.PlayerManager {

    private final ConcurrentHashMap<UUID, SGPlayer> players = new ConcurrentHashMap<>();

    private final SGPluginBootstrap plugin;

    @Override
    public SGPlayer getSGPlayer(UUID id) {
        return players.get(id);
    }

    public SGPlayer loadPlayer(UUID uuid) {
        SGPlayer sgPlayer = getSGPlayer(uuid);
        if(sgPlayer == null) {
            sgPlayer = plugin.getStorage().loadPlayer(uuid);
        }
        return sgPlayer;
    }

    public void savePlayer(SGPlayer sgPlayer) {
        plugin.getStorage().savePlayer(sgPlayer);
    }

    public void saveAndRemove(UUID uuid) {
        SGPlayer sgPlayer = players.remove(uuid);
        savePlayer(sgPlayer);
    }
}
