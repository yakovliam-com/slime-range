package com.yakovliam.slimerange.listener;

import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.api.message.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
        event.setJoinMessage(null);

        Message.builder()
                .addLine("&e%player% &7joined.")
                .build()
                .broadcast("%player%", event.getPlayer().getName());

        // load into cache
        plugin.getUserCache().getCache().get(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        Message.builder()
                .addLine("&e%player% &7left.")
                .build()
                .broadcast("%player%", event.getPlayer().getName());

        // invalidate from cache
        plugin.getUserCache().getCache().synchronous().invalidate(event.getPlayer().getUniqueId());
    }
}
