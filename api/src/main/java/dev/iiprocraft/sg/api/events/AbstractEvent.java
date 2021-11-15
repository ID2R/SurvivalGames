package dev.iiprocraft.sg.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author DirectPlan
 */
public abstract class AbstractEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
