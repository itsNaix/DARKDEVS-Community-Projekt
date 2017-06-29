package de.darkdevs.cp.listeners;

import com.google.common.util.concurrent.AbstractScheduledService;
import de.darkdevs.cp.main.Main;
import de.darkdevs.cp.utils.InventoryUtils;
import de.darkdevs.cp.utils.punishment.PunishmentGUI;
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

import static de.darkdevs.cp.utils.InventoryUtils.*;

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
                try {
                    if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§4Ban")) {
                        player.closeInventory();
                        setPunishment("ban");
                        Inventory inv = InventoryUtils.punishTimeMenu();
                        player.openInventory(inv);
                    } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§9Mute")) {
                        setPunishment("mute");
                    } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§cKick")) {
                        setPunishment("kick");
                    }
                } catch(NullPointerException e) {
                    System.out.println("Schon wieder die komische NullPointerException");
                }
            }



            if (inventory.getName().equalsIgnoreCase("§lChoose duration")) {
                event.setCancelled(true);
                try {
                        if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§a+1 Week")) {
                            if (inventory.getItem(18).getType() == Material.THIN_GLASS) {
                                inventory.setItem(18, InventoryUtils.createItem("§bWeeks", Material.STAINED_CLAY, (short) 14));
                            } else {
                                InventoryUtils.increaseAmount(inventory.getItem(18), 1);
                            }
                        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§a+1 Day")) {
                            if (inventory.getItem(20).getType() == Material.THIN_GLASS) {
                                inventory.setItem(20, InventoryUtils.createItem("§bDays", Material.STAINED_CLAY, (short) 6));
                            } else {
                                InventoryUtils.increaseAmount(inventory.getItem(20), 1);
                            }
                        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§a+1 Hour")) {
                            if (inventory.getItem(22).getType() == Material.THIN_GLASS) {
                                inventory.setItem(22, InventoryUtils.createItem("§bHours", Material.STAINED_CLAY, (short) 1));
                            } else {
                                InventoryUtils.increaseAmount(inventory.getItem(22), 1);
                            }
                        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§a+5 Minutes")) {
                            if (inventory.getItem(24).getType() == Material.THIN_GLASS) {
                                inventory.setItem(24, InventoryUtils.createItem("§bMinutes", Material.STAINED_CLAY, (short) 4));
                                inventory.getItem(24).setAmount(5);
                            } else {
                                InventoryUtils.increaseAmount(inventory.getItem(24), 5);
                            }
                        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§a+5 Seconds")) {
                            if (inventory.getItem(26).getType() == Material.THIN_GLASS) {
                                inventory.setItem(26, InventoryUtils.createItem("§bSeconds", Material.STAINED_CLAY));
                                inventory.getItem(26).setAmount(5);
                            } else {
                                InventoryUtils.increaseAmount(inventory.getItem(26), 5);
                            }
                        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§cPermanent")) {

                        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§aDone")) {
                            long duration = InventoryUtils.getDuration(inventory);
                            player.closeInventory();
                            if(getPunishment().equalsIgnoreCase("ban")) {
                                Bukkit.broadcastMessage("Du hast " + PunishmentGUI.getPlayername() + " wegen " + PunishmentGUI.getReason() + " für " + duration + " Milisekunden gebannt");
                            } else {
                            }
                        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§c-1 Week")) {
                            if (!(inventory.getItem(18).getType() == Material.THIN_GLASS)) {
                                if(inventory.getItem(18).getAmount() == 1) {
                                    inventory.setItem(18, InventoryUtils.createItem("§bWeeks", Material.THIN_GLASS));
                                } else {
                                    InventoryUtils.decreaseAmount(inventory.getItem(18), 1);
                                }
                            }
                        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§c-1 Day")) {
                            if (!(inventory.getItem(20).getType() == Material.THIN_GLASS)) {
                                if(inventory.getItem(20).getAmount() == 1) {
                                    inventory.setItem(20, InventoryUtils.createItem("§bDays", Material.THIN_GLASS));
                                } else {
                                    InventoryUtils.decreaseAmount(inventory.getItem(20), 1);
                                }
                            }
                        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§c-1 Hour")) {
                            if (!(inventory.getItem(22).getType() == Material.THIN_GLASS)) {
                                if (inventory.getItem(22).getAmount() == 1) {
                                    inventory.setItem(22, InventoryUtils.createItem("§bHours", Material.THIN_GLASS));
                                } else {
                                }
                                InventoryUtils.decreaseAmount(inventory.getItem(22), 1);
                            }
                        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§c-5 Minutes")) {
                            if (!(inventory.getItem(24).getType() == Material.THIN_GLASS)) {
                                if (inventory.getItem(24).getAmount() == 5) {
                                    inventory.setItem(24, InventoryUtils.createItem("§bMinutes", Material.THIN_GLASS));
                                } else {
                                InventoryUtils.decreaseAmount(inventory.getItem(24), 5);
                            }
                            }
                        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§c-5 Seconds")) {
                            if (!(inventory.getItem(26).getType() == Material.THIN_GLASS)) {
                                if (inventory.getItem(26).getAmount() == 5) {
                                    inventory.setItem(26, InventoryUtils.createItem("§bSeconds", Material.THIN_GLASS));
                                } else {
                                }
                                InventoryUtils.decreaseAmount(inventory.getItem(26), 5);
                            }
                        }
                } catch(NullPointerException e) {
                    System.out.println("NullPointer Kack Lol unwichtig");
                }
             }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



    }