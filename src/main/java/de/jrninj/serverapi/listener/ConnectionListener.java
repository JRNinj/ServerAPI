package de.jrninj.serverapi.listener;

import de.jrninj.serverapi.ServerAPI;
import de.jrninj.serverapi.coins.CustomPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ConnectionListener implements Listener {

    private ServerAPI plugin;
    public ConnectionListener(ServerAPI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PostLoginEvent event){
        ProxiedPlayer player = event.getPlayer();
        CustomPlayer playerData = new CustomPlayer(plugin, player.getUniqueId());

        plugin.getPlayerManager().addCustomPlayer(player.getUniqueId(),playerData);
        player.sendMessage("§c---------------------------");
        player.sendMessage("§7Das erste Mal gejoint: §6" + playerData.getFirstTimeJoined());
        player.sendMessage("§c---------------------------");
        player.sendMessage("§7Coins: §6" + playerData.getCoins() + "§7   Euro: §6" + playerData.getEuros());
        player.sendMessage("§c---------------------------");

    }
    @EventHandler
    public void onLeave(PlayerDisconnectEvent event){
        plugin.getPlayerManager().removeCustomPlayer(event.getPlayer().getUniqueId());

    }

}
