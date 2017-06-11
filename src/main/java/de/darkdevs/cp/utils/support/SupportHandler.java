package de.darkdevs.cp.utils.support;

import de.darkdevs.cp.utils.MySQL;
import de.darkdevs.cp.utils.var;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

import static de.darkdevs.cp.utils.var.userExists;

public class SupportHandler{

    public static boolean isClosed(int supportID) {
        try {
            ResultSet rs = MySQL.getResult("SELECT * FROM players_support WHERE supportID ='" + supportID + "'");
            if (rs.next()) if (rs.getInt("closed") == 0) return false;
        } catch (SQLException ex) { ex.printStackTrace(); }
        return true;
    }

    public static String getName(int supportID) {
        try {
            if (!isClosed(supportID)) {
                ResultSet rs = MySQL.getResult("SELECT * FROM players_support WHERE supportID ='" + supportID + "'");
                if (rs.next()) return rs.getString("name");
            }
        } catch(Exception e) { e.printStackTrace(); }
        return "Error";
    }

    public static String getUUID(int supportID) {
        try {
            if (!isClosed(supportID)) {
                ResultSet rs = MySQL.getResult("SELECT * FROM players_support WHERE supportID ='" + supportID + "'");
                if (rs.next()) return rs.getString("uuid");
            }
        } catch(Exception e) { e.printStackTrace(); }
        return "Error";
    }

    public static String getMessage(int supportID) {
        try {
            if (!isClosed(supportID)) {
                ResultSet rs = MySQL.getResult("SELECT * FROM players_support WHERE supportID ='" + supportID + "'");
                if (rs.next()) return rs.getString("message");
            }
        } catch(Exception e) { e.printStackTrace(); }
        return "Error";
    }

    public static String getTimestamp(int supportID) {
        try {
            if (!isClosed(supportID)) {
                ResultSet rs = MySQL.getResult("SELECT * FROM players_support WHERE supportID ='" + supportID + "'");
                if (rs.next()) return rs.getTimestamp("timestamp").toString();
            }
        } catch(Exception e) { e.printStackTrace(); }
        return "Error";
    }

    public static void close(int supportID) {
        if (!isClosed(supportID)) {
            MySQL.execute("UPDATE players_support SET closed=1 WHERE supportID='" + supportID + "'");
        }
    }

    public static void createTicket(Player p, String message) {
        if (userExists(p, "players_money")) {
            MySQL.execute("INSERT INTO players_support(supportID,name,uuid,message,timestamp,closed) VALUES (null,'" + p.getName() + "','" + p.getUniqueId().toString() + "','" + message + "',null, '0')");
        }
    }

}
