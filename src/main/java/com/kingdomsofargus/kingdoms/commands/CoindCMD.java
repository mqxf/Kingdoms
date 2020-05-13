package com.kingdomsofargus.kingdoms.commands;

import com.kingdomsofargus.kingdoms.Kingdoms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoindCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            Player p = Bukkit.getPlayer(args[0]);
            if (p != null) {
                sender.sendMessage("" + Kingdoms.getCore().getUserManager().getUser(p).getPurse_coins());
            } else {
                sender.sendMessage(ChatColor.RED + "That player is not online.");
            }
        } else {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                p.sendMessage("" + Kingdoms.getCore().getUserManager().getUser(p).getPurse_coins());
            }
        }
        return false;
    }
}
