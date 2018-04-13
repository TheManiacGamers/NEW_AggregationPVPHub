package me.yeroc.aggregationhub.managers;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.aggregationhub.Main;
import me.yeroc.aggregationhub.utils.Board;
import me.yeroc.aggregationhub.utils.CoreyAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Corey on 22/01/2018.
 */
public class Scoreboard {
    private Main plugin;
    static Scoreboard instance = new Scoreboard();

    public Scoreboard() {

    }

    public static Scoreboard getInstance() {
        return instance;
    }

    private StringsManager strings = StringsManager.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private PlayerStatistics pStats = PlayerStatistics.getInstance();
    private CoreyAPI api = CoreyAPI.getInstance();

    private HashMap<String, String> scoreboardInfo = new HashMap<>();

    public void setScoreboard(Player p) {
        org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
        final org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();
        final Objective objective = board.registerNewObjective("test", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(strings.scoreboardTitle);
        Score separator = objective.getScore(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "+-------------------+");
        separator.setScore(19);

        Score playerName = objective.getScore(ChatColor.RED + "" + ChatColor.BOLD + p.getName());
        playerName.setScore(18);

        Score playerBalance = objective.getScore(ChatColor.GRAY + "Money: " + ChatColor.WHITE + "$" + api.getBalance(p));
        playerBalance.setScore(17);

        scoreboardInfo.put(p.getName() + ".Money", api.getBalance(p).toString()); // ADDING MONEY TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

        Score playerLevel = objective.getScore(ChatColor.GRAY + "Level: " + ChatColor.WHITE + pStats.getLevel(p));
        playerLevel.setScore(16);

        scoreboardInfo.put(p.getName() + ".Level", pStats.getLevel(p).toString()); // ADDING LEVEL TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

        Score playertoNxtLvl = objective.getScore(ChatColor.GRAY + "Exp To Next Lvl: " + ChatColor.WHITE + pStats.getExperience(p));
        playertoNxtLvl.setScore(15);

        scoreboardInfo.put(p.getName() + ".ExpToNextLvl", pStats.getExperience(p).toString()); // ADDING EXPTONEXTLVL TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

        Score playerGroup = objective.getScore(ChatColor.GRAY + "Group: " + ChatColor.WHITE + pStats.getGroup(p));
        playerGroup.setScore(14);

        scoreboardInfo.put(p.getName() + ".Group", pStats.getGroup(p)); // ADDING GROUP TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

        Score playerKills = objective.getScore(ChatColor.GRAY + "Kills: " + ChatColor.WHITE + pStats.getKills(p));
        playerKills.setScore(13);

        scoreboardInfo.put(p.getName() + ".Kills", pStats.getKills(p).toString()); // ADDING KILLS TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

        Score playerDeaths = objective.getScore(ChatColor.GRAY + "Deaths: " + ChatColor.WHITE + pStats.getDeaths(p));
        playerDeaths.setScore(12);

        scoreboardInfo.put(p.getName() + ".Deaths", pStats.getDeaths(p).toString()); // ADDING DEATHS TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.


        Score playerKDR = objective.getScore(ChatColor.GRAY + "KDR: " + ChatColor.WHITE + "#disabled#"); //toKD(pStats.kills, pStats.deaths));
        playerKDR.setScore(11);

        // KDR IS NOT NEEDED IN SCOREBOARD INFO HASHMAP

        Score playerHighestKS = objective.getScore(ChatColor.GRAY + "Highest KS: " + ChatColor.WHITE + pStats.getLongestKillstreak(p));
        playerHighestKS.setScore(10);

        scoreboardInfo.put(p.getName() + ".HighestKS", pStats.getLongestKillstreak(p).toString()); // ADDING LONGEST KILLSTREAK TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

        Score playerCheckpoints = objective.getScore(ChatColor.GRAY + "Checkpoints: " + ChatColor.WHITE + pStats.getCheckpointsFound(p));
        playerCheckpoints.setScore(9);

        scoreboardInfo.put(p.getName() + ".Checkpoints", pStats.getCheckpointsFound(p).toString()); // ADDING CHECKPOINTS FOUND  TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

        Score playerAchievements = objective.getScore(ChatColor.GRAY + "Achievements: " + ChatColor.WHITE + pStats.getAchievementsCompleted(p));
        playerAchievements.setScore(8);

        scoreboardInfo.put(p.getName() + ".Achievements", pStats.getAchievementsCompleted(p).toString()); // ADDING ACHIEVEMENTS COMPLETED TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

        Score playerBounty = objective.getScore(ChatColor.GRAY + "Bounty On Head: " + ChatColor.WHITE + pStats.getBounty(p));
        playerBounty.setScore(7);

        scoreboardInfo.put(p.getName() + ".Bounty", pStats.getBounty(p).toString()); // ADDING BOUNTY TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

        Score emptyLine = objective.getScore("                            ");
        emptyLine.setScore(6);

        Score voteParty = objective.getScore(ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.YELLOW + "" + ChatColor.BOLD + "VoteParty:");
        voteParty.setScore(5);

        Score votesNeeded = objective.getScore(ChatColor.GRAY + "Votes Needed: " + ChatColor.WHITE + "Unknown");
        votesNeeded.setScore(4);

        p.setScoreboard(board);
    }

    // Money Level ExpToNextLvl Group Kills Deaths HighestKS Checkpoints Achievements Bounty
    public void checkUpdate(Player p) {
        org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
        final org.bukkit.scoreboard.Scoreboard boardz = manager.getNewScoreboard();
        final Objective objective = boardz.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(strings.scoreboardTitle);
        List<Player> ntr = new ArrayList<>();
        if (!(api.getBalance(p).toString().equals(scoreboardInfo.get(p.getName() + ".Money")))) {
            scoreboardInfo.put(p.getName() + ".Money", api.getBalance(p).toString()); // ADDING MONEY TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.
            ntr.add(p);
        }

        if (!(pStats.getLevel(p).toString().equals(scoreboardInfo.get(p.getName() + ".Level")))) {
            scoreboardInfo.put(p.getName() + ".Level", pStats.getLevel(p).toString()); // ADDING LEVEL TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.
            ntr.add(p);
        }
        if (!(pStats.getExperience(p).toString().equals(scoreboardInfo.get(p.getName() + ".ExpToNextLvl")))) {
            scoreboardInfo.put(p.getName() + ".ExpToNextLvl", pStats.getExperience(p).toString()); // ADDING EXPTONEXTLVL TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.
            ntr.add(p);
        }
        if (!(pStats.getGroup(p).equals(scoreboardInfo.get(p.getName() + ".Group")))) {
            scoreboardInfo.put(p.getName() + ".Group", pStats.getGroup(p)); // ADDING GROUP TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.
            ntr.add(p);
        }
        if (!(pStats.getKills(p).toString().equals(scoreboardInfo.get(p.getName() + ".Kills")))) {
            scoreboardInfo.put(p.getName() + ".Kills", pStats.getKills(p).toString()); // ADDING KILLS TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.
            ntr.add(p);
        }
        if (!(pStats.getDeaths(p).toString().equals(scoreboardInfo.get(p.getName() + ".Deaths")))) {
            scoreboardInfo.put(p.getName() + ".Deaths", pStats.getDeaths(p).toString()); // ADDING DEATHS TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.
            ntr.add(p);
        }
        if (!(pStats.getLongestKillstreak(p).toString().equals(scoreboardInfo.get(p.getName() + ".HighestKS")))) {
            scoreboardInfo.put(p.getName() + ".HighestKS", pStats.getLongestKillstreak(p).toString()); // ADDING LONGEST KILLSTREAK TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.
            ntr.add(p);
        }
        if (!(pStats.getEasterEggsFound(p).toString()).equals(scoreboardInfo.get(p.getName() + ".EasterEggs"))) {
            scoreboardInfo.put(p.getName() + ".EasterEggs", pStats.getCheckpointsFound(p).toString()); // ADDING CHECKPOINTS FOUND  TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.
            ntr.add(p);
        }
        if (!(pStats.getAchievementsCompleted(p).toString().equals(scoreboardInfo.get(p.getName() + ".Achievements")))) {
            scoreboardInfo.put(p.getName() + ".Achievements", pStats.getAchievementsCompleted(p).toString()); // ADDING ACHIEVEMENTS COMPLETED TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.
            ntr.add(p);
        }
        if (!(pStats.getBounty(p).toString().equals(scoreboardInfo.get(p.getName() + ".Bounty")))) {
            scoreboardInfo.put(p.getName() + ".Bounty", pStats.getBounty(p).toString()); // ADDING BOUNTY TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.
            ntr.add(p);
        }
        if (ntr.contains(p)) {
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(strings.scoreboardTitle);
            Score separator = objective.getScore(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "+-------------------+");
            separator.setScore(19);

            Score playerName = objective.getScore(ChatColor.RED + "" + ChatColor.BOLD + p.getName());
            playerName.setScore(18);

            Score playerBalance = objective.getScore(ChatColor.GRAY + "Money: " + ChatColor.WHITE + "$" + api.getBalance(p));
            playerBalance.setScore(17);

            scoreboardInfo.put(p.getName() + ".Money", api.getBalance(p).toString()); // ADDING MONEY TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

            Score playerLevel = objective.getScore(ChatColor.GRAY + "Level: " + ChatColor.WHITE + pStats.getLevel(p));
            playerLevel.setScore(16);

            scoreboardInfo.put(p.getName() + ".Level", pStats.getLevel(p).toString()); // ADDING LEVEL TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

            Score playertoNxtLvl = objective.getScore(ChatColor.GRAY + "Exp To Next Lvl: " + ChatColor.WHITE + pStats.getExperience(p));
            playertoNxtLvl.setScore(15);

            scoreboardInfo.put(p.getName(), ".ExpToNextLvl" + pStats.getExperience(p).toString()); // ADDING EXPTONEXTLVL TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

            Score playerGroup = objective.getScore(ChatColor.GRAY + "Group: " + ChatColor.WHITE + pStats.getGroup(p));
            playerGroup.setScore(14);

            scoreboardInfo.put(p.getName(), ".Group" + pStats.getGroup(p)); // ADDING GROUP TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

            Score playerKills = objective.getScore(ChatColor.GRAY + "Kills: " + ChatColor.WHITE + pStats.getKills(p));
            playerKills.setScore(13);

            scoreboardInfo.put(p.getName(), ".Kills" + pStats.getKills(p).toString()); // ADDING KILLS TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

            Score playerDeaths = objective.getScore(ChatColor.GRAY + "Deaths: " + ChatColor.WHITE + pStats.getDeaths(p));
            playerDeaths.setScore(12);

            scoreboardInfo.put(p.getName(), ".Deaths" + pStats.getDeaths(p).toString()); // ADDING DEATHS TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.


            Score playerKDR = objective.getScore(ChatColor.GRAY + "KDR: " + ChatColor.WHITE + "#disabled#"); //toKD(pStats.kills, pStats.deaths));
            playerKDR.setScore(11);

            // KDR IS NOT NEEDED IN SCOREBOARD INFO HASHMAP

            Score playerHighestKS = objective.getScore(ChatColor.GRAY + "Highest KS: " + ChatColor.WHITE + pStats.getLongestKillstreak(p));
            playerHighestKS.setScore(10);

            scoreboardInfo.put(p.getName(), ".HighestKS" + pStats.getLongestKillstreak(p).toString()); // ADDING LONGEST KILLSTREAK TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

            Score playerCheckpoints = objective.getScore(ChatColor.GRAY + "Easter Eggs: " + ChatColor.WHITE + pStats.getEasterEggsFound(p));
            playerCheckpoints.setScore(9);

            scoreboardInfo.put(p.getName(), ".EasterEggs" + pStats.getEasterEggsFound(p).toString()); // ADDING CHECKPOINTS FOUND  TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

            Score playerAchievements = objective.getScore(ChatColor.GRAY + "Achievements: " + ChatColor.WHITE + pStats.getAchievementsCompleted(p));
            playerAchievements.setScore(8);

            scoreboardInfo.put(p.getName(), ".Achievements" + pStats.getAchievementsCompleted(p).toString()); // ADDING ACHIEVEMENTS COMPLETED TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

            Score playerBounty = objective.getScore(ChatColor.GRAY + "Bounty On Head: " + ChatColor.WHITE + pStats.getBounty(p));
            playerBounty.setScore(7);

            scoreboardInfo.put(p.getName() + ".Bounty", pStats.getBounty(p).toString()); // ADDING BOUNTY TO HASHMAP TO CHECK IF INFO IS DIFFERENT LATER.

            Score emptyLine = objective.getScore("                            ");
            emptyLine.setScore(6);

            Score voteParty = objective.getScore(ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.YELLOW + "" + ChatColor.BOLD + "VoteParty:");
            voteParty.setScore(5);

            Score votesNeeded = objective.getScore(ChatColor.GRAY + "Votes Needed: " + ChatColor.WHITE + "Unknown");
            votesNeeded.setScore(4);

            p.setScoreboard(boardz);
            ntr.remove(p);
        }
//        board.set(0, "hey");
    }


}
//
//    public void updateScoreboard(Player p) {
//        return;
////        org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
////        final org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();
////        final Objective objective = board.registerNewObjective("test", "dummy");
////        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
////        objective.setDisplayName(strings.scoreboardTitle);
////
////        Score separator = objective.getScore(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "+-------------------+");
////        separator.setScore(19);
////
////        Score playerName = objective.getScore(ChatColor.GRAY + "" + ChatColor.BOLD + ">" + ChatColor.RED + "" + ChatColor.BOLD + " " + p.getName() + ":");
////        playerName.setScore(18);
////
////        Score playerBalance = objective.getScore(ChatColor.GRAY + "Money: " + ChatColor.WHITE + "$" + api.getBalance(p));
////        playerBalance.setScore(17);
////
////        Score playerLevel = objective.getScore(ChatColor.GRAY + "Level: " + ChatColor.WHITE + pStats.getLevel(p));
////        playerLevel.setScore(16);
////
////        Score playertoNxtLvl = objective.getScore(ChatColor.GRAY + "Exp To Next Lvl: " + ChatColor.WHITE + pStats.getExperience(p));
////        playertoNxtLvl.setScore(15);
////
////        Score playerGroup = objective.getScore(ChatColor.GRAY + "Group: " + ChatColor.WHITE + pStats.getGroup(p));
////        playerGroup.setScore(14);
////
////        Score playerKills = objective.getScore(ChatColor.GRAY + "Kills: " + ChatColor.WHITE + pStats.getKills(p));
////        playerKills.setScore(13);
////
////        Score playerDeaths = objective.getScore(ChatColor.GRAY + "Deaths: " + ChatColor.WHITE + pStats.getDeaths(p));
////        playerDeaths.setScore(12);
////
////        Score playerKDR = objective.getScore(ChatColor.GRAY + "KDR: " + ChatColor.WHITE + "#disabled#"); //toKD(pStats.kills, pStats.deaths));
////        playerKDR.setScore(11);
////
////        Score playerHighestKS = objective.getScore(ChatColor.GRAY + "Highest KS: " + ChatColor.WHITE + pStats.getLongestKillstreak(p));
////        playerHighestKS.setScore(10);
////
////        Score playerCheckpoints = objective.getScore(ChatColor.GRAY + "Checkpoints: " + ChatColor.WHITE + pStats.getCheckpointsFound(p));
////        playerCheckpoints.setScore(9);
////
////        Score playerAchievements = objective.getScore(ChatColor.GRAY + "Achievements: " + ChatColor.WHITE + pStats.getAchievementsCompleted(p));
////        playerAchievements.setScore(8);
////
////        Score playerBounty = objective.getScore(ChatColor.GRAY + "Bounty On Head: " + ChatColor.WHITE + pStats.getBounty(p));
////        playerBounty.setScore(7);
////
////        Score emptyLine = objective.getScore("                            ");
////        emptyLine.setScore(6);
////
////        Score voteParty = objective.getScore(ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.YELLOW + "" + ChatColor.BOLD + "VoteParty:");
////        voteParty.setScore(5);
////
////        Score votesNeeded = objective.getScore(ChatColor.GRAY + "Votes Needed: " + ChatColor.WHITE + "Unknown");
////        votesNeeded.setScore(4);
////
////        p.setScoreboard(board);
//    }
//
//    @Override
//    public void run() {
//        if (!(Bukkit.getOnlinePlayers().size() >= 0)) {
//            return;
//        }
//        for (Player p : Bukkit.getOnlinePlayers()) {
//            if (pStats.scoreboard.get(p.getUniqueId()).equalsIgnoreCase("disabled")) {
//                return;
//            }
//            org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
//            final org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();
//            final Objective objective = board.registerNewObjective("test", "dummy");
//            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
//            objective.setDisplayName(strings.scoreboardTitle);
//
//            Score separator = objective.getScore(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "+-------------------+");
//            separator.setScore(19);
//
//            Score playerName = objective.getScore(ChatColor.GRAY + "" + ChatColor.BOLD + ">" + ChatColor.RED + "" + ChatColor.BOLD + " " + p.getName() + ":");
//            playerName.setScore(18);
//
//            Score playerBalance = objective.getScore(ChatColor.GRAY + "Money: " + ChatColor.WHITE + "$" + api.getBalance(p));
//            playerBalance.setScore(17);
//
//            Score playerLevel = objective.getScore(ChatColor.GRAY + "Level: " + ChatColor.WHITE + pStats.getLevel(p));
//            playerLevel.setScore(16);
//
//            Score playerKills = objective.getScore(ChatColor.GRAY + "Kills: " + ChatColor.WHITE + pStats.getKills(p));
//            playerKills.setScore(15);
//
//            Score playerDeaths = objective.getScore(ChatColor.GRAY + "Deaths: " + ChatColor.WHITE + pStats.getDeaths(p));
//            playerDeaths.setScore(14);
//
//            Score playerKDR = objective.getScore(ChatColor.GRAY + "KDR: " + ChatColor.WHITE + "#disabled#"); //toKD(pStats.kills, pStats.deaths));
//            playerKDR.setScore(13);
//
//            Score playerCheckpoints = objective.getScore(ChatColor.GRAY + "Checkpoints: " + ChatColor.WHITE + pStats.getCheckpointsFound(p));
//            playerCheckpoints.setScore(12);
//
//            Score playerAchievements = objective.getScore(ChatColor.GRAY + "Achievements: " + ChatColor.WHITE + pStats.getAchievementsCompleted(p));
//            playerAchievements.setScore(11);
//
//            p.setScoreboard(board);
//        }
//    }
//
//    private String toKD(int kills, int deaths) {
//        if (deaths == 0) {
//            return kills + "";
//        }
//        return new DecimalFormat("#.##").format(kills / deaths);
//    }
//}
