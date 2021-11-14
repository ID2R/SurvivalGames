package dev.iiprocraft.sg.api.storage.misc;

import lombok.Data;

/**
 * @author DirectPlan
 */

@Data
public class ConnectionCredentials {

    private final String host, username, password, database;
    private final int port, maximumPoolSize;
}