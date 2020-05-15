package com.kingdomsofargus.kingdoms.kingdom.impl;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.Kingdom;
import com.kingdomsofargus.kingdoms.user.User;
import com.kingdomsofargus.kingdoms.utils.Color;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KingdomKickArgument extends CommandArgument {

    private final Kingdoms plugin;

    public KingdomKickArgument(Kingdoms plugin) {
        super("kick", "Kick a player from your kingdom", new String[]{"kick"});
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
        User user = Kingdoms.getCore().getUserManager().getUser(player);
        int kingdom = user.getKingdom_id();
        Kingdom userKingdom = Kingdoms.getCore().getKindomManager().getKingdom(kingdom);

        String targetPlayer = args[1];

        if (userKingdom.members.contains(Kingdoms.getCore().getUserManager().getOfflineUUID(targetPlayer))) {
            player.sendMessage(Color.color("&cThat player is not in your kingdom!"));
            return false;
        }



        if (!userKingdom.getLeader().equalsIgnoreCase(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "Only the leader can kick people.");
            return true;
        }


        Kingdoms.getCore().getUserManager().saveOfflineUser(Kingdoms.getCore().getUserManager().getOfflineUUID(targetPlayer), "kingdom_id", 0);
        userKingdom.members.remove(Kingdoms.getCore().getUserManager().getOfflineUUID(targetPlayer));
        sender.sendMessage(Color.color("&7You have kicked &c" + targetPlayer + " &7from the kingdom."));
        return false;
    }


}