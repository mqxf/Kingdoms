package com.kingdomsofargus.kingdoms.kingdom.impl;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.KingdomExecutor;
import com.kingdomsofargus.kingdoms.kingdom.KingdomManager;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.Utils;
import com.kingdomsofargus.kingdoms.utils.command.BukkitUtils;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;

import net.md_5.bungee.api.ChatColor;

public class KingdomWhoArgument extends CommandArgument {

	private final Kingdoms plugin;
	
	public KingdomWhoArgument(Kingdoms plugin) {
        super("who", "Get info on a kingdom", new String[]{"i", "info"});
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName() + " <kingdomName>";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You cannot execute this command as console.");
			return true;
		}
		
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: " + getUsage(label));
            return true;
        }
        
        String name = args[1];
        
        if (!KingdomManager.kingdomExists(name)) {
        	sender.sendMessage(ChatColor.RED + "That kingdom does not exist");
        } else {
        	String kingdomName = name;
        	String kingdomLeader = KingdomManager.getLeader(name);
        	String kingdomTag = KingdomManager.getTag(name);
        	
        	sender.sendMessage(ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
        	sender.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + kingdomName + "'s Info");
        	sender.sendMessage(ChatColor.YELLOW + "  Leader: " + ChatColor.GRAY + kingdomLeader);
        	sender.sendMessage(ChatColor.YELLOW + "  TAG: " + ChatColor.GRAY + kingdomTag);
        	sender.sendMessage(ChatColor.YELLOW + "  Bank: " + ChatColor.GRAY + KingdomManager.getBank(kingdomName));
        	sender.sendMessage("");
        	sender.sendMessage(ChatColor.GOLD.toString() + "Members:");
        	sender.sendMessage(ChatColor.YELLOW + "  " + KingdomManager.getMembers(name));
        	sender.sendMessage(ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
        }
        
		return false;
	}

}
