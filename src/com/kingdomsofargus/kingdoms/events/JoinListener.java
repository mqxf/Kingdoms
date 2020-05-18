package com.kingdomsofargus.kingdoms.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.gui.GenderGUI;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.rank.permissions.Permissions;
import com.kingdomsofargus.kingdoms.sql.Methods;

import net.md_5.bungee.api.ChatColor;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(null);
		
		Kingdoms.getInstance().KP.put(player, new KingdomPlayer(player));
		
		if (!Methods.playerExists(player.getUniqueId())) {
			Methods.createPlayer(player.getUniqueId(), player);
			player.sendMessage(ChatColor.GREEN + "Created Profile");
			GenderGUI.applyUI(player);
		} else {
			Methods.getPlayer(player.getUniqueId(), player);
			player.sendMessage(ChatColor.GREEN + "Loaded Profile");
		}
	
		Methods.getPlayer(player.getUniqueId(), player);
		Permissions.setupPermissions(player);
	}
}