package me.yeroc.aggregationhub.utils;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.aggregationhub.Main;
import me.yeroc.aggregationhub.managers.Scoreboard;
import me.yeroc.aggregationhub.minigames.KOTL;
import me.yeroc.aggregationhub.minigames.Maze;
import me.yeroc.aggregationhub.utils.TitleAPI.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Corey on 4/02/2018.
 */
public class Timer {

    private Main plugin;
    public static Timer instance = new Timer();

    public Timer(Main main) {
    }

    public Timer() {

    }

    public static Timer getInstance() {
        return instance;
    }

    private KOTL kotl = KOTL.getInstance();
    private Maze maze = Maze.getInstance();
    private Scoreboard scoreboard = Scoreboard.getInstance();

    public void startTab() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GOLD + "" + ChatColor.BOLD + "A" + ChatColor.GREEN + "" + ChatColor.BOLD + "ggregationPVP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "-" + "-");
                }
            }
        }, 5L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "A" + ChatColor.GOLD + "" + ChatColor.BOLD + "g" + ChatColor.GREEN + "" + ChatColor.BOLD + "gregationPVP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "--" + "--");
                }
            }
        }, 10L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "Ag" + ChatColor.GOLD + "" + ChatColor.BOLD + "g" + ChatColor.GREEN + "" + ChatColor.BOLD + "regationPVP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "---" + "---");
                }
            }
        }, 15L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "Agg" + ChatColor.GOLD + "" + ChatColor.BOLD + "r" + ChatColor.GREEN + "" + ChatColor.BOLD + "egationPVP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "----" + "----");
                }
            }
        }, 20L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "Aggr" + ChatColor.GOLD + "" + ChatColor.BOLD + "e" + ChatColor.GREEN + "" + ChatColor.BOLD + "gationPVP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "----[" + "]----");
                }
            }
        }, 25L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "Aggre" + ChatColor.GOLD + "" + ChatColor.BOLD + "g" + ChatColor.GREEN + "" + ChatColor.BOLD + "ationPVP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "----[" + ChatColor.RED + "HB" + ChatColor.GOLD + "]----");
                }
            }
        }, 30L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "Aggreg" + ChatColor.GOLD + "" + ChatColor.BOLD + "a" + ChatColor.GREEN + "" + ChatColor.BOLD + "tionPVP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "----[" + ChatColor.RED + "HUB" + ChatColor.GOLD + "]----");
                }
            }
        }, 35L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "Aggrega" + ChatColor.GOLD + "" + ChatColor.BOLD + "t" + ChatColor.GREEN + "" + ChatColor.BOLD + "ionPVP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "----[" + ChatColor.RED + "HUB" + ChatColor.GOLD + "]----");
                }
            }
        }, 40L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "Aggregat" + ChatColor.GOLD + "" + ChatColor.BOLD + "i" + ChatColor.GREEN + "" + ChatColor.BOLD + "onPVP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "----[" + ChatColor.RED + "HUB" + ChatColor.GOLD + "]----");
                }
            }
        }, 45L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "Aggregati" + ChatColor.GOLD + "" + ChatColor.BOLD + "o" + ChatColor.GREEN + "" + ChatColor.BOLD + "nPVP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "----[" + ChatColor.RED + "HUB" + ChatColor.GOLD + "]----");
                }
            }
        }, 50L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "Aggregatio" + ChatColor.GOLD + "" + ChatColor.BOLD + "n" + ChatColor.GREEN + "" + ChatColor.BOLD + "PVP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "----[" + ChatColor.RED + "HUB" + ChatColor.GOLD + "]----");
                }
            }
        },55L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "Aggregation" + ChatColor.GOLD + "" + ChatColor.BOLD + "P" + ChatColor.GREEN + "" + ChatColor.BOLD + "VP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "----[" + ChatColor.RED + "HUB" + ChatColor.GOLD + "]----");
                }
            }
        }, 60L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "AggregationP" + ChatColor.GOLD + "" + ChatColor.BOLD + "V" + ChatColor.GREEN + "" + ChatColor.BOLD + "P" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "----[" + ChatColor.RED + "HUB" + ChatColor.GOLD + "]----");
                }
            }
        }, 65L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "AggregationPV" + ChatColor.GOLD + "" + ChatColor.BOLD + "P" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "----[" + ChatColor.RED + "HUB" + ChatColor.GOLD + "]----");
                }
            }
        }, 70L, 125L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendTabTitle(p.getPlayer(), ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "AggregationPVP" + ChatColor.GRAY + " ]=-", ChatColor.GOLD + "----[" + ChatColor.RED + "HUB" + ChatColor.GOLD + "]----");
                }
            }
        }, 75L, 125L);
    }

    public void startTimers() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() == 0) {
                    return;
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    kotl.addSeconds(p);
                    maze.addSeconds(p);
                    scoreboard.checkUpdate(p);
                }
            }
        }, 0L, 20L);
    }

//    public void startTab() {
//        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
//            @Override
//            public void run() {
//                if (Bukkit.getOnlinePlayers().size() == 0) {
//                    return;
//                }
//                for (Player p : Bukkit.getOnlinePlayers()) {
//                    TitleAPI.sendTabTitle(p, ChatColor.GOLD + "" + ChatColor.BOLD + "AggregationPVP", ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GOLD + "" + ChatColor.BOLD + "Hub" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "]");
//                }
//            }
//        }, 0L, 60L);
//    }
//
//    public void startScoreboard() {
//        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
//            @Override
//            public void run() {
//                if (Bukkit.getOnlinePlayers().size() == 0) {
//                    return;
//                }
//                for (Player p : getOnlinePlayers()) {
//                }
//            }
//        }, 0, 20L);
//    }
}
