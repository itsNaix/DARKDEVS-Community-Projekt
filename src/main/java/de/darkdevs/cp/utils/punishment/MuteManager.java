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

    public static void mute(String uuid, String name, String reason, long seconds) {
        long current = System.currentTimeMillis();
        long millis = seconds * 1000;
        long end = current + millis;
        MySQL.execute("INSERT INTO players_muted (name, uuid, muteEnd, reason) VALUES ('" + name + "','" + uuid + "','" + end + "','" + reason + "')");
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
        long current = System.currentTimeMillis();
        long end = getEnd(uuid);
        if(end == -1) {
            return "§9Permanent";
        }
        long millis = end - current;

        long seconds = 0;
        long minutes = 0;
        long hours = 0;
        long days = 0;
        long weeks = 0;
        while(millis > 1000) {
            millis -= 1000;
            seconds++;
        }
        while(seconds > 60) {
            seconds -= 60;
            minutes++;
        }
        while(minutes > 60) {
            minutes -= 60;
            hours++;
        }
        while(hours > 24) {
            hours = 24;
            days++;
        }
        while(days > 7) {
            days -= 7;
            weeks++;
        }
        System.out.println(millis);
        System.out.println(weeks);
        System.out.println(days);
        System.out.println(hours);
        System.out.println(minutes);
        System.out.println(seconds);
        return "§9" + weeks + " Woche(n) " + days + " Tag(e) " + hours + " Stunde(n) " + minutes + " Minute(n) " + seconds + " Sekunde(n)";
    }
}
