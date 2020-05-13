package com.kingdomsofargus.kingdoms.commands.economy;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Converter;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;

public class SetBalanceCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender,  Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			
		} else {
			Player player = (Player) sender;
			
			if (!player.hasPermission("commands.balance.set")) {
				player.sendMessage(ChatColor.RED + "No Permission");
			} else {
			 if (args.length <= 2) {
					player.sendMessage(ChatColor.RED + "Usage: /setbalance <purse:bank> <player> <amount>");
				} else {
					if (args[0].equals("purse")) {
						if (Bukkit.getPlayer(args[1]) != null) {
							Player target = Bukkit.getPlayer(args[1]);
							
							if (isStringInt(args[2])) {
								KingdomPlayer.setPurseBalance(target.getUniqueId(), Integer.parseInt(args[2]));
								player.sendMessage(Utils.chat("&eSuccessfully set &6&l" + target.getName() + "'s &ePurse Balance to &6&l" + args[2]));
							} else {
								player.sendMessage(ChatColor.RED + "That is not an integer.");
							}
						} else {
							player.sendMessage(ChatColor.RED + "That player is not online");
						}
					} else if (args[0].equals("bank")) {
						if (Bukkit.getPlayer(args[1]) != null) {
							Player target = Bukkit.getPlayer(args[1]);
							
							if (isStringInt(args[2])) {
								KingdomPlayer.setBankBalance(target.getUniqueId(), Integer.parseInt(args[2]));
								player.sendMessage(Utils.chat("&eSuccessfully set &6&l" + target.getName() + "'s &eBank Balance to &6&l" + args[2]));
							} else {
								player.sendMessage(ChatColor.RED + "That is not an integer.");
							}
						} else {
							player.sendMessage(ChatColor.RED + "That player is not online");
						}
					} else {
						player.sendMessage(ChatColor.RED + "Usage: /setbalance <purse:bank> <player> <amount>");
					}
				}
			}
		}
 		
		return false;
	}
	
	public boolean isStringInt(String s) {
	    try
	    {
	        DecimalFormat df = new DecimalFormat("#.00");
	        return true;
	    } catch (NumberFormatException ex) {
	        return false;
	    }
	}
	
}
