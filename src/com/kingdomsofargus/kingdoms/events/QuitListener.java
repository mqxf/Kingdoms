package com.kingdomsofargus.kingdoms.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.kingdomsofargus.kingdoms.rank.permissions.Permissions;

public class QuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		event.setQuitMessage(null);
		Permissions.playerPermissions.remove(event.getPlayer().getUniqueId());
	}
}
