package com.kingdomsofargus.kingdoms.gui.kingdom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.player.User;
import com.kingdomsofargus.kingdoms.utils.Utils;

public class KingdomDiplomacyPlayerUI {

	private static int time = 5;
	
	public void warCountdown(Player player) {
		
		User u = Kingdoms.getInstance().USERS.get(player);
		
		ItemStack yes = new ItemStack(Material.GOLDEN_SWORD);
		ItemMeta yesMeta = yes.getItemMeta();
		yesMeta.setDisplayName(Utils.chat("&Go to war"));
		yes.setItemMeta(yesMeta);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Kingdoms.getInstance(), new Runnable() {
			public void run() {
				if (player.getOpenInventory().getTitle().contains("Go to war.")) {
					KingdomDiplomacyGUI.applyWarUI(player, 4, true);
				}
				
				
				
				else {
					time--;
				}
			}
		}, 20L);
		
		KingdomDiplomacyGUI.applyWarUI(player, 5, true);
		
		int task1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Kingdoms.getInstance(), new Runnable() {
			public void run() {
				if (player.getOpenInventory().getTitle().contains("Go to war.")) {
					KingdomDiplomacyGUI.applyWarUI(player, 4, true);
				}
				else {
					Bukkit.getScheduler().cancelTask(u.getTask1());
					Bukkit.getScheduler().cancelTask(u.getTask2());
					Bukkit.getScheduler().cancelTask(u.getTask3());
					Bukkit.getScheduler().cancelTask(u.getTask4());
					Bukkit.getScheduler().cancelTask(u.getTask5());
					Bukkit.getScheduler().cancelTask(u.getTask6());
				}
			}
		}, 20L);
	
		int task2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Kingdoms.getInstance(), new Runnable() {
			public void run() {
				if (player.getOpenInventory().getTitle().contains("Go to war.")) {
					KingdomDiplomacyGUI.applyWarUI(player, 3, true);
				}
				else {
					Bukkit.getScheduler().cancelTask(u.getTask1());
					Bukkit.getScheduler().cancelTask(u.getTask2());
					Bukkit.getScheduler().cancelTask(u.getTask3());
					Bukkit.getScheduler().cancelTask(u.getTask4());
					Bukkit.getScheduler().cancelTask(u.getTask5());
					Bukkit.getScheduler().cancelTask(u.getTask6());
				}
			}
		}, 40L);
		
		int task3 = Bukkit.getScheduler().scheduleSyncDelayedTask(Kingdoms.getInstance(), new Runnable() {
			public void run() {
				if (player.getOpenInventory().getTitle().contains("Go to war.")) {
					KingdomDiplomacyGUI.applyWarUI(player, 2, true);
				}
				else {
					Bukkit.getScheduler().cancelTask(u.getTask1());
					Bukkit.getScheduler().cancelTask(u.getTask2());
					Bukkit.getScheduler().cancelTask(u.getTask3());
					Bukkit.getScheduler().cancelTask(u.getTask4());
					Bukkit.getScheduler().cancelTask(u.getTask5());
					Bukkit.getScheduler().cancelTask(u.getTask6());
				}
			}
		}, 60L);
		
		int task4 = Bukkit.getScheduler().scheduleSyncDelayedTask(Kingdoms.getInstance(), new Runnable() {
			public void run() {
				if (player.getOpenInventory().getTitle().contains("Go to war.")) {
					KingdomDiplomacyGUI.applyWarUI(player, 1, true);
				}
				else {
					Bukkit.getScheduler().cancelTask(u.getTask1());
					Bukkit.getScheduler().cancelTask(u.getTask2());
					Bukkit.getScheduler().cancelTask(u.getTask3());
					Bukkit.getScheduler().cancelTask(u.getTask4());
					Bukkit.getScheduler().cancelTask(u.getTask5());
					Bukkit.getScheduler().cancelTask(u.getTask6());
				}
			}
		}, 80L);
		
		int task5 = Bukkit.getScheduler().scheduleSyncDelayedTask(Kingdoms.getInstance(), new Runnable() {
			public void run() {
				if (player.getOpenInventory().getTitle().contains("Go to war.")) {
					KingdomDiplomacyGUI.applyWarUI(player, 0, false);
					player.getOpenInventory().setItem(15, yes);
				}
				else {
					Bukkit.getScheduler().cancelTask(u.getTask1());
					Bukkit.getScheduler().cancelTask(u.getTask2());
					Bukkit.getScheduler().cancelTask(u.getTask3());
					Bukkit.getScheduler().cancelTask(u.getTask4());
					Bukkit.getScheduler().cancelTask(u.getTask5());
					Bukkit.getScheduler().cancelTask(u.getTask6());
				}
			}
		}, 100L);
		
		int task6 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Kingdoms.getInstance(), new Runnable() {
			public void run() {
				if (!player.getOpenInventory().getTitle().contains("Go to war.")) {
					Bukkit.getScheduler().cancelTask(u.getTask1());
					Bukkit.getScheduler().cancelTask(u.getTask2());
					Bukkit.getScheduler().cancelTask(u.getTask3());
					Bukkit.getScheduler().cancelTask(u.getTask4());
					Bukkit.getScheduler().cancelTask(u.getTask5());
					Bukkit.getScheduler().cancelTask(u.getTask6());
				}
			}
		}, 0, 1L);
		
		u.setTask1(task1);
		u.setTask2(task2);
		u.setTask3(task3);
		u.setTask4(task4);
		u.setTask5(task5);
		u.setTask6(task6);
		
	}
	
}
