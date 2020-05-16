package com.kingdomsofargus.kingdoms.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.commands.CheckpointCommand;
import com.kingdomsofargus.kingdoms.commands.SBToggleCommand;
import com.kingdomsofargus.kingdoms.gui.GenderGUI;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.player.User;
import com.kingdomsofargus.kingdoms.rank.permissions.Permissions;
import com.kingdomsofargus.kingdoms.scoreboard.AssembleBoard;
import com.kingdomsofargus.kingdoms.sql.Methods;
import com.kingdomsofargus.kingdoms.utils.CheckpointManager;
import com.kingdomsofargus.kingdoms.utils.Utils;
import com.sk89q.jchronic.utils.Time;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_15_R1.PacketPlayOutWorldParticles;

public class JoinListener implements Listener {
	
	Kingdoms main = Kingdoms.getInstance();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(Utils.chat("&7[&a+&7] " + event.getPlayer().getName()));
		
		Kingdoms.getInstance().USERS.put(player, new User(player));
		Kingdoms.getInstance().combined.put(player, false);
		Kingdoms.getInstance().KP.put(player, new KingdomPlayer(player));
		Kingdoms.getInstance().invite.put(player, false);
		Kingdoms.getInstance().inviteName.put(player, null);
		Kingdoms.getInstance().currentCP.put(player, 1);
		Kingdoms.getInstance().hitCP.put(player, false);
		Kingdoms.getInstance().checkpointID.put(player, null);
			
		Location location = player.getLocation();
					
		
			
		if (!Methods.playerExists(player.getUniqueId())) {
			Methods.createPlayer(player.getUniqueId(), player);
			player.sendMessage(ChatColor.GREEN + "Created Profile");
			GenderGUI.applyUI(player);
			Kingdoms.getInstance().completedCP.put(player, false);
			CheckpointManager.createNewCheckpoint(CheckpointCommand.getCheckpoint(1), player);
		} else {
			Methods.getPlayer(player.getUniqueId(), player);
			player.sendMessage(ChatColor.GREEN + "Loaded Profile");
			
			if (!KingdomPlayer.getCP(player.getUniqueId())) {
				CheckpointManager.createNewCheckpoint(CheckpointCommand.getCheckpoint(1), player);
			}
			
			
		}
	
		player.sendMessage(ChatColor.GRAY + "Use /sbtoggle to toggle your scoreboard on/off");
		AssembleBoard.hasScoreboard.add(player.getUniqueId());
		Permissions.setupPermissions(player);
		
	}
}