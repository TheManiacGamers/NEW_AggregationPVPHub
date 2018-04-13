package me.yeroc.aggregationhub.listeners;

import com.sk89q.minecraft.util.commands.ChatColor;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import me.yeroc.aggregationhub.Main;
import me.yeroc.aggregationhub.managers.PermissionsManager;
import me.yeroc.aggregationhub.managers.PlayerStatistics;
import me.yeroc.aggregationhub.managers.Scoreboard;
import me.yeroc.aggregationhub.managers.StringsManager;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static me.yeroc.aggregationhub.Main.econ;
import static org.bukkit.Bukkit.getServer;

/**
 * Created by Corey on 4/2/2016.
 */
public class PlayerListener implements Listener {
    public HashMap<Player, String> stats = new HashMap<Player, String>();
    private Main plugin;
    private StringsManager strings = StringsManager.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private PlayerStatistics pStats = PlayerStatistics.getInstance();
    private Scoreboard scoreboard = Scoreboard.getInstance();

    private boolean enabled = true;

    double kills;
    double deaths;

    public PlayerListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission(perms.Hub_Leave_Notify)) {
            e.setQuitMessage(strings.quit + p.getName());
        } else {
            e.setQuitMessage(null);
        }
        p.getInventory().clear();
        pStats.save(p);
        Bukkit.getServer().dispatchCommand(p, "tospawn");
        scoreboard.setScoreboard(p);
    }

    @EventHandler()
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (!(econ.hasAccount(p))) {
            econ.createPlayerAccount(p);
        }
        p.setMaxHealth(20D);
        p.setGameMode(GameMode.SURVIVAL);
        if (p.hasPermission(perms.Hub_Join_Notify)) {
            e.setJoinMessage(strings.join + p.getName());
        } else {
            e.setJoinMessage(null);
        }
        pStats.load(p);
        pStats.addJoins(p, 1);
        applyAttackSpeed(p);
        scoreboard.setScoreboard(p);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        p.setCollidable(false);
    }

    @EventHandler
    public void onBlockDrop(BlockBreakEvent e) {
        e.setDropItems(false);
        e.getBlock().getDrops().clear();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        List<ItemStack> items = e.getDrops();
        e.setKeepInventory(true);
        for (ItemStack item : items) {
            p.getInventory().addItem(item);
            if (item.getType() == Material.DIAMOND_SWORD) {
                p.getInventory().setItem(8, item);
            }
            if (item.getType() == Material.ENDER_CHEST) {
                p.getInventory().setItem(4, item);
            }
        }
        p.performCommand("tospawn");
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Location demonCave = new Location(Bukkit.getWorld("world"), 250, 25, -392);
        Location waterHole = new Location(Bukkit.getWorld("world"), 98, 9, -391);
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getEntity();
        Location loc = p.getLocation();
        loc.setY(loc.getY() - 1);
        Block b = loc.getBlock();
        if (b.getLocation().equals(demonCave) && b.getType() == Material.NETHERRACK) {
            e.setCancelled(false);
            return;
        }
        if (b.getLocation().equals(waterHole) && b.getType() == Material.STONE) {
            e.setCancelled(false);
            return;
        }
        e.setCancelled(true);
        p.setFireTicks(0);
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            e.setCancelled(true);
            return;
        }
        e.setCancelled(false);
    }

    private Location inTheTree = new Location(Bukkit.getWorld("world"), 215.5, 3, -298.5);
    private Location islandTree = new Location(Bukkit.getWorld("world"), 149.5, 6, -297.5);
    private Location skyblockTree = new Location(Bukkit.getWorld("world"), 68.5, 27, -260.5);
    private Location ultimateView = new Location(Bukkit.getWorld("world"), 162.5, 93, -184.5);
    private Location theWaterfall = new Location(Bukkit.getWorld("world"), 93.5, 29, -268.5);
    private Location theWindmill = new Location(Bukkit.getWorld("world"), 114.5, 30, -359.5);
    private Location rooftop = new Location(Bukkit.getWorld("world"), 185.5, 48, -388.5);
    private Location theMushy = new Location(Bukkit.getWorld("world"), 66.5, 15, -268.5);
    private Location cobblestoneGenerator = new Location(Bukkit.getWorld("world"), 84.5, 31, -262.5);
    private Location demonCave = new Location(Bukkit.getWorld("world"), 249.5, 30, -387.5);
    private Location waterHole = new Location(Bukkit.getWorld("world"), 99.5, 19, -385.5);
    private Location rootPortal = new Location(Bukkit.getWorld("world"), 82.5, 3, -262.5);
    private Location stonehenge = new Location(Bukkit.getWorld("world"), 117.5, 7, -242.5);
    private Location underTheBridge = new Location(Bukkit.getWorld("world"), 137.5, 0, -263.5);
    private Location skyblockSign = new Location(Bukkit.getWorld("world"), 89.5, 73, -267.5);
    private Location prisonTower = new Location(Bukkit.getWorld("world"), 291.5, 42, -274.5);
    private Location prisonCell = new Location(Bukkit.getWorld("world"), 315.5, 21, -219.5);
    private Location wheatFarm = new Location(Bukkit.getWorld("world"), 74.5, 5, -316.5);
    private Location pyramidObserver = new Location(Bukkit.getWorld("world"), 241.5, 4, -358.5);
    private Location farmhouse = new Location(Bukkit.getWorld("world"), 116, 9, -307.5);

    @EventHandler
    public void onPlayerTPEasterEgg(PlayerInteractEvent e) {
        List<String> list = new ArrayList<>();
        Player p = e.getPlayer();
        if (!(e.getAction().equals(Action.PHYSICAL))) {
            return;
        }
        if (!(e.getClickedBlock().getType() == Material.GOLD_PLATE)) {
            return;
        }
        Location loc = p.getLocation();
        loc.setY(loc.getY());
        Block b = loc.getBlock();
        if (b.getLocation().equals(inTheTree)
                || b.getLocation().equals(islandTree)
                || (b.getLocation().equals(skyblockTree)
                || (b.getLocation().equals(ultimateView)
                || (b.getLocation().equals(theWaterfall)
                || (b.getLocation().equals(theWindmill)
                || (b.getLocation().equals(rooftop)
                || (b.getLocation().equals(theMushy)
                || (b.getLocation().equals(cobblestoneGenerator)
                || (b.getLocation().equals(demonCave)
                || (b.getLocation().equals(waterHole)
                || (b.getLocation().equals(rootPortal)
                || (b.getLocation().equals(stonehenge)
                || (b.getLocation().equals(underTheBridge)
                || (b.getLocation().equals(skyblockSign)
                || (b.getLocation().equals(prisonTower)
                || (b.getLocation().equals(prisonCell)
                || (b.getLocation().equals(wheatFarm)
                || (b.getLocation().equals(pyramidObserver)
                || (b.getLocation().equals(farmhouse)))))))))))))))))))) {
            return;
        }
        if (!(p.isSneaking())) {
            return;
        }
        for (String s : pStats.easterEggsFound.get(p.getUniqueId())) {
            list.add(s);
        }
        if (pStats.easterEggsFound.get(p.getUniqueId()).size() == 0) {
            p.sendMessage(strings.defaultMsgs + ChatColor.RED + "You have not found any Easter Eggs. Go find some first before using this!");
            return;
        }
        Random random = new Random();
        String randomLocation = list.get(random.nextInt(pStats.easterEggsFound.get(p.getUniqueId()).size()));

        if (randomLocation.equalsIgnoreCase("In The Tree")) {
            inTheTree.setY(inTheTree.getY() + 1);
            p.teleport(inTheTree);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Island Tree")) {
            islandTree.setY(islandTree.getY() + 1);
            p.teleport(islandTree);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Skyblock Tree")) {
            skyblockTree.setY(skyblockTree.getY() + 1);
            p.teleport(skyblockTree);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Ultimate View")) {
            ultimateView.setY(ultimateView.getY() + 1);
            p.teleport(ultimateView);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("The Waterfall")) {
            theWaterfall.setY(theWaterfall.getY() + 1);
            p.teleport(theWaterfall);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("The Windmill")) {
            theWindmill.setY(theWindmill.getY() + 1);
            p.teleport(theWindmill);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Rooftop")) {
            rooftop.setY(rooftop.getY() + 1);
            p.teleport(rooftop);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("The Mushy")) {
            theMushy.setY(theMushy.getY() + 1);
            p.teleport(theMushy);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Cobblestone Generator")) {
            cobblestoneGenerator.setY(cobblestoneGenerator.getY() + 1);
            p.teleport(cobblestoneGenerator);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Demon Cave")) {
            demonCave.setY(demonCave.getY() + 1);
            p.teleport(demonCave);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Water Hole")) {
            waterHole.setY(waterHole.getY() + 1);
            p.teleport(waterHole);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Root Portal")) {
            rootPortal.setY(rootPortal.getY() + 1);
            p.teleport(rootPortal);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Stonehenge")) {
            stonehenge.setY(stonehenge.getY() + 1);
            p.teleport(stonehenge);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Under The Bridge")) {
            underTheBridge.setY(underTheBridge.getY() + 1);
            p.teleport(underTheBridge);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Skyblock Sign")) {
            skyblockSign.setY(skyblockSign.getY() + 1);
            p.teleport(skyblockSign);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Prison Tower")) {
            prisonTower.setY(prisonTower.getY() + 1);
            p.teleport(prisonTower);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Prison Cell")) {
            prisonCell.setY(prisonCell.getY() + 1);
            p.teleport(prisonCell);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Wheat Farm")) {
            wheatFarm.setY(wheatFarm.getY() + 1);
            p.teleport(wheatFarm);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Pyramid Observer")) {
            pyramidObserver.setY(pyramidObserver.getY() + 1);
            p.teleport(pyramidObserver);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        }
        if (randomLocation.equalsIgnoreCase("Farmhouse")) {
            farmhouse.setY(farmhouse.getY() + 1);
            p.teleport(farmhouse);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Teleporting you to: " + ChatColor.YELLOW + "" + ChatColor.BOLD + randomLocation);
            return;
        } else {
            p.sendMessage(strings.defaultMsgs + ChatColor.RED + "Unknown Error. Perhaps " + randomLocation + " hasn't been set yet?");
        }

    }

    public static void checkEntity() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("AggregationHub"), new BukkitRunnable() {
            @Override
            public void run() {
                World world = getServer().getWorld("world");
                List<Entity> entList = world.getEntities();

                for (Entity current : entList) {
                    if (current.getType() == EntityType.DROPPED_ITEM) {
                        current.remove();
                    }
                }
            }
        }, 0L, 20L);
    }

    @EventHandler
    public void onEntityInWorld(EntitySpawnEvent e) {
        World world = getServer().getWorld("world");
        List<Entity> entList = world.getEntities();

        for (Entity current : entList) {
            if (current.getType() == EntityType.DROPPED_ITEM) {
                current.remove();
            }
        }
    }

    private void applyAttackSpeed(Player p) {
        p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(enabled ? 1024.0D : 4.0D);
        p.saveData();
    }

    @EventHandler
    public void onLoseHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }


//                Location loc = p.getLocation();
//                loc.setY(loc.getY() - 1);
//                if (!(loc.getBlock().getType() == Material.NETHERRACK)) {
//                    e.setCancelled(true);
//                } else {
//                    Block b = loc.getBlock();
//                    if (!(b.getLocation().equals(demonCave))) {
//                        b.getLocation().setY(+1);
//                        e.setCancelled(true);
//                    }
//                    return;
//                }
//
//                if (!(loc.getBlock().getType() == Material.STONE)) {
//                    e.setCancelled(true);
//                } else {
//                    Block b = loc.getBlock();
//                    b.getLocation().setY(+1);
//                    if (!(b.getLocation().equals(demonCave))) {
//                        e.setCancelled(true);
//                    }
//                    return;
//                }
//                return;
//
//                if (!(p.getLocation().getBlockX() == 250 && p.getLocation().getBlockY() == 25 && p.getLocation().getBlockZ() == -392)) {
//                    return;
//                }
//                if (!(p.getLocation().getBlockX() == 98 && p.getLocation().getBlockY() == 10 && p.getLocation().getBlockZ() == -391)) {
//                    e.setCancelled(true);
//                    return;
//                }
//                e.setCancelled(false);
//                return;
//    }


}