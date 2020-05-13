package com.kingdomsofargus.kingdoms.kingdom.impl;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KingdomJoinArgument extends CommandArgument {

	private final Kingdoms plugin;
	
	public KingdomJoinArgument(Kingdoms plugin) {
        super("join", "Join a kingdom", new String[]{"j"});
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName() + " <faction>";
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
        	
        Player player = (Player) sender;
        String name = args[1];

        if (!Kingdoms.getCore().getKindomManager().kingdomExists(name)) {
        	player.sendMessage(ChatColor.RED + "That kingdom does not exist!");
        	return true;
        	}
        
        if (Kingdoms.getCore().getUserManager().getUser(player).getKingdom_id() == 0) {
        	player.sendMessage(ChatColor.RED + "Successfully joined the kingdom.");
        	// TODO Create join system / Member Sysytem
        } else {
        	player.sendMessage(ChatColor.RED + "You are already in a kingdom!");
        	return true;
        }
        
		return false;
	}

}
