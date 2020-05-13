package com.kingdomsofargus.kingdoms.commands.npc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.NPC.NPCreator;
import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;

public class NpcCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("commands.npc")) {
				if (args.length > 0)  {
					String skin = args[0];
					NPCreator.createNPC(player.getLocation(), player, skin);
				} else {
					player.sendMessage(Utils.chat("&cUsage: /spawnnpc <skin>"));
				}
				
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "Only players can use this");
		}

		return false;
	}

	
	
	
}
