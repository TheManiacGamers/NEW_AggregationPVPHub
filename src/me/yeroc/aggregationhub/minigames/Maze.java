package me.yeroc.aggregationhub.minigames;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.aggregationhub.Main;
import me.yeroc.aggregationhub.managers.StringsManager;
import me.yeroc.aggregationhub.utils.TitleAPI.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.NameTagVisibility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Corey on 3/02/2018.
 */
public class Maze implements Listener {

    private Main plugin;
    public static Maze instance = new Maze();

    public Maze(Main main) {
    }

    public Maze() {

    }

    public static Maze getInstance() {
        return instance;
    }

    private StringsManager strings = StringsManager.getInstance();

    public static HashMap<UUID, Integer> seconds = new HashMap<UUID, Integer>();
    //    public static HashMap<UUID, String> started = new HashMap<UUID, String>();
//    public static HashMap<UUID, String> found = new HashMap<UUID, String>();
    public ArrayList<UUID> started = new ArrayList<UUID>();
    public ArrayList<UUID> found = new ArrayList<UUID>();

    private Location start1 = new Location(Bukkit.getWorld("world"), 129, 0, -233);
    private Location start2 = new Location(Bukkit.getWorld("world"), 128, 0, -234);
    private Location start3 = new Location(Bukkit.getWorld("world"), 127, 0, -235);

    private Location leave1 = new Location(Bukkit.getWorld("world"), 130, 1, -233);
    private Location leave2 = new Location(Bukkit.getWorld("world"), 129, 0, -235);
    private Location leave3 = new Location(Bukkit.getWorld("world"), 128, 0, -235);
    private Location leave4 = new Location(Bukkit.getWorld("world"), 127, 1, -236);
    private Location leave5 = new Location(Bukkit.getWorld("world"), 130, 1, -234);
    private Location leave6 = new Location(Bukkit.getWorld("world"), 128, 1, -236);

    private Location end1 = new Location(Bukkit.getWorld("world"), 111, 0, -217);
    private Location end2 = new Location(Bukkit.getWorld("world"), 115, 3, -221);

    private Location goldBlock = new Location(Bukkit.getWorld("world"), 113, 1, -219);

    // 129 0 -233 & 128 0 -234 & 127 0 -235 - STARTING TIME
    // 130 1 -233 & 129 0 -235 & 128 0 -235 & 127 1 -236 & 130 1 -234 & 128 1 -236 - FINISH TIME / PLAYER LEFT MAZE AREA
    // 111 0 -217 & 115 3 -221 - COMPLETED MAZE / INSIDE OF CUBOID
    // 113 1 -219 - GOLD BLOCK

    public void addSeconds(Player p) {
        if (!(started.contains(p.getUniqueId()))) {
            return;
        }
        if (started.contains(p.getUniqueId())) {
            seconds.put(p.getUniqueId(), seconds.get(p.getUniqueId()) + 1);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (started.contains(p.getUniqueId())) {
            started.remove(p.getUniqueId());
        }
        if (started.contains(p.getUniqueId())) {
            found.remove(p.getUniqueId());
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Block b = e.getClickedBlock();
            if (b.getType().equals(Material.GOLD_BLOCK)) {
                if (b.getLocation().equals(goldBlock)) {
                    if (started.contains(p.getUniqueId()) && found.contains(p.getUniqueId())) {
                        p.sendMessage(strings.maze + ChatColor.GREEN + "Congratulations! Rewards are currently not implemented.");
                        started.remove(p.getUniqueId());
                        found.remove(p.getUniqueId());
                        p.performCommand("spawn");
                    } else {
                        Main.log(strings.maze + p.getName() + " found Gold Block but must not have gone through the start.");
                    }
                }
            }
        }
    }

    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location playerLoc = p.getLocation();
        Block b = playerLoc.getBlock().getRelative(0, -1, 0);
        Location pLoc = b.getLocation();
        if (pLoc.equals(start1) || (pLoc.equals(start2)) || (pLoc.equals(start3))) {
            if (!(started.contains(p.getUniqueId()))) {
                p.sendMessage(strings.maze + ChatColor.GREEN + "Entering the Maze. Good luck!");
                started.add(p.getUniqueId());
            }
            return;
        }
        if (pLoc.equals(leave1) || (pLoc.equals(leave2)) || (pLoc.equals(leave3) || (pLoc.equals(leave4) || (pLoc.equals(leave5) || (pLoc.equals(leave6)))))) {
            if (started.contains(p.getUniqueId())) {
                p.sendMessage(strings.maze + ChatColor.RED + "Leaving the Maze. Nice try!");
                started.remove(p.getUniqueId());
                if (found.contains(p.getUniqueId())) {
                    found.remove(p.getUniqueId());
                }
            }
            return;
        }
        if (locationIsInCuboid(p)) {
            if (started.contains(p.getUniqueId())) {
                if (found.contains(p.getUniqueId())) {
                    return;
                }
                found.add(p.getUniqueId());
                p.sendMessage(strings.maze + ChatColor.GREEN + "Congratulations, you found the end of the Maze!");
                p.sendMessage(strings.maze + ChatColor.GREEN + "Right click the Gold Block for your Reward!");
            }
        }
    }

    public Integer getSeconds(Player p) {
        if (seconds.get(p.getUniqueId()) == null) {
            return 0;
        }
        return seconds.get(p.getUniqueId());
    }

    private boolean locationIsInCuboid(Player player) {
        Location loc = player.getLocation();
        if (loc.getX() > 111 && loc.getX() < 115) {
            if (loc.getY() > 0 && loc.getY() < 3) {
                if (loc.getZ() > -222 && loc.getZ() < -216) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }
}
