package com.kingdomsofargus.kingdoms.kingdom.impl;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.Kingdom;
import com.kingdomsofargus.kingdoms.utils.Utils;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

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
        	
        if (Kingdoms.getCore().getKindomManager().kingdomExists(name)) {
        	player.sendMessage(ChatColor.RED + "You are not in a kingdom");
        	return true;
        }
        
        if (Kingdoms.getCore().getUserManager().getUser(player).getuClass().equalsIgnoreCase("King") || Kingdoms.getCore().getUserManager().getUser(player).getuClass().equalsIgnoreCase("Queen")) {
        	if (Bukkit.getPlayer(name) != null) {
	        	Player target = Bukkit.getPlayer(name);
	        	int kingdom = Kingdoms.getCore().getUserManager().getUser(player).getKingdom_id();
				Kingdom userKingdom = Kingdoms.getCore().getKindomManager().getKingdom(kingdom);
        	if (Kingdoms.getCore().getUserManager().getUser(target).getKingdom_id() == 0) {
        			target.sendMessage(Utils.chat("&eYou were invited to join &6&l" + Kingdoms.getCore().getKindomManager().getKingdom(kingdom).getName()));
        			target.sendMessage(ChatColor.GRAY.toString() + ChatColor.ITALIC + "You have 1 minute to /k accept");
        			String invites = userKingdom.getInvites();
        			if (invites.equalsIgnoreCase("")) {
						Kingdoms.getCore().getKindomManager().getKingdom(kingdom).setInvites(target.getUniqueId().toString());
						System.out.println("Invites: " + userKingdom.getInvites());
					} else {
						Kingdoms.getCore().getKindomManager().getKingdom(kingdom).setInvites(invites + ":" + target.getUniqueId().toString());
					}

				System.out.println("Invites: " + userKingdom.getInvites());
					new BukkitRunnable() {
						
						@Override
						public void run() {
							String invites = userKingdom.getInvites();
							String regex = "\\s*\\b:" + player.getUniqueId().toString() +"\\b\\s*";
							String newInvites = invites.replaceAll(regex, "");
							Kingdoms.getCore().getKindomManager().getKingdom(kingdom).setInvites(newInvites);
							target.sendMessage(Utils.chat("&eYour invite for &6&l" + userKingdom.getName() + " &e has expired."));
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

		return false;
	}

}
