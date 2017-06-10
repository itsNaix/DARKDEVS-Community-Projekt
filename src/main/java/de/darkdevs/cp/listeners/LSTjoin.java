package de.darkdevs.cp.listeners;

import de.darkdevs.cp.utils.MySQL;
import de.darkdevs.cp.utils.ranks.RankHandler;
import de.darkdevs.cp.utils.var;
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
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();

        String joinMessage = var.joinMessage;
        joinMessage = joinMessage.replace("%rankColor%", RankHandler.getRankColor(p));
        joinMessage = joinMessage.replace("%PLAYERNAME%", p.getDisplayName());

        e.setJoinMessage(joinMessage.trim());

        try {
            ResultSet rs_money = MySQL.getResult("SELECT * FROM players_money WHERE uuid='" + uuid + "'");
            if(!rs_money.next()) MySQL.execute("INSERT INTO players_money(name,uuid,money) VALUES ('" + p.getName() + "','" + uuid + "',0)");

            ResultSet rs_rank = MySQL.getResult("SELECT * FROM players_rank WHERE uuid='" + uuid + "'");
            if (!rs_rank.next()) MySQL.execute("INSERT INTO players_rank(name,uuid,rankID,rankReceivedFrom) VALUES ('" + p.getName() + "','" + uuid + "',0, 'Console')");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
