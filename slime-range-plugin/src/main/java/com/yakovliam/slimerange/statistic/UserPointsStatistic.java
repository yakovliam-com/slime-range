package com.yakovliam.slimerange.statistic;

import com.yakovliam.slimerange.SlimeRangePlugin;
import dev.spaceseries.spacestatistics.api.Statistic;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserPointsStatistic extends Statistic<UUID, Double> {

    /**
     * Slime range plugin
     */
    private final SlimeRangePlugin plugin;

    /**
     * User points statistic
     *
     * @param plugin plugin
     */
    public UserPointsStatistic(SlimeRangePlugin plugin) {
        super("slimerange-points");
        this.plugin = plugin;

        // initialize keys
        Set<UUID> allPlayers = Arrays.stream(Bukkit.getOfflinePlayers()).map(OfflinePlayer::getUniqueId).collect(Collectors.toSet());
        update(allPlayers, true, true);
    }

    @Override
    protected Double get(UUID key) {
        return plugin.getUserCache().getCache().get(key).join().getPoints();
    }

    @Override
    protected String keyAsString(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }

    @Override
    protected String valueAsString(Double aDouble) {
        return Integer.toString(aDouble.intValue());
    }
}
