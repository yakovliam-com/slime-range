package com.yakovliam.slimerange.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum GunItem {

    WOODEN_GUN(Material.WOODEN_HOE,
            "&f&lWooden Gun",
            "&eThe wooden gun!",
            "&7Right click me to shoot!"),

    STONE_GUN(Material.STONE_HOE,
            "&7&lStone Gun",
            "&eThe stone gun!",
            "&7Right click me to shoot!"),

    GOLDEN_GUN(Material.GOLDEN_HOE,
            "&6&lGolden Gun",
            "&eThe golden gun!",
            "&7Right click me to shoot!"),

    IRON_GUN(Material.IRON_HOE,
            "&a&lIron Gun",
            "&eThe iron gun!",
            "&7Right click me to shoot!"),

    DIAMOND_GUN(Material.DIAMOND_HOE,
            "&d&lDiamond Gun",
            "&eThe diamond gun!",
            "&7Right click me to shoot!");

    /**
     * Material
     */
    private final Material material;

    /**
     * Name
     */
    private final String name;

    /**
     * Lore
     */
    private final List<String> lore;

    /**
     * Gun Item
     *
     * @param material material
     * @param name     name
     * @param lore     lore
     */
    GunItem(Material material, String name, String... lore) {
        this.material = material;
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        this.lore = Arrays.stream(lore)
                .map(l -> ChatColor.translateAlternateColorCodes('&', l))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new item
     *
     * @return item
     */
    public ItemStack create() {
        ItemStack itemStack = new ItemStack(this.material, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(this.name);
        meta.setLore(this.lore);

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    /**
     * Is a given gun
     *
     * @param material material
     * @return is equal to
     */
    public boolean is(Material material) {
        return this.material.equals(material);
    }

    /**
     * Is gun
     *
     * @param material material
     * @return is a gun
     */
    public static boolean isGun(Material material) {
        return Arrays.stream(GunItem.values())
                .anyMatch(g -> g.is(material));
    }
}
