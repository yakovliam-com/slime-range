package com.yakovliam.slimerange.storage;

import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.model.user.User;

import java.util.UUID;

public abstract class StorageImplementation {

    /**
     * Slime range plugin
     */
    protected SlimeRangePlugin plugin;

    /**
     * Initializes the storage
     */
    public abstract void init();

    /**
     * Storage implementation
     *
     * @param plugin plugin
     */
    public StorageImplementation(SlimeRangePlugin plugin) {
        this.plugin = plugin;
    }


    /**
     * Returns a user
     *
     * @param uuid uuid
     * @return user
     */
    public abstract User getUser(UUID uuid);

    /**
     * Saves a user
     *
     * @param user user
     */
    public abstract void saveUser(User user);
}
