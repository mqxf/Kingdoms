package com.kingdomsofargus.kingdoms.commands.npc;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.utils.Utils;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

public class InfoNPCCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("commands.infonpc")) {
				if (Kingdoms.getCore().info != null) {
					Kingdoms.getCore().info.despawn();
				}
				Location location = player.getLocation();
				NPCRegistry registry = CitizensAPI.getNPCRegistry();
				NPC npc = registry.createNPC(EntityType.VILLAGER, Utils.chat("&e&lINFO"));
				npc.spawn(location);
				Kingdoms.getCore().info = npc;
				setInfo(location);
			}
		}
		return false;
	}
	
	public void setInfo(Location location) {
		FileConfiguration config = Kingdoms.getCore().getConfig();
		
		config.set("npc.info.world", location.getWorld().getName());
		config.set("npc.info.x", location.getX());
		config.set("npc.info.y", location.getY());
		config.set("npc.info.z", location.getZ());
		Kingdoms.getCore().saveConfig();
	}
	public static Location getInfo() {
		FileConfiguration config = Kingdoms.getCore().getConfig();
		
		String world = config.getString("npc.info.world");
		int x = config.getInt("npc.info.x");
		int y = config.getInt("npc.info.y");
		int z = config.getInt("npc.info.z");
		
		Location location = new Location(Bukkit.getWorld(world), x, y, z);
		
		return location;
	}
	
	public static void spawnInfo(Location location) {
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		NPC npc = registry.createNPC(EntityType.VILLAGER, Utils.chat("&e&lINFO"));
		npc.spawn(location);
		Kingdoms.getCore().info = npc;
	}

}
