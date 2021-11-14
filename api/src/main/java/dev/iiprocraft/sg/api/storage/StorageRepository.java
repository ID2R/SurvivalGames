package dev.iiprocraft.sg.api.storage;

import dev.iiprocraft.sg.api.player.SGPlayer;

import java.util.UUID;

/**
 * @author DirectPlan
 */
public interface StorageRepository extends StorageConnection {

    SGPlayer loadPlayer(UUID uuid);

    void savePlayer(SGPlayer player);
}
