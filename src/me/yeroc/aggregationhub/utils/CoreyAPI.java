package me.yeroc.aggregationhub.utils;


import me.yeroc.aggregationhub.Main;
import me.yeroc.aggregationhub.managers.StringsManager;
import me.yeroc.aggregationhub.utils.TitleAPI.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityEvent;

/**
 * Created by Corey on 20/01/2018.
 */
public class CoreyAPI {

    StringsManager strings = StringsManager.getInstance();
    static CoreyAPI instance = new CoreyAPI();

    private CoreyAPI() {
    }

    public static CoreyAPI getInstance() {
        return instance;
    }

    public boolean checkSender(CommandSender sender) {
        if (sender instanceof Player) {
            return true;
        } else {
            sender.sendMessage(strings.needToBePlayerCMD);
            return false;
        }
    }

    public boolean checkPermission(Player p, String s) {
        if (p.hasPermission(s)) {
            return true;
        } else {
            p.sendMessage(strings.noPermissionCMD);
            return false;
        }
    }

    public boolean checkPlayer(EntityEvent e) {
        if (e.getEntityType().equals(EntityType.PLAYER)) {
            return true;
        } else {
            return false;
        }
    }

    //

    public Double getBalance(Player p) {
        if (Main.econ.hasAccount(p)) {
            return Main.econ.getBalance(p);
        } else {
            Main.econ.createPlayerAccount(p);
            Main.log(p.getName() + " did not have an economy account!");
            return Main.econ.getBalance(p);
        }
    }

}
