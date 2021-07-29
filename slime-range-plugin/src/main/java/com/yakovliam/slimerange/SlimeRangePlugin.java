package com.yakovliam.slimerange;

import com.yakovliam.slimerange.api.Plugin;
import com.yakovliam.slimerange.api.message.Message;
import com.yakovliam.slimerange.listener.PlayerItemListener;
import com.yakovliam.slimerange.listener.PlayerJoinListener;
import com.yakovliam.slimerange.storage.Storage;
import com.yakovliam.slimerange.user.UserCache;

public class SlimeRangePlugin extends Plugin {

    /**
     * Storage
     */
    private Storage storage;

    /**
     * User cache
     */
    private UserCache userCache;

    @Override
    public void onEnable() {
        Message.initAudience(this);

        this.storage = new Storage(this);

        this.userCache = new UserCache(this);

        // register listeners
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerItemListener(this), this);
    }

    /**
     * Returns storage
     *
     * @return storage
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Returns user cache
     *
     * @return user cache
     */
    public UserCache getUserCache() {
        return userCache;
    }
}
