package com.kingdomsofargus.kingdoms.kingdom.impl;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.Kingdom;
import com.kingdomsofargus.kingdoms.user.User;
import com.kingdomsofargus.kingdoms.utils.Color;
import com.kingdomsofargus.kingdoms.utils.Utils;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

        if (Bukkit.getPlayer(args[1]) == null) {
        	player.sendMessage(Color.color("&cThat player is not online"));
        	return false;
		}

        Player target = Bukkit.getPlayer(args[1]);

		User user = Kingdoms.getCore().getUserManager().getUser(player);
		int kingdom = user.getKingdom_id();
		Kingdom userKingdom = Kingdoms.getCore().getKindomManager().getKingdom(kingdom);
		User targetUser = Kingdoms.getCore().getUserManager().getUser(target);
        	
        if (targetUser.getKingdom_id() != 0) {
        	player.sendMessage(ChatColor.RED + "You are not in a kingdom");
        	return true;
        }

        if (!userKingdom.getLeader().equalsIgnoreCase(player.getUniqueId().toString())) {
        	player.sendMessage(ChatColor.RED + "Only the leader can invite people.");
        	return true;
		}

        if (!Kingdoms.getCore().getInviteManager().getInvites().containsKey(target.getUniqueId().toString())) {
			target.sendMessage(Utils.chat("&eYou were invited to join &6&l" + Kingdoms.getCore().getKindomManager().getKingdom(kingdom).getName()));
			target.sendMessage(ChatColor.GRAY.toString() + ChatColor.ITALIC + "You have 1 minute to /k accept");
			Kingdoms.getCore().getInviteManager().createNewInvite(target, userKingdom);


			new BukkitRunnable() {

				@Override
				public void run() {
					Kingdoms.getCore().getInviteManager().getInvites().remove(target.getUniqueId().toString());
					target.sendMessage(Utils.chat("&eYour invite for &6&l" + userKingdom.getName() + " &ehas expired."));
				}
			}.runTaskLater(plugin, 60 * 20L);
		}

		return false;
	}

}
