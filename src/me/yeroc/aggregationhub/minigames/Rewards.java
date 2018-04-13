package me.yeroc.aggregationhub.minigames;

import me.yeroc.aggregationhub.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Corey on 6/02/2018.
 */
public class Rewards {

    private Main plugin;
    public static Rewards instance = new Rewards();

    public Rewards(Main main) {
    }

    public Rewards() {

    }

    public static Rewards getInstance() {
        return instance;
    }

    public void giveXP(Player p, int i) {
        p.setLevel(p.getLevel() + i);
    }

    public void giveMoney(Player p, int i) {
        Main.econ.depositPlayer(p, i);
    }

    public void addPermission(Player p, String perm) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add " + perm);
    }

    public void upgradeRank(Player p) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " promote");
    }
}
