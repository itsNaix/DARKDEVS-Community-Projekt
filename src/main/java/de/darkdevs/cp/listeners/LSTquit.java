package de.darkdevs.cp.listeners;

import de.darkdevs.cp.utils.ranks.RankHandler;
import de.darkdevs.cp.utils.var;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Skillkiller on 09.06.2017.
 */
public class LSTquit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        String quitmessage = var.quitMessage;
        quitmessage = quitmessage.replace("%rankColor%", RankHandler.getRankColor(p));
        quitmessage = quitmessage.replace("%PLAYERNAME%", RankHandler.getRankColor(p));

        e.setQuitMessage(quitmessage.trim());
    }
}
