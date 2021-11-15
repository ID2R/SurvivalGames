package dev.iiprocraft.sg.base.storage;

import dev.iiprocraft.sg.base.game.arena.SGArena;
import dev.iiprocraft.sg.base.player.SGPlayer;

import java.util.Map;
import java.util.UUID;

/**
 * @author DirectPlan
 */
public interface StorageRepository extends StorageConnection {

    SGPlayer loadPlayer(UUID uuid);

    void savePlayer(SGPlayer player);

    Map<String, SGArena> loadArenas();

    void saveArenas();
}
