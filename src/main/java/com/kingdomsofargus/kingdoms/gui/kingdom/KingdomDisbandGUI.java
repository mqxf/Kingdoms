package com.kingdomsofargus.kingdoms.gui.kingdom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kingdomsofargus.kingdoms.utils.Utils;

public class KingdomDisbandGUI {

	public static void apply(Player player, int number, boolean apply) {
		
		Inventory inv;
		
		if (apply) {
			inv = Bukkit.createInventory(null, 27, Utils.chat("&cAre you sure? &8(" + number + ")"));
		}
		else {
			inv = Bukkit.createInventory(null, 27, Utils.chat("&cAre you sure?"));
		}
		
			
		ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta paneMeta = pane.getItemMeta();
		paneMeta.setDisplayName(" ");
		pane.setItemMeta(paneMeta);
		
		ItemStack wait = new ItemStack(Material.BARRIER);
		ItemMeta yesMeta = wait.getItemMeta();
		yesMeta.setDisplayName(Utils.chat("&cPlease wait before confirming."));
		wait.setItemMeta(yesMeta);
		
		ItemStack no = new ItemStack(Material.RED_CONCRETE);
		ItemMeta noMeta = no.getItemMeta();
		noMeta.setDisplayName(Utils.chat("&cNo"));
		no.setItemMeta(noMeta);
		
		for (int i = 0; i < 27; i++) {
			if (i == 11) {
				inv.setItem(i, no);
			}
			else if (i == 15) {
				inv.setItem(i, wait);
			}
			else {
				inv.setItem(i, pane);
			}
		}
		
		player.openInventory(inv);
		
	}
	
}
