package com.kingdomsofargus.kingdoms.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;

public class RankCommand implements CommandExecutor {

	private Kingdoms main;
	
	public RankCommand(Kingdoms main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			Player player = (Player) sender;
			if (args.length == 1) {
				if (Bukkit.getPlayer(args[0]) != null) {
					Player target = Bukkit.getPlayer(args[0]);
					
					String rank = KingdomPlayer.getRank(player.getUniqueId());
					sender.sendMessage(Utils.chat("&6&l" + target.getDisplayName() + "'s &r&erank is &l" + rank));
				} else {
					sender.sendMessage(Utils.chat("&cThat player is not online or doesn't exist"));
				}
			}
			else if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Usage: /rank <player>");
			}
			else {
				sender.sendMessage("Too many arguments");
			}
		return false;
	}
}