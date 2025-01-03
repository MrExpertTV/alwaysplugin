package de.tehrion.alwayplugin_server;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.*;


public class AlwaysSpawnManager {
    private final AlwayPlugin_Server plugin;
    private final File locationFile;
    private Location spawnLocation;

    public AlwaysSpawnManager(AlwayPlugin_Server plugin) {
        this.plugin = plugin;
        this.locationFile = new File(plugin.getDataFolder(), "location.db");
        loadSpawnLocation();
    }

    public void setSpawnLocation(Location location) {
        spawnLocation = location;
        saveSpawnLocation();
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    private void saveSpawnLocation() {
        if (spawnLocation == null) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(locationFile))) {
            writer.write(spawnLocation.getWorld().getName() + ",");
            writer.write(spawnLocation.getX() + ",");
            writer.write(spawnLocation.getY() + ",");
            writer.write(spawnLocation.getZ() + ",");
            writer.write(spawnLocation.getYaw() + ",");
            writer.write(String.valueOf(spawnLocation.getPitch()));
        } catch (IOException e) {
            plugin.getLogger().severe("Fehler beim Speichern der Location: " + e.getMessage());
        }
    }

    private void loadSpawnLocation() {
        if (!locationFile.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(locationFile))) {
            String[] parts = reader.readLine().split(",");
            spawnLocation = new Location(
                    Bukkit.getWorld(parts[0]),
                    Double.parseDouble(parts[1]),
                    Double.parseDouble(parts[2]),
                    Double.parseDouble(parts[3]),
                    Float.parseFloat(parts[4]),
                    Float.parseFloat(parts[5])
            );
        } catch (Exception e) {
            plugin.getLogger().warning("Konnte Location nicht laden: " + e.getMessage());
        }
    }
}