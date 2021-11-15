package dev.iiprocraft.sg.base.storage.misc;

import lombok.Data;

/**
 * @author DirectPlan
 */

@Data
public class ConnectionCredentials {

    private final String host, username, password, database;
    private final int port, maximumPoolSize;
}