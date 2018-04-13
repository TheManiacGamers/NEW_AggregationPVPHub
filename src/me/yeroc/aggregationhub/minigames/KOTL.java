package me.yeroc.aggregationhub.minigames;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.aggregationhub.Main;
import me.yeroc.aggregationhub.managers.PlayerStatistics;
import me.yeroc.aggregationhub.managers.StringsManager;
import me.yeroc.aggregationhub.utils.TitleAPI.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

/**
 * Created by Corey on 3/02/2018.
 */
public class KOTL implements Listener {
    Main plugin;
    static KOTL instance = new KOTL();

    public KOTL() {

    }

    public static KOTL getInstance() {
        return instance;
    }

    private StringsManager strings = StringsManager.getInstance();
    public String kotl = "blank";
    Location box1 = new Location(Bukkit.getServer().getWorld("world"), 131, 4, -237);
    Location box2 = new Location(Bukkit.getServer().getWorld("world"), 95, 21, -201);
    Location ladder = new Location(Bukkit.getServer().getWorld("world"), 113, 14, -219);

    private ArrayList<Player> playersInRegion = new ArrayList<>();
    public HashMap<UUID, Integer> secondsAsKOTL = new HashMap<>();
    private ArrayList<UUID> told = new ArrayList<>();

    public KOTL(Main main) {
    }

//    public void startKOTL() {
//        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
//            @Override
//            public void run() {
//                for (Player players : playersInRegion) {
//                    checkKOTL(players);
//                }
//            }
//        }, 0L, 20L);
//    }

