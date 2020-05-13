package com.kingdomsofargus.kingdoms.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	private static List<Material> combinable = new ArrayList<Material>();


	public static String chat(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
	
	public static List<Material> getCombinable() {

		combinable.add(Material.SHEARS);
		combinable.add(Material.BOW);
		combinable.add(Material.FISHING_ROD);
		combinable.add(Material.ENCHANTED_BOOK);
		
		//LEATHER
		combinable.add(Material.LEATHER_BOOTS);
		combinable.add(Material.LEATHER_LEGGINGS);
		combinable.add(Material.LEATHER_CHESTPLATE);
		combinable.add(Material.LEATHER_HELMET);

		//CHAINMAIL
		combinable.add(Material.CHAINMAIL_BOOTS);
		combinable.add(Material.CHAINMAIL_LEGGINGS);
		combinable.add(Material.CHAINMAIL_CHESTPLATE);
		combinable.add(Material.CHAINMAIL_HELMET);
		
		//WOOD
		combinable.add(Material.WOODEN_SWORD);
		combinable.add(Material.WOODEN_HOE);
		combinable.add(Material.WOODEN_AXE);
		combinable.add(Material.WOODEN_PICKAXE);
		combinable.add(Material.WOODEN_SHOVEL);
		
		//STONE
		combinable.add(Material.STONE_SWORD);
		combinable.add(Material.STONE_HOE);
		combinable.add(Material.STONE_AXE);
		combinable.add(Material.STONE_PICKAXE);
		combinable.add(Material.STONE_SHOVEL);
		
		//IRON
		combinable.add(Material.IRON_SWORD);
		combinable.add(Material.IRON_HOE);
		combinable.add(Material.IRON_AXE);
		combinable.add(Material.IRON_PICKAXE);
		combinable.add(Material.IRON_SHOVEL);
		combinable.add(Material.IRON_BOOTS);
		combinable.add(Material.IRON_LEGGINGS);
		combinable.add(Material.IRON_CHESTPLATE);
		combinable.add(Material.IRON_HELMET);
		
		//GOLDEN
		combinable.add(Material.GOLDEN_SWORD);
		combinable.add(Material.GOLDEN_HOE);
		combinable.add(Material.GOLDEN_AXE);
		combinable.add(Material.GOLDEN_PICKAXE);
		combinable.add(Material.GOLDEN_SHOVEL);
		combinable.add(Material.GOLDEN_BOOTS);
		combinable.add(Material.GOLDEN_LEGGINGS);
		combinable.add(Material.GOLDEN_CHESTPLATE);
		combinable.add(Material.GOLDEN_HELMET);

		//DIAMOND
		combinable.add(Material.DIAMOND_SWORD);
		combinable.add(Material.DIAMOND_HOE);
		combinable.add(Material.DIAMOND_AXE);
		combinable.add(Material.DIAMOND_PICKAXE);
		combinable.add(Material.DIAMOND_SHOVEL);
		combinable.add(Material.DIAMOND_BOOTS);
		combinable.add(Material.DIAMOND_LEGGINGS);
		combinable.add(Material.DIAMOND_CHESTPLATE);
		combinable.add(Material.DIAMOND_HELMET);
		
		//ALL ARMOR/SWORD/AXES/PICKAXES/HOES/SHOVELS/
		return combinable;
	}
	
}
