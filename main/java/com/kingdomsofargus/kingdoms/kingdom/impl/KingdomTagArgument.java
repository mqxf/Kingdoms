package com.kingdomsofargus.kingdoms.kingdom.impl;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.KingdomManager;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.Utils;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;

import net.md_5.bungee.api.ChatColor;

public class KingdomTagArgument extends CommandArgument {


	private final Kingdoms plugin;
	
	public KingdomTagArgument(Kingdoms plugin) {
        super("tag", "Add a tag to your kingdom");
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You must be a player to execute this command");
			return true;
		} else {
			 if (args.length == 0) {
		            sender.sendMessage(ChatColor.RED + "Usage: " + getUsage(label));
		            return true;
		        }
		        
		        
		        Player player = (Player) sender;
		        String kingdom = KingdomPlayer.getKingdom(player.getUniqueId());
		        
		        if (kingdom.equals("NONE")) {
		        	sender.sendMessage(ChatColor.RED + "You aren't in a kingdom!");
		        	return true;
		        } else {
		        
			        if	(KingdomPlayer.getClass(player.getUniqueId()).equals("King") || KingdomPlayer.getClass(player.getUniqueId()).equals("Queen")) {
			        	if (args.length == 1) {
			        		player.sendMessage(Utils.chat("&cThe tag must be 1 word long"));
			        	} else {
			        		if (args[1].toString().length() > 8) {
			        			player.sendMessage(ChatColor.RED + "Tags can only be 1 - 8 char long");
			        			return true;
			        		}
			        		KingdomManager.setTag(kingdom, args[1]);
			        		player.sendMessage(Utils.chat("&eSuccessfully changed the tag of your kingdom to &6&l" + args[1]));

			        	}
			        }
		        		
		        	
		        	
		        }        
		}
		
		return false;
	}

}
