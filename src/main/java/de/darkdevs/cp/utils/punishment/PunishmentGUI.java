package de.darkdevs.cp.utils.punishment;

import de.darkdevs.cp.utils.InventoryUtils;
import org.bukkit.Bukkit;
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
        } else if(cmd.getName().equalsIgnoreCase("punishments")) {
            Player p = (Player) sender;
            setPlayername(args[0]);
        }
        return false;
    }

    public static void kickPlayer(Player p, Player supp, String reason) {
        p.kickPlayer("§8§lDarkDevs Community-Server§r\n" +
                "\n" +
                "§c§lDu wurdest vom Server gekickt!§r\n" +
                "\n" +
                "§7Grund: §9" + reason + "\n" +
                "§7Teammitglied: §9" + supp.getDisplayName() + "\n" +
                "\n" +
                "§7Du hast ein Problem mit dieser Entscheidung?\n" +
                "§9§nKomme mit uns ins Gespräch auf unserem Discord!");
    }
}
