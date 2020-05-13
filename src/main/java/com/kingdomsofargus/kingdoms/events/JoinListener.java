package com.kingdomsofargus.kingdoms.events;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.gui.GenderGUI;
import com.kingdomsofargus.kingdoms.user.User;
import com.kingdomsofargus.kingdoms.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(Utils.chat("&7[&a+&7] " + event.getPlayer().getName()));
		
		/**Kingdoms.getCore().USERS.put(player, new User(player));
		Kingdoms.getCore().combined.put(player, false);
		//Kingdoms.getCore().KP.put(player, new KingdomPlayer(player));
		Kingdoms.getCore().invite.put(player, false);
		Kingdoms.getCore().inviteName.put(player, null);
		Kingdoms.getCore().currentCP.put(player, 1);
		Kingdoms.getCore().hitCP.put(player, false);
		Kingdoms.getCore().checkpointID.put(player, null);

		Location location = player.getLocation();
**/
		if (!(Kingdoms.getCore().getUserManager().userExists(player.getUniqueId().toString()))) {
			player.sendMessage(ChatColor.GREEN + "Created Profile");

			Kingdoms.getCore().getUserManager().createNewUser(player);

			GenderGUI.applyUI(player);
			//Kingdoms.getCore().completedCP.put(player, false);
		//	CheckpointManager.createNewCheckpoint(CheckpointCommand.getCheckpoint(1), player);
		} else {
		player.sendMessage(ChatColor.GREEN + "Loaded Profile");
		new User(player.getUniqueId().toString());
		// TODO
		//CheckpointManager.createNewCheckpoint(CheckpointCommand.getCheckpoint(1), player);
		}
					
		/*
			
		if (!Kingdoms.getCore().getUserManager().getUsers().containsKey(player.getUniqueId())) {
			player.sendMessage(ChatColor.GREEN + "Created Profile");
			GenderGUI.applyUI(player);
			Kingdoms.getCore().completedCP.put(player, false);
			CheckpointManager.createNewCheckpoint(CheckpointCommand.getCheckpoint(1), player);
		} else {
			Methods.getPlayer(player.getUniqueId(), player);
			player.sendMessage(ChatColor.GREEN + "Loaded Profile");
			
			if (!KingdomPlayer.getCP(player.getUniqueId())) {
				CheckpointManager.createNewCheckpoint(CheckpointCommand.getCheckpoint(1), player);
			}
			

		}
	
		player.sendMessage(ChatColor.GRAY + "Use /sbtoggle to toggle your scoreboard on/off");
		SBProvider.hasScoreboard.add(player.getUniqueId());
		Permissions.setupPermissions(player);
		*/
	}
}