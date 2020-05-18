package com.kingdomsofargus.kingdoms.user;


import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.rank.classes.Classes;
import com.kingdomsofargus.kingdoms.sql.Callback;
import com.kingdomsofargus.kingdoms.sql.Database;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.*;

public class UserManager {

        private Database db;
        private Kingdoms core;
        private HashMap<UUID, User> users = new HashMap<>();

        public UserManager(Kingdoms core, Database db) {
            this.core = core;
            this.db = db;
        }

        public User getUser(Player p) {
            if (users.isEmpty()) {
                fetchUser(p);
            }
            if (users.keySet().contains(p.getUniqueId())) {
                return users.get(p.getUniqueId());
            } else {
                fetchUser(p);
                if (users.keySet().contains(p.getUniqueId())) {
                    return users.get(p.getUniqueId());
                }
            }
            return null;
        }

        public String getOfflineUUID(String name) {
            ExecutorService service = Executors.newSingleThreadExecutor();
            Future<String> fut = service.submit(() -> {
                try {
                    PreparedStatement statement = db.getConnection().prepareStatement("SELECT uuid FROM users WHERE `name` = ?");
                    statement.setString(1, name);
                    ResultSet rs = statement.executeQuery();
                    rs.next();
                    String fetchUUID = rs.getString("uuid");
                    rs.getStatement().close();
                    rs.close();
                    service.shutdown();
                    return fetchUUID;
                } catch (SQLException e) {
                    service.shutdown();
                    System.out.println("Error performing SQL query: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
                    e.printStackTrace();
                    return null;
                }
            });
            try {
                service.awaitTermination(25, TimeUnit.SECONDS);
                return fut.get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error terminating query async pool: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
            }
            return null;
        }

    private void fetchUser(Player p) {
        db.executeQuery("SELECT * FROM users WHERE uuid = ?", new Callback<ResultSet>() {
            @Override
            public void execute(ResultSet response) {
                try {
                    if (!response.isBeforeFirst()) {
                        createNewUser(p);
                    } else {
                        while (response.next()) {
                            User user = new User(response.getString("uuid"));
                            if (!p.getName().equals(response.getString("name"))) {
                                user.setUsername(p.getName());
                            } else {
                                user.setUsername(response.getString("name"));
                            }
                            user.setGender(response.getString("gender"));
                            user.setPurse_coins(response.getInt("purse_coins"));
                            user.setBank_coins(response.getInt("bank_coins"));
                            user.setRank(response.getString("rank"));
                            user.setuClass(response.getString("class"));
                            user.setSkills(response.getString("skills"));
                            user.setKingdom_id(response.getInt("kingdom_id"));
                            user.setLevel(response.getInt("level"));
                            user.setXp(response.getInt("xp"));
                            user.loadPerms(response.getString("perms"));
                            users.put(UUID.fromString(response.getString("uuid")), user);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }, p.getUniqueId().toString());
    }


        public void saveUsers() {
            for (User u : users.values()) {
                saveUser(false, u);
            }
        }

        private void saveUser(boolean single, User user) {
            try {
                PreparedStatement stmt = db.getConnection().prepareStatement("UPDATE users" +
                        " SET `username` = ?, `purse_coins` = ?, `bank_coins` = ?, `rank` = ?, `class` = ?, `kingdom_id` = ?, `level` = ?, `xp` = ?, `perms` = ?, `gender` = ?" +
                        "WHERE uuid = ?;");
                stmt.setString(1, user.getUsername());
                stmt.setInt(2, (int) user.getPurse_coins());
                stmt.setInt(3, (int) user.getBank_coins());
                stmt.setString(4, user.getRank());
                stmt.setString(5, user.getuClass());
                stmt.setInt(6, user.getKingdom_id());
                stmt.setInt(7, user.getLevel());
                stmt.setInt(8, user.getXp());
                stmt.setString(9, user.permsToStr());
                stmt.setString(10, user.getGender());
                stmt.setString(11, user.getUuid());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (single) {
                users.remove(UUID.fromString(user.getUuid()));
            }
        }

        private void createNewUser(Player p) {
            db.insertUser(p.getUniqueId().toString(), p.getName(), "none", 500, 0, core.getDefaultRank().getId(), Classes.getInstance().WANDERER.getName(), 0, 0, 0);
            fetchUser(p);
        }

        public void setrank(String uuid, String rank) {
            ExecutorService service = Executors.newSingleThreadExecutor();
            Future<String> fut = service.submit(() -> {
                try {
                    PreparedStatement statement = db.getConnection().prepareStatement("select uuid from users where uuid = ?");
                    statement.setString(1, uuid);
                    ResultSet rs = statement.executeQuery();
                    rs.getStatement().close();
                    String uuidFetch = rs.getString("uuid");
                    service.shutdown();
                    rs.close();
                    return uuidFetch;
                } catch (SQLException e) {
                    service.shutdown();
                    System.out.println("Error performing SQL query: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
                    return null;
                }
            });
            try {
                service.awaitTermination(25, TimeUnit.SECONDS);
                if (fut.get() == null) {
                } else {
                }
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error terminating query async pool: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
            }
        }

        public boolean unmuteUser(String uuid) {
            ExecutorService service = Executors.newSingleThreadExecutor();
            Future<Boolean> fut = service.submit(() -> {
                try {
                    PreparedStatement statement = db.getConnection().prepareStatement("delete from mutes where uuid = ?");
                    statement.setString(1, uuid);
                    statement.executeUpdate();
                    service.shutdown();
                    return true;
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
            }
            return false;
        }

        public boolean isUserMuted(UUID uuid) {
            ExecutorService service = Executors.newSingleThreadExecutor();
            Future<Boolean> fut = service.submit(() -> {
                try {
                    PreparedStatement statement = db.getConnection().prepareStatement("select * from mutes where uuid = ?");
                    statement.setString(1, uuid.toString());
                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        if (rs.getString("uuid") != null) {
                            // Test timings
                            if (rs.getString("end").equalsIgnoreCase("PERMANENT")) {
                                return true;
                            } else {
                                String endTimeStr = rs.getString("end");
                                long endTime = Long.valueOf(endTimeStr);
                                return System.currentTimeMillis() <= endTime;
                            }
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

        public HashMap<UUID, User> getUsers() {
            return users;
        }
}
