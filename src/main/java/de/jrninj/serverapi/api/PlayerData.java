package de.jrninj.serverapi.api;

import de.jrninj.serverapi.mysql.MySQL;
import de.jrninj.serverapi.utils.YMLFile;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerData {

    public static String getUUID(String username) {

        try {

            PreparedStatement statement = MySQL.getConnection().prepareStatement("SELECT UUID FROM players WHERE USERNAME = ?;");

            statement.setString(1, username);
            statement.executeQuery();

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {

                return resultSet.getString("UUID");

            } else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String getUsername(String uuid) {

        try {

            PreparedStatement statement = MySQL.getConnection().prepareStatement("SELECT USERNAME FROM players WHERE UUID = ?;");

            statement.setString(1, uuid);
            statement.executeQuery();

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {

                return resultSet.getString("USERNAME");

            } else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
