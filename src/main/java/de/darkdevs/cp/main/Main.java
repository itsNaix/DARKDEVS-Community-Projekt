package de.darkdevs.cp.main;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by DarkDevs  on 09.06.2017.
 */

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        System.out.println("[Community] Plugin wurde geladen!");
    }

    @Override
    public void onDisable() {

    }

}
