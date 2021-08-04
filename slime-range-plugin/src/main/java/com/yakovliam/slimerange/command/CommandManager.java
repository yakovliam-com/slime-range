package com.yakovliam.slimerange.command;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.MessageType;
import com.yakovliam.slimerange.SlimeRangePlugin;
import org.bukkit.ChatColor;

import java.util.Collections;

public class CommandManager extends BukkitCommandManager {

    public CommandManager(SlimeRangePlugin plugin) {
        super(plugin);

        enableUnstableAPI("help");
        enableUnstableAPI("brigadier");

        setFormat(MessageType.INFO, ChatColor.WHITE);
        setFormat(MessageType.HELP, ChatColor.GRAY);
        setFormat(MessageType.ERROR, ChatColor.RED);
        setFormat(MessageType.SYNTAX, ChatColor.GRAY);

        // TODO add more commands here, registering them
        Collections.singletonList(
                new SLCommand(plugin)
        ).forEach(c -> {
            c.registerCompletions();
            this.registerCommand(c);
        });
    }
}