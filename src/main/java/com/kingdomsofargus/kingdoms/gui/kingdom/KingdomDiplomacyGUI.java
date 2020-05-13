package com.kingdomsofargus.kingdoms.gui.kingdom;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.Kingdom;
import com.kingdomsofargus.kingdoms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class KingdomDiplomacyGUI {

	public static void applyWarUI(Player player, int time, boolean apply) {
		
	}
	
	public static void applyDiplomacyUI(Player player) {
		
	}
	
	public static void applyPeaceTreatyUI(Player player) {
		
	}
	
	@SuppressWarnings("deprecation")
	public static void applyMenu(Player player) {
		Inventory inventory = Bukkit.getServer().createInventory((InventoryHolder) null , 27, "Diplomacy GUI");
		
		int kingdom = Kingdoms.getCore().getUserManager().getUser(player).getKingdom_id();
		Kingdom userKingdom = Kingdoms.getCore().getKindomManager().getKingdom(kingdom);
		String leaderName = userKingdom.getLeader();
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
		sl.add(Utils.chat("&eBank: &7" + userKingdom.getBank()));
		sl.add(Utils.chat("&eWars W/L: &7"));
		
		sm.setLore(sl);
		head.setItemMeta(sm);
		
		
		
		inventory.setItem(11, head);
		
		player.openInventory(inventory);
	}
	
}
