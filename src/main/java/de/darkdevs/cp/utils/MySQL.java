package de.darkdevs.cp.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Created by julia on 09.06.2017.
 */
public class MySQL {

    private static String username;
    private static String password;
    private static String database;
    private static String host;
    private static String port;
    private static Connection con;

    public static void checkMySQLFile() {
        File file = new File("plugins/utils", "mysql.yml");
        File ordner = new File("plugins/utils/");
        if(!ordner.exists()) {
            ordner.mkdirs();
        }
        if(!file.exists()) {
            try {
                file.createNewFile();
                FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                cfg.options().copyDefaults(true);
                cfg.addDefault("username", "root");
                cfg.addDefault("password", "password");
                cfg.addDefault("database", "localhost");
                cfg.addDefault("host", "localhost");
                cfg.addDefault("port", "3306");
                cfg.save(file);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getMySQLData() {
        File file = new File("plugins/utils", "mysql.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        MySQL.username = cfg.getString("username");
        MySQL.password = cfg.getString("password");
        MySQL.database = cfg.getString("database");
        MySQL.host = cfg.getString("host");
        MySQL.port = cfg.getString("port");
    }

    public static void connect() {
        if(!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                Bukkit.getConsoleSender().sendMessage(var.pr + "§aSuccessfully connected to MySQL Database!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close() {
        if(isConnected()) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage(var.pr + "§cMySQL Connection closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected() {
        return con != null;
    }

    public static void execute(String qry) {
            try {
            PreparedStatement ps = con.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getResult(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void checkTables() {
        if(isConnected()) {
            MySQL.execute("CREATE TABLE IF NOT EXISTS Players (Name VARCHAR(100), UUID VARCHAR(100), Money INT(100))");
        }
    }

    public static Connection getConnection() {
        return con;
    }

}
