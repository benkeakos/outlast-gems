package com.outlast.gems.database;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQL {

    private static final String host = "178.63.127.184";
    private static final String port = "3306";
    private static final String username = "u3262_ugWWfSl28M";
    private static final String password = ".+9voyOcP0I2ZyEKx^U+ZlW+";
    private static final String database = "s3262_OutlastMCCore";
    private static Connection con;

    static ConsoleCommandSender console = Bukkit.getConsoleSender();

    // connect
    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
                console.sendMessage("\247c[\2476Outlast\247c] \247bConnected to database!");
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // disconnect
    public static void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                console.sendMessage("\247c[\2476Outlast\247c] \247bDatabase connection closed!");
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // isConnected
    public static boolean isConnected() {
        return (con != null);
    }

    // getConnection
    public static Connection getConnection() {
        return con;
    }


    public static void createTables() {

        //PLAYER DATA

        try {
            final PreparedStatement ps = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playerdata (uuid varchar(128), gems int)");


            final boolean rs = ps.execute();


            console.sendMessage("\247c[\2476Outlast\247c] \247bLoaded PlayerData!");


        } catch (final SQLException e) {
            e.printStackTrace();
        }


    }

}
