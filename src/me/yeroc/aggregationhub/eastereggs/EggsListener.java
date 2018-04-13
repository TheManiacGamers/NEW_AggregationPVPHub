package me.yeroc.aggregationhub.eastereggs;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.aggregationhub.Main;
import me.yeroc.aggregationhub.managers.ConfigsManager;
import me.yeroc.aggregationhub.managers.PlayerStatistics;
import me.yeroc.aggregationhub.utils.TitleAPI.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.io.File;
import java.io.IOException;


/**
 * Created by Corey on 26/01/2018.
 */
public class EggsListener implements Listener {

//    Locations locations = getInstance();

    public EggsListener(Main main) {
    }

    private PlayerStatistics pStats = PlayerStatistics.getInstance();
    private ConfigsManager configs = ConfigsManager.getInstance();

    private World w = Bukkit.getWorld("world");

//    private double inTheTree_X = configs.getCheckpoints().getDouble("In The Tree.X");
//    private double inTheTree_Y = configs.getCheckpoints().getDouble("In The Tree.Y");
//    private double inTheTree_Z = configs.getCheckpoints().getDouble("In The Tree.Z");
//    private Material inTheTree_Block = Material.getMaterial(configs.getCheckpoints().getString("In The Tree.Blockname"));
//    private Location inTheTree = new Location(w, inTheTree_X, inTheTree_Y, inTheTree_Z, 0F, 0F);
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    private double islandTree_X = configs.getCheckpoints().getDouble("Island Tree.X");
//    private double islandTree_Y = configs.getCheckpoints().getDouble("Island Tree.Y");
//    private double islandTree_Z = configs.getCheckpoints().getDouble("Island Tree.Z");
//    private Material islandTree_Block = Material.getMaterial(configs.getCheckpoints().getString("Island Tree.Blockname"));
//    private Location islandTree = new Location(w, islandTree_X, islandTree_Y, islandTree_Z, 0F, 0F);
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    private double skyblockTree_X = configs.getCheckpoints().getDouble("Skyblock Tree.X");
//    private double skyblockTree_Y = configs.getCheckpoints().getDouble("Skyblock Tree.Y");
//    private double skyblockTree_Z = configs.getCheckpoints().getDouble("Skyblock Tree.Z");
//    private Material skyblockTree_Block = Material.getMaterial(configs.getCheckpoints().getString("Skyblock Tree.Blockname"));
//    private Location skyblockTree = new Location(w, skyblockTree_X, skyblockTree_Y, skyblockTree_Z, 0F, 0F);
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    private double ultimateView_X = configs.getCheckpoints().getDouble("Ultimate View.X");
//    private double ultimateView_Y = configs.getCheckpoints().getDouble("Ultimate View.Y");
//    private double ultimateView_Z = configs.getCheckpoints().getDouble("Ultimate View.Z");
//    private Material ultimateView_Block = Material.getMaterial(configs.getCheckpoints().getString("Ultimate View.Blockname"));
//    private Location ultimateView = new Location(w, ultimateView_X, ultimateView_Y, ultimateView_Z, 0F, 0F);
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    private double theWaterfall_X = configs.getCheckpoints().getDouble("The Waterfall.X");
//    private double theWaterfall_Y = configs.getCheckpoints().getDouble("The Waterfall.Y");
//    private double theWaterfall_Z = configs.getCheckpoints().getDouble("The Waterfall.Z");
//    private Material theWaterfall_Block = Material.getMaterial(configs.getCheckpoints().getString("The Waterfall.Blockname"));
//    private Location theWaterfall = new Location(w, theWaterfall_X, theWaterfall_Y, theWaterfall_Z, 0F, 0F);
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    private double theWindmill_X = configs.getCheckpoints().getDouble("The Windmill.X");
//    private double theWindmill_Y = configs.getCheckpoints().getDouble("The Windmill.Y");
//    private double theWindmill_Z = configs.getCheckpoints().getDouble("The Windmill.Z");
//    private Material theWindmill_Block = Material.getMaterial(configs.getCheckpoints().getString("The Windmill.Blockname"));
//    private Location theWindmill = new Location(w, theWindmill_X, theWindmill_Y, theWindmill_Z, 0F, 0F);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    private Location rooftop = new Location(Bukkit.getWorld("world"), 185, 47, -389);
//    private Location theMushy = new Location(Bukkit.getWorld("world"), 66, 15, -269);
//    private Location cobblestoneGenerator = new Location(Bukkit.getWorld("world"), 84, 31, -263);
//    private Location demonCave = new Location(Bukkit.getWorld("world"), 249, 30, -388);
//    private Location waterHole = new Location(Bukkit.getWorld("world"), 99, 19, -386);
//    private Location rootPortal = new Location(Bukkit.getWorld("world"), 82, 3, -263);
//    private Location stonehenge = new Location(Bukkit.getWorld("world"), 117, 7, -243);
//    private Location underTheBridge = new Location(Bukkit.getWorld("world"), 137, 0, -264);
//    private Location skyblockSign = new Location(Bukkit.getWorld("world"), 89, 73, -268);
//    private Location prisonTower = new Location(Bukkit.getWorld("world"), 291, 42, -275);
//    private Location prisonCell = new Location(Bukkit.getWorld("world"), 315, 21, -220);
//    private Location wheatFarm = new Location(Bukkit.getWorld("world"), 74, 5, -317);
//    private Location pyramidObserver = new Location(Bukkit.getWorld("world"), 241, 4, -359);
//    private Location farmhouse = new Location(Bukkit.getWorld("world"), 116, 9, -308);

