package com.kingdomsofargus.kingdoms.commands.ranks;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.player.KingdomRanks;

import net.md_5.bungee.api.ChatColor;

public class DelRankCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Usage: /delrank <rank>");
			} else {
				if (KingdomRanks.rankExists(args[0])) {
					delRank(args[0]);
					Kingdoms.getInstance().ranks.remove(args[0]);
					Kingdoms.getInstance().getConfig().set("ranks." + args[0], null);
					Kingdoms.getInstance().getConfig().set("ranks." + args[0] + ".prefix", null);
					Kingdoms.getInstance().getConfig().set("ranks." + args[0] + ".permissions", null);
				} else {
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "That rank does not exist.");
					}
				}
			} else {
			Player player = (Player) sender;
			
			if (!player.hasPermission("commands.ranks.delete")) {
				player.sendMessage(ChatColor.RED + "No Permission");
			} else {
				if (args.length == 0) {
					player.sendMessage(ChatColor.RED + "Usage: /delrank <rank>");
				} else {
					if (KingdomRanks.rankExists(args[0])) {
						delRank(args[0]);
						Kingdoms.getInstance().ranks.remove(args[0]);
						Kingdoms.getInstance().getConfig().set("ranks." + args[0], null);
						Kingdoms.getInstance().getConfig().set("ranks." + args[0] + ".prefix", null);
						Kingdoms.getInstance().getConfig().set("ranks." + args[0] + ".permissions", null);
					} else {
						player.sendMessage(ChatColor.RED + "That rank does not exist.");
					}
				}
			}
		}
		
		return false;
	}
	
	public void delRank(String rank) {
		try {
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement("delete from ranks where name=?");
			statement.setString(1, rank);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
