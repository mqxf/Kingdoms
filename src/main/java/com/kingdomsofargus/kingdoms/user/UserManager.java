package com.kingdomsofargus.kingdoms.user;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.sql.Callback;
import com.kingdomsofargus.kingdoms.sql.Database;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
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
            System.out.println("Fetching Users....");
        }
        if (users.keySet().contains(p.getUniqueId())) {
            System.out.println("User was in KeySet");
            return users.get(p.getUniqueId());

        } else {
            fetchUser(p);
            System.out.println("last before");
            if (users.keySet().contains(p.getUniqueId())) {
                System.out.println("last");
                return users.get(p.getUniqueId());
            }
        }
        return null;
    }


    public String getOfflineUUID(String name) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> fut = service.submit(() -> {
            try {
                PreparedStatement statement = db.getConnection().prepareStatement("SELECT uuid FROM users WHERE `username` = ?");
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
                            if (!p.getName().equals(response.getString("username"))) {
                                user.setUsername(p.getName());
                            } else {
                                user.setUsername(response.getString("username"));
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
    public void saveOfflineUser(String uuid, String collumn, String value) {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("UPDATE users" +
                    " SET `" + collumn + "` = ? WHERE uuid = ?;");
            stmt.setString(1, value);
            stmt.setString(2, uuid);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void saveOfflineUser(String uuid, String collumn, int integer) {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("UPDATE users" +
                    " SET `" + collumn + "` = ? WHERE uuid = ?;");
            stmt.setInt(1, integer);
            stmt.setString(2, uuid);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createNewUser(Player p) {
        System.out.println("Attempting to create new player...");
        db.insertUser(p.getUniqueId().toString(), p.getName(), "none", 500, 0, "Default", "WANDERER", 0, 0, 0);
        fetchUser(p);
    }


    public boolean userExists(String uuid) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Boolean> fut = service.submit(() -> {
            try {
                PreparedStatement statement = db.getConnection().prepareStatement("select * from users where uuid = ?");
                statement.setString(1, uuid);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    if (rs.getString("username") != null) {
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

    public String getUuid(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/"+name;
        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(url));
            if(UUIDJson.isEmpty()) return "invalid name";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            return UUIDObject.get("id").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }


    public HashMap<UUID, User> getUsers() {
        return users;
    }
}
