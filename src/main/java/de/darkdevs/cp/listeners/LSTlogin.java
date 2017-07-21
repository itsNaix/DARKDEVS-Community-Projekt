package de.darkdevs.cp.listeners;

import de.darkdevs.cp.utils.punishment.BanManager;
import de.darkdevs.cp.utils.punishment.PunishmentGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;


/**
 * This class was created by Front. aka Scryptex on 02.07.2017.
 */
public class LSTlogin implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {

        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();


        if(BanManager.isBanned(p.getUniqueId().toString())) {
            long current = System.currentTimeMillis();
            long end = BanManager.getEnd(p.getUniqueId().toString());
            if(current < end || end == -1) {
                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§8§lDarkDevs Community-Server§r\n" +
                        "\n" +
                        "§c§lDu wurdest vom Server gebannt!§r\n" +
                        "\n" +
                        "§7Grund: §9" + BanManager.getReason(uuid) + "\n" +
                        "§7Verbleibende Zeit: §9" + BanManager.getRemainingTime(uuid) +"\n" +
                        "\n" +
                        "§7Du hast ein Problem mit dieser Entscheidung?\n" +
                        "§9§nKomme mit uns ins Gespräch auf unserem Discord!");
            }
        }
    }


}
