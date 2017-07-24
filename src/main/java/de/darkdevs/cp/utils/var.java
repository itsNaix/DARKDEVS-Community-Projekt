package de.darkdevs.cp.utils;

import de.darkdevs.cp.utils.ranks.RankHandler;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by julia on 09.06.2017.
 */
public class var {

    /*

    FARBEN DIE VERWENDET WERDEN SOLLEN:
    §8 - DUNKELGRAU
    §7 - HELLGRAU
    §9 - BLAU


     */

    public static String pr = "§8[§7DarkDevs§8]§7 ";
    public static String err = pr + "§c";
    public static String invalidUsage = err + "Invalid Command Usage!";
    public static List<String> notReceived = new ArrayList<>();

    public static boolean userExists(Player p, String table) {
       // if(p != null) {
            ResultSet rs = MySQL.getResult("SELECT * FROM " + table + " WHERE uuid ='" + p.getUniqueId() + "'");
            try {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
       // }
        return false;
    }

    public static boolean userExists(OfflinePlayer p, String table) {
        //if(p != null) {
            ResultSet rs = MySQL.getResult("SELECT * FROM " + table + " WHERE uuid ='" + p.getUniqueId() + "'");
            try {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
      //  }
        return false;
    }

    public static String joinMessage = "§8[§a+§8] §%rankColor%%PLAYERNAME%";
    public static String quitMessage = "§8[§c-§8] §%rankColor%%PLAYERNAME%";

    public static String chatFormat = "§%rankColor%%RANG% §8| §7%PLAYERNAME% §8>> §f%MESSAGE%";

    public static String helpMessage = pr + "§7Bitte gib §9/hilfe §7ein, um Hilfe für diesen Befehl zu bekommen!";
}
