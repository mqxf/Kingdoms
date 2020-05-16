package com.kingdomsofargus.kingdoms.kingdom.impl;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.Kingdom;
import com.kingdomsofargus.kingdoms.user.User;
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
		User user = Kingdoms.getCore().getUserManager().getUser(player);

		if (args.length < 2) {
			sender.sendMessage(net.md_5.bungee.api.ChatColor.RED + "Usage: " + getUsage(label));
			return true;
		}

		if (user.getKingdom_id() == 0) {
        	sender.sendMessage(ChatColor.RED + "You aren't in a kingdom!");
        	return true;
		}

		Kingdom usersKingdom = Kingdoms.getCore().getKindomManager().getKingdom(user.getKingdom_id());

		if (!usersKingdom.getLeader().equalsIgnoreCase(player.getUniqueId().toString())) {
			player.sendMessage(ChatColor.RED + "Only the leader can invite people.");
			return true;
		}

		String targetK = args[1];

		if (!Kingdoms.getCore().getKindomManager().kingdomExists(targetK)) {
			player.sendMessage(ChatColor.RED + "That kingdom does not exist.");
			return false;
		}

		Kingdom targetKingdom = Kingdoms.getCore().getKindomManager().getKingdom(Kingdoms.getCore().getKindomManager().getKingdomByName(targetK));

		Kingdoms.getCore().getKingdomDiplomacyGUI().applyMenu(player, targetKingdom);
		
		return false;
	}

}
