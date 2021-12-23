package de.jrninj.serverapi.listener;

import de.jrninj.serverapi.utils.YMLFile;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;

public class JoinListener implements Listener {

    @EventHandler
    public void postLogin(PostLoginEvent e) {
        ProxiedPlayer player = e.getPlayer();

        try {
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.getFile());

            if(config.get("Players." + player.getUniqueId().toString()) == null) {

                config.set("Players." + player.getUniqueId().toString() + ".name", player.getName());

                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, YMLFile.getFile());

            }

            if(!config.getString("Players." + player.getUniqueId().toString() + ".name").equals(player.getName())) {

                config.set("Players." + player.getUniqueId().toString() + ".name", player.getName());

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}