package com.aronck.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FactionPermissions {

    private static File configFile = new File("plugins/Server-System/factionPermissions");
    private static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

}
