package net.bcloud.bengine.api.listener;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public interface EventListener extends Listener {

    void registerListeners();

    default void unregisterListeners() {
        HandlerList.unregisterAll(this);
    }
}
