package dev.iiprocraft.sg.api.storage;

/**
 * @author DirectPlan
 */
public interface StorageConnection {

    void connect();

    void close();
}
