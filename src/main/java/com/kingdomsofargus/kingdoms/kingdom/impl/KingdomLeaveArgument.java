package com.kingdomsofargus.kingdoms.kingdom.impl;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.user.User;
import com.kingdomsofargus.kingdoms.utils.Utils;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KingdomLeaveArgument extends CommandArgument {

	private final Kingdoms plugin;
	
	public KingdomLeaveArgument(Kingdoms plugin) {
        super("leave", "Leave a kingdom");
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You cannot execute this command as console.");
			return true;
		}
		
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: " + getUsage(label));
            return true;
        }
        
        
        Player player = (Player) sender;
		User user = Kingdoms.getCore().getUserManager().getUser(player);
        int kingdom = Kingdoms.getCore().getUserManager().getUser(player).getKingdom_id();
        
        if (kingdom == 0) {
        	sender.sendMessage(ChatColor.RED + "You aren't in a kingdom!");
        	return true;
        }
        if (Kingdoms.getCore().getUserManager().getUser(player).getClass().equals("King")
        		|| Kingdoms.getCore().getUserManager().getUser(player).getClass().equals("Queen")) {
        	player.sendMessage(Utils.chat("&eYou are a leader, do /k disband to disband your kingdom."));
        	} else {
        		Kingdoms.getCore().getUserManager().getUser(player).setuClass("Wanderer");
			    Kingdoms.getCore().getUserManager().getUser(player).setKingdom_id(0);
        		player.sendMessage(ChatColor.YELLOW + "Successfully left the kingdom");
        		Player leader = Bukkit.getPlayer(Kingdoms.getCore().getKindomManager().getKingdom(user.getKingdom_id()).getLeader());
        		leader.sendMessage(ChatColor.RED + player.getName() + " left your kingdom!");
        }        
		return false;
	}

}
