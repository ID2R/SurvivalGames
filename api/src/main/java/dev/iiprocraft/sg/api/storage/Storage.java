package dev.iiprocraft.sg.api.storage;

import dev.iiprocraft.sg.api.player.SGPlayer;
import dev.iiprocraft.sg.api.storage.impl.MongoStorage;
import dev.iiprocraft.sg.api.storage.impl.MySQLStorage;
import dev.iiprocraft.sg.api.storage.impl.YAMLStorage;
import dev.iiprocraft.sg.api.storage.misc.ConnectionCredentials;

import java.util.UUID;

/**
 * @author DirectPlan
 */
public class Storage {

    private StorageRepository storageRepository;

    public Storage(StorageMethod storageMethod, ConnectionCredentials credentials) {

        switch (storageMethod) {
            case MYSQL: {
                storageRepository = new MySQLStorage(credentials);
                return;
            }
            case MONGODB: {
                storageRepository = new MongoStorage(credentials);
                return;
            }
            case YAML: {
                storageRepository = new YAMLStorage();
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
