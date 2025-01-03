package de.tehrion.alwayplugin_server.listeners;

import de.tehrion.alwayplugin_server.AlwayPlugin_Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldEventListener implements Listener {
    private final AlwayPlugin_Server plugin;

    public WorldEventListener(AlwayPlugin_Server plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (plugin.getConfig().getBoolean("Activation.NoMobs", false)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (plugin.getConfig().getBoolean("Activation.NoWeather", false) && event.toWeatherState()) {
            event.setCancelled(true);
        }
    }
}