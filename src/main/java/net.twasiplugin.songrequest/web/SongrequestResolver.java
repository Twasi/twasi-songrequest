package net.twasiplugin.songrequest.web;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import net.twasi.core.database.models.User;
import net.twasi.core.logger.TwasiLogger;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasi.core.services.providers.JWTService;
import net.twasiplugin.songrequest.web.model.SongrequestDTO;

public class SongrequestResolver implements GraphQLQueryResolver {

    public SongrequestDTO songrequest(String token) {
        try {
            User user = ServiceRegistry.get(JWTService.class).getManager().getUserFromToken(token);
            try {
                ServiceRegistry.get(DataService.class).get(net.twasi.core.database.repositories.UserRepository.class).commit(user);
            } catch (IllegalArgumentException ignored) {}

            if (user == null) {
                return null;
            }

            return new SongrequestDTO(user);
        } catch (Throwable t) {
            TwasiLogger.log.error("Fatal Error in GraphQL.", t);
            return null;
        }
    }

}
