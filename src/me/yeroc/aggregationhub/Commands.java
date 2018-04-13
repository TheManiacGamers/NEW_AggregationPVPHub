package me.yeroc.aggregationhub;

import com.sk89q.minecraft.util.commands.ChatColor;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import me.yeroc.aggregationhub.managers.PermissionsManager;
import me.yeroc.aggregationhub.managers.PlayerStatistics;
import me.yeroc.aggregationhub.managers.StringsManager;
import me.yeroc.aggregationhub.minigames.KOTL;
import me.yeroc.aggregationhub.utils.CoreyAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.UUID;

/**
 * Created by Corey on 4/2/2016.
 */
public class Commands {

    Main plugin;
    private StringsManager strings = StringsManager.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private CoreyAPI api = CoreyAPI.getInstance();
    private KOTL kotl = KOTL.getInstance();
    private PlayerStatistics pStats = PlayerStatistics.getInstance();
    private String hah = (ChatColor.AQUA + "-=[ " + ChatColor.BLUE + "" + ChatColor.BOLD + "Hub" + ChatColor.AQUA + " ]=-");
    private String infoPrefix = (ChatColor.AQUA + "-=[ " + ChatColor.BLUE + "" + ChatColor.BOLD + "Information" + ChatColor.AQUA + " ]=-");

    public Commands(Main plugin) {
        this.plugin = plugin;
    }

    @Command(aliases = "getblock", desc = "Get the location of the block underneath you")
    public void onGetBlock(CommandContext args, CommandSender sender) {
        if (!(api.checkSender(sender))) {
            return;
        }
        Player p = (Player) sender;
        Material block = p.getLocation().subtract(0, 1, 0).getBlock().getType();
        Block b = p.getLocation().subtract(0, 1, 0).getBlock();
        p.sendMessage(ChatColor.GREEN + "Block Name: " + block.toString() + ". Block Location- X: " + b.getLocation().getX() + " Y: " + b.getLocation().getY() + " Z: " + b.getLocation().getZ());
    }

    @Command(aliases = "scoreboard", desc = "Disable or Enable your scoreboard!")
    public void onScoreboard(CommandContext args, CommandSender sender) {
        if (!(api.checkSender(sender))) {
            return;
        }
        Player p = (Player) sender;
        if (!(api.checkPermission(p, perms.Hub_Scoreboard_Toggle))) {
            return;
        }
        if (args.argsLength() == 0) {
            sender.sendMessage(ChatColor.RED + "Incorrect Usage. /scoreboard <toggle>");
        }
        if (args.argsLength() == 1) {
            if (args.getString(0).equalsIgnoreCase("toggle"))
                if (pStats.scoreboard.get(p.getUniqueId()).equalsIgnoreCase("disabled")) {
                    pStats.scoreboard.put(p.getUniqueId(), "enabled");
                    p.sendMessage(strings.defaultMsgs + ChatColor.GOLD + "Your scoreboard has been " + ChatColor.GREEN + "Enabled" + ChatColor.GOLD + "!");
                    return;
                } else {
                    pStats.scoreboard.put(p.getUniqueId(), "disabled");
                    p.sendMessage(strings.defaultMsgs + ChatColor.GOLD + "Your scoreboard has been " + ChatColor.RED + "Disabled" + ChatColor.GOLD + "!");
                    p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                    return;
                }
        } else {
            sender.sendMessage(ChatColor.RED + "Incorrect Usage. /scoreboard <toggle>");
        }
    }

    @Command(aliases = "stats", desc = "Statistics ")
    public void onPlayerStats(CommandContext args, CommandSender sender) {
        if (!(api.checkSender(sender))) {
            return;
        }
        Player p = (Player) sender;
        if (!(api.checkPermission(p, perms.Hub_Statistics_View))) {
            return;
        }
//        group
//        joins
//        secondsOnline
//        kills
//        deaths
//        currentKillstreak
//        longestKillstreak
//        experience
//        level
//        bounty
//        chatCredits
//        bullseyeHits
//        grenadesThrown
//        smokeBombsThrown
//        flashBangsThrown
//        checkpointsFound
//        achievementsCompleted

        sender.sendMessage(ChatColor.RED + "Group" + ": " + ChatColor.GOLD + pStats.getGroup(p));
        sender.sendMessage(ChatColor.RED + "Joins" + ": " + ChatColor.GOLD + pStats.getJoins(p));
        sender.sendMessage(ChatColor.RED + "Kills" + ": " + ChatColor.GOLD + pStats.getKills(p));
        sender.sendMessage(ChatColor.RED + "Deaths" + ": " + ChatColor.GOLD + pStats.getDeaths(p));
        sender.sendMessage(ChatColor.RED + "Current Killstreak" + ": " + ChatColor.GOLD + pStats.getCurrentKillstreak(p));
        sender.sendMessage(ChatColor.RED + "Longest Killstreak" + ": " + ChatColor.GOLD + pStats.getLongestKillstreak(p));
        sender.sendMessage(ChatColor.RED + "Achievements Completed" + ": " + ChatColor.GOLD + pStats.getAchievementsCompleted(p));
        sender.sendMessage(ChatColor.RED + "Hours Online" + ": " + ChatColor.GOLD + pStats.getHoursOnline(p));

    }

