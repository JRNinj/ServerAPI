package de.jrninj.serverapi.mysql;

import de.jrninj.serverapi.ServerAPI;
import de.jrninj.serverapi.utils.YMLFile;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.IOException;
import java.sql.*;

public class MySQL {

    private static Connection connection;

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

    public static String getMySQLPort() {
        try {

            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(YMLFile.getMessagesFile());

            if(config.get("Information.MySQL Port") == null) {
                return "§4Kein Config Eintrag gefunden!";
            }

            return config.getString("Information.MySQL Port");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "§4Kein Config Eintrag gefunden!";
    }

    public static void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + getMySQLHost() + ":" + getMySQLPort() + "/" + getMySQLDatabase(), getMySQLUser(), getMySQLPassword());
                ServerAPI.getPlugin().getProxy().getConsole().sendMessage(ServerAPI.getPrefix() + "§2Die MySQL Verbindung wurde erfolgreich hergestellt!");
            } catch (SQLException e) {
                ServerAPI.getPlugin().getProxy().getConsole().sendMessage(ServerAPI.getPrefix() + "§4Bei der Verbindung zur MySQL Datenbank ist ein Fehler aufgetreten §7--> bitte üperprüfe ob deine Datenbank gestartet und alle Werte richtig in die Config eingegeben wurden!");
            }
        }
    }

    public static void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
                ServerAPI.getPlugin().getProxy().getConsole().sendMessage(ServerAPI.getPrefix() + "§2Die MySQL Verbindung wurde erfolgreich unterbrochen!");
            } catch (SQLException e) {
                ServerAPI.getPlugin().getProxy().getConsole().sendMessage(ServerAPI.getPrefix() + "§4Die MySQL Datenbank konnte nicht entkoppelt werden §7--> bitte üperprüfe ob deine Datenbank gestartet und alle Werte richtig in die Config eingegeben wurden!");
            }
        }
    }

    public static boolean isConnected() {
        return  (connection != null);
    }

    public static void update(String query) {
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getResult(String query) {
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
