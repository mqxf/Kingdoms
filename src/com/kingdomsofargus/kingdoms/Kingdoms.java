package com.kingdomsofargus.kingdoms;

import java.util.HashMap;
import java.util.Map;

import com.kingdomsofargus.kingdoms.api.DataFile;
import com.kingdomsofargus.kingdoms.sql.Database;
import com.kingdomsofargus.kingdoms.tasks.BackupTask;
import com.kingdomsofargus.kingdoms.user.Rank;
import com.kingdomsofargus.kingdoms.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.kingdomsofargus.kingdoms.commands.DelRankCommand;
import com.kingdomsofargus.kingdoms.commands.GenderCommand;
import com.kingdomsofargus.kingdoms.commands.RankCommand;
import com.kingdomsofargus.kingdoms.commands.SetPrefixCommand;
import com.kingdomsofargus.kingdoms.commands.SetRankCommand;
import com.kingdomsofargus.kingdoms.events.ChatListener;
import com.kingdomsofargus.kingdoms.events.InventoryListener;
import com.kingdomsofargus.kingdoms.events.JoinListener;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;

import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;

public class Kingdoms extends JavaPlugin {


	public boolean hasThroneRoom = false;
	public boolean hasTreasureVault = false;
	public boolean aboveGround = false;
	public boolean oneDayPlaytime = false;

	private Rank defaultRank;
	public DataFile ranksFile;
	private DataFile messageFile;
	private DataFile configFile;
	private Database database;
	private DataFile databaseFile;
	private UserManager userManager;
	public static Kingdoms core;
	private Map<String, Rank> idToRank = new HashMap<>();
	//public HashMap<Player, KingdomPlayer> KP;

	//PLAYER {UUID, NAME, GENDER, PURSE_COINS, RANK, BANK_COINS, CLASS, KINGDOM, LEVEL, XP, FIRSTJOIN}
	//SKILLS {UUID, S1, X1, S2, X2, S3, X3, S4, X4, S5, X5}    (S = Skill) (X = XP)
	//KINGDOM {UUID (King/Queen), NAME}

	/*
	 * Work separation:
	 *
	 * Freddie: permission flatfile
	 *
	 * Maxim: GUI's
	 *
	 * Kingdom setup
	 *
	 *
	 *
	 */



	public void onEnable() {
		Kingdoms.core = this;


		configFile = new DataFile(this, "config", true);
		messageFile = new DataFile(this, "messages", true);
		databaseFile = new DataFile(this, "database", true);
		ranksFile = new DataFile(this, "ranks", true);
		database = new Database(this, databaseFile);
		userManager = new UserManager(this, database);

		//KP = new HashMap<Player, KingdomPlayer>();

		getCommand("delrank").setExecutor(new DelRankCommand());
		getCommand("rank").setExecutor(new RankCommand(this));
		getCommand("setrank").setExecutor(new SetRankCommand(this));
		getCommand("setprefix").setExecutor(new SetPrefixCommand());
		getCommand("gender").setExecutor(new GenderCommand());

		PluginManager pm = Bukkit.getPluginManager();
			new ChatListener(this);
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new InventoryListener(), this);

		new BukkitRunnable() {
			@Override
			public void run() {
				new BackupTask(core);
			}
		}.runTaskTimerAsynchronously(core, 1L, 600 * 20);

		loadRanks();
	}

	public void onDisable() {
		Kingdoms.core = null;
		getDb().disconnect();
	}

	public UserManager getUserManager() {
		return userManager;
	}

	private void loadRanks() {
		for (String s : ranksFile.getConfig().getConfigurationSection("ranks").getKeys(false)) {
			Rank rank = new Rank(ranksFile.getConfig().getString("ranks." + s + ".id"),
					ranksFile.getConfig().getString("ranks." + s + ".prefix"),
					ChatColor.valueOf(ranksFile.getConfig().getString("ranks." + s + ".color")),
					ranksFile.getConfig().getStringList("ranks." + s + ".perms"));
			if (ranksFile.getConfig().isSet("ranks." + s + ".default")) {
				defaultRank = rank;
			}
			if (ranksFile.getConfig().isSet("ranks." + s + ".inherit")) {
				rank.setInherits(ranksFile.getConfig().getStringList("ranks." + s + ".inherit"));
			}
			idToRank.put(ranksFile.getConfig().getString("ranks." + s + ".id"), rank);
		}
		if (defaultRank == null) {
			System.out.println("Crashing! No default rank was set! This is NOT GOOD!");
			Bukkit.getPluginManager().disablePlugin(this);
		}
	}

	public DataFile getConfigFile() {
		return configFile;
	}

	public DataFile getMessageFile() {
		return messageFile;
	}

	public DataFile getDatabaseFile() {
		return databaseFile;
	}

	public Rank getDefaultRank() {
		return defaultRank;
	}

	public Map<String, Rank> getIdToRank() {
		return idToRank;
	}

	public Database getDb() {
		return database;
	}

	public static Kingdoms getCore() {
		return core;
	}
}
