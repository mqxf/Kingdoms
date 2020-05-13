package com.kingdomsofargus.kingdoms.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.rank.permissions.Permissions;
import com.kingdomsofargus.kingdoms.utils.Utils;

public class QuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		event.setQuitMessage(Utils.chat("&7[&c-&7] " + event.getPlayer().getName()));
		Permissions.playerPermissions.remove(event.getPlayer().getUniqueId());
		Kingdoms.getCore().combined.remove(event.getPlayer());
	}
}
