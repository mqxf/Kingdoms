package com.kingdomsofargus.kingdoms.kingdom.impl;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.gui.kingdom.KingdomDisbandGUI;
import com.kingdomsofargus.kingdoms.utils.Utils;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;

import net.md_5.bungee.api.ChatColor;

public class KingdomDisbandArgument extends CommandArgument {

	private final Kingdoms plugin;
	
	public KingdomDisbandArgument(Kingdoms plugin) {
        super("disband", "Disband a kingdom", new String[]{"undefine"});
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You cannot execute this command as console.");
			return true;
		}
		
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: " + getUsage(label));
            return true;
        }
        
        
        Player player = (Player) sender;
		int kingdom = Kingdoms.getCore().getUserManager().getUser(player).getKingdom_id();
        
        if (kingdom == 0) {
        	sender.sendMessage(ChatColor.RED + "You aren't in a kingdom!");
        	return true;
        } else {
        
        	if (Kingdoms.getCore().getUserManager().getUser(player).getClass().equals("King") || Kingdoms.getCore().getUserManager().getUser(player).getClass().equals("Queen")) {
        		
        		ItemStack yes = new ItemStack(Material.LIME_CONCRETE);
        		ItemMeta yesMeta = yes.getItemMeta();
        		yesMeta.setDisplayName(Utils.chat("&aYes"));
        		yes.setItemMeta(yesMeta);
        		
        		KingdomDisbandGUI.apply(player, 5, true);
        		
        		new BukkitRunnable() {
					@Override
					public void run() {
						if (player.getOpenInventory().getTitle().contains("Are you sure?")) {
							KingdomDisbandGUI.apply(player, 4, true);
						}
						
					}
				}.runTaskLater(Kingdoms.getCore(), 20L);
				
				KingdomDisbandGUI.apply(player, 5, true);
        		new BukkitRunnable() {
					@Override
					public void run() {
						if (player.getOpenInventory().getTitle().contains("Are you sure?")) {
							KingdomDisbandGUI.apply(player, 3, true);
						}
						
					}
				}.runTaskLater(Kingdoms.getCore(), 40L);
        		
        		new BukkitRunnable() {
					@Override
					public void run() {
						if (player.getOpenInventory().getTitle().contains("Are you sure?")) {
							KingdomDisbandGUI.apply(player, 2, true);
						}
						
					}
				}.runTaskLater(Kingdoms.getCore(), 60L);
        		
        		new BukkitRunnable() {
					
					@Override
					public void run() {
						if (player.getOpenInventory().getTitle().contains("Are you sure?")) {
							KingdomDisbandGUI.apply(player, 1, true);
						}
						
					}
				}.runTaskLater(Kingdoms.getCore(), 80L);
        		
        		new BukkitRunnable() {
					
					@Override
					public void run() {
						if (player.getOpenInventory().getTitle().contains("Are you sure?")) {
							KingdomDisbandGUI.apply(player, 0, false);
							player.getOpenInventory().setItem(15, yes);
						}
						
					}
				}.runTaskLater(Kingdoms.getCore(), 100L);
	        } else {
	        	player.sendMessage(ChatColor.RED + "You do not have permission to disband this kingdom!");
	        }
        }        
		return false;
	}

}
