package de.darkdevs.cp.utils.punishment;

import de.darkdevs.cp.utils.MySQL;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class was created by Front. aka Scryptex on 29.06.2017.
 */
public class MuteManager {

    public static void mute(String uuid, String name, String reason, int seconds) {
        long current = System.currentTimeMillis();
        long millis = seconds * 1000;
        long end = current + millis;
        MySQL.execute("INSERT INTO players_muted (name, uuid, muteEnd, reason) VALUES ('" + name + "','" + uuid + "','" + end + "','" + reason + "')");
        if(Bukkit.getPlayer(name) != null) {
            Bukkit.getPlayer(name).sendMessage("§cDu wurdest gemuted!\n" +
                    "\n" +
                    "§3Grund: §e" + getReason(uuid) + "\n" +
                    "§3Verbleibende Zeit: §e" + getRemainingTime(uuid) + "\n" +
                    "§3Stelle einen Einspruch auf unserem Discord!");
        }
    }

    public static void unmute(String uuid) {
        MySQL.execute("DELETE FROM players_muted WHERE uuid='" + uuid + "'");
    }

    public static boolean isMuted(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT muteEnd FROM players_muted WHERE uuid='" + uuid + "'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getReason(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM players_muted WHERE uuid='" + uuid + "'");
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
        ResultSet rs = MySQL.getResult("SELECT * FROM players_muted WHERE uuid='" + uuid + "'");
        try {
            while(rs.next()) {
                return rs.getLong("muteEnd");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getMutedPlayers() {
        List<String> list = new ArrayList<String>();
        ResultSet rs = MySQL.getResult("SELECT * FROM players_muted");
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
