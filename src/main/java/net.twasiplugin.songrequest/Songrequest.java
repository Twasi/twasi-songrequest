package net.twasiplugin.songrequest;

import net.twasi.core.logger.TwasiLogger;
import net.twasi.core.plugin.TwasiPlugin;
import net.twasi.core.plugin.api.TwasiUserPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Songrequest extends TwasiPlugin {
    private Properties config = new Properties();
    private File configFile;

    private String[] requiredProperties = {
            "line.max",

            "provider.youtube.enabled",
            "provider.youtube.secret",

            "provider.spotify.enabled",
            "provider.spotify.secret",

            "provider.soundcloud.enabled",
            "provider.soundcloud.secret",
    };

    @Override
    public void onActivate() {
        configFile = new File(getDataFolder().getPath() + "/songrequest.properties");
        try {
            config.load(new FileInputStream(configFile));
        } catch (FileNotFoundException e) {
            TwasiLogger.log.error("songrequest.properties file not found. Please create that first and then try to active Songrequest again.", e);
        } catch (IOException e) {
            TwasiLogger.log.error("IOException while reading songrequest.properties.", e);
        }

        Arrays.stream(requiredProperties).forEach(prop -> {
            if (!config.containsKey(prop)) {
                TwasiLogger.log.error("Key not found in config: " + prop);
            }
        });
    }

    @Override
    public Class<? extends TwasiUserPlugin> getUserPluginClass() {
        return SongrequestUP.class;
    }
}
