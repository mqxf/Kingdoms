package com.kingdomsofargus.kingdoms.kingdom.impl;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.gui.kingdom.KingdomDiplomacyGUI;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//import com.kingdomsofargus.kingdoms.player.KingdomPlayer;

public class KingdomDiplomacyArgument  extends CommandArgument {

	// /k diplomacy <kingdomName> 
	//checks: not your kingdom, kingdom exists, you are king/queen
	
	private final Kingdoms plugin;
	
	public KingdomDiplomacyArgument(Kingdoms plugin) {
        super("diplomacy", "Diplomacy");
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName() + ' ' + "<kingdom>";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to execute this command");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (Kingdoms.getCore().getUserManager().getUser(player).getKingdom_id() != 0) {
        	sender.sendMessage(ChatColor.RED + "You aren't in a kingdom!");
        	return true;
		}
		
		if (Kingdoms.getCore().getUserManager().getUser(player).getuClass().equalsIgnoreCase("King") || Kingdoms.getCore().getUserManager().getUser(player).getuClass().equalsIgnoreCase("Queen")) {
			if (args.length < 2) {
				player.sendMessage(ChatColor.RED + getUsage("diplomacy"));
			} else {
				String targetKingdom = args[1];
				
				if (!Kingdoms.getCore().getKindomManager().kingdomExists(targetKingdom)) {
					player.sendMessage(ChatColor.RED + "That kingdom does not exist!");
				} else {
					KingdomDiplomacyGUI.applyMenu(player);
				}
			}
		} else {
        	player.sendMessage(ChatColor.RED + "You do not have permission to enter diplomacy in this kingdom!");
        	return true;
		}
		
		return false;
	}

}
