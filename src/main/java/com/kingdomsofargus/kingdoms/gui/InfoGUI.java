package com.kingdomsofargus.kingdoms.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.kingdomsofargus.kingdoms.utils.Utils;

public class InfoGUI {

	public static void apply(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, Utils.chat("&8INFO"));
		
		player.openInventory(inv);
	}
	
}
