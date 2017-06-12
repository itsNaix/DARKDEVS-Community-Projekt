package de.darkdevs.cp.main;

import de.darkdevs.cp.commands.CMDmünzen;
import de.darkdevs.cp.commands.CMDrank;
import de.darkdevs.cp.commands.CMDsupport;
import de.darkdevs.cp.listeners.LSTPlayerChat;
import de.darkdevs.cp.listeners.LSTinventory;
import de.darkdevs.cp.listeners.LSTjoin;
import de.darkdevs.cp.listeners.LSTquit;
import de.darkdevs.cp.utils.MySQL;
import de.darkdevs.cp.utils.var;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by DarkDevs  on 09.06.2017.
 */

public class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        super.onEnable();
        MySQL.checkMySQLFile();
        MySQL.getMySQLData();
        MySQL.connect();

        if (MySQL.isConnected()) {
            MySQL.checkTables();
            init();
            Bukkit.getConsoleSender().sendMessage(var.pr + "Plugin wurde geladen!");
        } else {
            Bukkit.getConsoleSender().sendMessage(var.pr + "MySQL Verbindung nicht aufgebaut!");
        }

    }

    @Override
    public void onDisable() {
        MySQL.close();
    }

    private void init() {

        this.getCommand("münzen").setExecutor(new CMDmünzen());
        this.getCommand("rank").setExecutor(new CMDrank());
        //this.getCommand("support").setExecutor(new CMDsupport());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LSTjoin(), getPlugin());
        pm.registerEvents(new LSTquit(), getPlugin());
        pm.registerEvents(new LSTPlayerChat(), getPlugin());
        //pm.registerEvents(new LSTinventory(), getPlugin());

    }

    public static Main getPlugin() {
        return plugin;
    }

}
