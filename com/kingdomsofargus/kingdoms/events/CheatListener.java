package com.kingdomsofargus.kingdoms.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.gui.bans.CheatBanGUI;


public class CheatListener implements Listener {

	private Kingdoms main;
	
	public CheatListener(Kingdoms main) {
		this.main = main;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getView().getTitle().contains("Ban")) {
			Player player = (Player) e.getWhoClicked();
			ItemStack item = e.getCurrentItem();
			if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
				e.setCancelled(true);
				String name = item.getItemMeta().getDisplayName();
				
				if (name.contains("Next")) {
					CheatBanGUI.applyBanUI(main.BANUI.get(player), player, 2);
				}
				else if (name.contains("Previous")) {
					CheatBanGUI.applyBanUI(main.BANUI.get(player), player, 1);
				}
				else if (name.contains("Close")) {
					player.closeInventory();
					main.BANUI.remove(player);
				}
				else if (name.contains("KillAura")) {
					player.closeInventory();
					main.banPlayer("KA", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("ClickAura")) {
					player.closeInventory();
					main.banPlayer("CA", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("TpAura")) {
					player.closeInventory();
					main.banPlayer("TA", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("Aimbot")) {
					player.closeInventory();
					main.banPlayer("AM", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("Fightbot")) {
					player.closeInventory();
					main.banPlayer("FB", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("Fastbow")) {
					player.closeInventory();
					main.banPlayer("FA", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("AntiKnockback")) {
					player.closeInventory();
					main.banPlayer("AK", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("Reach")) {
					player.closeInventory();
					main.banPlayer("RE", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("Criticals")) {
					player.closeInventory();
					main.banPlayer("CR", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("AutoClicker")) {
					player.closeInventory();
					main.banPlayer("AC", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("Fly")) {
					player.closeInventory();
					main.banPlayer("FL", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("Glide")) {
					player.closeInventory();
					main.banPlayer("GL", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("Speed")) {
					player.closeInventory();
					main.banPlayer("SP", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("HighJump")) {
					player.closeInventory();
					main.banPlayer("HJ", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("FastFall")) {
					player.closeInventory();
					main.banPlayer("FF", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("NoFall")) {
					player.closeInventory();
					main.banPlayer("NF", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("Spider")) {
					player.closeInventory();
					main.banPlayer("SE", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("LongJump")) {
					player.closeInventory();
					main.banPlayer("LJ", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("Jesus")) {
					player.closeInventory();
					main.banPlayer("JE", main.BANUI.get(player), 25);
					main.BANUI.remove(player);
				}
				else if (name.contains("Timer")) {
					player.closeInventory();
					main.banPlayer("TI", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("Blink")) {
					player.closeInventory();
					main.banPlayer("BL", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("Step")) {
					player.closeInventory();
					main.banPlayer("ST", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("SelfDamage")) {
					player.closeInventory();
					main.banPlayer("SD", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("MiniJump")) {
					player.closeInventory();
					main.banPlayer("MJ", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("Sneak")) {
					player.closeInventory();
					main.banPlayer("SN", main.BANUI.get(player), 7);
					main.BANUI.remove(player);
				}
				else if (name.contains("Phase")) {
					player.closeInventory();
					main.banPlayer("PH", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("QuickLadder")) {
					player.closeInventory();
					main.banPlayer("QL", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("Derp")) {
					player.closeInventory();
					main.banPlayer("DE", main.BANUI.get(player), 7);
					main.BANUI.remove(player);
				}
				else if (name.contains("NoSlowdown")) {
					player.closeInventory();
					main.banPlayer("NS", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("FastUse")) {
					player.closeInventory();
					main.banPlayer("FU", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("FastBreak")) {
					player.closeInventory();
					main.banPlayer("FB", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("FastPlace")) {
					player.closeInventory();
					main.banPlayer("FP", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("Nuker")) {
					player.closeInventory();
					main.banPlayer("NU", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("BlockReach")) {
					player.closeInventory();
					main.banPlayer("BR", main.BANUI.get(player), 7);
					main.BANUI.remove(player);
				}
				else if (name.contains("Liquids")) {
					player.closeInventory();
					main.banPlayer("LQ", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("Scaffold")) {
					player.closeInventory();
					main.banPlayer("SC", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("Xray")) {
					player.closeInventory();
					main.banPlayer("XR", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("GhostHand")) {
					player.closeInventory();
					main.banPlayer("GH", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("FastEat")) {
					player.closeInventory();
					main.banPlayer("FE", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("Regen")) {
					player.closeInventory();
					main.banPlayer("RG", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("NoSwing")) {
					player.closeInventory();
					main.banPlayer("NW", main.BANUI.get(player), 7);
					main.BANUI.remove(player);
				}
				else if (name.contains("AutoRespawn")) {
					player.closeInventory();
					main.banPlayer("AR", main.BANUI.get(player), 7);
					main.BANUI.remove(player);
				}
				else if (name.contains("InventoryMove")) {
					player.closeInventory();
					main.banPlayer("IM", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("ItemDrops")) {
					player.closeInventory();
					main.banPlayer("ID", main.BANUI.get(player), 7);
					main.BANUI.remove(player);
				}
				else if (name.contains("AutoLoot")) {
					player.closeInventory();
					main.banPlayer("AL", main.BANUI.get(player), 15);
					main.BANUI.remove(player);
				}
				else if (name.contains("PortalInventory")) {
					player.closeInventory();
					main.banPlayer("PI", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				else if (name.contains("ExtraInventory")) {
					player.closeInventory();
					main.banPlayer("EI", main.BANUI.get(player), 30);
					main.BANUI.remove(player);
				}
				
			}
			else {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onDamageNatural(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.ENTITY_EXPLOSION || e.getCause() == DamageCause.BLOCK_EXPLOSION) {
			e.setCancelled(true);
		}
	}
	
	
}
