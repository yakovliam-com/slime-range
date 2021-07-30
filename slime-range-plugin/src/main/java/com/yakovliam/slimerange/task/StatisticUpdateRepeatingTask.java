package com.yakovliam.slimerange.task;

import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.model.task.RepeatingTask;

import java.util.Collections;

public class StatisticUpdateRepeatingTask extends RepeatingTask {
    /**
     * Repeating task
     *
     * @param plugin plugin
     */
    public StatisticUpdateRepeatingTask(SlimeRangePlugin plugin) {
        // period = 10 seconds
        super(plugin, 200L, true);
    }

    @Override
    public void run() {
        // update statistic
        plugin.getUserPointsStatistic().update(Collections.emptyList(), false);
    }
}
