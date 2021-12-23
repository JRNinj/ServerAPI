package de.jrninj.serverapi.api;

import de.jrninj.serverapi.utils.YMLFile;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.IOException;

public class PlayerData {

    public static String getUUID(String username) {
        try {

            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.getFile());

            for(String s : config.getSection("Players").getKeys()) {

                if(config.getString("Players." + s + ".name").equals(username)) {
                    return s;
                }

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "§4The Username not exist in our system!";
    }

    public static String getUsername(String uuid) {
        try {

            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.getFile());

            if(!config.contains("Players." + uuid + ".name")) {
                return "§4The UUID not exist in our system!";
            }

            return config.getString("Players." + uuid + ".name");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "§4The UUID not exist in our system!";
    }

}
