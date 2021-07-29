package com.yakovliam.slimerange.storage;

import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.storage.implementation.JsonStorageImplementation;

public class Storage {

    /**
     * Storage implementation
     */
    private final StorageImplementation storageImplementation;

    public Storage(SlimeRangePlugin plugin) {
        this.storageImplementation = new JsonStorageImplementation(plugin);
    }

    /**
     * Returns storage implementation
     *
     * @return storage implementation
     */
    public StorageImplementation getStorageImplementation() {
        return storageImplementation;
    }
}
