package com.yakovliam.slimerange.listener;

import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.api.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

        Player player = event.getPlayer();

        // clear inventory
        player.getInventory().clear();

        // give iron hoe
        ItemStack itemStack = new ItemStack(Material.IRON_HOE);
        ItemMeta meta = Bukkit.getServer().getItemFactory().getItemMeta(Material.IRON_HOE);
        meta.setDisplayName(ChatColor.GRAY + "Gun");
        itemStack.setItemMeta(meta);

        player.getInventory().setItem(0, new ItemStack(Material.IRON_HOE));
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
