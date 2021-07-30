package com.yakovliam.slimerange.cooldown;

import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.api.wrapper.Pair;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    /**
     * Cooldown map
     * <p>
     * Represents
     * [UUID], [ms time when the cooldown ends, associated task]
     */
    private final Map<UUID, Pair<Long, BukkitTask>> cooldownMap;

    /**
     * Slime range plugin
     */
    private final SlimeRangePlugin plugin;

    /**
     * Cooldown manager
     *
     * @param plugin plugin
     */
    public CooldownManager(SlimeRangePlugin plugin) {
        this.plugin = plugin;
        this.cooldownMap = new HashMap<>();
    }

    /**
     * Adds a cooldown
     *
     * @param uuid                 uuid
     * @param lengthInMilliseconds length
     */
    public void addCooldown(UUID uuid, Long lengthInMilliseconds) {
        // get ending time
        long endingTime = System.currentTimeMillis() + lengthInMilliseconds;

        // if user is currently in a cooldown, remove/cancel and make a new one
        removeCooldown(uuid);

        // create new
        BukkitTask task = Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> removeCooldown(uuid), (lengthInMilliseconds / (long) 1000) * 20);

        // add to map
        cooldownMap.put(uuid, new Pair<>(endingTime, task));
    }

    /**
     * Removes a currently active cooldown
     *
     * @param uuid uuid
     */
    public void removeCooldown(UUID uuid) {
        if (!cooldownMap.containsKey(uuid)) {
            return;
        }

        Pair<Long, BukkitTask> cooldownInfo = cooldownMap.get(uuid);

        // cancel
        cooldownInfo.getRight().cancel();

        // remove
        cooldownMap.remove(uuid);
    }

    /**
     * Returns cooldown info
     *
     * @param uuid uuid
     * @return cooldown info
     */
    public Pair<Long, BukkitTask> getCooldownInfo(UUID uuid) {
        return cooldownMap.getOrDefault(uuid, null);
    }
}
