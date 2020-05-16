package com.kingdomsofargus.kingdoms.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import com.kingdomsofargus.kingdoms.Kingdoms;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;

public class MySQL {
	
	@Getter public static MySQL instance;
	
	private Connection connection;
	private String database, host, username, password;
	private int port;
	
	public void setupSQL() {
		host = Kingdoms.getInstance().getConfig().getString("DATABASE.HOST");
		port = Kingdoms.getInstance().getConfig().getInt("DATABASE.PORT");
		database = Kingdoms.getInstance().getConfig().getString("DATABASE.DATABASE");
		username = Kingdoms.getInstance().getConfig().getString("DATABASE.USERNAME");
		password = Kingdoms.getInstance().getConfig().getString("DATABASE.PASSWORD");

		try {
			synchronized (this) {
				if (getConnection() != null && !getConnection().isClosed()) {
					return;
				}

				Class.forName("com.mysql.jdbc.Driver");
				setConnection(
						DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database,
								this.username, this.password));

				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL CONNECTED");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}
