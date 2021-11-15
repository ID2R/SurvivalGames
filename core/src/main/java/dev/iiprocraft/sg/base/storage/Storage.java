package dev.iiprocraft.sg.base.storage;

import dev.iiprocraft.sg.base.config.ConfigKeys;
import dev.iiprocraft.sg.base.player.SGPlayer;
import dev.iiprocraft.sg.base.storage.impl.JSONStorage;
import dev.iiprocraft.sg.base.storage.impl.MongoStorage;
import dev.iiprocraft.sg.base.storage.impl.MySQLStorage;
import dev.iiprocraft.sg.base.storage.misc.ConnectionCredentials;
import dev.iiprocraft.sg.base.storage.misc.StorageMethod;
import java.util.UUID;

/**
 * @author DirectPlan
 */
public class Storage {

    private final StorageRepository storageRepository;

    public Storage() {

        String host = ConfigKeys.STORAGE_HOST.getString();
        int port = ConfigKeys.STORAGE_PORT.getInteger();
        String username = ConfigKeys.STORAGE_USERNAME.getString();
        String password = ConfigKeys.STORAGE_PASSWORD.getString();
        String database = ConfigKeys.STORAGE_DATABASE.getString();
        int maximumPoolSize = ConfigKeys.STORAGE_MAXIMUM_POOL_SIZE.getInteger();

        StorageMethod storageMethod = StorageMethod.fromStr(ConfigKeys.STORAGE_METHOD.getString());

        ConnectionCredentials credentials = new ConnectionCredentials(host, username, password, database, port, maximumPoolSize);
        switch (storageMethod) {
            case MYSQL: {
                storageRepository = new MySQLStorage(credentials);
                break;
            }
            case MONGODB: {
                storageRepository = new MongoStorage(credentials);
                break;
            }
            case JSON: {
                storageRepository = new JSONStorage(credentials);
                break;
            }
            default : {
                throw new IllegalArgumentException("Unknown Type of storage !");
            }
        }
    }

    public void connect() {
        storageRepository.connect();
    }

    public SGPlayer loadPlayer(UUID uuid) {

        return storageRepository.loadPlayer(uuid); /* TODO: Async this operation */
    }

    public void savePlayer(SGPlayer player) {
        storageRepository.savePlayer(player); /* TODO: Async this operation */
    }

    public void close() {
        storageRepository.close();
    }
}
