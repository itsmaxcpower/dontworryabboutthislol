package dev.skptical.HyperBans.Storage;

import dev.skptical.HyperBans.API.Util;
import dev.skptical.HyperBans.Bootstrap.HyperBans;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Storage {

    public static enum Method {
        MYSQL,
        YAML,
        SQLITE,
        H2,

    }

    private static Method currentStorageMethod;

    private static String password, user, database, host;
    protected static String tablePrefix;
    private static int port;

    public static Method StorageMethod(){
        if(currentStorageMethod == null){
            Util.reload();
        }
        return currentStorageMethod;
    }

    public static void startup(FileManager files){
        try{
            files.generateConfig();
            files.generateMessages();

            if(files.getConfig().getString("data.storage-type") != null){
                //TODO: ADD YAML FILES SETUP
                //TODO: ADD SQLITE SETUP
                //TODO: ADD H2 SETUP
                switch (files.getConfig().getString("data.storage-type").toUpperCase()) {
                    case "MYSQL":
                        currentStorageMethod = Method.MYSQL;
                        port = files.getConfig().getInt("data.port");
                        password = files.getConfig().getString("data.password");
                        user = files.getConfig().getString("data.username");
                        database = files.getConfig().getString("data.database");
                        host = files.getConfig().getString("data.address");
                        tablePrefix = files.getConfig().getString("data.table-prefix");
                        MySQL sql = new MySQL();
                        sql.setInfo(host, database, user, password, port);
                        sql.startUp();
                        Connection con = MySQL.getConnection();
                        break;
                        //TODO: ADD POOL SETTINGS
                    case "YAML":
                        currentStorageMethod = Method.YAML;
                        files.generateData();
                        files.reloadData();
                        files.saveData();
                        break;
                    case "SQLITE":
                        currentStorageMethod = Method.SQLITE;
                        break;
                    case "H2":
                        currentStorageMethod = Method.H2;
                        break;


                    default:
                        currentStorageMethod = Method.YAML;
                        files.generateData();
                        files.getConfig().set("data.storage-type", "YAML");
                        files.saveConfig();
                        files.reloadData();
                        files.saveData();
                        break;

                }
                files.reloadConfig();
                files.reloadMessages();
                files.saveMessages();
                files.saveConfig();


            }else{

                files.generateConfigOver();
                startup(files);
            }



        }catch (InvalidConfigurationException | IOException e) {
            HyperBans.instance().getServer().getConsoleSender().sendMessage(HyperBans.instance().prefix + ChatColor.RED.toString() + "There was an error while trying to generate files.");
            HyperBans.instance().getServer().getConsoleSender().sendMessage(HyperBans.instance().prefix + ChatColor.RED.toString() + "Error caused by: " + e.getCause());
            e.printStackTrace();
        }

    }


    public static void shutdown(HyperBans plugin){


    }






}
