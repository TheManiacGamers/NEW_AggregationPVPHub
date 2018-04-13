 package me.yeroc.aggregationhub.eastereggs;

import me.yeroc.aggregationhub.Main;
import me.yeroc.aggregationhub.managers.StringsManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by Corey on 26/01/2018.
 */
public class Locations {


    static Locations instance = new Locations();

    private StringsManager strings = StringsManager.getInstance();
//    private Scoreboard sc = Scoreboard.getInstance();
    Main plugin;

    private Locations() {
    }

    public static Locations getInstance() {
        return instance;
    }

    public static Location inTheTree = new Location(Bukkit.getWorld("world"), 215.5, 4, -298.5);
    public static Location islandTree = new Location(Bukkit.getWorld("world"), 149.5, 7, -297.5);
    public static Location theWindmill = new Location(Bukkit.getWorld("world"), 114.5, 24, -359.5);
    public static Location inTheTree2 = new Location(Bukkit.getWorld("world"), 215.5, 3, -298.5);
    public static Location islandTree2 = new Location(Bukkit.getWorld("world"), 149.5, 6, -297.5);
    public static Location theWindmill2 = new Location(Bukkit.getWorld("world"), 114.5, 23, -359.5);

}
