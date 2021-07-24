package com.kingdomsofargus.kingdoms.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("deprecation")
public class ItemBuilder {
	
	public static ItemStack createItem(Material material, String name, int amount, short type) { // NEED TO MAKE IT SUPPORT MORE
		ItemStack itemStack = new ItemStack(material, amount, type);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(Utils.chat(name));
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack createItem(Material material, String name, int amount) { // NEED TO MAKE IT SUPPORT MORE
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(Utils.chat(name));
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack createItem(Material material, String name, int amount, short type, List<String> lore) { 
		ItemStack itemStack = new ItemStack(material, amount, type);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(Utils.chat(name));
		itemMeta.setLore(lore);
		
		itemStack.setItemMeta(itemMeta);
		
		
		return itemStack;
	}
	
}
