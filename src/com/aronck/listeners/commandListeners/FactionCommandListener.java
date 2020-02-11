package com.aronck.listeners.commandListeners;

import com.aronck.utils.Config;
import com.aronck.utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FactionCommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("faction")){

            if(args.length==0){

                //args.length==0 :

                if(sender instanceof Player)sender.sendMessage(Strings.getFactionDescription((Player)sender));
                else System.out.println(Strings.NOTA_PLAYER);
                return true;

            }else if(args.length==1){

                //args.length==1 : leave/join/list/delete

                if(args[0].equalsIgnoreCase("leave")){
                    if(!(sender instanceof Player)){
                        System.out.println(Strings.NOTA_PLAYER);
                        return true;
                    }

                    Player player = (Player) sender;

                    //checks if player has the permission to execute the command
                    if(!player.hasPermission(Strings.FACTION_LEAVE_PERMISSION)){
                        player.sendMessage(Strings.NO_PERMISSION);
                        return true;
                    }

                    //checks if player is in a faction
                    if(Config.isPlayerInFaction(player)){

                        //removing player from faction
                        player.sendMessage(Strings.SUCCESSFULLY_REMOVED_FROM_FACTION);
                        Config.removePlayerFromFaction(player);
                        return true;
                    }else{
                        player.sendMessage(Strings.NOT_IN_FACTION);
                        return true;
                    }

                }else if(args[0].equalsIgnoreCase("join")){
                    if(!(sender instanceof Player)){
                        System.out.println(Strings.NOTA_PLAYER);
                        return true;
                    }
                    Player player = (Player) sender;
                    if(!player.hasPermission(Strings.FACTION_JOIN_PERMISSION)){
                        player.sendMessage(Strings.NO_PERMISSION);
                        return true;
                    }

                    //checks if player got invited into the faction
                    if(Config.factionInvitations.get(player)==null){
                        player.sendMessage(Strings.NO_OPEN_INVITATIONS);
                        return true;
                    }

                    //checks if player already is in a faction
                    if(Config.isPlayerInFaction(player)){
                        player.sendMessage(Strings.ALREADY_IN_FACTION);
                        return true;
                    }

                    //adding player to faction
                    Config.addPlayerToFaction(Config.factionInvitations.get(player), player);
                    player.sendMessage(Strings.getSuccessfullyJoinedFaction(Config.factionInvitations.get(player)));
                    Config.factionInvitations.put(player, null);
                    return true;

                }else if(args[0].equalsIgnoreCase("list")){

                    if(sender instanceof Player) {
                        Player player = (Player) sender;
                        if (!player.hasPermission(Strings.FACTION_LIST_PERMISSION)) {
                            player.sendMessage(Strings.NO_PERMISSION);
                            return true;
                        }
                    }

                    sender.sendMessage(Strings.getFactionNames());
                    return true;

                }else if(args[0].equalsIgnoreCase("delete")){
                    if(!(sender instanceof Player)){
                        System.out.println(Strings.NOTA_PLAYER);
                        return true;
                    }
                    Player player = (Player) sender;

                    //checks if player has permission to execute the command
                    if(!player.hasPermission(Strings.FACTION_DELETE_PERMISSION)){
                        player.sendMessage(Strings.NO_PERMISSION);
                        return true;
                    }

                    //checks if player is in a faction
                    if(Config.isPlayerInFaction(player)){

                        //removing player from faction
                        player.sendMessage(Strings.SUCCESSFULLY_DELETED_FACTION);
                        Config.deleteFaction(Config.getFactionOfPlayer(player));
                        return true;
                    }else{
                        player.sendMessage(Strings.NOT_IN_FACTION);
                        return true;
                    }

                }
                return false;
            }else if(args.length==2){
                if(args[0].equalsIgnoreCase("create")){

                    if(!(sender instanceof Player)){
                        System.out.println(Strings.NOTA_PLAYER);
                        return true;
                    }

                    Player player = (Player) sender;
                    //checks if player has the permission to execute the command
                    if(!player.hasPermission(Strings.FACTION_CREATE_PERMISSION)){
                        player.sendMessage(Strings.NO_PERMISSION);
                        return true;
                    }

                    if(Config.isPlayerInFaction(player)){
                        player.sendMessage(Strings.ALREADY_IN_FACTION);
                        return true;
                    }

                    Config.addFaction(args[1], player);
                    player.sendMessage(Strings.SUCCESSFULLY_CREATED_FACTION);


                }
            }
            return false;
        }

        return false;
    }
}
