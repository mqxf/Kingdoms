package com.kingdomsofargus.kingdoms.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.player.KingdomRanks;
import com.kingdomsofargus.kingdoms.utils.Utils;

public class ChatListener implements Listener {

	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = (Player) e.getPlayer();
		String rank = KingdomPlayer.getRank(player.getUniqueId());
		String prefix = KingdomRanks.getPrefix(rank);
		String classes = KingdomPlayer.getClass(player.getUniqueId());
		String message = e.getMessage();
		
		if (rank.equals("Member")) {
			e.setFormat(Utils.chat("&e[" + classes + "] &7" + " " + player.getName() + "&f: " + message));
		} else {
		e.setFormat(Utils.chat("&e[" + classes + "] &7" + prefix + " " + player.getName() + "&f: " + message));
		}
	}
}