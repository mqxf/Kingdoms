package com.kingdomsofargus.kingdoms.kingdom;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.sql.Callback;
import com.kingdomsofargus.kingdoms.sql.Database;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class KingdomManager {

	private Database db;
	private Kingdoms core;
	private HashMap<Integer, Kingdom> kingdoms = new HashMap<>();
	public KingdomManager(Kingdoms core, Database db) {
		this.core = core;
		this.db = db;
	}

	public Kingdom getKingdom(int id) {
		if (kingdoms.isEmpty()) {
			fetchKingdom(id);
		}
		if (kingdoms.keySet().contains(id)) {
			return kingdoms.get(id);
		} else {
			fetchKingdom(id);
			if (kingdoms.keySet().contains(id)) {
				return kingdoms.get(id);
			}
		}
		return null;
	}

	public void fetchKingdom(int id) {
		db.executeQuery("SELECT * FROM kingdoms WHERE id = ?", new Callback<ResultSet>() {
			@Override
			public void execute(ResultSet response) {
				try {
						while (response.next()) {
							Kingdom kingdom = new Kingdom(response.getString("leader"), response.getString("name"), response.getInt("id"));
							kingdom.setName(response.getString("name"));
							kingdom.setLeader(response.getString("leader"));
							kingdom.setTag(response.getString("tag"));
							kingdom.loadMembers(response.getString("members"));
							kingdom.setAllies(response.getString("allies"));
							kingdom.setNeutral(response.getString("neutral"));
							kingdom.setIn_War(response.getString("in_war"));
							kingdom.setEnemy(response.getString("enemy"));
							// TODO setup roles
							kingdom.setId(response.getInt("id"));
							kingdom.setAnnouncement(response.getString("announcement"));
							kingdom.setBank(response.getInt("bank"));
							kingdoms.put(response.getInt("id"), kingdom);
						}


			} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}, id);
	}


	public void saveKingdoms() {
		for (Kingdom k : kingdoms.values()) {
			saveKingdom(false, k);
		}
	}

	public boolean kingdomExists(String name) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<Boolean> fut = service.submit(() -> {
			try {
				PreparedStatement statement = db.getConnection().prepareStatement("select * from kingdoms where name = ?");
				statement.setString(1, name);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					if (rs.getString("name") != null) {
						return true;
					} else {
						return false;
					}
				}
				return false;
			} catch (SQLException e) {
				service.shutdown();
				System.out.println("Error performing SQL query: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
				return false;
			}
		});
		try {
			service.shutdown();
			return fut.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Error terminating query async pool: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
			return false;
		}
	}

	public int getKingdomByName(String name) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<Integer> fut = service.submit(() -> {
			try {
				PreparedStatement statement = db.getConnection().prepareStatement("select * from kingdoms where name = ?");
				statement.setString(1, name);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					if (rs.getString("name") != null) {
						return rs.getInt("id");
					} else {
						return 0;
					}
				}
				return 0;
			} catch (SQLException e) {
				service.shutdown();
				System.out.println("Error performing SQL query: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
				return 0;
			}
		});
		try {
			service.shutdown();
			return fut.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Error terminating query async pool: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
			return 0;
		}
	}


	private void saveKingdom(boolean single, Kingdom kingdom) {
		try {
			PreparedStatement stmt = db.getConnection().prepareStatement("UPDATE kingdoms SET `name` = ?, `leader` = ?, `tag` = ?, `bank` = ?, allies = ?, enemy = ?, neutral = ?, members = ? WHERE id = ?;");
			stmt.setString(1, kingdom.getName());
			stmt.setString(2, kingdom.getLeader());
			stmt.setString(3, kingdom.getTag());
			stmt.setInt(4, kingdom.getBank());
			stmt.setString(5, kingdom.getAllies());
			stmt.setString(6, kingdom.getEnemy());
			stmt.setString(7, kingdom.getNeutral());
			stmt.setString(8, kingdom.membersToString());
			stmt.setInt(9, kingdom.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		if (single) {
			kingdoms.remove(kingdom.getId());
		}
	}

	public void createNewKingdom(Player p, String name, int random_id) {
		db.insertKingdom(random_id, p.getUniqueId().toString(), name, "none", 0);
		fetchKingdom(random_id);
	}


	public HashMap<Integer, Kingdom> getKingdoms() {
		return kingdoms;
	}
}
