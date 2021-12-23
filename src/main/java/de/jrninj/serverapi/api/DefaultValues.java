package de.jrninj.serverapi.api;

import de.jrninj.serverapi.utils.YMLFile;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.IOException;

public class DefaultValues {

    public static String getNoPerms() {

        try {

            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.getMessagesFile());

            if(config.get("Messages.Keine Rechte (Fehler)") == null) {
                return "&4Dafür hast du keine Rechte!";
            }

            String s = config.getString("Messages.Keine Rechte (Fehler)");
            s = s.replace("&", "§");
            return s;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "&4Dafür hast du keine Rechte!";
    }

    public static String getMySQLHost() {
        try {

            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.getMessagesFile());

            if(config.get("Information.MySQL Host") == null) {
                return "§4Kein Config Eintrag gefunden!";
            }

            return config.getString("Information.MySQL Host");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "§4Kein Config Eintrag gefunden!";
    }

    public static String getMySQLUser() {
        try {

            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.getMessagesFile());

            if(config.get("Information.MySQL Benutzername") == null) {
                return "§4Kein Config Eintrag gefunden!";
            }

            return config.getString("Information.MySQL Benutzername");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "§4Kein Config Eintrag gefunden!";
    }

    public static String getMySQLPassword() {
        try {

            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.getMessagesFile());

            if(config.get("Information.MySQL Passwort") == null) {
                return "§4Kein Config Eintrag gefunden!";
            }

            return config.getString("Information.MySQL Passwort");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "§4Kein Config Eintrag gefunden!";
    }

    public static String getMySQLDatabase() {
        try {

            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.getMessagesFile());

            if(config.get("Information.MySQL Datenbank") == null) {
                return "§4Kein Config Eintrag gefunden!";
            }

            return config.getString("Information.MySQL Datenbank");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "§4Kein Config Eintrag gefunden!";
    }

    public static String getMySQLTable() {
        try {

            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.getMessagesFile());

            if(config.get("Information.MySQL Tabelle") == null) {
                return "§4Kein Config Eintrag gefunden!";
            }

            return config.getString("Information.MySQL Tabelle");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "§4Kein Config Eintrag gefunden!";
    }

}
