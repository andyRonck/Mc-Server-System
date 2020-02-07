package com.aronck.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@SuppressWarnings("JavaDoc")
public class Config {

    public static HashMap<Player, String> factionInvitations = new HashMap<>();

    private static File configFile = new File("plugins/Server-System/factions");
    private static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    /**
     * adds a new faction and adds the given players to the faction
     *
     * @param name
     * @param players
     */
    public static void addFaction(String name, Player ... players){
        StringBuilder playersString = new StringBuilder();
        for (int i = 0; i < players.length; i++) {
            Player p = players[i];
            playersString.append(p.getName());
            if(i < players.length-1)playersString.append(",");
        }
        config.set(Strings.getFactionsPlayersPath(name), playersString.toString());
        config.set(Strings.FACTIONS_NAMES_PATH, config.get(Strings.FACTIONS_NAMES_PATH) + "," + name);
    }

    public static void deleteFaction(Player player){
        deleteFaction(getFactionOfPlayer(player));
    }

    public static void deleteFaction(String faction){

        String factions = (String)config.get(Strings.getFactionsPlayersPath(faction));
        int index = factions.indexOf(faction);

        //removes the factions name
        factions = factions.substring(index, index + faction.length());

        //removes any unnecessary comma
        if(factions.startsWith(","))factions = factions.substring(1, factions.length());
        else if(factions.endsWith(","))factions = factions.substring(0, factions.length() - 1);

        config.set(Strings.FACTIONS_NAMES_PATH, factions);
        config.set(Strings.getFactionPath(faction), null);
    }

    /**
     * add a player to a given faction
     *
     * @param faction
     * @param player
     */
    public static void addPlayerToFaction(String faction, Player player){
        if(!factionExists(faction))return;
        config.set(Strings.getFactionsPlayersPath(faction), config.get(Strings.getFactionsPlayersPath(faction)) + "," + player.getName());
    }

    /**
     * removes a player from its faction
     *
     * @param player
     */
    public static void removePlayerFromFaction(Player player){
        removePlayerFromFaction(player, getFactionOfPlayer(player));
    }

    /**
     * removes a specific player from a specified faction
     *
     * @param player
     * @param faction
     */
    public static void removePlayerFromFaction(Player player, String faction){
        String players = (String)config.get(Strings.getFactionsPlayersPath(faction));
        int index = players.indexOf(player.getName());

        //removes the players name
        players = players.substring(index, index + player.getName().length());

        //removes any unnecessary comma
        if(players.startsWith(","))players = players.substring(1, players.length());
        else if(players.endsWith(","))players = players.substring(0, players.length() - 1);

    }


    /**
     * checks if a faction already exists
     *
     * @param faction
     * @return boolean
     */
    public static boolean factionExists(String faction){
        return config.get(Strings.getFactionPath(faction))!=null;
    }


    /**
     *returns an array of the player objects in that faction
     *
     * @param faction
     * @return Player[]
     */
    public static Player[] getPlayersOfFaction(String faction){
        if(!factionExists(faction))return null;
        Object[] objects = ((String)(config.get(Strings.getFactionsPlayersPath(faction)))).split(",");
        Player[] players = new Player[objects.length];
        for(int i = 0;i< objects.length;i++) players[i] = Bukkit.getPlayer((String)objects[i]);
        return players;
    }

    /**
     * sets the players of {@code faction} to {@code players}
     *
     * @param faction
     * @param players
     */
    public static void setPlayersOfFaction(String faction, Player[] players){
        String playersString = "";
        for (int i = 0; i < players.length; i++) {
            Player p = players[i];
            playersString += p.getName();
            if(i < players.length-1)playersString += ",";
        }
        setPlayersOfFaction(faction, playersString);
    }


    /**
     * sets the players of {@code faction} to {@code players}
     *
     * @param faction
     * @param players
     */
    public static void setPlayersOfFaction(String faction, String players){
        config.set(Strings.getFactionsPlayersPath(faction), players);
    }


    /**
     * returns the number of factions
     *
     * @return int
     */
    public static int getNumberOfFactions(){
        return config.getInt(Strings.FACTIONS_COUNT_PATH);
    }

    /**
     * sets the number of factions in the config to {@value}
     *
     * @param count
     */
    public static void setNumberOfFactions(int count){
        config.set(Strings.FACTIONS_COUNT_PATH, count);
    }

    /**
     * returns an array of all the faction names
     *
     * @return String[]
     */
    public static String[] getFactionNames(){
        return getFactionsString().split(",");
    }

    /**
     * returns the String with all the faction names separated by a comma
     *
     * @return String
     */
    public static String getFactionsString(){
        return (String)config.get(Strings.FACTIONS_NAMES_PATH);
    }

    /**
     * returns the faction of a player.
     * If the player is not in a faction, it returns null
     *
     * @param player
     * @return String
     */
    public static String getFactionOfPlayer(Player player){
        String[] factions = getFactionNames();
        for(int i = 0;i<factions.length;i++){
            Player[] players = getPlayersOfFaction(factions[i]);
            for(int j = 0;j<players.length;i++){
                if(players[i].equals(player))return factions[i];
            }
        }
        return null;
    }

    /**
     * checks if the player is in any faction
     *
     * @param player
     * @return boolean
     */
    public static boolean isPlayerInFaction(Player player){
        return getFactionOfPlayer(player)==null;
    }

    /**
     * checks if the player is in the given faction
     *
     * @param player
     * @param faction
     * @return boolean
     */
    public static boolean isPlayerInFaction(Player player, String faction){
        Player[] players = getPlayersOfFaction(faction);
        for(int i = 0;i<players.length;i++){
            if(players[i].equals(player))return true;
        }
        return false;
    }

    /**
     * Saves the changes in the config file
     *
     */
    public static void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
