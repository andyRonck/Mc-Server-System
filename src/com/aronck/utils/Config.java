package com.aronck.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Config {

    public static File configFile = new File("plugins/Server-System/factions");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    public void addFaction(String name, Player ... players){
        String playersString = "";
        for (int i = 0; i < players.length; i++) {
            Player p = players[i];
            playersString += p.getName();
            if(i < players.length-1)playersString += ",";
        }
        config.set("faction." + name + ".players", playersString);
    }

    public void addPlayerToFaction(String faction, Player player){
        if(!factionExists(faction))return;
        config.set("faction." + faction + ".players", config.get("faction." + faction + ".players") + "," + player.getName());
    }

    public boolean factionExists(String faction){
        return config.get("faction." + faction)==null;
    }

    public Player[] getPlayersOfFaction(String faction){
        if(!factionExists(faction))return null;
        Object[] objects = ((String)(config.get("faction." + faction + ".players"))).split(",");
        Player[] players = new Player[objects.length];
        for(int i = 0;i< objects.length;i++) players[i] = (Player)objects[i];
        return players;
    }

    public int getNumberOfFactions(){
        return config.getInt("faction.count");
    }

    public void setNumberOfFactions(int count){
        config.set("faction.count", count);
    }

    public static void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
