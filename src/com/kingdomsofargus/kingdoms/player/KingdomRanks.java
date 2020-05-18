package com.kingdomsofargus.kingdoms.player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kingdomsofargus.kingdoms.Kingdoms;

import net.md_5.bungee.api.ChatColor;

public class KingdomRanks {

	/*
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
	}*/
}
