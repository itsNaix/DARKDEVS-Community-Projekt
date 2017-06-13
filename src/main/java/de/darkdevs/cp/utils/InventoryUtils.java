package de.darkdevs.cp.utils;

import de.darkdevs.cp.utils.support.SupportHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

}
