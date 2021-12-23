package de.jrninj.serverapi.utils;

import de.jrninj.serverapi.ServerAPI;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YMLFile {

    public static File file = new File(ServerAPI.getPlugin().getDataFolder().getPath() + "/../../../configs", "players.yml");
    public static File messagesFile = new File(ServerAPI.getPlugin().getDataFolder().getPath() + "/../../../configs", "messages.yml");

    public static void fileCreations(){

        try {
            //File
            if (!file.exists()) {
                file.createNewFile();
            }

            //Messages
            if (!messagesFile.exists()) {
                messagesFile.createNewFile();

                Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(messagesFile);

                config.set("Messages.Server Prefix", "&6Time&cTravel &0>> &7");
                config.set("Messages.Keine Rechte (Fehler)", "&4Dafür hast du keine Rechte!");
                config.set("Information.MySQL Host", "xxx.mysql.de");
                config.set("Information.MySQL Benutzername", "Max Mustermann");
                config.set("Information.MySQL Passwort", "12345678");
                config.set("Information.MySQL Datenbank", "DBX35Q");
                config.set("Information.MySQL Tabelle", "table");

                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, messagesFile);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