    @Command(aliases = "aggregationghub", max = 1, desc = "Base command for hub!")
    public void onHah(CommandContext args, CommandSender sender) {
        if (!(api.checkSender(sender))) {
            return;
        }
        Player p = (Player) sender;
        if (args.argsLength() == 0) {
            if (!(api.checkPermission(p, perms.Hub_Version))) {
                return;
            }
            sender.sendMessage(infoPrefix);
            sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Version: " + ChatColor.AQUA + plugin.getDescription().getVersion());
            sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Author: " + ChatColor.AQUA + " [TheManiacGamers]");
            sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Testers: " + ChatColor.AQUA + " [Rookie1200], [pilchard2002]");
            sender.sendMessage(hah);
            return;
        } else {
            sender.sendMessage(ChatColor.RED + "Incorrect Usage.");
        }
    }

    @Command(aliases = "uuid", max = 1, desc = "Get the UUID of any online player!")
    public void onUUID(CommandContext args, CommandSender sender) {
        if (sender.hasPermission("Hah.UUID")) {
            if (args.argsLength() == 0) {
                sender.sendMessage(ChatColor.RED + "You must specify an online players name!");
                return;
            }
            if (args.argsLength() == 1) {
                Player t = Bukkit.getPlayer(args.getString(0));
                if (t.isOnline()) {
                    UUID tUUID = t.getUniqueId();
                    sender.sendMessage(ChatColor.AQUA + "That players UUID is " + ChatColor.GREEN + ChatColor.BOLD + "" + tUUID + "" + ChatColor.AQUA + "!");
                } else {
                    sender.sendMessage(ChatColor.RED + "That player is not online!");
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!s");
        }
    }

    @Command(aliases = "seconds", desc = "Get the seconds as KOTL")
    public void onSeconds(CommandContext args, CommandSender sender) {
        if (!(api.checkSender(sender))) {
            return;
        }
        Player p = (Player) sender;
        p.sendMessage("" + kotl.secondsAsKOTL.get(p.getUniqueId()));
    }

    @Command(aliases = "suicide", desc = "Suicide is bad kids.")
    public void onSuicide(CommandContext args, CommandSender sender) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.RED + "NO!!! YOU HAVE TOO MUCH TO LIVE FOR!!!!");
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(sender.getName() + " tried to suicide. Offer them a cookie.");
            }
        } else {
            sender.sendMessage(strings.needToBePlayerCMD);
        }
    }

    @Command(aliases = "eastereggs", desc = "View your found easter eggs.")
    public void onPlayerGetEggs(CommandContext args, CommandSender sender) {
        if (!(api.checkSender(sender))) {
            return;
        }
        Player p = (Player) sender;
        if (!(api.checkPermission(p, perms.Hub_EasterEggs_View))) {
            return;
        }
        if (args.argsLength() == 0) {
            if (pStats.easterEggsAmountFound.get(p.getUniqueId()) == 0) {
                p.sendMessage(ChatColor.RED + "-=[ " + ChatColor.GOLD + " EASTER EGGS " + ChatColor.RED + "]=-");
                p.sendMessage(ChatColor.RED + "You have not found any easter eggs yet.");
                return;
            }
            p.sendMessage(ChatColor.RED + "-=[ " + ChatColor.GOLD + " FOUND EASTER EGGS " + ChatColor.RED + "]=-");
            for (String s : pStats.easterEggsFound.get(p.getUniqueId())) {
                p.sendMessage(ChatColor.RED + " - " + ChatColor.GOLD + s);
            }
        } else {
            sender.sendMessage(strings.incorrectArgs);
        }
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}