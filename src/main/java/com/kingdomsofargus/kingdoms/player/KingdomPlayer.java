package com.kingdomsofargus.kingdoms.player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.commands.economy.BalanceCommand;

public class KingdomPlayer {

	//NEED TO IMPLEMENT METHODS
	
	private Player player;
	
	public Player getPlayer() {
		return player;
	}
	
	public KingdomPlayer(Player player) {
		this.player = player;
	}
	
	public static void setGender(UUID uuid, String gender) {
		try {
		     String query = "update users set GENDER = ? where UUID = ?";
		     PreparedStatement preparedStmt = Kingdoms.mySQL.getConnection().prepareStatement(query);
		     preparedStmt.setString(1, gender);
		     preparedStmt.setString(2, uuid.toString());
			 preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String getGender(UUID uuid) {
		try {
			PreparedStatement statement = Kingdoms.getInstance(). mySQL.getConnection().prepareStatement("SELECT GENDER FROM users WHERE UUID=?");
			statement.setString(1, uuid.toString());
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "No data for user.";
	}
	public static String getRank(UUID uuid) {
		try {
			PreparedStatement statement = Kingdoms.getInstance(). mySQL.getConnection().prepareStatement("SELECT RANK FROM users WHERE UUID=?");
			statement.setString(1, uuid.toString());
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "No data for user.";
	}
	public static void setRank(UUID uuid, String rank) {
		try {
		     String query = "update users set RANK = ? where UUID = ?";
		     PreparedStatement preparedStmt = Kingdoms.mySQL.getConnection().prepareStatement(query);
		     preparedStmt.setString(1, rank);
		     preparedStmt.setString(2, uuid.toString());
			 preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String getClass(UUID uuid) {
		try {
			PreparedStatement statement = Kingdoms.getInstance(). mySQL.getConnection().prepareStatement("SELECT CLASS FROM users WHERE UUID=?");
			statement.setString(1, uuid.toString());
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "No data for user.";
	}
	public static String getKingdom(UUID uuid) {
		try {
			PreparedStatement statement = Kingdoms.getInstance(). mySQL.getConnection().prepareStatement("SELECT KINGDOM FROM users WHERE UUID=?");
			statement.setString(1, uuid.toString());
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "No data for user.";
	}
	public static void setKingdom(UUID uuid, String name) {
		try {
		     String query = "update users set KINGDOM = ? where UUID = ?";
		     PreparedStatement preparedStmt = Kingdoms.mySQL.getConnection().prepareStatement(query);
		     preparedStmt.setString(1, name);
		     preparedStmt.setString(2, uuid.toString());
			 preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void setClass(UUID uuid, String suffixClass) {
		try {
		     String query = "update users set CLASS = ? where UUID=?";
		     PreparedStatement preparedStmt = Kingdoms.mySQL.getConnection().prepareStatement(query);
		     preparedStmt.setString(1, suffixClass);
		     preparedStmt.setString(2, uuid.toString());
			 preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static double getPurseBalance(UUID uuid) {
		try {
			String query = "SELECT PURSE_COINS FROM users WHERE UUID=?";
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement(query);
			statement.setString(1, uuid.toString());
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			double balance = ((Number) resultSet.getObject(1)).doubleValue();
			return balance;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void setPurseBalance(UUID uuid, double balance) {
		try {
			String query = "update users set PURSE_COINS = ? where UUID = ?";
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement(query);
			statement.setDouble(1, balance);
			statement.setString(2, uuid.toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static double getBankBalance(UUID uuid) {
		try {
			String query = "SELECT BANK_COINS FROM users WHERE UUID=?";
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement(query);
			statement.setString(1, uuid.toString());
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			double balance = ((Number) resultSet.getObject(1)).doubleValue();
			return balance;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void setBankBalance(UUID uuid, double balance) {
		try {
			String query = "update users set BANK_COINS = ? where UUID = ?";
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement(query);
			statement.setDouble(1, balance);
			statement.setString(2, uuid.toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setCP(UUID uuid, boolean cp) {
		try {
			String query = "update users set CP = ? where UUID = ?";
			PreparedStatement statement = Kingdoms.mySQL.getConnection().prepareStatement(query);
			statement.setBoolean(1, cp);
			statement.setString(2, uuid.toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static boolean getCP(UUID uuid) {
		try {
			String query = "SELECT CP FROM users WHERE UUID=?";
			PreparedStatement statement =  Kingdoms.mySQL.getConnection().prepareStatement(query);
			statement.setString(1, uuid.toString());
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
