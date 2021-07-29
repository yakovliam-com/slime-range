package com.yakovliam.slimerange.user;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.api.cache.AsyncCache;
import com.yakovliam.slimerange.model.user.User;

import java.util.UUID;

public class UserCache extends AsyncCache<UUID, User> {

    /**
     * Cache
     *
     * @param plugin plugin
     */
    public UserCache(SlimeRangePlugin plugin) {
        super(Caffeine.newBuilder()
                .buildAsync((uuid) -> plugin.getStorage().getStorageImplementation().getUser(uuid)));
    }
}
