package dev.skptical.HyperBans.Commands;

import dev.skptical.HyperBans.Storage.Data.Message;
import dev.skptical.HyperBans.API.Util;
import dev.skptical.HyperBans.Bootstrap.HyperBans;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class Plugin implements CommandExecutor, TabCompleter {

    public Plugin(HyperBans pl){
        Util.registerCommand("hyperbans", pl, this, this);

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(command.getName().equalsIgnoreCase("hyperbans") || command.getName().equalsIgnoreCase("hb") || command.getName().equalsIgnoreCase("bans")){
            if(commandSender instanceof Player){
                Player p = (Player)commandSender;
                if(strings.length == 0){
                    p.sendMessage(Util.getColored(Message.version).replaceAll("%version%", Util.getVersion()));
                }
                if(strings.length > 0){
                    if(strings[0].equalsIgnoreCase("version") || strings[0].equalsIgnoreCase("v") || strings[0].equalsIgnoreCase("ver")){
                        p.sendMessage(Util.getColored(Message.version).replaceAll("%version%", Util.getVersion()));
                    }else if (strings[0].equalsIgnoreCase("reload") || strings[0].equalsIgnoreCase("rl") ||  strings[0].equalsIgnoreCase("r")){
                        if(p.hasPermission("hyperbans.reload")){
                            Util.reload();
                            p.sendMessage(Util.getColored(Message.reload).replaceAll("%version%", Util.getVersion()));
                            Bukkit.getConsoleSender().sendMessage(HyperBans.prefix + Util.getColored("&aHyperBans has been reloaded."));
                        }else{
                            p.sendMessage(Util.getColored(Message.permission));
                        }

                    }else{
                        p.sendMessage(Util.getColored(Message.version).replaceAll("%version%", Util.getVersion()));
                    }
                }

            }else{
                CommandSender p = commandSender;
                if(strings.length == 0){
                    p.sendMessage(Util.getColored(Message.version).replaceAll("%version%", Util.getVersion()));
                }
                if(strings.length > 0){
                    if(strings[0].equalsIgnoreCase("version") || strings[0].equalsIgnoreCase("v") || strings[0].equalsIgnoreCase("ver")){
                        p.sendMessage(Util.getColored(Message.version).replaceAll("%version%", Util.getVersion()));
                    }else if (strings[0].equalsIgnoreCase("reload") || strings[0].equalsIgnoreCase("rl") || strings[0].equalsIgnoreCase("r")){
                        Util.reload();
                        Bukkit.getConsoleSender().sendMessage(HyperBans.prefix + Util.getColored("&aHyperBans has been reloaded."));
                    }else{
                        p.sendMessage(Util.getColored(Message.version).replaceAll("%version%", Util.getVersion()));
                    }
                }
            }


        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
