package com.kingdomsofargus.kingdoms.kingdom.impl;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.Kingdom;
import com.kingdomsofargus.kingdoms.kingdom.invites.Invite;
import com.kingdomsofargus.kingdoms.user.User;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class KingdomAcceptArgument extends CommandArgument {

	private final Kingdoms plugin;
	public static HashMap<String, UUID> receievedInvite = new HashMap<String, UUID>();
	
	public KingdomAcceptArgument(Kingdoms plugin) {
        super("accept", "Accept a kingdom invite", new String[]{"join, j"});
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

		if (args.length < 1) {
			sender.sendMessage(ChatColor.RED + "Usage: " + getUsage(label));
			return true;
		}

		Player player = (Player) sender;

		if (!Kingdoms.getCore().getInviteManager().getInvites().containsKey(player.getUniqueId().toString())) {
			player.sendMessage(ChatColor.RED + "You have not been invited to join this kingdom!");
			return false;
		}


		Invite invite = Kingdoms.getCore().getInviteManager().getInvite(player);

		Kingdom kingdom = Kingdoms.getCore().getKindomManager().getKingdom(invite.getInvitedToKingdom());
		User user = Kingdoms.getCore().getUserManager().getUser(player);


		if (user.getKingdom_id() != 0) {
			player.sendMessage(ChatColor.RED + "You are already in a kingdom!");
			return false;
		}

		if (invite.getInvitedToKingdom() != kingdom.getId()) {
			player.sendMessage(ChatColor.RED + "You have not been invited to join this kingdom!");
			return false;
		}

		player.sendMessage(ChatColor.RED + "Successfully joined the kingdom.");
		user.setKingdom_id(kingdom.getId());
		kingdom.addMember(player.getUniqueId().toString());
		Kingdoms.getCore().getInviteManager().getInvites().remove(player.getUniqueId().toString());
		Kingdoms.getCore().getInviteManager().uninviteUser(player.getUniqueId().toString());

		return false;
	}

}
