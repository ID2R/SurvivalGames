package dev.iiprocraft.sg.api.storage.impl;

import dev.iiprocraft.sg.api.player.SGPlayer;
import dev.iiprocraft.sg.api.storage.StorageRepository;

import java.util.UUID;

/**
 * @author DirectPlan
 */
public class YAMLStorage implements StorageRepository {


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
