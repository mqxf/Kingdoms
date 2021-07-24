package com.kingdomsofargus.kingdoms.kingdom.impl;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.gui.kingdom.KingdomDiplomacyGUI;
import com.kingdomsofargus.kingdoms.kingdom.KingdomManager;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;

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
		
		if (KingdomPlayer.getKingdom(player.getUniqueId()).equals("None")) {
        	sender.sendMessage(ChatColor.RED + "You aren't in a kingdom!");
        	return true;
		}
		
		if (KingdomPlayer.getClass(player.getUniqueId()).equals("King") || KingdomPlayer.getClass(player.getUniqueId()).equals("Queen")) {
			if (args.length < 2) {
				player.sendMessage(ChatColor.RED + getUsage("diplomacy"));
			} else {
				String targetKingdom = args[1];
				
				if (!KingdomManager.kingdomExists(targetKingdom)) {
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
