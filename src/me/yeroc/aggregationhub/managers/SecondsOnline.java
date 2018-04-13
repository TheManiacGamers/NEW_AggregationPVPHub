package me.yeroc.aggregationhub.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Corey on 25/01/2018.
 */
public class SecondsOnline extends BukkitRunnable {

    private PlayerStatistics pStats = PlayerStatistics.getInstance();

    @Override
    public void run() {
        if (Bukkit.getServer().getOnlinePlayers().size() >= 0) {
            return;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            pStats.secondsOnline.put(p.getUniqueId(), pStats.secondsOnline.get(p.getUniqueId()) + 1);
        }
    }
}
