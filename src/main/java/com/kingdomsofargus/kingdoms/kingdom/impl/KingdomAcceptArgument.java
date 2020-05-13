package com.kingdomsofargus.kingdoms.kingdom.impl;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.utils.Utils;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class KingdomAcceptArgument extends CommandArgument {

	private final Kingdoms plugin;
	public static HashMap<String, UUID> receievedInvite = new HashMap<String, UUID>();
	
	public KingdomAcceptArgument(Kingdoms plugin) {
        super("accept", "Accept a kingdom invite", new String[]{"join, j"});
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName() + " <player>";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You cannot execute this command as console.");
			return true;
		}
		else {
			Player player = (Player) sender;
			if (Kingdoms.getCore().invite.get(player)) {
				String kingdom = Kingdoms.getCore().inviteName.get(player);
				player.sendMessage(Utils.chat("&aJoined &b" + kingdom));
				//KingdomPlayer.setKingdom(player.getUniqueId(), kingdom);
				// TODO
			}
			else {
				player.sendMessage(Utils.chat("&cYou have not been invited into a kingdom in the past minute."));
			}
		}
        	
        	
        
		return false;
	}

}
