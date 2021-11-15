package dev.iiprocraft.sg.base.storage.misc;

import lombok.Data;
import lombok.Getter;

/**
 * @author DirectPlan
 */

@Data
public class ConnectionCredentials {

    @Getter
    private final String host, username, password, database;
    @Getter
    private final int port, maximumPoolSize;

    public ConnectionCredentials(String host, String username, String password, String database, int port, int maximumPoolSize) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.database = database;
        this.port = port;
        this.maximumPoolSize = maximumPoolSize;
    }

}