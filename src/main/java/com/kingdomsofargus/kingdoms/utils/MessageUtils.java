package com.kingdomsofargus.kingdoms.utils;

import com.kingdomsofargus.kingdoms.Kingdoms;
import org.bukkit.command.CommandSender;

public class MessageUtils {
    public static void sendMessageFromConfig(String configName, CommandSender sender) {
        sender.sendMessage(Color.color(Kingdoms.getCore().getMessageFile().getString("messages." + configName)));
    }
}
