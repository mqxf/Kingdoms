package com.kingdomsofargus.kingdoms.kingdom;

import java.awt.Insets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.mojang.datafixers.types.templates.List;

public class KingdomManager {

	public static int costToMake = 100;
	public static boolean dayPlaytime = false;
	public static boolean hasThromeRoom = false;
	public static boolean hasTreasureVault = false;
	
	public static boolean kingdomExists(String name) {
		try {
			PreparedStatement statement = Kingdoms.getInstance(). mySQL.getConnection().prepareStatement("SELECT * FROM kingdoms WHERE NAME=?");
			statement.setString(1, name);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void addPlayerToLeader(String kingdom, UUID uuid) {
		try {
			String query = "UPDATE users SET CLASS = ? WHERE UUID=?";
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement(query);
			
        	if	(KingdomPlayer.getGender(uuid) == "King") {
        		statement.setString(1, "King");
        	} else {
        		statement.setString(1, "Queen");
        	}
        	statement.setString(2, uuid.toString());
        	
        	statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createKingdom(String name, String leader) {
		try {
			String query = "INSERT INTO kingdoms (NAME,LEADER,BALANCE,TAG) VALUES (?,?,?,?)";
			PreparedStatement insert = Kingdoms.mySQL.getConnection().prepareStatement(query);
			
			insert.setString(1, name);
			insert.setString(2, leader);
			insert.setInt(3, 0);
			
			String tag = name.charAt(0) + name.charAt(1) + name.charAt(2) + "";
			
			insert.setString(4, tag);
			
			insert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteKingdom(String name) {
		try {
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement("delete from kingdoms where name=?");
			statement.setString(1, name);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setTag(String name, String tag) {
		try {
			String query = "UPDATE kingdoms SET TAG = ? WHERE NAME=?";
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement(query);
			
			statement.setString(1, tag);
			statement.setString(2, name);
			
			statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static String getTag(String name) {
		try {
			String query = "SELECT TAG FROM kingdoms WHERE NAME=?";
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement(query);
			statement.setString(1, name);
			
			ResultSet rs = statement.executeQuery();
			rs.next();
			return rs.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Couldn't find TAG";
	}
	public static String getLeader(String name) {
		try {
			String query = "SELECT LEADER FROM kingdoms WHERE NAME=?";
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement(query);
			statement.setString(1, name);
			
			ResultSet rs = statement.executeQuery();
			rs.next();
			return rs.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Couldn't find LEADER";
	}
	public static void renameKingdom(String name, String newName) {
		try {
			String query = "UPDATE kingdoms SET NAME = ? WHERE NAME = ?";
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement(query);
			
			statement.setString(1, newName);
			statement.setString(2, name);
			statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> getMembers(String name) {
		try {
			String query = "SELECT NAME FROM users WHERE KINGDOM=?";
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement(query);
			statement.setString(1, name);
			
			ResultSet rs = statement.executeQuery();
			
			ArrayList<String> names = new ArrayList<String>();
			
			while (rs.next()) {
				String userName = rs.getString("NAME");
				names.add(userName);
			}
			return names;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static double getBank(String name) {
		ArrayList<String> names = getMembers(name);
		double balance = 0;
		for (int i = 0; i < names.size(); i++) {
			String player = names.get(i);
			double playerbal = KingdomPlayer.getBankBalance(Bukkit.getPlayer(player).getUniqueId());
			balance = balance + playerbal;
		}
		return balance;
	}
	
}
