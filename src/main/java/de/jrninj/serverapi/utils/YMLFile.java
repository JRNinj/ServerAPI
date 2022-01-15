package de.jrninj.serverapi.utils;

import de.jrninj.serverapi.ServerAPI;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class YMLFile {

    public static File primalConfig = new File(ServerAPI.getPlugin().getDataFolder().getPath(), "config.yml");

    public static void fileCreations() {

        try {
            if (!ServerAPI.getPlugin().getDataFolder().exists()) {
                ServerAPI.getPlugin().getDataFolder().mkdir();
            }

            //Primal Config
            if (!primalConfig.exists()) {
                primalConfig.createNewFile();

                Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(primalConfig);

                config.set("Paths.Speicherpfad (vom ServerAPI Pluginordner ausgesehen)", "/../../configs");
                config.set("Settings.MySQL", false);
                config.set("Settings.Lobby Server", false);

                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, primalConfig);
            }

            //Create Directory
            Path saveDirectory = Paths.get(ServerAPI.getPlugin().getDataFolder().getPath() + ConfigurationProvider.getProvider(YamlConfiguration.class).load(primalConfig).getString("Paths.Speicherpfad (vom ServerAPI Pluginordner ausgesehen)"));
            if (!Files.exists(saveDirectory)) {
                Files.createDirectories(saveDirectory);
            }

            //File
            if (!getFile().exists()) {
                getFile().createNewFile();
            }

            //Messages
            if (!getMessagesFile().exists()) {
                getMessagesFile().createNewFile();

                Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(getMessagesFile());

                config.set("Messages.Server Prefix", "§5Shulker§6Games §0>> §7");
                config.set("Messages.Keine Rechte (Fehler)", "&4Dafür hast du keine Rechte!");
                config.set("Information.MySQL Host", "xxx.mysql.de");
                config.set("Information.MySQL Port", "3306");
                config.set("Information.MySQL Benutzername", "Max Mustermann");
                config.set("Information.MySQL Passwort", "12345678");
                config.set("Information.MySQL Datenbank", "DBX35Q");
                config.set("Information.MySQL Tabelle", "table");

                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, getMessagesFile());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static File getFile() {
        try {
            return new File(ServerAPI.getPlugin().getDataFolder().getPath() + ConfigurationProvider.getProvider(YamlConfiguration.class).load(primalConfig).getString("Paths.Speicherpfad (vom ServerAPI Pluginordner ausgesehen)"), "players.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File getMessagesFile() {
        try {
            return new File(ServerAPI.getPlugin().getDataFolder().getPath() + ConfigurationProvider.getProvider(YamlConfiguration.class).load(primalConfig).getString("Paths.Speicherpfad (vom ServerAPI Pluginordner ausgesehen)"), "messages.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
