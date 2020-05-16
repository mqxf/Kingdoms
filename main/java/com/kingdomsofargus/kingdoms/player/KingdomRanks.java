package com.kingdomsofargus.kingdoms.player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import com.kingdomsofargus.kingdoms.Kingdoms;

import net.md_5.bungee.api.ChatColor;

public class KingdomRanks {

	public static String getPrefix(String rank) {
		try {
			PreparedStatement statement = Kingdoms.getInstance(). mySQL.getConnection().prepareStatement("SELECT prefix FROM ranks WHERE name=?");
			statement.setString(1, rank);
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ChatColor.RED + "[NO PREFIX FOUND]";
	}
	public static void setPrefix(String prefix, String rank) {
		try {
		     String query = "update ranks set prefix = ? where name = ?";
		     PreparedStatement preparedStmt = Kingdoms.mySQL.getConnection().prepareStatement(query);
		     preparedStmt.setString(1, prefix);
		     preparedStmt.setString(2, rank);
			 preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static boolean rankExists(String rank) {
		try {
			PreparedStatement statement = Kingdoms.getInstance(). mySQL.getConnection().prepareStatement("SELECT * FROM ranks WHERE name=?");
			statement.setString(1, rank);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void createRank(String newRank, String prefix) {
		try {
			if (rankExists(newRank) != true) {
				PreparedStatement insert = Kingdoms.mySQL.getConnection()
						.prepareStatement("INSERT INTO ranks (NAME,PREFIX) VALUES (?,?)");
				
				//NAME
				insert.setString(1, newRank);
				//PREFIX
				insert.setString(2, prefix);
				
				insert.execute();
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Rank inserted : " + newRank);
				

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
