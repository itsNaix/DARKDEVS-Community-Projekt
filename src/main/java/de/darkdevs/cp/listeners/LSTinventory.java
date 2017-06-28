package de.darkdevs.cp.listeners;

import com.google.common.util.concurrent.AbstractScheduledService;
import de.darkdevs.cp.utils.InventoryUtils;
import de.darkdevs.cp.utils.support.SupportHandler;
import de.darkdevs.cp.utils.var;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sun.security.krb5.internal.Ticket;

import java.util.function.BiFunction;

public class LSTinventory implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        Inventory inventory = event.getInventory(); // The inventory that was clicked in

        try {
            ItemStack item = event.getCurrentItem();
            if (inventory.getName().equalsIgnoreCase("§lSupport Menü")) {

                if (item.getItemMeta().getDisplayName().contains("#")) {
                    int SupportID = Integer.parseInt(item.getItemMeta().getDisplayName().split("#")[1]);
                    player.openInventory(InventoryUtils.TicketOptions(SupportID));
                }
                if (event.getClick().isRightClick()) player.closeInventory();
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
                    player.sendMessage("§7\"" + SupportHandler.getQuestion(SupportID).substring(0, SupportHandler.getQuestion(SupportID).length() - 1) + "\"");
                }
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§lAnswer")) {
                    player.sendMessage(var.err + "Zur Zeit in Entwicklung!");
                }
                if (event.getClick().isRightClick()) player.openInventory(InventoryUtils.SupportMenü());
                event.setCancelled(true);

            }

            if (inventory.getName().equalsIgnoreCase("§lChoose Punishment")) {
                event.setCancelled(true);
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§4Ban")) {
                    player.closeInventory();
                    Inventory inv = InventoryUtils.reasonInput();
                    player.openInventory(inv);
                } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§9Mute")) {
                }
            }

            if (inventory.getName().equalsIgnoreCase("repairing") && inventory.getType() == InventoryType.ANVIL) {
                Bukkit.broadcastMessage(item.getItemMeta().getDisplayName());
                player.closeInventory();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



    }