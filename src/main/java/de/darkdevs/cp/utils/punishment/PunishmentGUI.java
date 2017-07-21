package de.darkdevs.cp.utils.punishment;

import de.darkdevs.cp.utils.InventoryUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This class was created by Front. aka Scryptex on 28.06.2017.
 */
public class PunishmentGUI implements CommandExecutor {

    /*

    Usage: /punish <Player> (Reason)

     */

    private static String playername;
    private static String reason;
    private static boolean isMuted = false;

    public static String getPlayername() {
        return playername;
    }

    public static void setPlayername(String playername) {
        PunishmentGUI.playername = playername;
    }

    public static String getReason() {
        return reason;
    }

    public static void setReason(String reason) {
        PunishmentGUI.reason = reason;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("punish")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                setPlayername(args[0]);
                setReason(args[1]);
                Inventory inv = InventoryUtils.PunishmentMenu();
                p.openInventory(inv);
            }
        }
        return false;
    }
}
