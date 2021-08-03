package com.yakovliam.slimerange.expansion;

import com.yakovliam.slimerange.SlimeRangePlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SlimeRangePlaceholderExpansion extends PlaceholderExpansion {

    /**
     * Slime range plugin
     */
    private final SlimeRangePlugin plugin;

    /**
     * Slime range placeholder expansion
     *
     * @param plugin plugin
     */
    public SlimeRangePlaceholderExpansion(SlimeRangePlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * The placeholder identifier of this expansion. May not contain {@literal %},
     * {@literal {}} or _
     *
     * @return placeholder identifier that is associated with this expansion
     */
    @Override
    public @NotNull String getIdentifier() {
        return "slimerange";
    }

    /**
     * The author of this expansion
     *
     * @return name of the author for this expansion
     */
    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().stream()
                .findFirst()
                .orElse("yakovliam");
    }

    /**
     * The version of this expansion
     *
     * @return current version of this expansion
     */
    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.equalsIgnoreCase("points-int")) {
            return Integer.toString((int) plugin.getUserCache().getCache().get(player.getUniqueId()).join().getPoints());
        } else if (params.equalsIgnoreCase("points")) {
            return Double.toString(plugin.getUserCache().getCache().get(player.getUniqueId()).join().getPoints());
        } else {
            return null;
        }
    }
}
