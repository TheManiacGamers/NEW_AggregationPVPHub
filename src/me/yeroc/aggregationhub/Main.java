package me.yeroc.aggregationhub;

import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.*;
import me.yeroc.aggregationhub.eastereggs.EggsListener;
import me.yeroc.aggregationhub.listeners.DoubleJump;
import me.yeroc.aggregationhub.listeners.ServerSelectorListener;
import me.yeroc.aggregationhub.managers.ConfigsManager;
import me.yeroc.aggregationhub.minigames.KOTL;
import me.yeroc.aggregationhub.listeners.PVPListener;
import me.yeroc.aggregationhub.listeners.PlayerListener;
import me.yeroc.aggregationhub.managers.PlayerStatistics;
import me.yeroc.aggregationhub.managers.StringsManager;
import me.yeroc.aggregationhub.minigames.Maze;
import me.yeroc.aggregationhub.utils.Timer;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by TheManiacGamers on 4/2/2016.
 */
public class Main extends JavaPlugin implements Listener {
    public static Main instance;
    public static Main plugin;
    public Location spawn = null;
    protected ArrayList<Class> cmdClasses;
    public static Economy econ = null;
    private CommandsManager<CommandSender> commands;
    public static Chat chat = null;

    //    SettingsManager settings = SettingsManager.getInstance();
    private StringsManager strings = StringsManager.getInstance();
    //    private Scoreboard scoreboard = Scoreboard.getInstance();
    private PlayerStatistics pStats = PlayerStatistics.getInstance();
    private ConfigsManager configs = ConfigsManager.getInstance();
    private Timer timer = Timer.getInstance();
    private KOTL kotl = KOTL.getInstance();

    public static Main getInstance() {
        return instance;
    }

    public static void log(String message) {
        System.out.println("[AggregationHub] " + message);
    }

    public static void logClass(String className, String message) {
        System.out.println("[AggregationHub] " + className + " " + message);
    }

    public void onReload() {
        if (Bukkit.getOnlinePlayers().size() != 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                pStats.load(p);
            }
        }
    }

    public void onEnable() {
        plugin = this;
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new EggsListener(this), this);
        pm.registerEvents(new PVPListener(this), this);
        pm.registerEvents(new KOTL(this), this);
        pm.registerEvents(new Maze(this), this);
        pm.registerEvents(new DoubleJump(this), this);
        pm.registerEvents(new ServerSelectorListener(this), this);
        registerCommandClass(Commands.class);
        registerCommands();
        configs.setup(this);
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        File dataBase = new File(plugin.getDataFolder() + File.separator + "PlayerData" + File.separator);
        if (!(dataBase.exists())) {
            dataBase.mkdir();
        }
        if (Bukkit.getOnlinePlayers().size() != 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                pStats.load(p);
                DoubleJump.canDoubleJump.put(p.getUniqueId(), "Yes");
                log("Loaded " + p.getName() + "'s statistics file.");
                if (!(p.getGameMode().equals(GameMode.CREATIVE))) {
                    p.setFlying(false);
                }
            }
        }
        setupChat();
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
//        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
//            public void run() {
//                for (Player a : Bukkit.getOnlinePlayers()) {
//                    TitleAPI.sendTabTitle(a.getPlayer(), (ChatColor.GOLD + "" + ChatColor.BOLD + "AggregationPVP.com"), (strings.hubTab));
//                    kotl.addSeconds(a);
//                }
//            }
//        }, 0L, 20L);
//        new Scoreboard().runTaskTimer(this, 0L, 20 * 5);
//        new SecondsOnline().runTaskTimer(this, 0L, 20L);
        kotl.kotl = "blank";
        PlayerListener.checkEntity();
        timer.startTimers();
        timer.startTab();
        log("Enabled successfully!");
    }


    public void onDisable() {
        if (Bukkit.getOnlinePlayers().size() != 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                pStats.save(p);
                kotl.kotl = null;
            }
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            commands.execute(cmd.getName(), args, sender, sender);
        } catch (CommandPermissionsException e) {

            sender.sendMessage(ChatColor.RED + "You don't have permission.");
        } catch (MissingNestedCommandException e) {
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (CommandUsageException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {

                sender.sendMessage(ChatColor.RED + "You need to enter a number!");
            } else {
                sender.sendMessage(ChatColor.RED + "Error occurred, contact developer. [TheManiacGamers]");
                sender.sendMessage(ChatColor.RED + "Message: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (CommandException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }

        return true;
    }

    protected void registerCommandClass(Class cmdClass) {
        if (cmdClasses == null)
            cmdClasses = new ArrayList<Class>();

        cmdClasses.add(cmdClass);
    }

    protected void registerCommands() {
        if (cmdClasses == null || cmdClasses.size() < 1) {

            log("Could not register commands! Perhaps you registered no classes?");
            return;
        }

        // Register the commands that we want to use
        commands = new CommandsManager<CommandSender>() {
            @Override
            public boolean hasPermission(CommandSender player, String perm) {
                return getInstance().hasPerm(player, perm);
            }


        };
        commands.setInjector(new SimpleInjector(this));
        final CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(this, commands);

        for (Class cmdClass : cmdClasses)
            cmdRegister.register(cmdClass);
    }

    public boolean hasPerm(CommandSender sender, String perm) {
        return sender instanceof ConsoleCommandSender || sender.hasPermission(perm);
    }

}