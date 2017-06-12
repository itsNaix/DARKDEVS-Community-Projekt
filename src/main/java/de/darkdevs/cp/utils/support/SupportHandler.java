package de.darkdevs.cp.utils.support;

import de.darkdevs.cp.utils.MySQL;
import de.darkdevs.cp.utils.var;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static de.darkdevs.cp.utils.var.userExists;

public class SupportHandler{

    public static boolean isClosed(int supportID) {
        try {
            ResultSet rs = MySQL.getResult("SELECT * FROM players_support WHERE supportID ='" + supportID + "'");
            if (rs.next()) if (rs.getInt("closed") == 0) return false;
        } catch (SQLException ex) { ex.printStackTrace(); }
        return true;
    }

    public static void setClosed(int supportID) {
        if (!isClosed(supportID)) {
            MySQL.execute("UPDATE players_support SET closed=1 WHERE supportID='" + supportID + "'");
        }
    }

    public static void createTicket(Player p, String message) {
        if (userExists(p, "players_money")) {
            MySQL.execute("INSERT INTO players_support(supportID,name,uuid,question,answer,timestamp,closed,received) VALUES (null,'" + p.getName() + "','" + p.getUniqueId().toString() + "','" + message + "',null,null, '0', '0')");
        }
    }

    public static void answerTicket(int supportID, String message) {
        try {
            if (!isClosed(supportID)) {
                ResultSet rs = MySQL.getResult("SELECT * FROM players_support WHERE supportID ='" + supportID + "'");
                if (rs.next()) MySQL.execute("UPDATE players_support SET answer='" + message + "' WHERE supportID='" + supportID + "'");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean hasReceived(int supportID) {
        try {
            ResultSet rs = MySQL.getResult("SELECT * FROM players_support WHERE supportID ='" + supportID + "'");
            if (rs.next()) if (rs.getInt("received") == 0) return false;
        } catch (SQLException ex) { ex.printStackTrace(); }
        return true;
    }

    public static void setReceived(int supportID) {
        if (!hasReceived(supportID)) {
            MySQL.execute("UPDATE players_support SET received='1' WHERE supportID='" + supportID + "'");
        }
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

    public static String getAnswer(int supportID) {
        try {
            if (!isClosed(supportID)) {
                ResultSet rs = MySQL.getResult("SELECT * FROM players_support WHERE supportID ='" + supportID + "'");
                if (rs.next()) return rs.getString("answer");
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

    public static void CheckReceived(){
        try {
            List<String> supportIDs = new ArrayList<>();
            ResultSet resultSet = MySQL.getResult("select * from players_support");
            while (resultSet.next()) {
                supportIDs.add(Integer.toString(resultSet.getInt("supportID")));
            }
            for ( String id_raw : supportIDs ) {
                int id = Integer.parseInt(id_raw);
                if(!SupportHandler.hasReceived(id)) {
                    Player target = Bukkit.getPlayerExact(SupportHandler.getName(id));
                    target.sendMessage(var.pr + SupportHandler.getAnswer(id));
                    break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
