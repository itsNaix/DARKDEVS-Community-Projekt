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

    public static boolean userExists(Player p) {
        ResultSet rs = MySQL.getResult("SELECT * FROM players_money WHERE uuid ='" +  p.getUniqueId() + "'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
