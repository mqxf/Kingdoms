package com.kingdomsofargus.kingdoms.gui;

import com.kingdomsofargus.kingdoms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class DisguiseGUI {

	
	public static void apply(Player player) {
		Inventory inventory = Bukkit.getServer().createInventory((InventoryHolder) null, 54, Utils.chat("&8Disguise GUI"));
		/**
		for (String string : Kingdoms.getCore().getConfig().getConfigurationSection("ranks").getKeys(false)) {
			Material material = Material.valueOf(Kingdoms.getCore().getConfig().getString("ranks." + string + "material"));
			String name = Kingdoms.getCore().getConfig().getString("ranks." + string + ".name");
			
			if (material != null) {
				inventory.addItem();
			} else {
				player.sendMessage(ChatColor.RED + "There is an error with the materials of the ranks, please contact a higher-up.");
			}
		}
		**/
		
		player.openInventory(inventory);
	}
	
}
