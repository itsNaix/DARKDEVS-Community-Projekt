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

        if (inventory.getName().equalsIgnoreCase("§lSupport Menü") || inventory.getName().contains("Ticket")) {

            event.setCancelled(true);

        }


    }

}
