package com.kingdomsofargus.kingdoms.commands.economy;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.sql.Methods;
import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;

public class BalanceCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You cannot execute this command as console");
		} else {
			Player player = (Player) sender;
			
			if (args.length == 0) {
				double bankBalance = KingdomPlayer.getBankBalance(player.getUniqueId());
				double purseBalance = KingdomPlayer.getPurseBalance(player.getUniqueId());
				player.sendMessage(Utils.chat("&7&m--------------------"));
				player.sendMessage(Utils.chat("&6&lYour Balance"));
				player.sendMessage(Utils.chat("&7&m--------------------"));
				player.sendMessage(Utils.chat("&6Purse Coins: &e" + purseBalance));
				player.sendMessage(Utils.chat("&6Bank Coins: &e" + bankBalance));
				player.sendMessage(Utils.chat("&7&m--------------------"));		
			}
			
			if (args.length == 1) {
				if (Bukkit.getPlayer(args[0]) != null) {
					Player target = Bukkit.getPlayer(args[0]);
					
					double bankBalance = KingdomPlayer.getBankBalance(target.getUniqueId());
					double purseBalance = KingdomPlayer.getPurseBalance(target.getUniqueId());
					
					player.sendMessage(Utils.chat("&7&m--------------------"));
					player.sendMessage(Utils.chat("&6&l" + target.getName() + "'s Balance"));
					player.sendMessage(Utils.chat("&7&m--------------------"));
					player.sendMessage(Utils.chat("&6Purse Coins: &e" + purseBalance));
					player.sendMessage(Utils.chat("&6Bank Coins: &e" + bankBalance));
					player.sendMessage(Utils.chat("&7&m--------------------"));		
				}
				else {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					
					if (Methods.playerExists(target.getUniqueId())) {
						
						double purse = KingdomPlayer.getPurseBalance(target.getUniqueId());
						double bank = KingdomPlayer.getBankBalance(target.getUniqueId());
						
						player.sendMessage(Utils.chat("&7&m--------------------"));
						player.sendMessage(Utils.chat("&6&l" + target.getName() + "'s Balance"));
						player.sendMessage(Utils.chat("&7&m--------------------"));
						player.sendMessage(Utils.chat("&6Purse Coins: &e" + purse));
						player.sendMessage(Utils.chat("&6Bank Coins: &e" + bank));
						player.sendMessage(Utils.chat("&7&m--------------------"));		
					} else {
						player.sendMessage(ChatColor.RED + "That player has not played before.");
					}
				}
			}
		}
		return false;
	}

}