    private Location inTheTree = new Location(Bukkit.getWorld("world"), 215, 3, -299);
    private Location islandTree = new Location(Bukkit.getWorld("world"), 149, 6, -298);
    private Location skyblockTree = new Location(Bukkit.getWorld("world"), 68, 27, -261);
    private Location ultimateView = new Location(Bukkit.getWorld("world"), 162, 91, -184);
    private Location theWaterfall = new Location(Bukkit.getWorld("world"), 93, 27, -269);
    private Location theWindmill = new Location(Bukkit.getWorld("world"), 114, 23, -360);
    private Location rooftop = new Location(Bukkit.getWorld("world"), 185, 47, -389);
    private Location theMushy = new Location(Bukkit.getWorld("world"), 66, 15, -269);
    private Location cobblestoneGenerator = new Location(Bukkit.getWorld("world"), 84, 31, -263);
    private Location demonCave = new Location(Bukkit.getWorld("world"), 249, 30, -388);
    private Location waterHole = new Location(Bukkit.getWorld("world"), 99, 19, -386);
    private Location rootPortal = new Location(Bukkit.getWorld("world"), 82, 3, -263);
    private Location stonehenge = new Location(Bukkit.getWorld("world"), 117, 7, -243);
    private Location underTheBridge = new Location(Bukkit.getWorld("world"), 137, 0, -264);
    private Location skyblockSign = new Location(Bukkit.getWorld("world"), 89, 73, -268);
    private Location prisonTower = new Location(Bukkit.getWorld("world"), 291, 42, -275);
    private Location prisonCell = new Location(Bukkit.getWorld("world"), 315, 21, -220);
    private Location wheatFarm = new Location(Bukkit.getWorld("world"), 74, 5, -317);
    private Location pyramidObserver = new Location(Bukkit.getWorld("world"), 241, 4, -359);
    private Location farmhouse = new Location(Bukkit.getWorld("world"), 116, 9, -308);


