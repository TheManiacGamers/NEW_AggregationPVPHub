package me.yeroc.aggregationhub.listeners;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.aggregationhub.Main;
import me.yeroc.aggregationhub.utils.TitleAPI.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Corey on 7/02/2018.
 */
public class DoubleJump implements Listener {

    public DoubleJump(Main main) {
    }

    public static HashMap<UUID, String> canDoubleJump = new HashMap<>();
    private ArrayList<Player> playersInRegion = new ArrayList<>();

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player p = event.getPlayer();
        if (p.getGameMode() == GameMode.SURVIVAL) {
            for (Player onlineP : Bukkit.getOnlinePlayers()) {
                if (locationIsInCuboid(onlineP)) {
                    playersInRegion.add(onlineP);
                } else {
                    playersInRegion.remove(onlineP);
                }
            }
            if (playersInRegion.contains(p)) {
                return;
            }
            if (canDoubleJump.get(p.getUniqueId()).equalsIgnoreCase("Yes")) {
                if (p.hasPermission("DoubleJump.use") && (!(playersInRegion.contains(p)))) { //
                    p.setVelocity(p.getLocation().getDirection().multiply(3.5).setY(1.5));
                    event.setCancelled(true);
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    return;
                }
                event.setCancelled(true);
                p.setAllowFlight(false);
                p.setFlying(false);
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        p.setCollidable(false);
        for (Player onlineP : Bukkit.getOnlinePlayers()) {
            if (locationIsInCuboid(onlineP)) {
                playersInRegion.add(onlineP);
            } else {
                playersInRegion.remove(onlineP);
            }
        }
        if (playersInRegion.contains(p)) {
            return;
        }
        if ((p.getGameMode() == GameMode.SURVIVAL) && (p.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) && (!p.isFlying())) {
            if (canDoubleJump.get(p.getUniqueId()).equalsIgnoreCase("Yes")) {
                if (playersInRegion.contains(p)) {
                    return;
                }
                p.setAllowFlight(true);
            } else {
                p.setAllowFlight(false);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        canDoubleJump.put(p.getUniqueId(), "Yes");
    }

    private boolean locationIsInCuboid(Player player) {
        Location loc = player.getLocation();
        if (loc.getX() > 95 && loc.getX() < 131) {
            if (loc.getY() > 4 && loc.getY() < 21) {
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