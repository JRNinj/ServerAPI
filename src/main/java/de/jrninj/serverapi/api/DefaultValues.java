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

}
