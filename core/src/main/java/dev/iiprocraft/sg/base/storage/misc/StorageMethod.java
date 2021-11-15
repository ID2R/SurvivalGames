package dev.iiprocraft.sg.base.storage.misc;

/**
 * @author DirectPlan
 */
public enum StorageMethod {
    MYSQL, MONGODB, JSON, UNKNOWN;


    public static StorageMethod fromStr(String value) {
        StorageMethod method;
        try {
            method = StorageMethod.valueOf(value);
        }catch (Exception ex) {
            method = StorageMethod.UNKNOWN;
        }
        return method;
    }
}
