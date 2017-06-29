package de.darkdevs.cp.utils;

import de.darkdevs.cp.utils.support.SupportHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryUtils {

    public static ItemStack createItem(String name, Material material){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack createItem(String name, Material material, short subID){
        ItemStack itemStack = new ItemStack(material, 1, subID);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static Inventory SupportMenü() {

        try {
            Inventory inv = Bukkit.createInventory(null, 18, "§lSupport Menü");
            List<String> supportIDs = new ArrayList<>();
            ResultSet resultSet = MySQL.getResult("select * from players_support");
            while (resultSet.next()) {
                supportIDs.add(Integer.toString(resultSet.getInt("supportID")));
            }
            for ( String id_raw : supportIDs ) {
                int id = Integer.parseInt(id_raw);
            if(!SupportHandler.isClosed(id)) inv.addItem(createItem("§l" + SupportHandler.getName(id) + " §r#" + id_raw, Material.BOOK));
            }
            return inv;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public static Inventory PunishmentMenu(Player p) {

            Inventory inv = Bukkit.createInventory(null, 9*3, "§lChoose Punishment");
            ItemStack ban = createItem("§4Ban", Material.DIAMOND_SWORD);
            ItemMeta bm = ban.getItemMeta();
            List<String> l = new ArrayList<>();
            l.add("");
            bm.setLore(l);
            ban.setItemMeta(bm);
            ItemStack kick = createItem("§cKick", Material.IRON_SWORD);
            ItemMeta km = kick.getItemMeta();
            km.setLore(l);
            kick.setItemMeta(km);
            ItemStack mute = createItem("§9Mute", Material.BARRIER);
            ItemStack luecke = createItem("", Material.STAINED_GLASS_PANE, (short) 7);
            for(int i = 0; i < 9*3; i++) {
                switch (i) {
                    case 10:
                        inv.setItem(i, ban);
                        break;
                    case 13:
                        inv.setItem(i, kick);
                        break;
                    case 16:
                        inv.setItem(i, mute);
                        break;
                    default:
                        inv.setItem(i, luecke);
                        break;
                }
        }
            return inv;

    }

    public static Inventory punishTimeMenu() {

        Inventory inv = Bukkit.createInventory(null, 9*6, "§lChoose duration");
        List<String> l = new ArrayList<>();
        l.add("§c§l§nSpamming the button leads to glitches!");
        ItemStack weeks = createItem("§bWeeks", Material.THIN_GLASS);
        ItemStack weekUp = createItem("§a+1 Week", Material.STONE_BUTTON);
        ItemMeta iwu = weekUp.getItemMeta();
        iwu.setLore(l);
        weekUp.setItemMeta(iwu);
        ItemStack weekDown = createItem("§c-1 Week", Material.STONE_BUTTON);
        ItemMeta iwd = weekDown.getItemMeta();
        iwd.setLore(l);
        weekDown.setItemMeta(iwd);
        ItemStack days = createItem("§bDays", Material.THIN_GLASS);
        ItemStack daysUp = createItem("§a+1 Day", Material.STONE_BUTTON);
        ItemMeta idu = daysUp.getItemMeta();
        idu.setLore(l);
        daysUp.setItemMeta(idu);
        ItemStack daysDown = createItem("§c-1 Day", Material.STONE_BUTTON);
        ItemMeta idd = daysDown.getItemMeta();
        idd.setLore(l);
        daysDown.setItemMeta(idd);
        ItemStack hours = createItem("§bHours", Material.THIN_GLASS);
        ItemStack hoursUp = createItem("§a+1 Hour", Material.STONE_BUTTON);
        ItemMeta ihu = hoursUp.getItemMeta();
        ihu.setLore(l);
        hoursUp.setItemMeta(ihu);
        ItemStack hoursDown = createItem("§c-1 Hour", Material.STONE_BUTTON);
        ItemMeta ihd = hoursDown.getItemMeta();
        ihd.setLore(l);
        hoursDown.setItemMeta(ihd);
        ItemStack minutes = createItem("§bMinutes", Material.THIN_GLASS);
        ItemStack minutesUp = createItem("§a+5 Minutes", Material.STONE_BUTTON);
        ItemMeta imu = minutesUp.getItemMeta();
        imu.setLore(l);
        minutesUp.setItemMeta(imu);
        ItemStack minutesDown = createItem("§c-5 Minutes", Material.STONE_BUTTON);
        ItemMeta imd = minutesDown.getItemMeta();
        imd.setLore(l);
        minutesDown.setItemMeta(imd);
        ItemStack seconds = createItem("§bSeconds", Material.THIN_GLASS);
        ItemStack secondsUp = createItem("§a+5 Seconds", Material.STONE_BUTTON);
        ItemMeta isu = secondsUp.getItemMeta();
        isu.setLore(l);
        secondsUp.setItemMeta(isu);
        ItemStack secondsDown = createItem("§c-5 Seconds", Material.STONE_BUTTON);
        ItemMeta isd = secondsDown.getItemMeta();
        isd.setLore(l);
        secondsDown.setItemMeta(isd);
        ItemStack unlimited = createItem("§cPermanent", Material.WOOL, (short) 14);
        ItemStack done = createItem("§aDone", Material.WOOL, (short) 5);
        ItemStack luecke = createItem("", Material.STAINED_GLASS_PANE, (short) 7);

        for(int i = 0; i < 9*6; i++) {
            switch(i) {
                case 9:
                    inv.setItem(i, weekUp);
                    break;
                case 11:
                    inv.setItem(i, daysUp);
                    break;
                case 13:
                    inv.setItem(i, hoursUp);
                    break;
                case 15:
                    inv.setItem(i, minutesUp);
                    break;
                case 17:
                    inv.setItem(i, secondsUp);
                    break;
                case 18:
                    inv.setItem(i, weeks);
                    break;
                case 20:
                    inv.setItem(i, days);
                    break;
                case 22:
                    inv.setItem(i, hours);
                    break;
                case 24:
                    inv.setItem(i, minutes);
                    break;
                case 26:
                    inv.setItem(i, seconds);
                    break;
                case 27:
                    inv.setItem(i, weekDown);
                    break;
                case 29:
                    inv.setItem(i, daysDown);
                    break;
                case 31:
                    inv.setItem(i, hoursDown);
                    break;
                case 33:
                    inv.setItem(i, minutesDown);
                    break;
                case 35:
                    inv.setItem(i, secondsDown);
                    break;
                case 45:
                    inv.setItem(i, unlimited);
                    break;
                case 53:
                    inv.setItem(i, done);
                    break;
                default:
                    inv.setItem(i, luecke);
                    break;
            }
        }

        return inv;
    }


    public static Inventory TicketOptions(int TicketID) {
        Inventory options = Bukkit.createInventory(null, 9, "§lTicket #" + TicketID);
        options.setItem(8, createItem("§c§lClose Ticket", Material.BARRIER));
        options.setItem(0, createItem("§lGet Message", Material.PAPER));
        options.setItem(1, createItem("§lAnswer", Material.BOOK_AND_QUILL));
        return  options;
    }

    public static Inventory TextInput(int TicketID) {
        Inventory input = Bukkit.createInventory(null, InventoryType.ANVIL);
        input.setItem(0, createItem("Answer for #" + TicketID, Material.PAPER));
        return input;
    }

    public static void increaseAmount(ItemStack i, int amount) {
        int current = i.getAmount();
        i.setAmount(current + amount);
    }

    public static void decreaseAmount(ItemStack i, int amount) {
        int current = i.getAmount();
        if(current - amount < 1) {
            return;
        } else {
            i.setAmount(current - amount);
        }
    }

    public static long getDuration(Inventory inv) {
        long weeks;
        long days;
        long hours;
        long minutes;
        long seconds;
        if(inv.getItem(18).getType() == Material.THIN_GLASS) {
            weeks = 0;
        } else {
            weeks = inv.getItem(18).getAmount() * 7 * 24 * 60 * 60;
        }
        if(inv.getItem(20).getType() == Material.THIN_GLASS) {
            days = 0;
        } else {
            days = inv.getItem(20).getAmount() * 24 * 60 * 60;
        }
        if(inv.getItem(22).getType() == Material.THIN_GLASS) {
            hours = 0;
        } else {
            hours = inv.getItem(22).getAmount() * 60 * 60;
        }
        if(inv.getItem(24).getType() == Material.THIN_GLASS) {
            minutes = 0;
        } else {
            minutes = inv.getItem(24).getAmount() * 60;
        }
        if(inv.getItem(26).getType() == Material.THIN_GLASS) {
            seconds = 0;
        } else {
            seconds = inv.getItem(26).getAmount();
        }
        long duration = (weeks + days + hours + minutes + seconds) * 1000;
        return duration;
    }

}
