package de.darkdevs.cp.listeners;

import de.darkdevs.cp.utils.punishment.MuteManager;
import de.darkdevs.cp.utils.ranks.RankHandler;
import de.darkdevs.cp.utils.var;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Skillkiller on 10.06.2017.
 */
public class LSTPlayerChat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        //Check ob Player gemuted
        String uuid = e.getPlayer().getUniqueId().toString();
        if(MuteManager.isMuted(uuid)) {
            if(!(MuteManager.getEnd(uuid) < System.currentTimeMillis())) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("§7----------------\n" +
                        "§c§lDu wurdest gemuted!\n" +
                        "\n" +
                        "§7Grund: §9" + MuteManager.getReason(uuid) + "\n" +
                        "§7Verbleibende Zeit: \n" +
                        "§9" + MuteManager.getRemainingTime(uuid) + "\n" +
                        "\n" +
                        "§7Du hast ein Problem mit dieser Entscheidung?\n" +
                        "§9§nKomme mit uns ins Gespräch auf unserem Discord!\n" +
                        "§r§7----------------");
            } else {
                MuteManager.unmute(uuid);
                String newMessage = var.chatFormat;
                newMessage = newMessage.replace("%rankColor%", RankHandler.getRankColor(e.getPlayer()));
                newMessage = newMessage.replace("%RANG%", RankHandler.getRankName(e.getPlayer()));
                newMessage = newMessage.replace("%PLAYERNAME%", e.getPlayer().getDisplayName());
                newMessage = newMessage.replace("%MESSAGE%", e.getMessage());

                e.setFormat(newMessage.trim());
            }
        } else {


            //Verarbeite Platzhalter!

            String newMessage = var.chatFormat;
            newMessage = newMessage.replace("%rankColor%", RankHandler.getRankColor(e.getPlayer()));
            newMessage = newMessage.replace("%RANG%", RankHandler.getRankName(e.getPlayer()));
            newMessage = newMessage.replace("%PLAYERNAME%", e.getPlayer().getDisplayName());
            newMessage = newMessage.replace("%MESSAGE%", e.getMessage());

            e.setFormat(newMessage.trim());
        }
    }
}
