package de.darkdevs.cp.utils;

import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by julia on 09.06.2017.
 */
public class var {

    public static String pr = "§8[§7Community§8]§r ";
    public static String invalidUsage = "§8[§7Community§8]§c Invalid Command Usage!";

    public static boolean userExists(Player p, String table) {
        ResultSet rs = MySQL.getResult("SELECT * FROM " + table +" WHERE uuid ='" +  p.getUniqueId() + "'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String joinMessage = "§8[§a+§8] §a%PLAYERNAME%";
    public static String quitMessage = "§8[§c-§8] §c%PLAYERNAME%";

    public static String chatFormat = "%S% %RANG% %PLAYERNAME%: %MESSAGE%";
}
