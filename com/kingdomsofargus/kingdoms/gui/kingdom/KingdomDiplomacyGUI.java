package com.kingdomsofargus.kingdoms.gui.kingdom;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.kingdomsofargus.kingdoms.kingdom.KingdomManager;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.ItemBuilder;
import com.kingdomsofargus.kingdoms.utils.Utils;

@SuppressWarnings("deprecation")
public class KingdomDiplomacyGUI {

	public static void applyWarUI(Player player, int time, boolean apply) {
		
		Inventory inv;
		
		if (apply) {
			inv = Bukkit.createInventory(null, 27, Utils.chat("&cGo to war. &8(" + time + ")"));
		}
		else {
			inv = Bukkit.createInventory(null, 27, Utils.chat("&cGo to war."));
		}
		
		ItemStack cancel = ItemBuilder.createItem(Material.RED_CONCRETE, "&cCancel", 1);
		
		ItemStack wait = ItemBuilder.createItem(Material.BARRIER, "&cPlease wait...", 1);
		
		ItemStack pane = ItemBuilder.createItem(Material.GRAY_STAINED_GLASS_PANE, " ", 1);
		
		inv.setItem(11, cancel);
		inv.setItem(15, wait);
		
		for (int i = 0; i < 27; i++) {
			if (i == 11 || i == 15) {
				
			}
			else {
				inv.setItem(i, pane);
			}
		}
		
	}
	
	public static void applyDiplomacyUI(Player player) {
		Inventory inventory = Bukkit.getServer().createInventory(null, 27, "Change diplomacy");
		
		ItemStack ally = ItemBuilder.createItem(Material.PINK_WOOL, "&dAlly", 1);
		
		ItemStack neutral = ItemBuilder.createItem(Material.WHITE_WOOL, "&fNeutral", 1);
		
		ItemStack enemy = ItemBuilder.createItem(Material.RED_WOOL, "&cEnemy", 1);
		
		ItemStack pane = ItemBuilder.createItem(Material.GRAY_STAINED_GLASS_PANE, " ", 1);
		
		inventory.setItem(11, ally);
		inventory.setItem(13, neutral);
		inventory.setItem(15, enemy);
		
		for (int i = 0; i < 27; i++) {
			if (1 == 11 || i == 13 ||i == 15) {
				
			}
			else {
				inventory.setItem(i, pane);
			}
		}
		
	}
	
	public static void applyPeaceTreatyUI(Player player) {
		
		Inventory inventory = Bukkit.getServer().createInventory(null, 27, "Offer peace treaty");
		
		ItemStack map = ItemBuilder.createItem(Material.MAP, "&fSend peace treaty", 1);
		
	}
	
	public static void applyMenu(Player player) {
		Inventory inventory = Bukkit.getServer().createInventory((InventoryHolder) null , 27, "Kingdom dpilomacy");
		
		String kingdom = KingdomPlayer.getKingdom(player.getUniqueId());
		String leaderName = KingdomManager.getLeader(kingdom);
		
		ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (byte) SkullType.PLAYER.ordinal());
		SkullMeta sm = (SkullMeta) head.getItemMeta();
		List<String> sl = new ArrayList<>();
		sm.setOwner(leaderName);
		sm.setDisplayName(Utils.chat("&6" + leaderName));
		Player leader = Bukkit.getPlayer(leaderName);
		
		sl.add(Utils.chat("&eRelationship: &7"));
		sl.add(Utils.chat("&eRenown: &7"));
		sl.add(Utils.chat("&ePower: &7"));
		sl.add(Utils.chat("&eLand: &7"));
		sl.add(Utils.chat("&eBank: &7" + KingdomManager.getBank(kingdom)));
		sl.add(Utils.chat("&eWars W/L: &7"));
		
		sm.setLore(sl);
		head.setItemMeta(sm);
		
		List<String> lore = new ArrayList<String>();
		lore.add(Utils.chat("&eChange to: &bAlly, &fNeutral or &cEnemy"));
		
		ItemStack lectern = ItemBuilder.createItem(Material.LECTERN, "&bChange diplomacy", 1, lore);
		
		lore.clear();
		lore.add(Utils.chat("&eYou can even sesnd a gift with the treaty."));
		
		ItemStack map = ItemBuilder.createItem(Material.MAP, "&fOffer peace treaty", 1, lore);
		
		ItemStack war = ItemBuilder.createItem(Material.GOLDEN_SWORD, "&4Go to war", 1);
		
		ItemStack exit = ItemBuilder.createItem(Material.BARRIER, "&cClose", 1);
		
		ItemStack pane = ItemBuilder.createItem(Material.GRAY_STAINED_GLASS_PANE, " ", 1);
		
		inventory.setItem(11, head);
		inventory.setItem(12, lectern);
		inventory.setItem(13, map);
		inventory.setItem(14, war);
		inventory.setItem(16, exit);
		
		for (int i = 0; i < 27; i++) {
			if (i > 9 && i < 17) {
				
			}
			else {
				inventory.setItem(i, pane);
			}
		}
		
		
		player.openInventory(inventory);
	}
	
}
