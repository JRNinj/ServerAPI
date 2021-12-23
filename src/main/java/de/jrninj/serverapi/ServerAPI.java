package de.jrninj.serverapi;

import de.jrninj.serverapi.api.DefaultValues;
import de.jrninj.serverapi.listener.JoinListener;
import de.jrninj.serverapi.utils.YMLFile;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.IOException;

public final class ServerAPI extends Plugin {

    private static ServerAPI plugin;

    @Override
    public void onEnable() {
        plugin = this;

        YMLFile.fileCreations();

        register();

    }

    @Override
    public void onDisable() {



    }

    public void register() {
        //Listener
        getProxy().getPluginManager().registerListener(this, new JoinListener());

    }

    public static ServerAPI getPlugin() {
        return plugin;
    }

    public static String getPrefix() {
        try {

            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.messagesFile);

            if(config.get("Messages.Server Prefix") == null) {
                return "§6Time§cTravel §0>> §7";
            }

            String s = config.getString("Messages.Server Prefix");
            s = s.replace("&", "§");
            return s;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "§6Time§cTravel §0>> §7";
    }
}
