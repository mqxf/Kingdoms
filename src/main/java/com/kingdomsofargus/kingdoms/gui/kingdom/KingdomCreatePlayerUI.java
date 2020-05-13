package com.kingdomsofargus.kingdoms.gui.kingdom;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KingdomCreatePlayerUI implements Listener {

	public static void menuCountdown(Player player) {

		//User u = Kingdoms.getCore().USERS.get(player);

		ItemStack yes = new ItemStack(Material.LIME_CONCRETE);
		ItemMeta yesMeta = yes.getItemMeta();
		yesMeta.setDisplayName(Utils.chat("&aCreate"));
		yes.setItemMeta(yesMeta);

		KingdomCreateGUI.applyMenu(player, 5, true);

		int task1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Kingdoms.getCore(), new Runnable() {
			public void run() {
				if (player.getOpenInventory().getTitle().contains("Please confirm your details.")) {
					KingdomCreateGUI.applyMenu(player, 4, true);
				}
				else {

				}
			}
		}, 20L);

		int task2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Kingdoms.getCore(), new Runnable() {
			public void run() {
				if (player.getOpenInventory().getTitle().contains("Please confirm your details.")) {
					KingdomCreateGUI.applyMenu(player, 3, true);
				}
				else {

				}
			}
		}, 40L);

		int task3 = Bukkit.getScheduler().scheduleSyncDelayedTask(Kingdoms.getCore(), new Runnable() {
			public void run() {
				if (player.getOpenInventory().getTitle().contains("Please confirm your details.")) {
					KingdomCreateGUI.applyMenu(player, 2, true);
				}
				else {

				}
			}
		}, 60L);

		int task4 = Bukkit.getScheduler().scheduleSyncDelayedTask(Kingdoms.getCore(), new Runnable() {
			public void run() {
				if (player.getOpenInventory().getTitle().contains("Please confirm your details.")) {
					KingdomCreateGUI.applyMenu(player, 1, true);
				}
				else {

				}
			}
		}, 80L);

		int task5 = Bukkit.getScheduler().scheduleSyncDelayedTask(Kingdoms.getCore(), new Runnable() {
			public void run() {
				if (player.getOpenInventory().getTitle().contains("Please confirm your details.")) {
					KingdomCreateGUI.applyMenu(player, 0, false);
					player.getOpenInventory().setItem(15, yes);
				}
				else {

				}
			}
		}, 100L);

		int task6 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Kingdoms.getCore(), new Runnable() {
			public void run() {
				if (!player.getOpenInventory().getTitle().contains("Please confirm your details.")) {

				}
			}
		}, 0, 1L);


	}
	
}
