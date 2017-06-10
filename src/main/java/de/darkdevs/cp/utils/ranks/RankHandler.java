package de.darkdevs.cp.utils.ranks;

import de.darkdevs.cp.utils.MoneyHandler;
import de.darkdevs.cp.utils.MySQL;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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
        return 99;
    }

    public static int getRankID(String rank) {

        List<String> ranks = Arrays.asList("Einwohner", "Stammspieler", "Bürgermeister", "Staff");
        int i = 0;
        for ( String s : ranks ) {
            if (s.equalsIgnoreCase(rank)) return i;
            else i++;
        }
        return 99;
    }

    //TODO java.sql.SQLException: Before start of result set
    public static void setRank(Player p, int rankID) {
        if(userExists(p, "players_rank") && !hasRank(p, rankID)) {
            MySQL.execute("UPDATE players_rank SET rankID=" + rankID + " WHERE uuid='" + p.getUniqueId().toString() + "'");
        }
    }

    //TODO java.sql.SQLException: Before start of result set
    public static void remRank(Player p, int rankID) {
        try {
            if (userExists(p, "players_rank") && hasRank(p, rankID)) {
                ResultSet rs = MySQL.getResult("SELECT rankID FROM players_rank WHERE uuid='" + p.getUniqueId().toString() + "'");
                if (rs.next()) {
                    int current = rs.getInt("rankID");
                    if (current != 1) {
                        int after = current - 1;
                        MySQL.execute("UPDATE players_rank SET rankID=" + after + " WHERE uuid='" + p.getUniqueId().toString() + "'");
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean hasRank(Player p, int rankID) {
        if(userExists(p, "players_rank")) {
            ResultSet rs = MySQL.getResult("SELECT * FROM players_rank WHERE uuid='" + p.getUniqueId().toString() + "'");
            try {
                if(rs.next()) {
                    return rs.getInt("rankID") == rankID;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } return false;
    }
    public static String getRankColor(Player p) {
            try {
        if(userExists(p, "players_rank")) {
            ResultSet rs = MySQL.getResult("SELECT rankID FROM players_rank WHERE uuid='" + p.getUniqueId().toString() + "'");
                if(rs.next()) {
                    int rankID = rs.getInt("rankID");
                    if(rankID == 0) {
                        return "a";
                    } else if(rankID == 1) {
                        return "6";
                    } else if(rankID == 2) {
                        return "9";
                    } else if(rankID >= 3) {
                        return "c";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "a";
    }

    public static String getRankColor(int rankID) {
                    if(rankID == 0) {
                        return "a";
                    } else if(rankID == 1) {
                        return "6";
                    } else if(rankID == 2) {
                        return "9";
                    } else if(rankID >= 3) {
                        return "c";
                    }
        return "a";
    }

    public static String getRankName(Player p) {
        List<String> ranks = Arrays.asList("Einwohner", "Stammspieler", "Bürgermeister", "Staff");
        return ranks.get(RankHandler.getRankID(p));
    }

    public static String getRankName(int rankID) {
        List<String> ranks = Arrays.asList("Einwohner", "Stammspieler", "Bürgermeister", "Staff");
        return ranks.get(rankID);
    }



}
