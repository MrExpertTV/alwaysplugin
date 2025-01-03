package de.tehrion.alwayplugin_server.listeners;

import de.tehrion.alwayplugin_server.AlwayPlugin_Server;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEventListener implements Listener {
    private final AlwayPlugin_Server plugin;

    public PlayerEventListener(AlwayPlugin_Server plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location spawn = plugin.getSpawnManager().getSpawnLocation();

        if (!player.hasPlayedBefore() && plugin.getConfig().getBoolean("Activation.EnableFirstJoinMessages", true)) {
            String firstJoinMessage = plugin.getConfig().getString("AlwaysJoinMessages.FirstJoin", "&aWillkommen {UserName} auf {ServerName}!");
            firstJoinMessage = ChatColor.translateAlternateColorCodes('&',
                    firstJoinMessage.replace("{UserName}", player.getName())
                            .replace("{ServerName}", plugin.getServer().getName()));
            event.setJoinMessage(firstJoinMessage);
            return;
        }

        if (plugin.getConfig().getBoolean("Activation.EnableJoinMessages", true)) {
            String joinMessage = plugin.getConfig().getString("AlwaysJoinMessages.Join", "&a[&a+&f] {UserName}");
            joinMessage = ChatColor.translateAlternateColorCodes('&',
                    joinMessage.replace("{UserName}", player.getName()));
            event.setJoinMessage(joinMessage);
        } else {
            event.setJoinMessage(null);
        }

        if (spawn != null) {
            player.teleport(spawn);

            boolean showMessage = plugin.getConfig().getBoolean("Activation.ShowTeleportMessage", true);
            if (showMessage) {
                String spawnMessage = plugin.getConfig().getString("Messages.AlwaysSpawn.Teleport-Message",
                        "&aDu wurdest zum Spawnpoint teleportiert!");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', spawnMessage));
            }
        } else {
            String notSetMessage = plugin.getConfig().getString("Messages.AlwaysSpawn.NotSetMessage",
                    "&cSpawnPoint wurde noch nicht gesetz! Bitte gib /setalwaysspawn ein!");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', notSetMessage));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (plugin.getConfig().getBoolean("Activation.EnableLeaveMessages", true)) {
            String leaveMessage = plugin.getConfig().getString("AlwaysJoinMessages.Leave", "&c[&c-&f] {UserName} hat den Server verlassen!");
            leaveMessage = ChatColor.translateAlternateColorCodes('&',
                    leaveMessage.replace("{UserName}", event.getPlayer().getName()));
            event.setQuitMessage(leaveMessage);
        } else {
            event.setQuitMessage(null);
        }
    }
}
