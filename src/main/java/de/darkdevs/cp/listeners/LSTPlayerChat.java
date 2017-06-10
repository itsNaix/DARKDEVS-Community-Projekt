package de.darkdevs.cp.listeners;

import de.darkdevs.cp.utils.var;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Skillkiller on 10.06.2017.
 */
public class LSTPlayerChat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        //TODO Rang einbauen!
        //Verarbeite Platzhalter!

        String newMessage = var.chatFormat.replace("%S%", ""); //Replacement bearbeiten!
        newMessage = newMessage.replace("%RANG%", "");         //Replacement bearbeiten!
        newMessage = newMessage.replace("%PLAYERNAME%", e.getPlayer().getDisplayName());
        newMessage = newMessage.replace("%MESSAGE%", e.getMessage());

        e.setFormat(newMessage.trim());
    }
}
