package de.darkdevs.cp.utils;

import de.darkdevs.cp.utils.ranks.RankHandler;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by julia on 09.06.2017.
 */
public class var {

    public static String pr = "§8[§7DarkDevs§8]§r ";
    public static String err = pr + "§c";
    public static String invalidUsage = err + "Invalid Command Usage!";

    public static boolean userExists(Player p, String table) {
        if(p != null) {
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
        }
        return false;
    }

    public static String joinMessage = "§8[§a+§8] §%rankColor%%PLAYERNAME%";
    public static String quitMessage = "§8[§c-§8] §%rankColor%%PLAYERNAME%";

    public static String chatFormat = "§%rankColor%%RANG% §8| §7%PLAYERNAME% §8>> §f%MESSAGE%";
}
