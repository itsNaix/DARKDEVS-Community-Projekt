package de.darkdevs.cp.main;

import de.darkdevs.cp.utils.MoneyHandler;
import de.darkdevs.cp.utils.MySQL;
import de.darkdevs.cp.utils.var;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by DarkDevs  on 09.06.2017.
 */

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        MySQL.checkMySQLFile();
        MySQL.getMySQLData();
        MySQL.connect();
        MySQL.checkTables();
        Bukkit.getConsoleSender().sendMessage(var.pr + "Plugin wurde geladen!");
    }

    @Override
    public void onDisable() {
        MySQL.close();
    }

}
