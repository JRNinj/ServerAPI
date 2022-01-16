package de.jrninj.serverapi;

import de.jrninj.serverapi.api.PlayerData;
import de.jrninj.serverapi.listener.InboxListener;
import de.jrninj.serverapi.listener.ConnectionListener;
import de.jrninj.serverapi.coins.PlayerManager;
import de.jrninj.serverapi.listener.PlayerDataListener;
import de.jrninj.serverapi.mysql.MySQL;
import de.jrninj.serverapi.utils.YMLFile;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.IOException;

public final class ServerAPI extends Plugin {

    private static ServerAPI plugin;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        plugin = this;

        YMLFile.fileCreations();

        register();

        //MySQL laden
        try {
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.primalConfig);

            if(config.getBoolean("Settings.MySQL")) {
                MySQL.connect();
            } else
                getProxy().getConsole().sendMessage(ServerAPI.getPrefix() + "§4MySQL ist derzeit deaktiviert, gehe in die Config um es zu aktivieren!");

        } catch (IOException e) {
            e.printStackTrace();
        }

        getProxy().getConsole().sendMessage(getPrefix() + "§2Die ServerAPI wurde erfolgreich aktiviert!");

    }

    @Override
    public void onDisable() {

        //MySQL entladen
        try {
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.primalConfig);

            if(config.getBoolean("Settings.MySQL")) {
                MySQL.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        getProxy().getConsole().sendMessage(getPrefix() + "§cDie ServerAPI wurde deaktiviert!");

    }

    public void register() {
        //Other Stuff
        playerManager = new PlayerManager();

        //Listener
        getProxy().getPluginManager().registerListener(this, new PlayerDataListener());
        getProxy().getPluginManager().registerListener(this, new ConnectionListener(this));
        getProxy().getPluginManager().registerListener(this, new InboxListener());

    }

    public static ServerAPI getPlugin() {
        return plugin;
    }

    public static String getPrefix() {
        try {

            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.getMessagesFile());

            if(config.get("Messages.Server Prefix") == null) {
                return "§5Shulker§6Games §0>> §7";
            }

            String s = config.getString("Messages.Server Prefix");
            s = s.replace("&", "§");
            return s;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "§6Time§cTravel §0>> §7";
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
