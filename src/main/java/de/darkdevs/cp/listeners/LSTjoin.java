package de.darkdevs.cp.listeners;

import de.darkdevs.cp.utils.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by julia on 09.06.2017.
 */
public class LSTjoin implements Listener {

    @EventHandler
    public static void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();

        try {
            ResultSet rs = MySQL.getResult("SELECT * FROM Players WHERE UUID='" + uuid + "'");
            if(!rs.next()) MySQL.execute("INSERT INTO Players(Name,UUID,Money) VALUES ('" + p.getName() + "','" + uuid + "',0)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
