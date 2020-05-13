package com.kingdomsofargus.kingdoms;

import com.kingdomsofargus.kingdoms.api.DataFile;
import com.kingdomsofargus.kingdoms.commands.CheckpointCommand;
import com.kingdomsofargus.kingdoms.commands.CoindCMD;
import com.kingdomsofargus.kingdoms.commands.SBToggleCommand;
import com.kingdomsofargus.kingdoms.commands.bans.CheatBanCommand;
import com.kingdomsofargus.kingdoms.commands.disguise.DisguiseCommand;
import com.kingdomsofargus.kingdoms.commands.economy.BalanceCommand;
import com.kingdomsofargus.kingdoms.commands.economy.SetBalanceCommand;
import com.kingdomsofargus.kingdoms.commands.npc.InfoNPCCommand;
import com.kingdomsofargus.kingdoms.commands.npc.NpcCommand;
import com.kingdomsofargus.kingdoms.commands.ranks.GenderCommand;
import com.kingdomsofargus.kingdoms.commands.ranks.RankCommand;
import com.kingdomsofargus.kingdoms.commands.staff.FlyCommand;
import com.kingdomsofargus.kingdoms.commands.staff.GamemodeCommand;
import com.kingdomsofargus.kingdoms.commands.staff.HealCommand;
import com.kingdomsofargus.kingdoms.commands.staff.HealthCommand;
import com.kingdomsofargus.kingdoms.events.*;
import com.kingdomsofargus.kingdoms.kingdom.KingdomExecutor;
import com.kingdomsofargus.kingdoms.kingdom.KingdomManager;
import com.kingdomsofargus.kingdoms.player.User;
import com.kingdomsofargus.kingdoms.sql.Database;
import com.kingdomsofargus.kingdoms.user.Rank;
import com.kingdomsofargus.kingdoms.user.UserManager;
import com.kingdomsofargus.kingdoms.utils.Utils;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Kingdoms extends JavaPlugin {

	/**
	 * Setup
	 * Author: Autumn
	 */

	private Rank defaultRank;
	public DataFile ranksFile;
	private DataFile messageFile;
	private DataFile configFile;
	private Database database;
	private DataFile databaseFile;
	private UserManager userManager;
	private KingdomManager kindomManager;
	public static Kingdoms core;
	private Map<String, Rank> idToRank = new HashMap<>();
	/** **/

	public boolean hasThroneRoom = false;
	public boolean hasTreasureVault = false;
	public boolean aboveGround = false;
	public boolean oneDayPlaytime = false;
	public List<String> ranks;
	public boolean banned = false;
	
	public HashMap<Player, User> USERS;
	//public HashMap<Player, KingdomPlayer> KP;
	public HashMap<Player, Player> BANUI;
	public HashMap<Player, Boolean> combined;
	
	public HashMap<Player, Integer> checkpointID;
	public HashMap<Player, Boolean> hitCP;
	public HashMap<Player, Boolean> completedCP;
	public HashMap<Player, Integer> currentCP;
	
	public HashMap<Player, Boolean> invite;
	public HashMap<Player, String> inviteName;
	
	public HashMap<Player, String> kingdom;
	
	//TO be put into SQL later
	public Location secondSpawn = new Location(Bukkit.getWorld("world"), 99.5, 66, 69.5);
	public Location firstSpawn = new Location(Bukkit.getWorld("world"), 1.5, 79, 37.5);
	public Location cp1 = new Location(Bukkit.getWorld("world"), 1.5, 79, 28.5);
	public Location cp2 = new Location(Bukkit.getWorld("world"), 15.5, 79, 28.5);
	public Location cp3 = new Location(Bukkit.getWorld("world"), 15.5, 79, 14.5);
	public Location cp4 = new Location(Bukkit.getWorld("world"), 34.5, 79, 14.5);
	public Location cp5 = new Location(Bukkit.getWorld("world"), 49.5, 79, -0.5);
	public Location cp6 = new Location(Bukkit.getWorld("world"), 63.5, 79, 1.5);
	public Location cp7 = new Location(Bukkit.getWorld("world"), 79.5, 79, -4.5);
	public Location cp8 = new Location(Bukkit.getWorld("world"), 79.5, 79, -19.5);
	public Location cp9 = new Location(Bukkit.getWorld("world"), 103.5, 79, -16.5);
	public Location cp10 = new Location(Bukkit.getWorld("world"), 123.5, 79, 7.5);
	public Location cp11 = new Location(Bukkit.getWorld("world"), 119.5, 79, 22.5);
	public Location cp12 = new Location(Bukkit.getWorld("world"), 107.5, 79, 35.5);
	public Location cp13 = new Location(Bukkit.getWorld("world"), 105.5, 79, 56.5);
	public Location infoNPC = new Location(Bukkit.getWorld("world"), 1.5, 79, 25.5, 0, 0);
	
	public NPC info;
	
	//PLAYER {UUID, NAME, GENDER, PURSE_COINS, RANK, BANK_COINS, CLASS, KINGDOM, LEVEL, XP, FIRSTJOIN}
	//SKILLS {UUID, S1, X1, S2, X2, S3, X3, S4, X4, S5, X5}    (S = Skill) (X = XP)
	//KINGDOM {UUID (King/Queen), NAME}
	//NPC {Name, int, int, int}
	
	/*
	 * Work separation:
	 * 
	 * 
	 * Maxim: GUI's
	 *
	 * 
	 *  TODO bans system via SQL and fix/change to new setup
	 * 
	 * 
	 * 
	 * 
	 */
	
	public void onEnable() {
		/**
		 * Setup
		 */
		Kingdoms.core = this;

		configFile = new DataFile(this, "config", true);
		messageFile = new DataFile(this, "messages", true);
		databaseFile = new DataFile(this, "database", true);
		ranksFile = new DataFile(this, "ranks", true);
		database = new Database(this, databaseFile);
		userManager = new UserManager(this, database);
		kindomManager = new KingdomManager(this, database);

		/**
		ranks = new ArrayList<String>();
		
		USERS = new HashMap<Player, User>();
		
		KP = new HashMap<Player, KingdomPlayer>();
		BANUI = new HashMap<Player, Player>();
		combined = new HashMap<Player, Boolean>();
		
		checkpointID = new HashMap<Player, Integer>();
		hitCP = new HashMap<Player, Boolean>();
		completedCP = new HashMap<Player, Boolean>();
		currentCP = new HashMap<Player, Integer>();
		
		invite = new HashMap<Player, Boolean>();
		inviteName = new HashMap<Player, String>();
		
		kingdom = new HashMap<Player, String>();

		mySQL = new MySQL();
		mySQL.setupSQL();
		 **/

		new ChatListener(this);

		/**
		 * autumn GUI's
		 */

		//new KingdomCreationGUI(this);


		//saveDefaultConfig();
		
		//resetViolations();
		
		//Location loc = InfoNPCCommand.getInfo();

		new BukkitRunnable() {
			@Override
			public void run() {
				Kingdoms.getCore().getUserManager().saveUsers();
				Kingdoms.getCore().getUserManager().getUsers().clear();
				Kingdoms.getCore().getKindomManager().saveKingdoms();
				Kingdoms.getCore().getKindomManager().getKingdoms().clear();
				System.out.println("[Kingdoms] A quick backup was saved -- Database updated");
			}
		}.runTaskTimerAsynchronously(core, 20, 600 * 20);

		loadRanks();
		registerCommands();
		registerEvents();
	}


	public void onDisable() {
		Kingdoms.core = null;
		getKindomManager().saveKingdoms();
		getUserManager().saveUsers();
		getDb().disconnect();
		NPC	 npc = this.info;
		npc.despawn();
	}

	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new QuitListener(), this);
		pm.registerEvents(new InventoryListener(), this);
		pm.registerEvents(new NPCEvent(), this);
		//pm.registerEvents(new CheatListener(this), this);
		pm.registerEvents(new PickUpListener(), this);
		pm.registerEvents(new ClickListener(), this);
		//pm.registerEvents(new MoveListener(), this);
	}

	private void registerCommands() {

		// User Commands
		getCommand("sbtoggle").setExecutor(new SBToggleCommand());
		getCommand("gender").setExecutor(new GenderCommand());
		getCommand("balance").setExecutor(new BalanceCommand());
		getCommand("disguise").setExecutor(new DisguiseCommand());
		getCommand("coins").setExecutor(new CoindCMD());
		//KINGDOM Commands
		getCommand("kingdom").setExecutor(new KingdomExecutor(this));

		//STAFF COMMANDS
		getCommand("setbalance").setExecutor(new SetBalanceCommand());
		getCommand("rank").setExecutor(new RankCommand());
		getCommand("cheatban").setExecutor(new CheatBanCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("gamemode").setExecutor(new GamemodeCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("health").setExecutor(new HealthCommand());
		getCommand("spawnnpc").setExecutor(new NpcCommand());
		getCommand("infonpc").setExecutor(new InfoNPCCommand());
		getCommand("checkpoint").setExecutor(new CheckpointCommand());
	}


	public void logCheatCheck(String code, String hack, Player player) {
		User u = USERS.get(player);
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("cheat.log")) {
				p.sendMessage(Utils.chat("" + player.getDisplayName() + " " + hack + " " + u.getVioations()));
			}
		}
		
		if (u.getVioations() >= 40) {
			banPlayer(code, player, 30);
		}
		
	}
	
	public void banPlayer(String code, Player p, int days) {
		if (!banned) {
			Random random = new Random();
			int number = random.nextInt(900000) + 100000;
			Location loc = p.getLocation();
			World world = p.getWorld();
			String w = world.getName();
			Bukkit.getWorld(w).createExplosion(loc.getX(), loc.getY(), loc.getZ(), 6F, false, false);
			for (Player player : Bukkit.getOnlinePlayers()) {
				
					player.sendMessage(Utils.chat("&c&lA player has been removed from your lobby for hacking or abuse."));
				
			}
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban -s " + p.getName() + " 30d " + code + "-" + number);
			
			banned = true;
			new BukkitRunnable() {
				public void run() {
					banned = false;
				}
			}.runTaskLater(this, 5L);
			
		}
		
	}

	/**
	public void resetViolations() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					User user = USERS.get(player);
					user.resetViolations();
				}
			}
		}.runTaskTimer(this, 0, 15 * 20L);
	}**/

	public UserManager getUserManager() {
		return userManager;
	}

	public KingdomManager getKindomManager() {
		return kindomManager;
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
