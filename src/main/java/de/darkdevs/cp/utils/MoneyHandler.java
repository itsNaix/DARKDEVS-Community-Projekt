package de.darkdevs.cp.utils;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by julia on 09.06.2017.
 */

public class MoneyHandler {

    private static boolean userExists(Player p) {
        ResultSet rs = MySQL.getResult("SELECT money FROM players_money WHERE uuid ='" +  p.getUniqueId() + "'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getMoney(Player p) {
        if(userExists(p)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT money FROM players_money WHERE uuid='" + p.getUniqueId().toString() + "'");
                if (rs.next()) {
                    return rs.getInt("money");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static void addMoney(Player p, int value) {
        if(userExists(p)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT Money FROM players_money WHERE UUID='" + p.getUniqueId().toString() + "'");
                if (rs.next()) {
                    int current = rs.getInt("money");
                    int after = current + value;
                    PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE players_money SET money=? WHERE uuid='" + p.getUniqueId().toString() + "'");
                    ps.setInt(1, after);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void remMoney(Player p, int value) {
        if(userExists(p)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT Money FROM players_money WHERE UUID='" + p.getUniqueId().toString() + "'");
                if (rs.next()) {
                    int current = rs.getInt("money");
                    int after = current - value;
                    PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE players_money SET money=? WHERE uuid='" + p.getUniqueId().toString() + "'");
                    ps.setInt(1, after);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setMoney(Player p, int money) {
        if(userExists(p)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT money FROM players_money WHERE uuid='" + p.getUniqueId().toString() + "'");
                if (rs.next()) {
                    PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE players_money SET money=? WHERE uuid='" + p.getUniqueId().toString() + "'");
                    ps.setInt(1, money);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
