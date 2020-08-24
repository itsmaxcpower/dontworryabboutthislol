package dev.skptical.HyperBans.Storage.Data;

import dev.skptical.HyperBans.Bootstrap.HyperBans;

public class Message {

    public static String version;

    public static String reload;

    public static String permission;

    public static void setMessages(HyperBans var1){
        version = var1.files.getMessages().getString("version");
        reload = var1.files.getMessages().getString("reload");
        permission = var1.files.getMessages().getString("no-permission");
    }



}
