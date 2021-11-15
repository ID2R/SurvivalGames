package dev.iiprocraft.sg.api.player;

import java.util.UUID;

/**
 * @author DirectPlan
 */
public interface PlayerManager {

    SGPlayer getSGPlayer(UUID id);
}
