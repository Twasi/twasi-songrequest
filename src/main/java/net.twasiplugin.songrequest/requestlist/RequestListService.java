package net.twasiplugin.songrequest.requestlist;

import net.twasi.core.services.IService;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class RequestListService implements IService {
    private List<RequestList> activeRequestLists = new ArrayList<>();

    public RequestList getRequestListForUser(ObjectId userId) {
        RequestList list = activeRequestLists.stream().filter(rl -> rl.getUserId().equals(userId)).findFirst().orElse(null);

        if (list == null) {
            list = new RequestList();
            list.loadForUser(userId);
            activeRequestLists.add(list);
        }

        return list;
    }
}
