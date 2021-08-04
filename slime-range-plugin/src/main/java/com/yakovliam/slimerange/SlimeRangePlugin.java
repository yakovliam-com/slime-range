package com.yakovliam.slimerange;

import com.yakovliam.slimerange.api.Plugin;
import com.yakovliam.slimerange.api.message.Message;
import com.yakovliam.slimerange.command.CommandManager;
import com.yakovliam.slimerange.cooldown.CooldownManager;
import com.yakovliam.slimerange.expansion.SlimeRangePlaceholderExpansion;
import com.yakovliam.slimerange.listener.PlayerItemListener;
import com.yakovliam.slimerange.listener.PlayerJoinListener;
import com.yakovliam.slimerange.listener.ProjectileHitListener;
import com.yakovliam.slimerange.statistic.UserPointsStatistic;
import com.yakovliam.slimerange.storage.Storage;
import com.yakovliam.slimerange.task.StatisticUpdateRepeatingTask;
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

    /**
     * Cooldown manager
     */
    private CooldownManager cooldownManager;

    /**
     * User points statistic
     */
    private UserPointsStatistic userPointsStatistic;

    @Override
    public void onEnable() {
        Message.initAudience(this);

        this.storage = new Storage(this);

        this.userCache = new UserCache(this);

        this.cooldownManager = new CooldownManager(this);

        // register listeners
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerItemListener(this), this);
        this.getServer().getPluginManager().registerEvents(new ProjectileHitListener(this), this);

        // register statistics
        this.userPointsStatistic = new UserPointsStatistic(this);
        userPointsStatistic.register();

        new StatisticUpdateRepeatingTask(this).start();

        // register expansion
        new SlimeRangePlaceholderExpansion(this).register();

        new CommandManager(this);
    }

    @Override
    public void onDisable() {
        // save storage
        userCache.getCache().synchronous().asMap().forEach((uuid, user) -> this.storage.getStorageImplementation().saveUser(user));
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

    /**
     * Returns cooldown manager
     *
     * @return cooldown manager
     */
    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    /**
     * Returns user points statistic
     *
     * @return statistic
     */
    public UserPointsStatistic getUserPointsStatistic() {
        return userPointsStatistic;
    }
}
