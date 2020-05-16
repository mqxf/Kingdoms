package com.kingdomsofargus.kingdoms.events.anticheat;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.player.User;

public class JesusCheck implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		User user = Kingdoms.getInstance().USERS.get(player);
		
		if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR
				|| player.getAllowFlight() == true) {
			return;
		}
		
	}
	
	public boolean onTopOfWater(Player player) {
		if (onTopOfWater(player.getLocation())) return true;
		return false;
	}
	
	public boolean onTopOfWater(Location location) {
		location.setY(location.getY() - 1.0);
		double expand = 0.3;
		for (double x = -expand; x <= expand; x += expand) {
			for (double z = -expand; z <= expand; z += expand) {
				if (location.clone().add(x, -0.5001, z).getBlock().getType() != Material.AIR) {
					return true;
				}
			}
		}
		return false;
	}

}
