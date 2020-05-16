package com.kingdomsofargus.kingdoms.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.kingdomsofargus.kingdoms.gui.custom.AnvilUI;

public class ClickListener implements Listener {

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.ANVIL || e.getClickedBlock().getType() == Material.CHIPPED_ANVIL || e.getClickedBlock().getType() == Material.DAMAGED_ANVIL) {
			    AnvilUI.applyUI(e.getPlayer());
				e.setCancelled(true);
			}
			else if (e.getClickedBlock().getType() == Material.CRAFTING_TABLE) {
				e.setCancelled(true);
			}
			else if (e.getClickedBlock().getType() == Material.BREWING_STAND) {
				e.setCancelled(true);
			}
		}
	}
	
}
