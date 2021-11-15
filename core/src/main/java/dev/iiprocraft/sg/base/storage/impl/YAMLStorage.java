package dev.iiprocraft.sg.base.storage.impl;

import dev.iiprocraft.sg.base.player.SGPlayer;
import dev.iiprocraft.sg.base.storage.StorageRepository;

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
