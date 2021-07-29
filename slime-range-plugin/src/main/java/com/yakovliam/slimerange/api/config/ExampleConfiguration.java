package com.yakovliam.slimerange.api.config;

import com.yakovliam.slimerange.api.Plugin;
import com.yakovliam.slimerange.api.config.generic.KeyedConfiguration;
import com.yakovliam.slimerange.api.config.generic.adapter.ConfigurationAdapter;

public final class ExampleConfiguration extends KeyedConfiguration {
    private final Plugin plugin;

    public ExampleConfiguration(Plugin plugin, ConfigurationAdapter adapter) {
        super(adapter, null); // here you would pass in the keys for the associated config, e.g. ConfigKeys.getKeys();
        this.plugin = plugin;

        init();
    }

    @Override
    protected void load(boolean initial) {
        super.load(initial);
    }

    @Override
    public void reload() {
        super.reload();
    }

    public Plugin getPlugin() {
        return this.plugin;
    }
}
