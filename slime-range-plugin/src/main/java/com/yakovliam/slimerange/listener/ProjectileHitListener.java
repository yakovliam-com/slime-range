package com.yakovliam.slimerange.listener;

import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.api.message.Message;
import com.yakovliam.slimerange.model.user.User;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Random;

public class ProjectileHitListener implements Listener {

    /**
     * Slime range plugin
     */
    private final SlimeRangePlugin plugin;

    /**
     * Projectile hit listener
     *
     * @param plugin plugin
     */
    public ProjectileHitListener(SlimeRangePlugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        // if not an arrow
        if (!(event.getEntity() instanceof Arrow)) {
            return;
        }

        // if not a target
        if (event.getHitBlock() == null || !event.getHitBlock().getType().equals(Material.TARGET)) {
            return;
        }

        // if not a player
        if (event.getEntity().getShooter() == null || !(event.getEntity().getShooter() instanceof Player)) {
            return;
        }

        // if not shot by a hoe
        if (!(((Arrow) event.getEntity()).getColor().equals(Color.fromRGB(44, 144, 203)))) {
            return;
        }

        // get shooter
        Player shooter = (Player) event.getEntity().getShooter();

        int randomPointAmount = new Random().nextInt(3 - 1 + 1) + 1;

        Message.builder()
                .addLine("&bYou hit the target!")
                .addLine("&a+" + randomPointAmount)
                .build().message(shooter);

        // get user
        User user = plugin.getUserCache().getCache().get(shooter.getUniqueId()).join();
        user.setPoints(user.getPoints() + randomPointAmount);

        // save
        plugin.getStorage().getStorageImplementation().saveUser(user);
    }
}
