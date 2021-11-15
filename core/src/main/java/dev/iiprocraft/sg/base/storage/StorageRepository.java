package dev.iiprocraft.sg.base.storage;

import dev.iiprocraft.sg.base.game.arena.SGArena;
import dev.iiprocraft.sg.base.player.SGPlayer;
import dev.iiprocraft.sg.base.storage.misc.ConnectionCredentials;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

/**
 * @author DirectPlan
 */
public abstract class StorageRepository implements StorageConnection {

    @Getter
    protected final ConnectionCredentials credentials;

    public StorageRepository(ConnectionCredentials credentials) {
        this.credentials = credentials;
    }


    public abstract SGPlayer loadPlayer(UUID uuid);

    public abstract void savePlayer(SGPlayer player);

    public abstract Map<String, SGArena> loadArenas();

    public abstract void saveArenas();
}
