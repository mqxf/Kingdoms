package com.kingdomsofargus.kingdoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.junit.validator.PublicClassValidator;

import com.deanveloper.skullcreator.SkullCreator;
import com.kingdomsofargus.kingdoms.commands.CheckpointCommand;
import com.kingdomsofargus.kingdoms.commands.SBToggleCommand;
import com.kingdomsofargus.kingdoms.commands.bans.*;
import com.kingdomsofargus.kingdoms.commands.disguise.DisguiseCommand;
import com.kingdomsofargus.kingdoms.commands.economy.*;
import com.kingdomsofargus.kingdoms.commands.npc.InfoNPCCommand;
import com.kingdomsofargus.kingdoms.commands.npc.NpcCommand;
import com.kingdomsofargus.kingdoms.commands.ranks.*;
import com.kingdomsofargus.kingdoms.commands.staff.FlyCommand;
import com.kingdomsofargus.kingdoms.commands.staff.GamemodeCommand;
import com.kingdomsofargus.kingdoms.commands.staff.HealCommand;
import com.kingdomsofargus.kingdoms.commands.staff.HealthCommand;
import com.kingdomsofargus.kingdoms.events.*;
import com.kingdomsofargus.kingdoms.events.anticheat.MoveListener;
import com.kingdomsofargus.kingdoms.kingdom.KingdomExecutor;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.player.User;
import com.kingdomsofargus.kingdoms.scoreboard.AssembleBoard;
import com.kingdomsofargus.kingdoms.sql.MySQL;
import com.kingdomsofargus.kingdoms.user.Rank;
import com.kingdomsofargus.kingdoms.utils.CheckpointManager;
import com.kingdomsofargus.kingdoms.utils.Utils;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.antlr.ExpressionParser.CaseContext;

import io.github.thatkawaiisam.assemble.Assemble;
import lombok.Getter;
import net.citizensnpcs.api.npc.NPC;

public class Kingdoms extends JavaPlugin {
	
	public static MySQL mySQL;
	
	public boolean hasThroneRoom = false;
	public boolean hasTreasureVault = false;
	public boolean aboveGround = false;
	public boolean oneDayPlaytime = false;
	public List<String> ranks;
	public boolean banned = false;
	
	private Map<String, Rank> idToRank = new HashMap<>();
	
	public HashMap<Player, User> USERS;
	public HashMap<Player, KingdomPlayer> KP;
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
	public Location infoNPC = new Location(Bukkit.getWorld("world"), 1.5, 79, 25.5, 0, 0);
	
	public NPC info;
	
	//PLAYER {UUID, NAME, GENDER, PURSE_COINS, RANK, BANK_COINS, CLASS, KINGDOM, LEVEL, XP, FIRSTJOIN}
	//SKILLS {UUID, S1, X1, S2, X2, S3, X3, S4, X4, S5, X5}    (S = Skill) (X = XP)
	//KINGDOM {UUID (King/Queen), NAME}
	//NPC {Name, int, int, int}
	
	@Getter public static Kingdoms instance;
	
	public void onEnable() {
		instance = this;
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
		
		getCommand("sbtoggle").setExecutor(new SBToggleCommand());
		getCommand("gender").setExecutor(new GenderCommand());
		getCommand("balance").setExecutor(new BalanceCommand()); 
		getCommand("disguise").setExecutor(new DisguiseCommand());
		
		//STAFF COMMANDS
		getCommand("setbalance").setExecutor(new SetBalanceCommand());
		getCommand("createrank").setExecutor(new CreateRankCommand());
		getCommand("delrank").setExecutor(new DelRankCommand());
		getCommand("rank").setExecutor(new RankCommand());
		getCommand("setrank").setExecutor(new SetRankCommand());
		getCommand("setprefix").setExecutor(new SetPrefixCommand());
		getCommand("cheatban").setExecutor(new CheatBanCommand());
		getCommand("delrank").setExecutor(new DelRankCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("gamemode").setExecutor(new GamemodeCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("health").setExecutor(new HealthCommand());
		getCommand("spawnnpc").setExecutor(new NpcCommand());
		getCommand("infonpc").setExecutor(new InfoNPCCommand());
		getCommand("checkpoint").setExecutor(new CheckpointCommand());
		
		//DEV TEST COMMANDS
		
		//KINGDOM
		getCommand("kingdom").setExecutor(new KingdomExecutor(this));
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ChatListener(), this);
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new QuitListener(), this);
		pm.registerEvents(new InventoryListener(), this);
		pm.registerEvents(new NPCEvent(), this);
		pm.registerEvents(new CheatListener(this), this);
		pm.registerEvents(new PickUpListener(), this);
		pm.registerEvents(new ClickListener(), this);
		pm.registerEvents(new MoveListener(), this);
		
		new Assemble(this, new AssembleBoard());
		
		saveDefaultConfig();
		
		resetViolations();
		
		Location loc = InfoNPCCommand.getInfo();
		
	}
	
	public void onDisable() {
		instance = null;
		NPC npc = this.info;
		npc.despawn();
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
	}
	
	public Map<String, Rank> getIdToRank() {
		return idToRank;
	}

}
