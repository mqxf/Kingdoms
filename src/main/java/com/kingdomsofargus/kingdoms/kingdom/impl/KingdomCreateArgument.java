package com.kingdomsofargus.kingdoms.kingdom.impl;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.gui.kingdom.KingdomCreatePlayerUI;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KingdomCreateArgument extends CommandArgument {

	private final Kingdoms plugin;
	
	public KingdomCreateArgument(Kingdoms plugin) {
        super("create", "Create a kingdom", new String[]{"make", "define"});
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
        
        if (name.length() < 4) {
        	sender.sendMessage(ChatColor.RED + "Kingdom names must have at least 4 characters.");
        	return true;
        }
        
        if (name.length() > 16) {
        	sender.sendMessage(ChatColor.RED + "Kingdom names must cannot be longer than 16 characters.");
        	return true;
        }
        
        if (!Kingdoms.getCore().getKindomManager().kingdomExists(name)) {
        	sender.sendMessage(ChatColor.RED + "That name is already taken!");
        	return true;
        }
        
        Player player = (Player) sender;
        int kingdom = Kingdoms.getCore().getUserManager().getUser(player).getKingdom_id();
        if (kingdom != 0) {
        	sender.sendMessage(ChatColor.RED + "You are already in a kingdom!");
        	return true;
        } else {
        
        KingdomCreatePlayerUI.menuCountdown(player);
        //Kingdoms.getCore().kingdom.put(player, name);
        }        
		return false;
	}

}
