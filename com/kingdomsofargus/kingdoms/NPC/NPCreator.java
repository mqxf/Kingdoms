package com.kingdomsofargus.kingdoms.NPC;

import javax.management.InstanceAlreadyExistsException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kingdomsofargus.kingdoms.utils.Utils;
import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDamageByEntityEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Equipment.EquipmentSlot;



public class NPCreator {
	
	@SuppressWarnings("deprecation")
	public static void createNPC(Location loc, Player player, String skin) {
		
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta bootMeta = boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 300, true);
		boots.setItemMeta(bootMeta);

		ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 50, true);
		leggings.setItemMeta(leggingsMeta);

		ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta chestplateMeta = chestplate.getItemMeta();
		chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 50, true);
		chestplate.setItemMeta(chestplateMeta);

		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta helmetMeta = helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 50, true);
		helmet.setItemMeta(helmetMeta);
		
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 450, true);
		sword.setItemMeta(swordMeta);
		
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		NPC npc = registry.createNPC(EntityType.PLAYER, Utils.chat(skin));
		npc.data().set(NPC.PLAYER_SKIN_UUID_METADATA, skin);
		npc.data().set(NPC.PLAYER_SKIN_USE_LATEST, false);
		npc.data().set(NPC.DEFAULT_PROTECTED_METADATA, false);

		npc.getTrait(Equipment.class).set(EquipmentSlot.HAND, sword);
		npc.getTrait(Equipment.class).set(EquipmentSlot.BOOTS, boots);
		npc.getTrait(Equipment.class).set(EquipmentSlot.LEGGINGS, leggings);
		npc.getTrait(Equipment.class).set(EquipmentSlot.CHESTPLATE, chestplate);
		npc.getTrait(Equipment.class).set(EquipmentSlot.HELMET, helmet);

		npc.getNavigator().getLocalParameters().attackRange(15.0).attackDelayTicks(5);
		npc.data().set(NPC.DAMAGE_OTHERS_METADATA, 100D);
		
		npc.spawn(loc);	
		npc.getNavigator().setTarget(player, true);
		
	}
}
