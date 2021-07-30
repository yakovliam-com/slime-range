package com.yakovliam.slimerange.listener;

import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.api.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerItemListener implements Listener {

    // TODO add cooldown

    /**
     * Slime range plugin
     */
    private final SlimeRangePlugin plugin;

    /**
     * Player item listener
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

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }

        // if item is not a hoe
        if (!(event.getItem() != null && event.getItem().getType().equals(Material.IRON_HOE))) {
            return;
        }

        // fire projectile
        Arrow arrow = player.getWorld().spawnArrow(player.getLocation().add(0, 1.2, 0), player.getLocation().getDirection(), 1.5f, 8);
        arrow.setShooter(player);
        arrow.setColor(Color.fromRGB(44, 144, 203));

        // spawn particles
        player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 50);

        Bukkit.getScheduler().runTaskLater(plugin, arrow::remove, 200L); // 5 seconds
    }
}
