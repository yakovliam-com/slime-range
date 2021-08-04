package com.yakovliam.slimerange.command;

import co.aikar.commands.BaseCommand;
import com.yakovliam.slimerange.SlimeRangePlugin;

public abstract class AbstractSLCommand extends BaseCommand {

    /**
     * Slime range core plugin
     */
    protected final SlimeRangePlugin plugin;

    /**
     * Slime range core command
     *
     * @param plugin plugin
     */
    public AbstractSLCommand(SlimeRangePlugin plugin) {
        this.plugin = plugin;
    }

    protected abstract void registerCompletions();
}
