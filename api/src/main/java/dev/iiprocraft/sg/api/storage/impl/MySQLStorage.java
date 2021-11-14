package dev.iiprocraft.sg.api.storage.impl;

import dev.iiprocraft.sg.api.player.SGPlayer;
import dev.iiprocraft.sg.api.storage.StorageRepository;
import dev.iiprocraft.sg.api.storage.misc.ConnectionCredentials;
import lombok.Data;

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
}
