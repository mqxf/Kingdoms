package com.kingdomsofargus.kingdoms.kingdom.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.KingdomExecutor;
import com.kingdomsofargus.kingdoms.kingdom.KingdomManager;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.Utils;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;

import net.md_5.bungee.api.ChatColor;

public class KingdomInviteArgument extends CommandArgument {

	private final Kingdoms plugin;
	
	public KingdomInviteArgument(Kingdoms plugin) {
        super("invite", "Invite a player to your kingdom", new String[]{"inv"});
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName() + " <player>";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You cannot execute this command as console.");
			return true;
		}
		
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: " + getUsage(label));
            return true;
        }
        	
        Player player = (Player) sender;
        String name = args[1];
        	
        if (KingdomPlayer.getKingdom(player.getUniqueId()).equals("NONE")) {
        	player.sendMessage(ChatColor.RED + "You are not in a kingdom");
        	return true;
        }
        
        if (KingdomPlayer.getClass(player.getUniqueId()).equals("King") || KingdomPlayer.getClass(player.getUniqueId()).equals("Queen")) {
        	if (Bukkit.getPlayer(name) != null) {
	        	Player target = Bukkit.getPlayer(name);
	        	String kingdom = KingdomPlayer.getKingdom(player.getUniqueId());
	        			
        	if (KingdomPlayer.getKingdom(target.getUniqueId()).equals("NONE")) {
        		if (!Kingdoms.getInstance().invite.get(target)) {
        			target.sendMessage(Utils.chat("&eYou were invited to join &6&l" + kingdom));
        			target.sendMessage(ChatColor.GRAY.toString() + ChatColor.ITALIC + "You have 1 minute to /k accept");
        			Kingdoms.getInstance().inviteName.put(target, kingdom);
        			Kingdoms.getInstance().invite.put(player, true);
        			new BukkitRunnable() {
						
						@Override
						public void run() {
							Kingdoms.getInstance().invite.put(target, false);
							
						}
					}.runTaskLater(JavaPlugin.getPlugin(Kingdoms.class), 60 * 20L);
        		}
        		else {
        			player.sendMessage(Utils.chat("&cThat player already has an outstanding invite."));
        		}
        		
        	} else {
        		sender.sendMessage(ChatColor.RED + "That player is already in a kingdom!");
        		return true;
        	}
        } else {
        	sender.sendMessage(ChatColor.RED + "That player is not online.");
        }
        }

		return false;
	}

}
