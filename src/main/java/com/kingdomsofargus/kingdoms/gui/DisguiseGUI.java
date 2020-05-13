package com.kingdomsofargus.kingdoms.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.utils.ItemBuilder;
import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;

public class DisguiseGUI {

	
	public static void apply(Player player) {
		Inventory inventory = Bukkit.getServer().createInventory((InventoryHolder) null, 54, Utils.chat("&8Disguise GUI"));
		
		for (String string : Kingdoms.getInstance().getConfig().getConfigurationSection("ranks").getKeys(false)) {
			Material material = Material.valueOf(Kingdoms.getInstance().getConfig().getString("ranks." + string + "material"));
			String name = Kingdoms.getInstance().getConfig().getString("ranks." + string + ".name");
			
			if (material != null) {
				inventory.addItem(/*ITEM*/);
			} else {
				player.sendMessage(ChatColor.RED + "There is an error with the materials of the ranks, please contact a higher-up.");
			}
		}
		
		player.openInventory(inventory);
	}
	
}
