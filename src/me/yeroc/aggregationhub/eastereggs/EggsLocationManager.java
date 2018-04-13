package me.yeroc.aggregationhub.eastereggs;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.aggregationhub.Main;
import me.yeroc.aggregationhub.managers.ConfigsManager;
import me.yeroc.aggregationhub.managers.StringsManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Corey on 3/02/2018.
 */
public class EggsLocationManager {
    Main plugin;

    private ConfigsManager configs = ConfigsManager.getInstance();
    private StringsManager strings = StringsManager.getInstance();

//    public static HashMap<String, Location> myMap = new HashMap<String, Location>();
//    public static HashMap<String, Material> blocks = new HashMap<String, Material>();

    public void setLocation(Player p, String name) {
        if (configs.getCheckpoints().getString("Checkpoints." + name) == null) {
            p.sendMessage(strings.defaultMsgs + ChatColor.RED + "Could not find that checkpoint. Creating it.");
        }
        Location pLoc = p.getLocation();
        configs.getCheckpoints().set("Checkpoints." + name + ".Block", pLoc.getBlockY());
        configs.getCheckpoints().set("Checkpoints." + name + ".X", pLoc.getX());
        configs.getCheckpoints().set("Checkpoints." + name + ".Y", pLoc.getY());
        configs.getCheckpoints().set("Checkpoints." + name + ".Z", pLoc.getZ());
        configs.getCheckpoints().set("Checkpoints." + name + ".Yaw", (double) pLoc.getYaw());
        configs.getCheckpoints().set("Checkpoints." + name + ".Pitch", (double) pLoc.getPitch());
        configs.saveCheckpoints();
        p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "You created the checkpoint: " + name + "!");

    }

    public void addLocation(Player p, String name) {
        if (configs.getCheckpoints().getString("Checkpoints." + name) == null) {
            Location pLoc = p.getLocation();

            configs.getCheckpoints().set("Checkpoints." + name + ".X", pLoc.getX());
            configs.getCheckpoints().set("Checkpoints." + name + ".Y", pLoc.getY());
            configs.getCheckpoints().set("Checkpoints." + name + ".Z", pLoc.getZ());
            configs.getCheckpoints().set("Checkpoints." + name + ".Yaw", (double) pLoc.getYaw());
            configs.getCheckpoints().set("Checkpoints." + name + ".Pitch", (double) pLoc.getPitch());
            configs.saveCheckpoints();
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "You created the checkpoint: " + name + "!");
        } else {
            p.sendMessage(strings.defaultMsgs + ChatColor.RED + "That checkpoint is already created. Use /checkpoints set <name>");
        }
    }

    public void tpLocation(Player p, String name) {
        if (configs.getCheckpoints().getString("Checkpoints." + name) != null) {
            World w = Bukkit.getServer().getWorld("world");
            double x = configs.getCheckpoints().getDouble("Checkpoints." + name + ".X");
            double y = configs.getCheckpoints().getDouble("Checkpoints." + name + ".Y");
            double z = configs.getCheckpoints().getDouble("Checkpoints." + name + ".Z");
            double yaw = configs.getCheckpoints().getDouble("Checkpoints." + name + ".Yaw");
            double pitch = configs.getCheckpoints().getDouble("Checkpoints." + name + ".Pitch");
            Location newLocation = new Location(w, x, y, z, (float) yaw, (float) pitch);

            p.teleport(newLocation);
            p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "You have been teleported to checkpoint: " + name + "!");
        } else {
            p.sendMessage(strings.defaultMsgs + ChatColor.RED + "Could not find that checkpoint.");
        }
    }

//    public void saveLocations(HashMap<String, Location> map) {
//        for (String name : map.keySet()) {
//            ArrayList<Location> list = new ArrayList<>();
//            for (Location loc = map.get(name)) {
//                list.add(loc);
//            }
//            for (String s : newLoc) {
//                configs.getCheckpoints().set("Checkpoints." + name, s);
//            }
//        }
//    }
//
//    public void createLocations(String name, Player player) {
//        String location = configs.getCheckpoints().getString("Checkpoints." + name);
//
//        if (location == null) {
//            World w = Bukkit.getServer().getWorld("world");
////                double x = Double.parseDouble(args[0]);
////                double y = Double.parseDouble(args[1]);
////                double z = Double.parseDouble(args[2]);
////                double yaw = Double.parseDouble(args[3]);
////                double pitch = Double.parseDouble(args[4]);
//
//            double x = player.getLocation().getX();
//            double y = player.getLocation().getY();
//            double z = player.getLocation().getZ();
//            double yaw = player.getLocation().getYaw();
//            double pitch = player.getLocation().getPitch();
//            Block block = player.getLocation().getBlock().getRelative(0, 1, 0);
//            blocks.put(name, block.getType());
//            Location loc = new Location(w, x, y, z, (float) yaw, (float) pitch);
//            loc.setWorld(w);
//            loc.setX(x);
//            loc.setY(y);
//            loc.setZ(z);
//            loc.setYaw((float) yaw);
//            loc.setPitch((float) pitch);
//
//            myMap.put(name, loc);
//            player.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "Checkpoint: " + name + " has been created!");
//        } else {
//            player.sendMessage(strings.defaultMsgs + ChatColor.RED + "That checkpoint has already been created.");
//        }
//    }
//
//    public String checkLocation(Player p) {
//        String found = "false";
//        Location pLoc = p.getLocation();
//
//
//        Location location = new Location(w, x, y, z, (float) yaw, (float) pitch);
//
//        return found;
//    }
//
//    public void getLocation() {
//
//    }
//
//    public void loadLocations() {
//        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
//        for (String name : configs.getCheckpoints().getStringList("Checkpoints")) {
//            ArrayList<String> list = new ArrayList<String>();
//            for (String s1 : configs.getCheckpoints().getConfigurationSection(name).getKeys(false)) {
//                list.add(configs.getCheckpoints().getString(name + "." + s1));
//            }
//            map.put(name, list);
//        }
//
//    }
}
