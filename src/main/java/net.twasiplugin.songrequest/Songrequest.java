package net.twasiplugin.songrequest;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import net.twasi.core.graphql.WebInterfaceApp;
import net.twasi.core.logger.TwasiLogger;
import net.twasi.core.plugin.TwasiPlugin;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.services.ServiceRegistry;
import net.twasiplugin.songrequest.api.YoutubeAPI;
import net.twasiplugin.songrequest.requestlist.RequestListService;
import net.twasiplugin.songrequest.tsss.websocket.SongrequestSocketServlet;
import net.twasiplugin.songrequest.web.SongrequestResolver;
import org.eclipse.jetty.server.handler.ContextHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Songrequest extends TwasiPlugin {
    public static Properties config = new Properties();
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
        // Register services
        ServiceRegistry.register(new RequestListService());

        configFile = new File(getDataFolder().getPath() + "/songrequest.properties");
        try {
            config.load(new FileInputStream(configFile));
        } catch (FileNotFoundException e) {
            TwasiLogger.log.error("songrequest.properties file not found. Please create that first and then try to active Songrequest again. Path: " + configFile.getAbsolutePath(), e);
        } catch (IOException e) {
            TwasiLogger.log.error("IOException while reading songrequest.properties. Path: " + configFile.getAbsolutePath(), e);
        }

        Arrays.stream(requiredProperties).forEach(prop -> {
            if (!config.containsKey(prop)) {
                TwasiLogger.log.error("Key not found in config: " + prop);
            }
        });

        YoutubeAPI.setUp();

        TwasiLogger.log.debug("Registered /apis/songrequest servlet");
        WebInterfaceApp.getServletHandler().addServlet(SongrequestSocketServlet.class, "/songrequest");
    }

    @Override
    public Class<? extends TwasiUserPlugin> getUserPluginClass() {
        return SongrequestUP.class;
    }

    @Override
    public GraphQLQueryResolver getGraphQLResolver() {
        return new SongrequestResolver();
    }
}
