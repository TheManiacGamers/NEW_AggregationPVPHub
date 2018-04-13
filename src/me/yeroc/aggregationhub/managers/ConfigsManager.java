package me.yeroc.aggregationhub.managers;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.aggregationhub.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by creyn63 on 5/07/2016.
 */
public class ConfigsManager {
    static ConfigsManager instance = new ConfigsManager();
    Main plugin;
    Plugin p;
    private FileConfiguration config;
    private File cFile;
    private FileConfiguration locationsData;
    private File locationsFile;


    private ConfigsManager() {
    }

    public static ConfigsManager getInstance() {
        return instance;
    }

//inside Settings class {
//public Location getSpawn() {
//gets coords from file, create Location spawn = new Location blah blah
//return spawn;

    public void setup(Plugin p) {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        cFile = new File(Bukkit.getServer().getPluginManager().getPlugin("AggregationHub").getDataFolder(), "config.yml");
        if (!cFile.exists()) {
            try {
                cFile.createNewFile();
            } catch (IOException ex) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create config.yml!");

            }
        }
        config = YamlConfiguration.loadConfiguration(cFile);

        locationsFile = new File(Bukkit.getServer().getPluginManager().getPlugin("AggregationHub").getDataFolder(), "locations.yml");

        if (!locationsFile.exists()) {
            try {
                locationsFile.createNewFile();
            } catch (IOException ex) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create locations.yml!");

            }
        }
        locationsData = YamlConfiguration.loadConfiguration(locationsFile);


        Main.log("Loaded the config.yml file successfully!");
        Main.log("Loaded the locations.yml file successfully!");
    }


    public FileConfiguration getConfig() {
        return config;
    }


    public FileConfiguration getCheckpoints() {
        return locationsData;
    }

    public void saveConfig() {
        try {
            config.save(cFile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
        }
    }

    public void saveCheckpoints() {
        try {
            locationsData.save(locationsFile);
        } catch (IOException ex) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save locations.yml!");
        }

    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cFile);
    }

    public PluginDescriptionFile getDesc() {
        return p.getDescription();
    }

    public File getPF(Player p) {
        File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("AggregationHub").getDataFolder(), File.separator + "PlayerData" + File.separator);
        File pfile = new File(userdata, File.separator + p.getUniqueId() + ".yml");
        return pfile;
    }

}