    public void checkKOTL(final Player p) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                Location playerLocation = p.getLocation();
                playerLocation.setY(playerLocation.getY() - 1);
                Block b = playerLocation.getBlock();
                Location pLoc = b.getLocation();
                playersInRegion.clear();
                for (Player players : Bukkit.getOnlinePlayers()) {
                    if (locationIsInCuboid(players)) {
                        playersInRegion.add(players);
                    }
                }
                if (!(told.contains(p.getUniqueId())) && (playersInRegion.contains(p))) {
                    p.sendMessage(strings.kotl + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Entering" + ChatColor.RED + " King of the Ladder region!");
                    told.add(p.getUniqueId());
                }
                if (told.contains(p.getUniqueId()) && (!(playersInRegion.contains(p)))) {
                    p.sendMessage(strings.kotl + ChatColor.GREEN + "" + ChatColor.BOLD + "Leaving" + ChatColor.RED + " King of the Ladder region!");
                    told.remove(p.getUniqueId());
                }
                if (kotl.equalsIgnoreCase(p.getName())) {
                    return;
                }
                if (kotl != null && (!(kotl.equalsIgnoreCase(p.getName())))) {
                    if (pLoc.equals(ladder)) {
                        for (Player kotlPlayers : playersInRegion) {
                            kotlPlayers.sendMessage(strings.kotl + ChatColor.GOLD + "" + ChatColor.BOLD + p.getName() + ChatColor.RED + " is now the King of the Ladder!");
                        }
                        p.sendMessage(strings.kotl + ChatColor.RED + "You are the King of the Ladder!");
                        kotl = p.getName();
                    }
                }
            }
        }, 0L, 5L);
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (kotl != null && (!(kotl.equalsIgnoreCase("blank")))) {
            TitleAPI.sendActionBar(p, ChatColor.GOLD + "" + ChatColor.BOLD + kotl + "" + ChatColor.RED + " is the current King of the Ladder!");
        }

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (kotl.equalsIgnoreCase(p.getName())) {
            kotl = "blank";
            for (Player players : playersInRegion) {
                TitleAPI.sendActionBar(players, ChatColor.RED + "The King of the Ladder has left the game!");
            }
        }
        told.remove(p.getUniqueId());
        playersInRegion.remove(p);
    }

    public void addSeconds(Player p) {
        if (kotl != null && (!(kotl.equalsIgnoreCase("blank")))) {
            if (kotl.equalsIgnoreCase(p.getName())) {
                secondsAsKOTL.put(p.getUniqueId(), secondsAsKOTL.get(p.getUniqueId()) + 1);
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location playerLocation = p.getLocation();
        playerLocation.setY(playerLocation.getY() - 1);
        Block b = playerLocation.getBlock();
        Location pLoc = b.getLocation();
        playersInRegion.clear();
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (locationIsInCuboid(players)) {
                playersInRegion.add(players);
            }
        }
        if (!(told.contains(p.getUniqueId())) && (playersInRegion.contains(p))) {
            p.sendMessage(strings.kotl + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Entering" + ChatColor.RED + " King of the Ladder region!");
            told.add(p.getUniqueId());
        }
        if (told.contains(p.getUniqueId()) && (!(playersInRegion.contains(p)))) {
            p.sendMessage(strings.kotl + ChatColor.GREEN + "" + ChatColor.BOLD + "Leaving" + ChatColor.RED + " King of the Ladder region!");
            told.remove(p.getUniqueId());
        }
        if (kotl.equalsIgnoreCase(p.getName()) && (!(kotl.equalsIgnoreCase(null)) && (!(kotl.equalsIgnoreCase("blank"))))) {
            return;
        }
        if (pLoc.equals(ladder)) {
            for (Player kotlPlayers : playersInRegion) {
                kotlPlayers.sendMessage(strings.kotl + ChatColor.GOLD + "" + ChatColor.BOLD + p.getName() + ChatColor.RED + " is now the King of the Ladder!");
                if (kotl.equalsIgnoreCase(kotlPlayers.getName())) {
                    TitleAPI.sendActionBar(kotlPlayers, ChatColor.RED + "You are no longer the King of the Ladder!");
                    secondsAsKOTL.remove(kotlPlayers.getUniqueId());
                }
            }
            p.sendMessage(strings.kotl + ChatColor.RED + "You are now the King of the Ladder!");
            kotl = p.getName();
            return;
        }

    }

    private boolean locationIsInCuboid(Player player) {
        Location loc = player.getLocation();
        if (loc.getX() > 95 && loc.getX() < 131) {
            if (loc.getY() > 4 && loc.getY() < 30) {
                if (loc.getZ() > -237 && loc.getZ() < -201) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }
}
//            TitleAPI.sendActionBar(p, ChatColor.RED + "You are now the King of the Ladder!");
//            TitleAPI.sendActionBar(kotlPlayers, ChatColor.GOLD + "" + ChatColor.BOLD + p.getName() + ChatColor.RED + " is now the King of the Ladder!");
//
//            if (!(kotl.equalsIgnoreCase(p.getName()))) {
//                for (Player kotlPlayers : playersInRegion) {
//                    kotlPlayers.sendMessage(strings.kotl + ChatColor.GOLD + "" + ChatColor.BOLD + p.getName() + ChatColor.RED + " is now the King of the Ladder!");
//                    if (kotl.equalsIgnoreCase(kotlPlayers.getName())) {
//                        TitleAPI.sendActionBar(kotlPlayers, ChatColor.RED + "You are no longer the King of the Hill!");
//                        secondsAsKOTL.remove(p.getUniqueId());
//                    }
////  TitleAPI.sendActionBar(kotlPlayers, ChatColor.GOLD + "" + ChatColor.BOLD + p.getName() + ChatColor.RED + " is now the King of the Ladder!");
//                }
//            }
//            p.sendMessage(strings.kotl + ChatColor.RED + "You are now the King of the Ladder!");
////                TitleAPI.sendActionBar(p, ChatColor.RED + "You are now the King of the Ladder!");
//            kotl = p.getName();

//
//    private void messageKOTL(Player p, Player l) {
//        if (locationIsInCuboid(p.getLocation(), box1, box2)) {
//            Location loc = p.getLocation();
//            loc.setY(loc.getY() - 1);
//            Block b = loc.getBlock();
//            Location kLoc = p.getLocation();
//            kLoc.setY(kLoc.getY() - 1);
//
//        }
//    }

// 131 4 -237 & 95 21 -201


//    public boolean locationIsInCuboid(Location playerLocation, Location min, Location max) {
//        boolean trueOrNot = false;
//        if (playerLocation.getWorld() == min.getWorld() && playerLocation.getWorld() == max.getWorld()) {
//            if (playerLocation.getX() >= min.getX() && playerLocation.getX() <= max.getX()) {
//                if (playerLocation.getY() >= min.getY() && playerLocation.getY() <= max.getY()) {
//                    if (playerLocation.getZ() >= min.getZ()
//                            && playerLocation.getZ() <= max.getZ()) {
//                        trueOrNot = true;
//                    }
//                }
//            }
//            if (playerLocation.getX() <= min.getX() && playerLocation.getX() >= max.getX()) {
//                if (playerLocation.getY() <= min.getY() && playerLocation.getY() >= max.getY()) {
//                    if (playerLocation.getZ() <= min.getZ()
//                            && playerLocation.getZ() >= max.getZ()) {
//                        trueOrNot = true;
//                    }
//                }
//            }
//        }
//        return trueOrNot;
//    }
