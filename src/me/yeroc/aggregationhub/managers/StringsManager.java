package me.yeroc.aggregationhub.managers;

import com.sk89q.minecraft.util.commands.ChatColor;

/**
 * Created by creyn63 on 5/07/2016.
 */
public class StringsManager {

    static StringsManager instance = new StringsManager();

    private StringsManager() {
    }

    public static StringsManager getInstance() {
        return instance;
    }

    /////////////////////////////////////////////// PLUGIN MESSAGES //////////////////////////////////////////////////////////////////
    public String prefix = (ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD + "Server" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.GREEN + "");
    public String menuPrefix = (ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD + "AggregationPVP" + ChatColor.GRAY + "" + ChatColor.BOLD + "]" + ChatColor.GREEN + "");
    public String nothingToDisplay = (prefix + ChatColor.RED + "" + ChatColor.BOLD + "Nothing to display here at the moment, check back later!");
    public String specifyANumber = (prefix + ChatColor.RED + "Please specify a number, between this range: " + ChatColor.DARK_RED + "");
    public String createdPlayerFile = (prefix + ChatColor.GOLD + "Player file was created correctly");
    public String newPlayer = (prefix + ChatColor.GOLD + "I see you're a new player! Let me just create your player file for you!");
    public String newPlayerCreated = (prefix + ChatColor.GOLD + "Your player file has been created!");
    public String playerNotOn = (prefix + ChatColor.RED + "The player you specified isn't online, try checking your arguments and trying again?");
    public String cantBeYourself = (prefix + ChatColor.RED + "The player you specify can't be yourself, please specify someone else!");
    public String fileSavedWrong = (prefix + ChatColor.RED + "Your player file didn't save how it should have! Please contact a staff member immediately.");
    public String notOnline = (prefix + ChatColor.RED + "The player you specified isn't online. Try checking your arguments.");
    public String join = (ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY);
    public String quit = (ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "-" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY);
    public String welcomeBack = (ChatColor.GOLD + "Welcome back to " + ChatColor.RED + "" + ChatColor.BOLD + "AggregationPVP" + ChatColor.GOLD + ", " + ChatColor.RED + "" + ChatColor.BOLD);
    public String createdFile = (" Created the player file for ");
    public String loadedFile = (" Loaded the player file for ");
    public String firstJoinp1 = (ChatColor.GOLD + "Welcome" + ChatColor.RED + ", "); //pName;
    public String firstJoinp2 = (ChatColor.GOLD + "" + ChatColor.BOLD + " to" + ChatColor.GOLD + "" + ChatColor.BOLD + " aggregationpvp.com " + ChatColor.GOLD + "!");
    public String defaultMsgs = (ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + "" + ChatColor.BOLD + "Server" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "] " + ChatColor.GREEN);
    public String kotl = (ChatColor.RED + "" + ChatColor.BOLD + "[" + ChatColor.GOLD + "" + ChatColor.BOLD + "KOTL" + ChatColor.RED + "" + ChatColor.BOLD + "] " + ChatColor.GREEN);
    public String maze = (ChatColor.RED + "" + ChatColor.BOLD + "[" + ChatColor.GOLD + "" + ChatColor.BOLD + "Maze" + ChatColor.RED + "" + ChatColor.BOLD + "] " + ChatColor.GREEN);
    public String aggregationPVPPrefix = (ChatColor.GRAY + "-=[ " + ChatColor.GREEN + "" + ChatColor.BOLD + "AggregationPVP" + ChatColor.GRAY + " ]=-");
    public String noPermissionInteract = (defaultMsgs + ChatColor.RED + "You do not have enough permissions to interact with this item. If you think this is a mistake please contact the owner.");
    public String scoreboardTitle = (ChatColor.GOLD + "" + ChatColor.BOLD + "[" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Aggregation" + ChatColor.RED + "" + ChatColor.BOLD + "PVP" + ChatColor.GOLD + "" + ChatColor.BOLD + "]");
    public String hubTab = (ChatColor.AQUA + "" + ChatColor.BOLD + "Hub" + ChatColor.AQUA);
    public String broadcast = (ChatColor.GRAY + "" + ChatColor.BOLD + "[ " + ChatColor.RED + "BROADCAST" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET + "");
    /////////////////////////////////////////////// VERSION MESSAGES /////////////////////////////////////////////////////////////////
    public String ver1 = (ChatColor.DARK_GRAY + "-----------==" + menuPrefix + ChatColor.DARK_GRAY + "==-----------");
    public String ver2 = (ChatColor.GOLD + "Version: " + ChatColor.RED + "1.0.0.0" + ChatColor.DARK_PURPLE + ".");
    public String ver3 = (ChatColor.GOLD + "Developer: " + ChatColor.RED + "[TheManiacGamers]" + ChatColor.DARK_PURPLE);
    public String ver4 = (ChatColor.GOLD + "Testers: " + ChatColor.RED + "[Roookie1200, pilchard2002]");
    public String ver5 = (ChatColor.DARK_GRAY + "-----------==" + menuPrefix + ChatColor.DARK_GRAY + "==-----------");
    /////////////////////////////////////////////// COMMAND MESSAGES /////////////////////////////////////////////////////////////////
    public String tooManyArgs = (prefix + ChatColor.RED + "You have too many arguments in your command, please use /plasma help for help!");
    public String incorrectCMD = (prefix + ChatColor.RED + "You have executed an incorrect command, please use /plasma help for help!");
    public String noPermissionCMD = (prefix + ChatColor.RED + "You do not have sufficient permissions to execute this command.");
    public String noPermissionCMDFix = (prefix + ChatColor.RED + "You require this permission: " + ChatColor.DARK_RED);
    public String needToBePlayerCMD = (prefix + ChatColor.RED + "You need to be a player to execute this command, sorry for any inconvenience.");
    public String incorrectArgs = (prefix + ChatColor.RED + "You have used incorrect arguments, please check the command and try again.");
    public String incorrectArgsFix = (prefix + ChatColor.RED + "Here are the valid arguments for this command: " + ChatColor.DARK_RED + "");
    /////////////////////////////////////////////// HELP MESSAGES ////////////////////////////////////////////////////////////////////
    public String helpPrefix = (ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD + "Help" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.GREEN + "");
    public String Page1 = (ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD + "Page 1" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.GREEN + "");
    public String helpP1Suffix = (ChatColor.DARK_GRAY + "-----------==" + Page1 + ChatColor.DARK_GRAY + "==-----------");
    public String Page2 = (ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD + "Page 2" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.GREEN + "");
    public String helpP2Suffix = (ChatColor.DARK_GRAY + "-----------==" + Page2 + ChatColor.DARK_GRAY + "==-----------");
    public String Page3 = (ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD + "Page 3" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.GREEN + "");
    public String helpP3Suffix = (ChatColor.DARK_GRAY + "-----------==" + Page3 + ChatColor.DARK_GRAY + "==-----------");
    public String help1 = (ChatColor.DARK_GRAY + "-----------==" + helpPrefix + ChatColor.DARK_GRAY + "==-----------");
    public String help2 = (ChatColor.GOLD + "/" + ChatColor.LIGHT_PURPLE + "p" + ChatColor.GOLD + " - Allows you to view the version page.");
    public String help3 = (ChatColor.GOLD + "/" + ChatColor.LIGHT_PURPLE + "p version" + ChatColor.GOLD + " - Allows you to view the version page.");
    public String help4 = (ChatColor.GOLD + "/" + ChatColor.LIGHT_PURPLE + "p help [1, 2]" + ChatColor.GOLD + " - Allows you to view this help page.");
    public String help5 = (ChatColor.GOLD + "/" + ChatColor.LIGHT_PURPLE + "" + ChatColor.GOLD + " - ");
    public String help6 = (ChatColor.GOLD + "/" + ChatColor.LIGHT_PURPLE + "" + ChatColor.GOLD + " - ");
    public String help7 = (ChatColor.GOLD + "/" + ChatColor.LIGHT_PURPLE + "" + ChatColor.GOLD + " - ");
    public String help8 = (ChatColor.GOLD + "/" + ChatColor.LIGHT_PURPLE + "" + ChatColor.GOLD + " - ");
    public String help9 = (ChatColor.GOLD + "/" + ChatColor.LIGHT_PURPLE + "" + ChatColor.GOLD + " - ");
    public String help10 = (ChatColor.GOLD + "/" + ChatColor.LIGHT_PURPLE + "" + ChatColor.GOLD + " - ");
    public String help11 = (ChatColor.GOLD + "/" + ChatColor.LIGHT_PURPLE + "" + ChatColor.GOLD + " - ");
    public String help12 = (ChatColor.GOLD + "/" + ChatColor.LIGHT_PURPLE + "" + ChatColor.GOLD + " - ");
    public String help13 = (ChatColor.GOLD + "/" + ChatColor.LIGHT_PURPLE + "" + ChatColor.GOLD + " - ");
    public String helpOnJoin1 = (ChatColor.GOLD + "Welcome to " + ChatColor.RED + "Plasma" + ChatColor.GOLD + "!");
    public String helpOnJoin1D1 = (ChatColor.GOLD + "We hope you have an awesome time!");
    public String helpOnJoin2 = (ChatColor.GOLD + "Need any help? Do /help or try contacting a staff member!");
    ///////////////////////////////////////////// HALF A HEART MESSAGES ////////////////////////////////////////////////////////////////////
    public String thugKills = (ChatColor.GREEN + "" + ChatColor.BOLD + "To reach " + ChatColor.AQUA + "" + ChatColor.BOLD + "Thug " + ChatColor.GREEN + "" + ChatColor.BOLD + ", you need to have " + ChatColor.AQUA + "" + ChatColor.BOLD + "500 kills" + ChatColor.GREEN + "" + ChatColor.BOLD + "!");
    public String soldierKills = (ChatColor.GREEN + "" + ChatColor.BOLD + "To reach " + ChatColor.AQUA + "" + ChatColor.BOLD + "Soldier" + ChatColor.GREEN + "" + ChatColor.BOLD + ", you need to have " + ChatColor.AQUA + "" + ChatColor.BOLD + "1500 kills" + ChatColor.GREEN + "" + ChatColor.BOLD + "!");
    public String hustlerKills = (ChatColor.GREEN + "" + ChatColor.BOLD + "To reach " + ChatColor.AQUA + "" + ChatColor.BOLD + "Hustler" + ChatColor.GREEN + "" + ChatColor.BOLD + ", you need to have " + ChatColor.AQUA + "" + ChatColor.BOLD + "3000 kills" + ChatColor.GREEN + "" + ChatColor.BOLD + "!");
    public String bossKills = (ChatColor.GREEN + "" + ChatColor.BOLD + "To reach " + ChatColor.AQUA + "" + ChatColor.BOLD + "Boss" + ChatColor.GREEN + "" + ChatColor.BOLD + ", you need to have " + ChatColor.AQUA + "" + ChatColor.BOLD + "4500 kills" + ChatColor.GREEN + "" + ChatColor.BOLD + "!");
    public String facilitatorKills = (ChatColor.GREEN + "" + ChatColor.BOLD + "To reach " + ChatColor.AQUA + "" + ChatColor.BOLD + "Facilitator" + ChatColor.GREEN + "" + ChatColor.BOLD + ", you need to have " + ChatColor.AQUA + "" + ChatColor.BOLD + "6000 kills" + ChatColor.GREEN + "" + ChatColor.BOLD + "!");
    public String kingpinKills = (ChatColor.GREEN + "" + ChatColor.BOLD + "To reach " + ChatColor.AQUA + "" + ChatColor.BOLD + "Kingpin" + ChatColor.GREEN + "" + ChatColor.BOLD + ", you need to have " + ChatColor.AQUA + "" + ChatColor.BOLD + "8000 kills" + ChatColor.GREEN + "" + ChatColor.BOLD + "!");
    public String publicenemyKills = (ChatColor.GREEN + "" + ChatColor.BOLD + "To reach " + ChatColor.AQUA + "" + ChatColor.BOLD + "Public Enemy" + ChatColor.GREEN + "" + ChatColor.BOLD + ", you need to have " + ChatColor.AQUA + "" + ChatColor.BOLD + "10000 kills" + ChatColor.GREEN + "" + ChatColor.BOLD + "!");
    public String godKills = (ChatColor.GREEN + "" + ChatColor.BOLD + "To reach " + ChatColor.AQUA + "" + ChatColor.BOLD + "God" + ChatColor.GREEN + "" + ChatColor.BOLD + ", you need to have " + ChatColor.AQUA + "" + ChatColor.BOLD + "15000 kills" + ChatColor.GREEN + "" + ChatColor.BOLD + "!");
    public String hahPrefixRanks = (ChatColor.AQUA + "-=[ " + ChatColor.BLUE + "" + ChatColor.BOLD + "Half A Heart Ranks" + ChatColor.AQUA + " ]=-");

}