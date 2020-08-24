package dev.skptical.HyperBans.Bootstrap;

import dev.skptical.HyperBans.Storage.Data.Message;
import dev.skptical.HyperBans.Commands.Plugin;
import dev.skptical.HyperBans.Storage.FileManager;
import dev.skptical.HyperBans.Storage.Storage;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class HyperBans extends JavaPlugin{

    public static String version;
    private static HyperBans plugin;
    public static HyperBans instance(){return plugin;}
    public static FileManager files;
    protected ConsoleCommandSender console;

    public static final String prefix = ChatColor.translateAlternateColorCodes('&', "&e&l[HyperBans] ");

    private void mainStartup(HyperBans instance) {
        console = instance.getServer().getConsoleSender();
        new Plugin(this);
        console.sendMessage(prefix + ChatColor.GREEN.toString() + "HyperBans BUKKIT has been successfully loaded.");
    }

    private void mainShutdown(HyperBans instance) {
        console = instance.getServer().getConsoleSender();
        console.sendMessage(prefix + ChatColor.RED.toString() + "HyperBans BUKKIT has successfully unloaded.");
        Storage.shutdown(instance);
    }


    @Override
    public void onEnable(){
        plugin = this;
        files = new FileManager();
        version = getDescription().getVersion();
        mainStartup(this);
        Storage.startup(files);
        Message.setMessages(this);



    }

    @Override
    public void onDisable(){
        mainShutdown(this);
        Storage.shutdown(this);

    }




}
