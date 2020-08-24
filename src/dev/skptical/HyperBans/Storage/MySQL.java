package dev.skptical.HyperBans.Storage;

import dev.skptical.HyperBans.Bootstrap.HyperBans;
import org.bukkit.ChatColor;

import java.sql.*;

public class MySQL {

    protected static Connection con;
    protected String host, database, user, password;
    protected int port;

    public void setInfo(String h, String d, String u, String pw, int p){
        this.port = p;
        this.host = h;
        this.database = d;
        this.user = u;
        this.password = pw;

    }

    protected void startUp(){
        openConnection();
        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+ Storage.tablePrefix + "bans"  +" (ID bigint(20), PlayerUUID varchar(200), IP varchar(45), Permanant bit(1), StartDate date, EndDate date, Reason varchar(2000), ExecutorUUID varchar(200), Active bit(1), ExecutorIGN varchar(30), IPBan bit(1), Silent bit(1), ServerScope varchar(200), Server varchar(200))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+ Storage.tablePrefix + "mutes"  +" (ID bigint(20), PlayerUUID varchar(200), IP varchar(45), Permanant bit(1), StartDate date, EndDate date, Reason varchar(2000), ExecutorUUID varchar(200), Active bit(1), ExecutorIGN varchar(30), IPBan bit(1), Silent bit(1), ServerScope varchar(200), Server varchar(200))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    protected void openConnection() {
        try {
            synchronized (this) {
                if (con != null && !con.isClosed()) {
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.password);
            }
            Statement state = con.createStatement();
            HyperBans.instance().getServer().getConsoleSender().sendMessage(HyperBans.instance().prefix + ChatColor.GREEN.toString() + "Successfully connected to MySQL database " + this.host +
                    ":" + this.port);
        } catch (SQLException | ClassNotFoundException e) {
            HyperBans.instance().getServer().getConsoleSender().sendMessage(HyperBans.instance().prefix + ChatColor.RED.toString() + "There was an error connecting to the MySQL database");
            HyperBans.instance().getServer().getConsoleSender().sendMessage(HyperBans.instance().prefix + ChatColor.YELLOW.toString() + this.host + ":" + this.port + "/" + this.database);
            HyperBans.instance().getServer().getConsoleSender().sendMessage(HyperBans.instance().prefix + ChatColor.YELLOW.toString() + this.user + ":" + this.password);
            HyperBans.instance().getServer().getConsoleSender().sendMessage(HyperBans.instance().prefix + ChatColor.RED.toString() + "Error caused by: " + e.getCause());

        }

        setConnection(con);
    }

    protected static void setConnection(Connection connection){
        con = connection;
    }

    public static Connection getConnection(){
        if(con == null){
            new MySQL().openConnection();

        }
        return con;
    }

}
