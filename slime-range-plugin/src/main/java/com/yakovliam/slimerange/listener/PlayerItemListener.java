package com.yakovliam.slimerange.listener;

import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.api.message.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerItemListener implements Listener {

    /**
     * Slime range plugin
     */
    private final SlimeRangePlugin plugin;

    /**
     * Player join listener
     *
     * @param plugin plugin
     */
    public PlayerItemListener(SlimeRangePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Message.builder()
                .addLine("&7Hey! You can't drop items here.")
                .build()
                .message(event.getPlayer());
        event.setCancelled(true);
    }
}
