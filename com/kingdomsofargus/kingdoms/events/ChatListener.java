package com.kingdomsofargus.kingdoms.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.player.KingdomRanks;
import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;
import pw.freddie.permissions.Permissions;
import pw.freddie.permissions.ranks.RankManager;
import pw.freddie.permissions.ranks.UserManager;

public class ChatListener implements Listener {

	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = (Player) e.getPlayer();
		String rank = KingdomPlayer.getRank(player.getUniqueId());
		String prefix = KingdomRanks.getPrefix(rank);
		String classes = KingdomPlayer.getClass(player.getUniqueId());
		String message = e.getMessage();
		
		String userRank = UserManager.getPlayersRank(player);
		String rankPrefix = RankManager.getRankPrefix(userRank);
		
			if (rankPrefix.length() < 3) {
				e.setFormat(Utils.chat(rankPrefix + e.getPlayer().getName()) + ChatColor.GRAY + ": " + e.getMessage());
			} else {
				e.setFormat(Utils.chat(rankPrefix + " " + e.getPlayer().getName()) + ChatColor.GRAY + ": " + e.getMessage());
			}
			
		/*
		if (rank.equals("Member")) {
			e.setFormat(Utils.chat("&e[" + classes + "] &7" + " " + player.getName() + "&f: " + message));
		} else {
		e.setFormat(Utils.chat("&e[" + classes + "] &7" + prefix + " " + player.getName() + "&f: " + message));
		}*/
	}
}