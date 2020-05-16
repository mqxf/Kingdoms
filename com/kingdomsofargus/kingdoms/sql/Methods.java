package com.kingdomsofargus.kingdoms.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.rank.classes.Classes;

import net.md_5.bungee.api.ChatColor;

public class Methods {
	
	private static Kingdoms main = Kingdoms.getInstance();
	
	public static boolean playerExists(UUID uuid) {
		try {
			PreparedStatement statement = Kingdoms.getInstance(). mySQL.getConnection().prepareStatement("SELECT * FROM users WHERE UUID=?");
			statement.setString(1, uuid.toString());
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//PLAYER {1. UUID, 2. NAME, 3. GENDER, 4. PURSE_COINS, 5. RANK, 6. BANK_COINS, 7. CLASS, 8. KINGDOM, 9. LEVEL, 10. XP}
	public static void createPlayer(final UUID uuid, Player player) {
		try {
			PreparedStatement statement = Kingdoms.mySQL.getConnection()
					.prepareStatement("SELECT * FROM USERS WHERE UUID=?");
			statement.setString(1, uuid.toString());
			ResultSet results = statement.executeQuery();
			results.next();
			System.out.print(1);
			if (playerExists(uuid) != true) {
				PreparedStatement insert = Kingdoms.mySQL.getConnection()
						.prepareStatement("INSERT INTO users (UUID,NAME,GENDER,PURSE_COINS,RANK,BANK_COINS,CLASS,KINGDOM,LEVEL,XP,CP) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
				
				//UUID
				insert.setString(1, uuid.toString());
				//PLAYER
				insert.setString(2, player.getName());
				//GENDER
				insert.setString(3, "TBD");
				//PURSECOINS
				insert.setInt(4, 500);
				//RANK
				insert.setString(5, "Member");
				//BANKCOINS
				insert.setInt(6, 0);
				Classes.getInstance();
				//CLASS
				insert.setString(7, Classes.WANDERER.getName());
				//KINGDOM
				insert.setString(8, "NONE"); 
				//LEVEL
				insert.setInt(9, 0);
				//XP
				insert.setInt(10, 0);
				//CHECKPOINT
				insert.setBoolean(11, false);
				
				insert.executeUpdate();
				
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Player inserted : " + player.getName());
				

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getPlayer(final UUID uuid, Player player) {
		
	}
	
}
