package com.kingdomsofargus.kingdoms.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;

@SuppressWarnings("deprecation")
public class PickUpListener implements Listener {

/*	@EventHandler
	public void onPickUpEvent(PlayerPickupItemEvent event) {
		
		Material material = event.getItem().getItemStack().getType();
		int amount = event.getItem().getItemStack().getAmount();
		ItemStack stack = event.getItem().getItemStack();
		
		if (material == Material.GOLD_INGOT) {
			if (stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()) {
				String name = stack.getItemMeta().getDisplayName();
				if (name.contains("money")) {
					double oldBalance = KingdomPlayer.getPurseBalance(event.getPlayer().getUniqueId());
					KingdomPlayer.setPurseBalance(event.getPlayer().getUniqueId(), (oldBalance + 1));
				}
			}
		}
		else if (material == Material.GOLD_NUGGET) {
			if (stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()) {
				String name = stack.getItemMeta().getDisplayName();
				if (name.contains("money")) {
					double oldBalance = KingdomPlayer.getPurseBalance(event.getPlayer().getUniqueId());
					KingdomPlayer.setPurseBalance(event.getPlayer().getUniqueId(), (oldBalance + 0.10));
				}
			}
		}
	}
	*/
}
