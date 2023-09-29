package net.bcloud.bengine.api.listener;

import net.bcloud.bengine.bPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractManager<P extends bPlugin<P>> implements Loadable {

    protected final P                  plugin;
    protected final Set<EventListener> listeners;

    public AbstractManager(@NotNull P plugin) {
        this.plugin = plugin;
        this.listeners = new HashSet<>();
    }

    @Override
    public void setup() {
        this.onLoad();
    }

    @Override
    public void shutdown() {
        this.listeners.forEach(EventListener::unregisterListeners);
        this.listeners.clear();
        this.onShutdown();
    }

    protected abstract void onLoad();

    protected abstract void onShutdown();

    /**
     * Adds listener to this manager. Listener will be registered when it's added.
     *
     * @param listener Listener to add.
     */
    protected void addListener(@NotNull EventListener listener) {
        if (this.listeners.add(listener)) {
            listener.registerListeners();
        }
    }

    @NotNull
    public P plugin() {
        return this.plugin;
    }
}