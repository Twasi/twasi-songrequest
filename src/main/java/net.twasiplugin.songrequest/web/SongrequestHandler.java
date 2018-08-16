package net.twasiplugin.songrequest.web;

import net.twasi.core.database.models.User;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.webinterface.lib.Commons;
import net.twasi.core.webinterface.lib.RequestHandler;
import net.twasiplugin.songrequest.requestlist.RequestList;
import net.twasiplugin.songrequest.requestlist.RequestListService;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletResponse;

public class SongrequestHandler extends RequestHandler {

    @Override
    public void handleGet(Request r, HttpServletResponse t) {
        if (!isAuthenticated(r)) {
            Commons.writeString(t, "Not authenticated", 401);
            return;
        }

        User user = getUser(r);

        // Get request list
        RequestList list = ServiceRegistry.get(RequestListService.class).getRequestListForUser(user.getId());

        Commons.writeDTO(t, list, 200);
    }
}
