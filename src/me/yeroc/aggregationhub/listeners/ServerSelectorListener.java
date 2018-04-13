package me.yeroc.aggregationhub.listeners;

/**
 * Created by Corey on 10/02/2018.
 */

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.aggregationhub.Main;
import me.yeroc.aggregationhub.managers.PermissionsManager;
import me.yeroc.aggregationhub.managers.StringsManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Corey on 9/11/2016.
 */
public class ServerSelectorListener implements Listener {
    Main plugin;

    public ServerSelectorListener(Main plugin) {
        this.plugin = plugin;
    }

    private StringsManager strings = StringsManager.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private static Inventory serverSelector = Bukkit.createInventory(null, 9, ChatColor.GREEN + "" + ChatColor.BOLD + "Server Selector!");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Material CompassM = Material.COMPASS;
        String CompassName = ChatColor.GREEN + "" + ChatColor.BOLD + "Server Selector";
        ItemStack Compass = new ItemStack(CompassM, 1);
        ItemMeta CompassMeta = Compass.getItemMeta();
        CompassMeta.setDisplayName(CompassName);
        CompassMeta.setLore(Arrays.asList(ChatColor.AQUA + "Interact with me to change your server!", ChatColor.BLUE + "" + ChatColor.BOLD + "Servers:", ChatColor.AQUA + "" + ChatColor.BOLD + " Half A Heart", ChatColor.AQUA + "" + ChatColor.BOLD + " Factions", ChatColor.AQUA + "" + ChatColor.BOLD + " Prison", ChatColor.AQUA + "" + ChatColor.BOLD + " Creative"));
        Compass.setItemMeta(CompassMeta);
        e.getPlayer().getInventory().setItem(4, Compass);
        e.getPlayer().updateInventory();
    }

    @EventHandler
    public void onInteractGUI(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Material DiamondSword = Material.DIAMOND_SWORD;
        String HalfAHeartName = ChatColor.GREEN + "" + ChatColor.BOLD + "Half A Heart";
        ItemStack HalfAHeart = new ItemStack(DiamondSword, 1);
        ItemMeta HalfAHeartMeta = HalfAHeart.getItemMeta();
        HalfAHeartMeta.setDisplayName(HalfAHeartName);
        HalfAHeartMeta.setLore(Arrays.asList(ChatColor.RED + "Click me to go to Half A Heart!"));
        HalfAHeart.setItemMeta(HalfAHeartMeta);

        Material IronFence = Material.IRON_FENCE;
        String PrisonName = ChatColor.GOLD + "" + ChatColor.BOLD + "Prison";
        ItemStack Prison = new ItemStack(IronFence, 1);
        ItemMeta PrisonMeta = Prison.getItemMeta();
        PrisonMeta.setDisplayName(PrisonName);
        PrisonMeta.setLore(Arrays.asList(ChatColor.RED + "Click me to go to Prison!"));
        Prison.setItemMeta(PrisonMeta);

        Material NetherStar = Material.NETHER_STAR;
        String CreativeName = ChatColor.GOLD + "" + ChatColor.BOLD + "Creative";
        ItemStack Creative = new ItemStack(NetherStar, 1);
        ItemMeta CreativeMeta = Creative.getItemMeta();
        CreativeMeta.setDisplayName(CreativeName);
        CreativeMeta.setLore(Arrays.asList(ChatColor.RED + "Click me to go to Creative!")); //
        Creative.setItemMeta(CreativeMeta);

        Material GoldSword = Material.GOLD_SWORD;
        String FactionsName = ChatColor.GOLD + "" + ChatColor.BOLD + "Factions";
        ItemStack Factions = new ItemStack(GoldSword, 1);
        ItemMeta FactionsMeta = Factions.getItemMeta();
        FactionsMeta.setDisplayName(FactionsName);
        FactionsMeta.setLore(Arrays.asList(ChatColor.RED + "Click me to go to Factions!"));//
        Factions.setItemMeta(FactionsMeta);


        // OLD LORES (added to current ones)
        // SURVIVAL: ChatColor.BLUE + "" + ChatColor.BOLD + "Awesome Survival", ChatColor.AQUA + "" + ChatColor.BOLD + "Customly Designed Items", ChatColor.AQUA + "" + ChatColor.BOLD + "Non-PvP!"));
        // CREATIVE: ChatColor.BLUE + "" + ChatColor.BOLD + "Want awesome plots? 99x99 wide?", ChatColor.AQUA + "" + ChatColor.BOLD + "Everything right at your fingertips!", ChatColor.AQUA + "" + ChatColor.BOLD + "Roleplays!"));
        // FACTIONS: ChatColor.BLUE + "" + ChatColor.BOLD + "Protect yourrseelllff!", ChatColor.AQUA + "" + ChatColor.BOLD + "Dominate the world!", ChatColor.AQUA + "" + ChatColor.BOLD + "PvP enabled!", ChatColor.AQUA + "Includes Custom Items!"));
        // PRISON: ChatColor.BLUE + "" + ChatColor.BOLD + "Mine Mine Miiineee!", ChatColor.AQUA + "" + ChatColor.BOLD + "Custom Pickaxe - Custom Currency", ChatColor.AQUA + "" + ChatColor.BOLD + "Potion Effects Everywhere!"));

        Material GlassPane2 = Material.STAINED_GLASS_PANE;
        String NoServerName = ChatColor.RED + "" + ChatColor.BOLD + "No Server Here";
        ItemStack NoServer = new ItemStack(GlassPane2, 1);
        ItemMeta NoServerMeta = NoServer.getItemMeta();
        NoServerMeta.setDisplayName(NoServerName);
        NoServerMeta.setLore(Arrays.asList(ChatColor.GOLD + "Try selecting a different server!", ChatColor.BLUE + "" + ChatColor.BOLD + "Servers:", ChatColor.AQUA + "" + ChatColor.BOLD + " Half A Heart", ChatColor.AQUA + "" + ChatColor.BOLD + " Factions", ChatColor.AQUA + "" + ChatColor.BOLD + " Prison", ChatColor.AQUA + "" + ChatColor.BOLD + " Creative"));
        NoServer.setItemMeta(NoServerMeta);
        if (p.getInventory().getItemInMainHand().getType() != null && (p.getInventory().getItemInMainHand().getType() == Material.COMPASS)) {
            if (p.hasPermission(perms.Hub_Server)) {
//                serverSelector.setItem(0, NoServer);
                serverSelector.setItem(1, HalfAHeart);
//                serverSelector.setItem(2, NoServer);
                serverSelector.setItem(3, Prison);
//                serverSelector.setItem(4, NoServer);
                serverSelector.setItem(5, Creative);
//                serverSelector.setItem(6, NoServer);
                serverSelector.setItem(7, Factions);
//                serverSelector.setItem(8, NoServer);
                p.openInventory(serverSelector);
//                p.sendMessage(strings.defaultMsgs + "Server Selector is currently under development, check back later!");
                e.setCancelled(true);
            } else {
                e.getPlayer().sendMessage(strings.noPermissionInteract);
                e.setCancelled(true);
            }
        }
        // not an item that can be interacted with (right clicking)

    }

    public void sendToServer(Player p, String targetServer) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(targetServer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
//        if (event.getClickedInventory() == null || event.getClickedInventory().getName() == null) {
//            return;
//        }
        if (event.getClickedInventory().equals(serverSelector)) {
            if (clicked.getType().equals(Material.NETHER_STAR)) {
                if (p.hasPermission(perms.Hub_Server_Creative)) {
                    event.setCancelled(true);
                    p.sendMessage(strings.defaultMsgs + "Sending you to Creative!");
                    sendToServer(p, "Creative");
                    return;
                } else {
                    p.sendMessage(strings.defaultMsgs + strings.noPermissionInteract);
                }
            }
            if (clicked.getType().equals(Material.DIAMOND_SWORD)) {
                if (p.hasPermission(perms.Hub_Server_HalfAHeart)) {
                    event.setCancelled(true);
                    p.sendMessage(strings.defaultMsgs + "Sending you to Half A Heart!");
                    sendToServer(p, "Hah");
                    return;
                } else {
                    p.sendMessage(strings.defaultMsgs + strings.noPermissionInteract);
                    p.closeInventory();
                }
            }
            if (clicked.getType().equals(Material.IRON_FENCE)) {
                if (p.hasPermission(perms.Hub_Server_Prison)) {
                    event.setCancelled(true);
                    p.sendMessage(strings.defaultMsgs + "Sending you to Prison!");
                    sendToServer(p, "Prison");
                    return;
                } else {
                    p.sendMessage(strings.defaultMsgs + strings.noPermissionInteract);
                    p.closeInventory();
                }
            }
            if (clicked.getType().equals(Material.GOLD_SWORD)) {
                if (p.hasPermission(perms.Hub_Server_Factions)) {
                    event.setCancelled(true);
                    p.sendMessage(strings.defaultMsgs + "Sending you to Factions!");
                    sendToServer(p, "Factions");
                    return;
                } else {
                    p.sendMessage(strings.defaultMsgs + strings.noPermissionInteract);
                }
            }
            p.sendMessage(strings.defaultMsgs + ChatColor.RED + "Try selecting a different item!");
            event.setCancelled(true);
//        } else {
//            if (Hub.buildable.get(p.getUniqueId()).equalsIgnoreCase("No")) {
//                event.setCancelled(true);
//                p.sendMessage(strings.defaultMsgs + ChatColor.RED + "You can't click this item.");
//                if (p.hasPermission("Hub.Build")) {
//                    p.sendMessage(strings.defaultMsgs + ChatColor.RED + "It looks like you have permission to do /build, which allows you to interact with your inventory!");
//                }
//            }
//        }
        } else {
            if (p.getGameMode().equals(GameMode.SURVIVAL)) {
                event.setCancelled(true);
            }
//        }
//        if (clicked.getType().equals(Material.COMPASS)) {
//            event.setCancelled(true);
//            return;
//        }
//        if (clicked.getType().equals(Material.DIAMOND_SWORD)) {
//            event.setCancelled(true);
//            return;
//        }
//        if (clicked.getType().equals(Material.BOW)) {
//            event.setCancelled(true);
//            return;
//        }
//        if (clicked.getType().equals(Material.ARROW)) {
//            event.setCancelled(true);
//            return;
//        }
//        if (clicked.getType().equals(Material.ENDER_CHEST)) {
//            event.setCancelled(true);
//            return;
//        }
//        if (clicked.getType().equals(Material.ARROW)) {
//            event.setCancelled(true);
//        }
        }

    }
}