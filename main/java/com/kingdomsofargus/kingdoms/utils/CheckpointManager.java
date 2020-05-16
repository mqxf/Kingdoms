package com.kingdomsofargus.kingdoms.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.commands.CheckpointCommand;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.player.User;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.v1_15_R1.PacketDataSerializer;
import net.minecraft.server.v1_15_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_15_R1.EntityFox.i;
public class CheckpointManager {
	
	public static void createNewCheckpoint(Location location, Player player) {
		
		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Kingdoms.class), new Runnable() {
			
			public void run() {
				
				tornado(player, location);
				
				for (Entity e : location.getChunk().getEntities()) {
					if (e.getLocation().distance(location) < 3) {
						if (e.equals(player)) {
							Bukkit.getScheduler().cancelTask(Kingdoms.getInstance().checkpointID.get(player));
							int current = Kingdoms.getInstance().currentCP.get(player);
							if (current >= 13) {
								Kingdoms.getInstance().completedCP.put(player, true);
								player.sendMessage(Utils.chat("&6You have completed the checkpoints!"));
								KingdomPlayer.setCP(player.getUniqueId(), true);
							}
							else  {
								createNewCheckpoint(CheckpointCommand.getCheckpoint(current + 1), player);
								Kingdoms.getInstance().currentCP.put(player, current + 1);
								player.sendMessage(Utils.chat("&6You have hit checkpoint &a" + current + "&6 find checkpoint &b" + (current + 1) + "&6 ahead!"));
							}
							
						}
					}
				}
			}
			
		}, 0, 1L);
		
		Kingdoms.getInstance().checkpointID.put(player, task);
		
	}
	
	public static void tornado(Player player, Location location) {
		CraftPlayer p = (CraftPlayer) player;
		p.spawnParticle(Particle.END_ROD, location, 0);
		int angle = 0;
		int max_height = 4;
		double max_radius = 1;
		int lines = 15;
		double height_increasement = 0.15;
		double radius_increasement = max_radius / max_height;
		for (int l = 0; l < lines; l++) {
		      for (double y = 0; y < max_height; y+=height_increasement ) {
		          double radius = y * radius_increasement;
		          double x = Math.cos(Math.toRadians(360/lines*l + y*25 - angle)) * radius;
		          double z = Math.sin(Math.toRadians(360/lines*l + y*25 - angle)) * radius;
		          p.spawnParticle(Particle.END_ROD, location.clone().add(x, y, z), 0);
		       }
		   }
		   angle++;
	}
	
	public static void turnBack(Player player) {
		User u = Kingdoms.getInstance().USERS.get(player);
		new BukkitRunnable() {
			
			@Override
			public void run() {
				u.setCP(false);
				
			}
		}.runTaskLater(Kingdoms.getInstance(), 30L);
	}
	
}
