package com.kingdomsofargus.kingdoms.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener; 
import org.bukkit.event.inventory.InventoryClickEvent;

import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.Utils;

public class InventoryListener implements Listener {
	
	@EventHandler
	public void onClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (event.getView().getTitle().contains("Gender Picker")) {
			event.setCancelled(true);
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&9King"))) {
				KingdomPlayer.setGender(player.getUniqueId(), "Male");
				player.sendMessage(Utils.chat("&eYou are now a &9&lKing"));
				player.closeInventory();
			}  
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&cQueen"))) {
				KingdomPlayer.setGender(player.getUniqueId(), "Female"); 
				player.sendMessage(Utils.chat("&eYou are now a &c&lQueen"));
				player.closeInventory();
			}
			event.setCancelled(true);
		}
	}
}
