package dev.skptical.HyperBans.Storage.Data;

import dev.skptical.HyperBans.Storage.FileManager;

import java.util.ArrayList;
import java.util.List;

public class Config{

    // Storage
    public static String storageType, database, address, username, password, tablePrefix;
    public static boolean useSsl;
    public static int port;

    // Server
    public static String serverName, consoleName;
    public static boolean updateCheck, autoCompletePlayers, useDisplayName;
    public static int playerIpLimit;


    // Punishments
    public static String warningsDefaultReason;
    public static boolean warningsEnabled, requireReason, warningsExpire, executeActionsAsConsole, deletePrevWarnings;
    public static int cooldown, warningsCooldown;

    public static boolean mutesEnabled, preventBypass;
    public static List<String> blackListedMuteCommands;
    public static String muteDefaultReason;

    public static boolean bansEnabled;
    public static String defaultBanReason;

    // Lockdown
    public static boolean continueAfterRestart;
    public static String lockdownDefaultMessage;

    // Notifications
    public static int notificationsThrottle;
    public static boolean notifyConsole, mutedPlayerTalk, dupeIpPlayerJoin, bannedPlayerJoin;

    // Bungeecord
    public static boolean bungeecordEnabled, sync, syncBroadcasts, syncNotifications;
    public static String defaultServerScope;

    // Exempt
    public static boolean exemptEnabled;
    public static String exemptGroups, exemptPlayers;

    // Durations
    public static String maxTempban, maxTempMute, defaultTempban, defaultTempMute;
    public static boolean roundDown;

    // Chat
    public static boolean chatEnabled, formatChat;
    public static String chatFormat;
    public static List<String> muteChatCommandBlackList;

}
