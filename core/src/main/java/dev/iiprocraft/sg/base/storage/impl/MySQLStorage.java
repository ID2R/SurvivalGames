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
public class MySQLStorage extends StorageRepository {


    public MySQLStorage(ConnectionCredentials credentials) {
        super(credentials);
    }

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
}
