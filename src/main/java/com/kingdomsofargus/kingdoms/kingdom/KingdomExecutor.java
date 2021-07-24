package com.kingdomsofargus.kingdoms.kingdom;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.impl.KingdomCreateArgument;
import com.kingdomsofargus.kingdoms.kingdom.impl.KingdomDiplomacyArgument;
import com.kingdomsofargus.kingdoms.kingdom.impl.KingdomDisbandArgument;
import com.kingdomsofargus.kingdoms.kingdom.impl.KingdomHelpArgument;
import com.kingdomsofargus.kingdoms.kingdom.impl.KingdomInviteArgument;
import com.kingdomsofargus.kingdoms.kingdom.impl.KingdomJoinArgument;
import com.kingdomsofargus.kingdoms.kingdom.impl.KingdomLeaveArgument;
import com.kingdomsofargus.kingdoms.kingdom.impl.KingdomRenameArgument;
import com.kingdomsofargus.kingdoms.kingdom.impl.KingdomTagArgument;
import com.kingdomsofargus.kingdoms.kingdom.impl.KingdomWhoArgument;
import com.kingdomsofargus.kingdoms.utils.command.ArgumentExecutor;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;

public class KingdomExecutor extends ArgumentExecutor {

    private final CommandArgument helpArgument;
	
    public KingdomExecutor(Kingdoms plugin) {
        super("kingdom");

        addArgument(helpArgument = new KingdomHelpArgument(this));
        addArgument(new KingdomCreateArgument(plugin));
        addArgument(new KingdomDisbandArgument(plugin));
        addArgument(new KingdomTagArgument(plugin));
        addArgument(new KingdomRenameArgument(plugin));
        addArgument(new KingdomInviteArgument(plugin));
        addArgument(new KingdomLeaveArgument(plugin));
        addArgument(new KingdomWhoArgument(plugin));
        addArgument(new KingdomJoinArgument(plugin));
        addArgument(new KingdomDiplomacyArgument(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            helpArgument.onCommand(sender, command, label, args);
            return true;
        }

        CommandArgument argument = getArgument(args[0]);
        if (argument != null) {
            String permission = argument.getPermission();
            if (permission == null || sender.hasPermission(permission)) {
                argument.onCommand(sender, command, label, args);
                return true;
            }
        }

        helpArgument.onCommand(sender, command, label, args);
        return true;
    }
}