package com.yakovliam.slimerange.listener;

import com.yakovliam.slimerange.SlimeRangePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collections;

public class PlayerJoinListener implements Listener {

    /**
     * Slime range plugin
     */
    private final SlimeRangePlugin plugin;

    /**
     * Player join listener
     *
     * @param plugin plugin
     */
    public PlayerJoinListener(SlimeRangePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // load into cache
        plugin.getUserCache().getCache().get(event.getPlayer().getUniqueId());
        // add to statistic
        plugin.getUserPointsStatistic().update(Collections.singletonList(event.getPlayer().getUniqueId()), true, true);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // save data
        plugin.getStorage().getStorageImplementation().saveUser(plugin.getUserCache().getCache().get(event.getPlayer().getUniqueId()).join());
        // invalidate from cache
        plugin.getUserCache().getCache().synchronous().invalidate(event.getPlayer().getUniqueId());
    }
}
