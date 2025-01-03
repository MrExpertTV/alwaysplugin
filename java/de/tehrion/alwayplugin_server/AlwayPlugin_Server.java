package de.tehrion.alwayplugin_server;

import de.tehrion.alwayplugin_server.listeners.PlayerEventListener;
import de.tehrion.alwayplugin_server.listeners.WorldEventListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class AlwayPlugin_Server extends JavaPlugin {

    private AlwaysSpawnManager spawnManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        spawnManager = new AlwaysSpawnManager(this);

        getServer().getPluginManager().registerEvents(new PlayerEventListener(this), this);
        getServer().getPluginManager().registerEvents(new WorldEventListener(this), this);

        if (getConfig().getBoolean("Activation.AlwaysDay", false)) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () ->
                    Bukkit.getWorlds().forEach(world -> world.setTime(1000)), 0L, 100L);
        }

        if (getConfig().getBoolean("Activation.NoMobs", false)) {
            getServer().getWorlds().forEach(world ->
                    world.setGameRuleValue("doMobSpawning", "false"));
        }

        getLogger().info("AlwaysPlugin aktiviert!");
    }

    @Override
    public void onDisable() {
        getLogger().info("AlwaysPlugin deaktiviert!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("alwaysplugin") || label.equalsIgnoreCase("ap")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("alwaysplugin.reload")) {
                    sender.sendMessage("§cDu hast keine Berechtigung, diesen Befehl auszuführen!");
                    return true;
                }

                reloadConfig();
                sender.sendMessage("§aDie Konfiguration wurde erfolgreich neu geladen!");
                getLogger().info("Die Konfiguration wurde durch " + sender.getName() + " neu geladen.");
                return true;
            }

            sender.sendMessage("§eVerwendung: /alwaysplugin reload");
            return true;
        }

        if (label.equalsIgnoreCase("setalwaysspawn") && sender instanceof Player) {
            Player player = (Player) sender;
            spawnManager.setSpawnLocation(player.getLocation());

            String message = getConfig().getString("Messages.AlwaysSpawn.SetAlwaysSpawn",
                    "§aAlwaysSpawn-Position gesetzt und gespeichert!");
            player.sendMessage(formatColorCodes(message));
            return true;
        }

        return false;
    }

    public AlwaysSpawnManager getSpawnManager() {
        return spawnManager;
    }

    private String formatColorCodes(String message) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', message);
    }
}
