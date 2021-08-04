package com.yakovliam.slimerange.command;

import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Single;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.api.message.Message;
import com.yakovliam.slimerange.item.GunItem;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("slimerange")
@CommandPermission("slimerange.command")
public class SLCommand extends AbstractSLCommand {

    /**
     * MineHut core command
     *
     * @param plugin plugin
     */
    public SLCommand(SlimeRangePlugin plugin) {
        super(plugin);
    }

    @Override
    protected void registerCompletions() {
    }

    @Subcommand("give")
    public void onGive(CommandSender sender, @Single OnlinePlayer target, @Single String type) {
        // get type from type
        GunItem item;
        try {
            item = GunItem.valueOf(type.toUpperCase());
        } catch (Exception e) {
            invalidGunType(sender);
            return;
        }

        // create itemstack
        ItemStack stack = item.create();

        Player targetPlayer = target.getPlayer();
        targetPlayer.getInventory().addItem(stack);
        targetPlayer.updateInventory();

        Message.builder()
                .addLine("&7Successfully given!")
                .build()
                .message(sender);
    }

    /**
     * Invalid gun type message
     *
     * @param sender sender
     */
    private void invalidGunType(CommandSender sender) {
        Message.builder()
                .addLine("&cInvalid gun type!")
                .build()
                .message(sender);
    }

    @Default
    @HelpCommand
    public void onDefault(CommandSender sender, CommandHelp help) {
        Message.builder()
                .addLine("&7SlimeRange Help:")
                .build()
                .message(sender);
        help.showHelp();
    }
}
