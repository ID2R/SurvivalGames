package dev.iiprocraft.sg.base.storage.impl;

import dev.iiprocraft.sg.base.game.arena.SGArena;
import dev.iiprocraft.sg.base.player.SGPlayer;
import dev.iiprocraft.sg.base.storage.StorageRepository;
import dev.iiprocraft.sg.base.storage.misc.ConnectionCredentials;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

/**
 * @author DirectPlan
 */
@Data
public class MySQLStorage implements StorageRepository {

    private final ConnectionCredentials credentials;

    @Override
    public void connect() {

    }

    @Override
    public void close() {

    }

    @Override
    public SGPlayer loadPlayer(UUID uuid) {
        return null;
    }

    @Override
    public void savePlayer(SGPlayer player) {

    }

    @Override
    public Map<String, SGArena> loadArenas() {
        return null;
    }

    @Override
    public void saveArenas() {

    }
}