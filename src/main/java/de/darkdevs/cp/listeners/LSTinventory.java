package de.darkdevs.cp.listeners;

import de.darkdevs.cp.utils.InventoryUtils;
import de.darkdevs.cp.utils.support.SupportHandler;
import de.darkdevs.cp.utils.var;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import sun.security.krb5.internal.Ticket;

public class LSTinventory implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        Inventory inventory = event.getInventory(); // The inventory that was clicked in
        ItemStack item = event.getCurrentItem();

        if (inventory.getName().equalsIgnoreCase("§lSupport Menü")) {

            if (item.getItemMeta().getDisplayName().contains("#")) {
                int SupportID = Integer.parseInt(item.getItemMeta().getDisplayName().split("#")[1]);
                player.openInventory(InventoryUtils.TicketOptions(SupportID));
            }
            event.setCancelled(true);

        }

        if (inventory.getName().contains("Ticket")) {

            int SupportID = Integer.parseInt(inventory.getTitle().split("#")[1]);
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lClose Ticket") && !SupportHandler.isClosed(SupportID)) {
                SupportHandler.setClosed(SupportID);
                player.openInventory(InventoryUtils.SupportMenü());
            }
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§lGet Message")) {
                player.sendMessage(var.pr + "Ticket #§7§l" + SupportID + "§7 von§7§l " + SupportHandler.getName(SupportID) + "§7:");
                player.sendMessage( "§7\"" + SupportHandler.getQuestion(SupportID).substring(0, SupportHandler.getQuestion(SupportID).length() - 1) + "\"");
            }
            event.setCancelled(true);

        }


    }

}
