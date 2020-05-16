package com.kingdomsofargus.kingdoms.gui.kingdom;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.ItemBuilder;
import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;
import scala.collection.immutable.ArraySeq.ofRef;

public class KingdomCreateGUI {
	
	public static void applyMenu(Player player, int time, boolean apply) {
			Inventory inv;
		
		if (apply) {
			inv = Bukkit.createInventory(null, 27, Utils.chat("&cPlease confirm your details. &8(" + time + ")"));
		}
		else {
			inv = Bukkit.createInventory(null, 27, Utils.chat("&cPlease confirm your details."));
		}
		
		//13
		ItemStack gender = ItemBuilder.createItem(Material.FEATHER, "&cConfirm gender &7(" + KingdomPlayer.getGender(player.getUniqueId()) + ")", 1);
		
		ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta paneMeta = pane.getItemMeta();
		paneMeta.setDisplayName("");
		pane.setItemMeta(paneMeta);
		
		//17
		ItemStack next = ItemBuilder.createItem(Material.BARRIER, "&cPlease wait.", 1);
		
		for (int i = 0; i < 27; i++) {
			if (i == 11) {
				inv.setItem(i, gender);
			}
			else if (i == 15) {
				inv.setItem(i, next);
			}
			else {
				inv.setItem(i, pane);
			}
		}
		
		player.openInventory(inv);
		
	}
	
	public static void applyGenderUI(Player player) {
		Inventory inventory = Bukkit.getServer().createInventory((InventoryHolder) null, 9, Utils.chat("&8Confirm gender"));
		ItemStack kingWool = new ItemStack(Material.BLUE_WOOL);
		ItemMeta kingWoolMeta = kingWool.getItemMeta();
		kingWoolMeta.setDisplayName(Utils.chat("&9King"));
		List<String> lore = new ArrayList<String>();
		lore.add(Utils.chat("&9Click me to become a"));
		lore.add(Utils.chat("&9&lKING!"));
		kingWoolMeta.setLore(lore);
		kingWool.setItemMeta(kingWoolMeta);
		
		ItemStack queenWool = new ItemStack(Material.RED_WOOL);
		ItemMeta queenWoolMeta = queenWool.getItemMeta();
		queenWoolMeta.setDisplayName(Utils.chat("&cQueen"));	
		List<String> queenLore = new ArrayList<String>();
		queenLore.add(Utils.chat("&cClick me to become a"));
		queenLore.add(Utils.chat("&c&lQUEEN!"));
		queenWoolMeta.setLore(queenLore);
		queenWool.setItemMeta(queenWoolMeta);
		
		ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta paneMeta = pane.getItemMeta();
		paneMeta.setDisplayName("");
		pane.setItemMeta(paneMeta);
		
		inventory.setItem(0, pane);
		inventory.setItem(1, pane);
		inventory.setItem(2, kingWool);
		inventory.setItem(3, pane);
		inventory.setItem(4, pane);
		inventory.setItem(5, pane);
		inventory.setItem(6, queenWool);
		inventory.setItem(7, pane);
		inventory.setItem(8, pane);
		
		player.openInventory(inventory);
		
		
	}
	
	
}
