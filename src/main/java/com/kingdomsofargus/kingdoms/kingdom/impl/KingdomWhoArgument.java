package com.kingdomsofargus.kingdoms.kingdom.impl;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.Kingdom;
import com.kingdomsofargus.kingdoms.utils.command.BukkitUtils;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class KingdomWhoArgument extends CommandArgument {

	private final Kingdoms plugin;
	
	public KingdomWhoArgument(Kingdoms plugin) {
        super("who", "Get info on a kingdom", new String[]{"i", "info"});
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName() + " <kingdomName>";
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
        
        if (!Kingdoms.getCore().getKindomManager().kingdomExists(name)) {
        	sender.sendMessage(ChatColor.RED + "That kingdom does not exist");
        } else {
        	String kingdomName = name;
        	int kingdom_id = Kingdoms.getCore().getKindomManager().getKingdomByName(name);
        	Kingdom kingdom = Kingdoms.getCore().getKindomManager().getKingdom(kingdom_id);
        	String kingdomLeader = getName(kingdom.getLeader());
        	String kingdomTag = kingdom.getTag();
        	
        	sender.sendMessage(ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
        	sender.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + kingdomName + "'s Info");
        	sender.sendMessage(ChatColor.YELLOW + "  Leader: " + ChatColor.GRAY + kingdomLeader);
        	sender.sendMessage(ChatColor.YELLOW + "  TAG: " + ChatColor.GRAY + kingdomTag);
        	sender.sendMessage(ChatColor.YELLOW + "  Bank: " + ChatColor.GRAY + kingdom.getBank());
        	sender.sendMessage("");
        	sender.sendMessage(ChatColor.GOLD.toString() + "Members:");
        	sender.sendMessage(ChatColor.YELLOW + " TODO " );
        	sender.sendMessage(ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
        }
        
		return false;
	}

	public String getName(String uuid) {
		String url = "https://api.mojang.com/user/profiles/"+uuid.replace("-", "")+"/names";
		try {
			@SuppressWarnings("deprecation")
			String nameJson = IOUtils.toString(new URL(url));
			JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
			String playerSlot = nameValue.get(nameValue.size()-1).toString();
			JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
			return nameObject.get("name").toString();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return "error";
	}

}
