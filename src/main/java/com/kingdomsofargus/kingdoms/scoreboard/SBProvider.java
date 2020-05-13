package com.kingdomsofargus.kingdoms.scoreboard;

import com.bizarrealex.aether.scoreboard.Board;
import com.bizarrealex.aether.scoreboard.BoardAdapter;
import com.bizarrealex.aether.scoreboard.cooldown.BoardCooldown;
import com.bizarrealex.aether.scoreboard.cooldown.BoardFormat;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

public class SBProvider implements BoardAdapter, Listener {

	public static ArrayList<UUID> hasScoreboard = new ArrayList<UUID>();
	
    public SBProvider(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public String getTitle(Player player) {
        return Utils.chat("&6&lKingdoms &7(Map 1)");
    }
    
    @Override
    public List<String> getScoreboard(Player player, Board board, Set<BoardCooldown> cooldowns) {
    	if (hasScoreboard.contains(player.getUniqueId())) {
        List<String> strings = new ArrayList<>();

        double purseBalance = KingdomPlayer.getPurseBalance(player.getUniqueId());
        double bankBalance = KingdomPlayer.getBankBalance(player.getUniqueId());
        
        String area = "&7NONE";
        String kingdom = KingdomPlayer.getKingdom(player.getUniqueId());
        strings.add("&7&m-------------------");
        strings.add("&6&lBalance:");
        strings.add("  &6Purse: &7" + purseBalance);
        strings.add("  &6Bank: &7" + bankBalance);
        strings.add("");
        strings.add("  &aKingdom: &7" + kingdom);
        strings.add("  &aArea: &7" + area);
        strings.add("");
        strings.add("&ekingdomsofargus.com");
        strings.add("&7&m-------------------");

        if (strings.size() == 2) {
            return null;
        }

        return strings;
    }
    	return null;
   }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Board board = Board.getByPlayer(player);

    }

}