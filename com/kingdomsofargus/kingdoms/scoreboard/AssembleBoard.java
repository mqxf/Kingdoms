 package com.kingdomsofargus.kingdoms.scoreboard;

import java.util.*;

import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.Utils;

import io.github.thatkawaiisam.assemble.AssembleAdapter;

public class AssembleBoard implements AssembleAdapter {
	
	public static ArrayList<UUID> hasScoreboard = new ArrayList<UUID>();
	
	@Override
	public String getTitle(Player player) {
		return Utils.chat(Kingdoms.getInstance().getConfig().getString("scoreboard.title"));
	}

	@Override
	public List<String> getLines(Player player) {
		final List<String> toReturn = new ArrayList<>();

        double purseBalance = KingdomPlayer.getPurseBalance(player.getUniqueId());
        double bankBalance = KingdomPlayer.getBankBalance(player.getUniqueId());
        
        String area = "&7NONE";
        String kingdom = KingdomPlayer.getKingdom(player.getUniqueId());
        
	        if (hasScoreboard.contains(player.getUniqueId())) {
	        
	        for (String string : Kingdoms.getInstance().getConfig().getStringList("scoreboard.lines")) {
	        	toReturn.add(Utils.chat(string)
	        			.replaceAll("%purseBalance%", String.valueOf(purseBalance))
	        			.replaceAll("%bankBalance%", String.valueOf(bankBalance))
	        			.replaceAll("%playerKingdom%", kingdom)
	        			.replaceAll("%areaStoodIn%", area));
	        }
        }
        
		return toReturn;
	}
	
	
}
