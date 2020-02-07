package com.aronck.utils;

import org.bukkit.entity.Player;

public class Strings {

    /***************Messages*****************/
    public static final String NO_PERMISSION = "§3Du hast dazu keine Berechtigung!";
    public static final String NOTA_PLAYER = "§3Du musst ein Spieler sein um diesen Befehl auszuführen!";
    public static final String NO_OPEN_INVITATIONS = "§aDu hast keine Faction Invitationen offen!";
    public static final String ALREADY_IN_FACTION = "§aDu bist bereits in einer Faction!";
    public static final String NOT_IN_FACTION = "§aDu bist in keiner Faction";
    public static final String SUCCESSFULLY_REMOVED_FROM_FACTION = "§2Du hast erfolgreich deine Faction verlassen.";
    public static final String SUCCESSFULLY_DELETED_FACTION = "§2Die Faction wurde erfolgreich entfernt";
    public static final String SUCCESSFULLY_CREATED_FACTION = "§aDie Faction wurde erfolgreich erstellt";

    public static String getFactionDescription(Player player){
        return getFactionDescription(Config.getFactionOfPlayer(player));
    }

    /**
     * returns the description of a given faction
     *
     * @param faction
     * @return String
     */
    public static String getFactionDescription(String faction){
        if(faction==null)return NOT_IN_FACTION;
        else return "§2Du bist in der Faction: " + faction;
    }

    /**
     * returns the text when somebody successfully joined the given faction
     *
     * @param faction
     * @return String
     */
    public static String getSuccessfullyJoinedFaction(String faction){
        return "§2Du bist erfolgreich der Faction: " + faction + "§2beigetreten";
    }

    public static String getFactionNames(){
        if(Config.getFactionsString()==null)return "§2Es existieren noch keine Factions";
        return "§2Die Factions sind: " + Config.getFactionsString();
    }

    /***************Factions*****************/
    public static final String FACTION_PREFIX = "faction.";
    public static final String FACTIONS_COUNT_PATH = FACTION_PREFIX + "count";
    public static final String FACTIONS_NAMES_PATH = FACTION_PREFIX + "names";

    public static String getFactionPath(String faction){
        return FACTION_PREFIX + faction;
    }

    public static String getFactionsPlayersPath(String faction){
        return getFactionPath(faction) + ".players";
    }

    public static String getCoinsOfFactionPath(String faction){
        return getFactionPath(faction) + ".coins";
    }

    /***************Permissions*****************/

    public static final String FACTION_CREATE_PERMISSION = "factions.create";
    public static final String FACTION_DELETE_PERMISSION = "factions.delete";
    public static final String FACTION_DELETE_OTHER_PERMISSION = "factions.other.delete";
    public static final String FACTION_JOIN_PERMISSION = "factions.join";
    public static final String FACTION_LEAVE_PERMISSION = "factions.leave";
    public static final String FACTION_LIST_PERMISSION = "factions.list";



}
