package me.yeroc.aggregationhub.managers;

/**
 * Created by Corey on 10/12/2016.
 */
public class PermissionsManager {
    static PermissionsManager instance = new PermissionsManager();

    private PermissionsManager() {
    }

    public static PermissionsManager getInstance() {
        return instance;
    }

    // JOIN AND LEAVE PERMISSIONS
    public String Hub_Join = "Hub.Join";
    public String Hub_Leave = "Hub.Leave";
    public String Hub_Join_Notify = "Hub.Join.Notify";
    public String Hub_Leave_Notify = "Hub.Leave.Notify";

    // MISC PERMISSIONS
    public String Hub_Donator = "Hub.Donator";
    public String Hub_Version = "Hub.Version";
    public String Hub_PVP = "Hub.PVP";

    // STATISTICS
    public String Hub_Statistics_View = "Hub.Statistics.View";

    // SCOREBOARD
    public String Hub_Scoreboard_Toggle = "Hub.Scoreboard.Toggle";
    public String Hub_Scoreboard_View = "Hub.Scoreboard.View";

    // EASTER EGGS
    public String Hub_EasterEggs_View = "Hub.EasterEggs.View";

    // SERVER SELECTOR
    public String Hub_Server = "Hub.Server";
    public String Hub_Server_Creative = "Hub.Server.Creative";
    public String Hub_Server_HalfAHeart = "Hub.Server.HalfAHeart";
    public String Hub_Server_Factions = "Hub.Server.Factions";
    public String Hub_Server_Prison = "Hub.Server.Prison";
}
