package com.yakovliam.slimerange.config;

import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.api.config.generic.adapter.ConfigurationAdapter;

public class KeylessConfiguration {

    private final ConfigurationAdapter adapter;

    private final SlimeRangePlugin plugin;

    public KeylessConfiguration(SlimeRangePlugin plugin, ConfigurationAdapter adapter) {
        this.plugin = plugin;
        this.adapter = adapter;
    }

    public ConfigurationAdapter getAdapter() {
        return adapter;
    }

    public SlimeRangePlugin getPlugin() {
        return plugin;
    }
}
