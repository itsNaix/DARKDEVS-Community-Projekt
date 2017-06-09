package de.darkdevs.cp.main;

import de.darkdevs.cp.commands.CMDmünzen;
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
        MySQL.checkTables();
        init();
        Bukkit.getConsoleSender().sendMessage(var.pr + "Plugin wurde geladen!");
    }

    @Override
    public void onDisable() {
        MySQL.close();
    }

    private void init() {

        this.getCommand("münzen").setExecutor(new CMDmünzen());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LSTjoin(), getPlugin());
        pm.registerEvents(new LSTquit(), getPlugin());

    }

    public static Main getPlugin() {
        return plugin;
    }

}
