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
    public static void setClosed(int supportID) {
        if (!isClosed(supportID)) {
            MySQL.execute("UPDATE players_support SET closed=1 WHERE supportID='" + supportID + "'");
            if (!hasReceived(supportID)) setReceived(supportID);
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
            ResultSet rs = MySQL.getResult("SELECT * FROM players_support WHERE supportID ='" + supportID + "'");
            if (rs.next()) return rs.getString("name");
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
    public static String getQuestion(int supportID) {
        try {
            if (!isClosed(supportID)) {
                ResultSet rs = MySQL.getResult("SELECT * FROM players_support WHERE supportID ='" + supportID + "'");
                if (rs.next()) return rs.getString("question");
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return "Error";
        }
        return "Empty";
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
    public static void sendUnreceived(Player p) {
        try {
            ResultSet rs = MySQL.getResult("SELECT * FROM players_support WHERE received='0' AND uuid='" + p.getUniqueId().toString() + "' AND answer IS NOT NULL");
            if (rs.next()) {
                int SupportID = rs.getInt("supportID");
                p.sendMessage(var.pr + "Dein Ticket mit der ID §l§9" + SupportID + " §7wurde bearbeitet:");
                p.sendMessage("§7" + rs.getString("answer"));
                setReceived(SupportID);
            }
        } catch (SQLException ex) {
            ex.getLocalizedMessage();
            return;
        }
    }

}
