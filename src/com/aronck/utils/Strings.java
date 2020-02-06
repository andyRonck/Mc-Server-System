package com.aronck.utils;

public class Strings {

    public static final String FACTION_PREFIX = "faction.";
    public static final String FACTIONS_COUNT_PATH = FACTION_PREFIX + "count";

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



}
