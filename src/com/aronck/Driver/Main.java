package com.aronck.Driver;

import com.aronck.listeners.commandListeners.FactionCommandListener;
import com.aronck.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Enabled");
        getCommand("faction").setExecutor(new FactionCommandListener());
        Config.save();
    }

    @Override
    public void onDisable() {
        System.out.println("Disabled");
    }
}
