package com.kingdomsofargus.kingdoms.events.anticheat;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.player.User;

public class MoveListener implements Listener {

	Kingdoms main = Kingdoms.getCore();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		Player player = e.getPlayer();
		User u = main.USERS.get(player);
		
		double distX = e.getTo().getX() - e.getFrom().getX();
		double distZ = e.getTo().getZ() - e.getFrom().getZ();
		double dist = (distX * distX) + (distZ * distZ);
		double lastDist = u.getLastDist();
		boolean onGround = isOnGround(e.getTo());
		boolean lastOnGround = u.wasLastOnGround();
		boolean beforeOnGround = u.isBeforeOnGround();
		boolean threeGround = u.isThreeGround();
		boolean fourGround = u.isFourGround();
		boolean fiveGround = u.isFiveGround();
		double distY = e.getTo().getY() - e.getFrom().getY();
		double lastDistY = u.getLastDistY();
		boolean lastClimb = u.isLastOnClimable();
		boolean beforeClimb = u.isThreeOnClimable();
		
		float friction = 0.91F;
		double shiftedLastDist = lastDist * friction;
		double equalness = dist - shiftedLastDist;
		double scaledEqualness = equalness * 138;
		
		double predictedDist = (lastDistY - 0.08D) * 0.9800000190734863D;
		
		boolean negative = false;
		boolean lastNegative = u.isLastNegative();
		boolean beforeNegative = u.isBeforeNegative();
		boolean threeNegative = u.isThreeNegative();
		
		if (distY < 0) {
			negative = true;
		}
		else {
			negative = false;
		}
		
		u.setLastNegative(negative);
		u.setBeforeNegative(lastNegative);
		u.setThreeNegative(beforeNegative);
		
		u.setLastOnClimable(isOnCimable(player.getLocation()));
		u.setThreeOnClimable(lastClimb);
		
		u.setLastDist(dist);
		u.setLastOnGround(onGround);
		u.setBeforeOnGround(lastOnGround);
		u.setThreeGround(beforeOnGround);
		u.setFourGround(threeGround);
		u.setFiveGround(fourGround);
		u.setSixGround(fiveGround);
		u.setLastDistY(distY);
		
		if (!onGround && lastOnGround) {
			u.setNoslowImmune(true);
		}
		
		if (onGround && !lastOnGround) {
			u.setNoslowImmune(false);
		}
		
		if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
			if ((player.getInventory().getChestplate() == null || (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() != Material.ELYTRA)) && !u.isFlyimmune() && !onGround && !lastOnGround && !u.isSpeedImmune() && !isOnIce(player.getLocation()) && player.getVehicle() == null && !player.hasPotionEffect(PotionEffectType.SPEED) && !player.getAllowFlight() && !beforeOnGround && !threeGround && !fourGround) {
				if (scaledEqualness >= 1 || dist > 1 || dist < -1) {
					Bukkit.broadcastMessage(scaledEqualness + "E");
					//airspeed
				}
			}
			
			if (!u.isFlyimmune() && !onGround && !lastOnGround && !beforeOnGround && Math.abs(predictedDist) >= 0.005D && (player.getInventory().getChestplate() == null || (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() != Material.ELYTRA))) {
				if (!isRoughlyEqual(distY, predictedDist) && !isInWater(player.getLocation()) && !player.isInsideVehicle() && !player.hasPotionEffect(PotionEffectType.JUMP) && !player.hasPotionEffect(PotionEffectType.POISON) && !player.getAllowFlight()) {
					Bukkit.broadcastMessage(distY + " " + predictedDist);
					//fly
				}
			}
			
			if (!onGround && !lastOnGround && !beforeOnGround && !threeGround) {
				if (e.getPlayer().isOnGround()) {
					Bukkit.broadcastMessage("Nofall or spoofing detected");
					
				}
			}
			
			if (distY < -4D) {
				Bukkit.broadcastMessage(distY + "Y");
				//fastfall
			}
			
			if (!isInWater(player.getLocation()) && negative && !lastNegative && beforeNegative && !threeNegative && (player.getInventory().getChestplate() == null || (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() != Material.ELYTRA))) {
				Bukkit.broadcastMessage("minijump!");
				
			}
			
			if (!isInWater(player.getLocation()) && !negative  && lastNegative && !beforeNegative && threeNegative && (player.getInventory().getChestplate() == null || (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() != Material.ELYTRA))) {
				Bukkit.broadcastMessage("minijump!");
				
			}
			
			if (distY > 0.118 && isOnCimable(player.getLocation()) && lastClimb && beforeClimb) {
				Bukkit.broadcastMessage("fastclimb");
				
			}
			
			
			
		}
		
	}
	
	public boolean isRoughlyEqual(double d1, double d2) {
		return Math.abs(d1 - d2) < 0.001;
	}
	
	public boolean isOnGround(Location loc) {
		double expand = 0.3;
		for (double x = -expand; x <= expand; x += expand) {
			for (double z = -expand; z <= expand; z += expand) {
				if (loc.clone().add(x, -0.5001, z).getBlock().getType() != Material.AIR) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isUnderBlock(Location loc) {
		double expand = 0.3;
		for (double x = -expand; x <= expand; x += expand) {
			for (double z = -expand; z <= expand; z += expand) {
				if (loc.clone().add(x, 1.81001, z).getBlock().getType() != Material.AIR) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isInWater(Location loc) {
		double expand = 0.3;
		for (double x = -expand; x <= expand; x += expand) {
			for (double z = -expand; z <= expand; z += expand) {
				if (loc.clone().add(x, -0.2001, z).getBlock().getType() == Material.WATER || loc.clone().add(x, -0.2001, z).getBlock().getType() == Material.LAVA) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isOnCimable(Location loc) {
		double expand = 0.3;
		for (double x = -expand; x <= expand; x += expand) {
			for (double z = -expand; z <= expand; z += expand) {
				if (loc.clone().add(x, -0.5001, z).getBlock().getType() == Material.LADDER || loc.clone().add(x, -0.5001, z).getBlock().getType() == Material.VINE) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isOnIce(Location loc) {
		double expand = 0.3;
		for (double x = -expand; x <= expand; x += expand) {
			for (double z = -expand; z <= expand; z += expand) {
				if (loc.clone().add(x, -0.5001, z).getBlock().getType() == Material.ICE || loc.clone().add(x, -0.5001, z).getBlock().getType() == Material.PACKED_ICE || loc.clone().add(x, -0.5001, z).getBlock().getType() == Material.BLUE_ICE) {
					return true;
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public void immuneCheck(EntityDamageByEntityEvent e) {
		if (e.getEntity().getType() == EntityType.PLAYER) {
			
			Player player = (Player) e.getEntity();
			User u = main.USERS.get(player);
			
			u.setSpeedImmune(true);
			u.setNoslowImmune(true);
			u.setFlyimmune(true);
			
			new BukkitRunnable() {
				public void run() {
					u.setSpeedImmune(false);
					u.setNoslowImmune(false);
					u.setFlyimmune(false);
				}
			}.runTaskLater(JavaPlugin.getPlugin(Kingdoms.class), 40L);
			
		}
	}
	
}
