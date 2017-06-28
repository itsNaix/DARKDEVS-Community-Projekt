package de.darkdevs.cp.utils.punishment;

import de.darkdevs.cp.utils.MySQL;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class was created by Front. aka Scryptex on 28.06.2017.
 */
public class BanManager {

    public static void ban(String uuid, String name, String reason, int seconds) {
        long current = System.currentTimeMillis();
        long millis = seconds * 1000;
        long end = current + millis;
        MySQL.execute("INSERT INTO players_banned (name, uuid, banEnd, reason) VALUES ('" + name + "','" + uuid + "','" + end + "','" + reason + "')");
        if(Bukkit.getPlayer(name) != null) {
            Bukkit.getPlayer(name).kickPlayer("§cDu wurdest vom Server gebannt!\n" +
                    "\n" +
                    "§3Grund: §e" + getReason(uuid) + "\n" +
                    "§3Verbleibende Zeit: §e" + getRemainingTime(uuid) + "\n" +
                    "§3Stelle einen Entbannungsantrag auf unserem Discord!");
        }
    }

    public static void unban(String uuid) {
        MySQL.execute("DELETE FROM players_banned WHERE uuid='" + uuid + "'");
    }

    public static boolean isBanned(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM players_banned WHERE uuid='" + uuid + "'");
        try {
            while(rs.next()) {
                return rs != null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getReason(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM players_banned WHERE uuid='" + uuid + "'");
        try {
            while(rs.next()) {
                return rs.getString("reason");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Long getEnd(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM players_banned WHERE uuid='" + uuid + "'");
        try {
            while(rs.next()) {
                return rs.getLong("banEnd");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getBannedPlayers() {
        List<String> list = new ArrayList<String>();
        ResultSet rs = MySQL.getResult("SELECT * FROM players_banned");
        try {
            while(rs.next()) {
                list.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getRemainingTime(String uuid) {
        return "";
    }
}
