package dev.skptical.HyperBans.API;

import dev.skptical.HyperBans.Bootstrap.HyperBans;
import dev.skptical.HyperBans.Storage.Data.Message;
import dev.skptical.HyperBans.Storage.FileManager;
import dev.skptical.HyperBans.Storage.Storage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;

public class Util extends FileManager {

    public static void registerCommand(String command, HyperBans instance, CommandExecutor executor, TabCompleter tabExecutor){
        instance.getCommand(command).setExecutor(executor);
        instance.getCommand(command).setTabCompleter(tabExecutor);
    }

    public static String getColored(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String getVersion(){
        return HyperBans.version;
    }

    public static String getPrefix(){
        return HyperBans.prefix;
    }

    protected static HyperBans instance = HyperBans.instance();


    protected static FileManager files = HyperBans.instance().files;

    public static void reload(){
        Storage.shutdown(instance);
        Storage.startup(files);
        Message.setMessages(instance);

    }

    public static boolean yamlConfig(){
        if(Storage.StorageMethod() == Storage.Method.YAML){
            return true;
        }
        return false;
    }

    public static boolean sqlConfig(){
        if(Storage.StorageMethod() == Storage.Method.MYSQL){
            return true;
        }
        return false;
    }

    public static boolean H2Config(){
        if(Storage.StorageMethod() == Storage.Method.H2){
            return true;
        }
        return false;
    }

    public static boolean liteConfig(){
        if(Storage.StorageMethod() == Storage.Method.SQLITE){
            return true;
        }
        return false;
    }
}
