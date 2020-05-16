package com.kingdomsofargus.kingdoms.gui.custom;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kingdomsofargus.kingdoms.utils.Utils;

public class AnvilUI {
	
	public static void applyUI(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 54, Utils.chat("&8Anvil"));
		
		ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta paneMeta = pane.getItemMeta();
		paneMeta.setDisplayName(" ");
		pane.setItemMeta(paneMeta);
		
		ItemStack red = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		paneMeta = red.getItemMeta();
		paneMeta.setDisplayName(" ");
		red.setItemMeta(paneMeta);
		
		ItemStack r = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta rMeta = r.getItemMeta();
		rMeta.setDisplayName(Utils.chat("&cPlace item below"));
		List<String> rLore = new ArrayList<String>();
		rLore.add(Utils.chat("&7Place an item below"));
		rLore.add(Utils.chat("&7the you want to combine"));
		rMeta.setLore(rLore);
		r.setItemMeta(rMeta);
		
		ItemStack invalid = new ItemStack(Material.BARRIER);
		ItemMeta iMeta = invalid.getItemMeta();
		iMeta.setDisplayName(Utils.chat("&cInvalid"));
		rLore.clear();
		rLore.add(Utils.chat("&7Please place"));
		rLore.add(Utils.chat("&7two items you"));
		rLore.add(Utils.chat("&7want to combine"));
		rLore.add(Utils.chat("&7in the slots"));
		rLore.add(Utils.chat("&7below."));
		invalid.setItemMeta(iMeta);
		
		ItemStack close = new ItemStack(Material.BARRIER);
		paneMeta = close.getItemMeta();
		paneMeta.setDisplayName(Utils.chat("&cClose"));
		close.setItemMeta(paneMeta);
		
		ItemStack combine = new ItemStack(Material.ANVIL);
		paneMeta = combine.getItemMeta();
		paneMeta.setDisplayName(Utils.chat("&aCombine!"));
		List<String> lore = new ArrayList<String>();
		lore.add(Utils.chat("&7Click me to"));
		lore.add(Utils.chat("&7Combine the two"));
		lore.add(Utils.chat("&7items below"));
		paneMeta.setLore(lore);
		combine.setItemMeta(paneMeta);
		
		for (int i = 0; i < 54; i++) {
			//11, 12, 14, 15, 20, 24, 45, 46, 47, 48, 50, 51, 52, 53
			if (i == 45 || i == 46 || i == 47 || i == 48 || i >= 50) {
				inv.setItem(i, red);
			}
			else if (i == 11 || i == 12 || i == 14 || i == 15 || i == 20 || i == 24) {
				inv.setItem(i, r);
			}
			else if (i == 13) {
				inv.setItem(i, invalid);
			}
			else if (i == 22) {
				inv.setItem(i, combine);
			}
			else if (i == 49) {
				inv.setItem(i, close);
			}
			else if (i == 29 || i == 33) {
				inv.setItem(i, null);
			}
			else {
				inv.setItem(i, pane);
			}
		}
		
		player.openInventory(inv);
		
	}
	
}
