package com.kingdomsofargus.kingdoms.kingdom.impl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.kingdomsofargus.kingdoms.kingdom.KingdomExecutor;
import com.kingdomsofargus.kingdoms.utils.JavaUtils;
import com.kingdomsofargus.kingdoms.utils.Utils;
import com.kingdomsofargus.kingdoms.utils.command.BukkitUtils;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;

import net.md_5.bungee.api.ChatColor;

public class KingdomHelpArgument extends CommandArgument {

	private final KingdomExecutor executor;
	private static final int HELP_PER_PAGE = 5;
    private ImmutableMultimap<Integer, String> pages;

	public KingdomHelpArgument(KingdomExecutor executor) {
		super("help", "View help on how to use kingdoms");
		this.executor = executor;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName();
	}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            showPage(sender, label, 1);
            return true;
        }

        Integer page = JavaUtils.tryParseInt(args[1]);

        if (page == null) {
            sender.sendMessage(ChatColor.RED + "'" + args[1] + "' is not a valid number.");
            return true;
        }

        showPage(sender, label, page);
        return true;
    }

    private void showPage(CommandSender sender, String label, int pageNumber) {
        // Create the multimap.
        if (pages == null) {
            boolean isPlayer = sender instanceof Player;
            int val = 1;
            int count = 0;
            Multimap<Integer, String> pages = ArrayListMultimap.create();
            for (CommandArgument argument : executor.getArguments()) {
                if (argument == this) continue;

                // Check the permission and if the player can access.
                String permission = argument.getPermission();
                if (permission != null && !sender.hasPermission(permission)) continue;
                if (argument.isPlayerOnly() && !isPlayer) continue;

                count++;
                pages.get(val).add(ChatColor.YELLOW + "/" + label + ' ' + argument.getName() + " » " + ChatColor.GRAY + argument.getDescription());
                if (count % HELP_PER_PAGE == 0) {
                    val++;
                }
            }

            // Finally assign it.
            this.pages = ImmutableMultimap.copyOf(pages);
        }

        int totalPageCount = (pages.size() / HELP_PER_PAGE) + 1;

        if (pageNumber < 1) {
            sender.sendMessage(ChatColor.RED + "You cannot view a page less than 1.");
            return;
        }

        if (pageNumber > totalPageCount) {
            sender.sendMessage(ChatColor.RED + "There are only " + totalPageCount + " pages.");
            return;
        }

        sender.sendMessage(ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
        sender.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + " Kingdom Help " + ChatColor.WHITE + "(Page " + pageNumber + '/' + totalPageCount + ')');
        for (String message : pages.get(pageNumber)) {
            sender.sendMessage("  " + message);
        }
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GREEN + " To view other pages, use " + ChatColor.YELLOW + '/' + label + ' ' + getName() + " <page#>" + ChatColor.GOLD + '.');
        sender.sendMessage(ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
    }
}