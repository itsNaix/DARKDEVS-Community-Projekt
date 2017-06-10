package de.darkdevs.cp.utils.ranks;

import de.darkdevs.cp.utils.MoneyHandler;
import de.darkdevs.cp.utils.MySQL;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

import static de.darkdevs.cp.utils.var.userExists;

/**
 * This class was created by Front. aka Scryptex on 09.06.2017.
 */
public class RankHandler {

    public static int getRankID(Player p) {
        try {
            if (userExists(p, "players_rank")) {
                ResultSet rs = MySQL.getResult("SELECT rankID FROM players_rank WHERE uuid='" + p.getUniqueId().toString() + "'");
                if (rs.next()) return rs.getInt("rankID");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    //TODO java.sql.SQLException: Before start of result set
    public static void addRank(Player p, int rankID) {
        if(userExists(p, "players_rank") && !hasRank(p, rankID)) {
            MySQL.execute("UPDATE players_rank SET rankID=" + rankID + " WHERE uuid='" + p.getUniqueId().toString() + "'");
        }
    }

    //TODO java.sql.SQLException: Before start of result set
    public static void remRank(Player p, int rankID) {
        try {
            if (userExists(p, "players_rank") && hasRank(p, rankID)) {
                ResultSet rs = MySQL.getResult("SELECT rankID FROM players_rank WHERE uuid='" + p.getUniqueId().toString() + "'");
                int current = rs.getInt("rankID");
                if(current != 1) {
                    int after = current -1;
                    MySQL.execute("UPDATE players_rank SET rankID=" + after + " WHERE uuid='" + p.getUniqueId().toString() + "'");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean hasRank(Player p, int rankID) {
        if(userExists(p, "players_rank")) {
            ResultSet rs = MySQL.getResult("SELECT rankID FROM players_rank WHERE uuid='" + p.getUniqueId().toString() + "'");
            try {
                return rs.getInt("rankID") >= rankID;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } return false;
    }

}
