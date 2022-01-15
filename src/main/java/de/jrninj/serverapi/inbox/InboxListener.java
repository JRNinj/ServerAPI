package de.jrninj.serverapi.inbox;

import de.jrninj.serverapi.ServerAPI;
import de.jrninj.serverapi.utils.YMLFile;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class InboxListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        try {
            if (ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.primalConfig).getBoolean("Settings.Lobby Server")) {
                ProxiedPlayer player = e.getPlayer();

                if (Inbox.get(player.getUniqueId()) != null) {

                    if (Inbox.get(player.getUniqueId()).toArray().length == 1) {
                        player.sendMessage(ServerAPI.getPrefix() + "§7Du hast §61 §7neue Benarichtigung!");
                    } else
                        player.sendMessage(ServerAPI.getPrefix() + "§7Du hast §6" + Inbox.get(player.getUniqueId()).toArray().length + " §7neue Benarichtigungen!");

                    for (String s : Inbox.get(player.getUniqueId())) {
                        player.sendMessage("§0• §7" + s);
                    }
                } else
                    player.sendMessage(ServerAPI.getPrefix() + "§7Du hast §6keine §7neuen Benarichtigungen!");

                Inbox.clear(player.getUniqueId());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
