package com.yakovliam.slimerange.listener;

import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.api.message.Message;
import com.yakovliam.slimerange.api.wrapper.Pair;
import com.yakovliam.slimerange.item.GunItem;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitTask;

public class PlayerItemListener implements Listener {

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
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }

        // if item is not a gun
        if (!(event.getItem() != null && GunItem.isGun(event.getItem().getType()))) {
            return;
        }

        // get cooldown info
        Pair<Long, BukkitTask> cooldownInfo = plugin.getCooldownManager().getCooldownInfo(player.getUniqueId());
        if (cooldownInfo != null) {
            // you're on cooldown!
            Message.builder()
                    .addLine("&7You can't shoot right now; you're on &ccooldown &7for 3 seconds.")
                    .build()
                    .message(player);
            return;
        }

        // fire projectile
        Arrow arrow = player.getWorld().spawnArrow(player.getLocation().add(0, 1.2, 0), player.getLocation().getDirection(), 1.8f, 4);
        arrow.setShooter(player);
        arrow.setColor(Color.fromRGB(44, 144, 203));

        // spawn particles
        player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 50);

        Bukkit.getScheduler().runTaskLater(plugin, arrow::remove, 200L); // 5 seconds

        // add cooldown, 3 seconds
        plugin.getCooldownManager().addCooldown(player.getUniqueId(), 3000L);
    }
}
