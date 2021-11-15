package dev.iiprocraft.sg.api.arena;

import java.util.UUID;

/**
 * @author DirectPlan
 */
public interface Arena {

    UUID getUniqueId();

    String getDisplayName();

    void setDisplayName(String displayName);

    void setUniqueId(UUID uniqueId);

    GameState getState();

    String getName();

    void setState(GameState state);
}
