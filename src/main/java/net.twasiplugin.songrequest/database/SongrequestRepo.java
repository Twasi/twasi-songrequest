package net.twasiplugin.songrequest.database;

import net.twasi.core.database.lib.Repository;
import org.bson.types.ObjectId;

public class SongrequestRepo extends Repository<SongrequestData> {
    public SongrequestData getByUser(ObjectId userId) {
        SongrequestData data = store.find(SongrequestData.class)
                .field("userId").equal(userId)
                .get();

        if (data == null) {
            // Not found, create
            data = new SongrequestData(userId);
        }

        store.save(data);

        return data;
    }
}
