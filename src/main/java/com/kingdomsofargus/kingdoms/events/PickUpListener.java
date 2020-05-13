package com.kingdomsofargus.kingdoms.events;

import org.bukkit.event.Listener;

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
