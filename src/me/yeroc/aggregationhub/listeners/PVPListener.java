package me.yeroc.aggregationhub.listeners;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.aggregationhub.Main;
import me.yeroc.aggregationhub.managers.PermissionsManager;
import me.yeroc.aggregationhub.managers.PlayerStatistics;
import me.yeroc.aggregationhub.managers.StringsManager;
import me.yeroc.aggregationhub.minigames.KOTL;
import me.yeroc.aggregationhub.utils.TitleAPI.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Corey on 9/11/2016.
 */
public class PVPListener implements Listener {
    Main plugin;

    public PVPListener(Main plugin) {
        this.plugin = plugin;
    }

    private StringsManager strings = StringsManager.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private PlayerStatistics pStats = PlayerStatistics.getInstance();
    private KOTL kotl = KOTL.getInstance();
    private ArrayList<Player> playersInRegion = new ArrayList<>();

    @EventHandler
    public void onAttacked(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player && e.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getEntity();
        Player a = (Player) e.getDamager();
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (locationIsInCuboid(players)) {
                playersInRegion.add(players);
            }
        }
        if (playersInRegion.contains(p)) {
            e.setCancelled(false);
            return;
        }
        if (a.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
            if (p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
                e.setCancelled(false);
            } else {
                TitleAPI.sendActionBar(a, ChatColor.RED + "That player does not have PVP Enabled!");
                e.setCancelled(true);
            }
            return;
        }
        TitleAPI.sendActionBar(a, ChatColor.RED + "You do not have PVP enabled! Hold your sword to enable PVP!");
        e.setCancelled(true);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerSwitchItems(PlayerItemHeldEvent e) {
        if (e.getNewSlot() == 8 && (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL))) {
            Player p = e.getPlayer();
            ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
            ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
            ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
            List<String> helmetLore = new ArrayList<>();
            ItemMeta helmetMeta = helmet.getItemMeta();
            helmetMeta.setDisplayName(ChatColor.RED + "PVPers Helmet!");
            helmetLore.add(ChatColor.DARK_PURPLE + "This helps protect your head when in those PVP battles!");
            helmetMeta.setLore(helmetLore);
            helmet.setItemMeta(helmetMeta);
            List<String> chestplateLore = new ArrayList<>();
            ItemMeta chestplateMeta = chestplate.getItemMeta();
            chestplateMeta.setDisplayName(ChatColor.RED + "PVPers Chestplate!");
            chestplateLore.add(ChatColor.DARK_PURPLE + "This helps protect your chest when in those PVP battles!");
            chestplateMeta.setLore(chestplateLore);
            chestplate.setItemMeta(chestplateMeta);
            List<String> leggingsLore = new ArrayList<>();
            ItemMeta leggingsMeta = leggings.getItemMeta();
            leggingsMeta.setDisplayName(ChatColor.RED + "PVPers Leggings!");
            leggingsLore.add(ChatColor.DARK_PURPLE + "This helps protect your legs when in those PVP battles!");
            leggingsMeta.setLore(leggingsLore);
            leggings.setItemMeta(leggingsMeta);
            List<String> bootsLore = new ArrayList<>();
            ItemMeta bootsMeta = boots.getItemMeta();
            bootsMeta.setDisplayName(ChatColor.RED + "PVPers Boots!");
            bootsLore.add(ChatColor.DARK_PURPLE + "This helps protect your feet when in those PVP battles!");
            bootsMeta.setLore(bootsLore);
            boots.setItemMeta(bootsMeta);
            p.getInventory().setHelmet(helmet);
            p.getInventory().setChestplate(chestplate);
            p.getInventory().setLeggings(leggings);
            p.getInventory().setBoots(boots);
            p.updateInventory();
            if (playersInRegion.contains(p)) {
                return;
            }
            p.sendMessage(ChatColor.GREEN + "PVP Enabled!");
            return;
        }
        if (e.getPreviousSlot() == 8 && (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL))) {
            Player p = e.getPlayer();
            ItemStack air = new ItemStack(Material.AIR);
            p.getInventory().setHelmet(air);
            p.getInventory().setChestplate(air);
            p.getInventory().setLeggings(air);
            p.getInventory().setBoots(air);
            if (playersInRegion.contains(p)) {
                return;
            }
            p.sendMessage(ChatColor.DARK_RED + "PVP Disabled!");
        } else {
            // do nothing
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player a = p.getKiller();
        pStats.addKills(a, p, 1);
        pStats.addDeaths(p, 1);
        e.setDeathMessage(null);
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (p.hasPermission(perms.Hub_PVP)) {
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
            List<String> swordLore = new ArrayList<>();
            ItemMeta swordMeta = sword.getItemMeta();
            swordMeta.setDisplayName(ChatColor.RED + "Enable PVP!");
            swordLore.add(ChatColor.DARK_PURPLE + "Hold this in your hand to enable PvP!");
            swordMeta.setLore(swordLore);
            sword.setItemMeta(swordMeta);
            p.getInventory().setItem(8, sword);
            p.updateInventory();
//            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {
//                @Override
//                public void run() {
//                    if (p.getItemInHand().equals(Material.DIAMOND_SWORD) && (p.getGameMode().equals(GameMode.SURVIVAL) && (p.getInventory().getBoots().equals(Material.AIR)))) {
//                        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);

//                        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
//                        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
//                        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
//                        List<String> helmetLore = new ArrayList<>();
//                        ItemMeta helmetMeta = helmet.getItemMeta();
//                        helmetMeta.setDisplayName(ChatColor.RED + "PVPers Helmet!");
//                        helmetLore.add(ChatColor.DARK_PURPLE + "This helps protect your head when in those PVP battles!");
//                        helmetMeta.setLore(helmetLore);
//                        helmet.setItemMeta(helmetMeta);
//                        List<String> chestplateLore = new ArrayList<>();
//                        ItemMeta chestplateMeta = chestplate.getItemMeta();
//                        chestplateMeta.setDisplayName(ChatColor.RED + "PVPers Chestplate!");
//                        chestplateLore.add(ChatColor.DARK_PURPLE + "This helps protect your chest when in those PVP battles!");
//                        chestplateMeta.setLore(chestplateLore);
//                        chestplate.setItemMeta(chestplateMeta);
//                        List<String> leggingsLore = new ArrayList<>();
//                        ItemMeta leggingsMeta = leggings.getItemMeta();
//                        leggingsMeta.setDisplayName(ChatColor.RED + "PVPers Leggings!");
//                        leggingsLore.add(ChatColor.DARK_PURPLE + "This helps protect your legs when in those PVP battles!");
//                        leggingsMeta.setLore(leggingsLore);
//                        leggings.setItemMeta(leggingsMeta);
//                        List<String> bootsLore = new ArrayList<>();
//                        ItemMeta bootsMeta = boots.getItemMeta();
//                        bootsMeta.setDisplayName(ChatColor.RED + "PVPers Boots!");
//                        bootsLore.add(ChatColor.DARK_PURPLE + "This helps protect your feet when in those PVP battles!");
//                        bootsMeta.setLore(bootsLore);
//                        boots.setItemMeta(bootsMeta);
//                        p.sendMessage(ChatColor.GREEN + "PVP Enabled!");
//                    } else {
//                        // do nothing
//                    }
//                }
//            }, 10, 0);
        }
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