    public void savePF(Player p) {
        try {
            returnFC(p).save(pStats.getPF(p));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void checkIfPlayerHas(Player p, String s) {
        if (pStats.easterEggsFound.get(p.getUniqueId()).contains(s)) {
            TitleAPI.sendActionBar(p, ChatColor.YELLOW + s);
            return;
        }
        TitleAPI.sendTitle(p, 20, 20, 20, ChatColor.YELLOW + s, ChatColor.GREEN + "has been found!");
        pStats.addEasterEggsFound(p, 1);
        pStats.easterEggsFound.get(p.getUniqueId()).add(s);
    }

    public FileConfiguration returnFC(Player p) {
        File pfile = pStats.getPF(p);
        return YamlConfiguration.loadConfiguration(pfile);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        int number = 0;
        for (String s : pStats.easterEggsFound.get(e.getPlayer().getUniqueId())) {
            number++;
        }
        if (number != pStats.getEasterEggsFound(e.getPlayer())) {
            pStats.easterEggsAmountFound.remove(e.getPlayer().getUniqueId());
            pStats.setEasterEggsFound(e.getPlayer(), number);
        }
        number = 0;
    }

    @EventHandler
    public void onPlayerFind(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location loc = p.getLocation();
        loc.setY(loc.getY() - 1);
//        for (Material block : EggsLocationManager.blocks.values()) {
//            if (loc.getBlock().getType().equals(block)) {
//                for (Location location : EggsLocationManager.myMap.values()) {
//                    if (p.getLocation())
//
//                }
//            }
//
//        }
        Block b = loc.getBlock();
        if (loc.getBlock().getType() == Material.SMOOTH_BRICK) {
            if (b.getLocation().equals(inTheTree)) {
                checkIfPlayerHas(p, "In The Tree");
                return;
            }
        }
        if (loc.getBlock().getType() == Material.LOG) {
            if (b.getLocation().equals(islandTree)) {
                checkIfPlayerHas(p, "Island Tree");
            }
            if (b.getLocation().equals(skyblockTree)) {
                checkIfPlayerHas(p, "Skyblock Tree");
            }
            if (b.getLocation().equals(ultimateView)) {
                checkIfPlayerHas(p, "Ultimate View");
            }
            if (b.getLocation().equals(theWaterfall)) {
                checkIfPlayerHas(p, "The Waterfall");
            }
            return;
        }
        if (loc.getBlock().getType() == Material.WOOD) {
            if (b.getLocation().equals(theWindmill)) {
                checkIfPlayerHas(p, "The Windmill");
            }
            if (b.getLocation().equals(rooftop)) {
                checkIfPlayerHas(p, "Rooftop");
            }
            return;
        }
        if (loc.getBlock().getType() == Material.STAINED_CLAY) {
            if (b.getLocation().equals(theMushy)) {
                checkIfPlayerHas(p, "The Mushy");
            }
            return;
        }
        if (loc.getBlock().getType() == Material.GRASS) {
            if (b.getLocation().equals(cobblestoneGenerator)) {
                checkIfPlayerHas(p, "Cobblestone Generator");
            }
            if (b.getLocation().equals(demonCave)) {
                checkIfPlayerHas(p, "Demon Cave");
            }
            if (b.getLocation().equals(waterHole)) {
                checkIfPlayerHas(p, "Water Hole");
            }
            if (b.getLocation().equals(rootPortal)) {
                checkIfPlayerHas(p, "Root Portal");
            }
            return;
        }
        if (loc.getBlock().getType() == Material.MOSSY_COBBLESTONE) {
            if (b.getLocation().equals(stonehenge)) {
                checkIfPlayerHas(p, "Stonehenge");
            }
            return;
        }
        if (loc.getBlock().getType() == Material.BEDROCK) {
            if (b.getLocation().equals(underTheBridge)) {
                checkIfPlayerHas(p, "Under The Bridge");
            }
            return;
        }
        if (loc.getBlock().getType() == Material.NETHER_BRICK) {
            if (b.getLocation().equals(skyblockSign)) {
                checkIfPlayerHas(p, "Skyblock Sign");
            }
            if (b.getLocation().equals(prisonTower)) {
                checkIfPlayerHas(p, "Prison Tower");
            }
            return;
        }
        if (loc.getBlock().getType() == Material.STONE) {
            if (b.getLocation().equals(prisonCell)) {
                checkIfPlayerHas(p, "Prison Cell");
            }
            return;
        }
        if (loc.getBlock().getType() == Material.JACK_O_LANTERN) {
            if (b.getLocation().equals(wheatFarm)) {
                checkIfPlayerHas(p, "Wheat Farm");
            }
            return;
        }
        if (loc.getBlock().getType() == Material.SAND) {
            if (b.getLocation().equals(pyramidObserver)) {
                checkIfPlayerHas(p, "Pyramid Observer");
            }
            return;
        }
        if (loc.getBlock().getType() == Material.SPRUCE_WOOD_STAIRS) {
            if (b.getLocation().equals(farmhouse)) {
                checkIfPlayerHas(p, "Farmhouse");
            }
            return;
        }
    }

}
