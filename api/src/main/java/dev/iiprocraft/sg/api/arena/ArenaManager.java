package dev.iiprocraft.sg.api.arena;

import java.util.Collection;

/**
 * @author DirectPlan
 */
public interface ArenaManager {

    Collection<? extends Arena> getArenas();

    Arena getArena(String arenaName);
}
