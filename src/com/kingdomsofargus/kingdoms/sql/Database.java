package com.kingdomsofargus.kingdoms.sql;

import com.kingdomsofargus.kingdoms.api.DataFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;

public class Database {

    private DriverManagerDataSource ds;
    private Connection connection;
    private JavaPlugin pl;

    public Database(JavaPlugin pl, DataFile config) {
        this.pl = pl;
        ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:mysql://" + config.getString("host") + ":" + config.getConfig().getInt("port") + "/" + config.getString("database") + "?characterEncoding=latin1&autoReconnect=true");
        ds.setPassword(config.getString("password"));
        ds.setUsername(config.getString("username"));
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        try {
            openConnection();
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `users` (`uuid` varchar(255), `username` varchar(255), `purse_coins` INT, `bank_coins` INT, `gender` varchar(255), `rank` varchar(255), `class` varchar(255), `level` INT, `xp` INT, `perms` TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void executeQuery(String query, final Callback<ResultSet> callback, Object... parameters) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            int i = 1;
            for (Object obj : parameters) {
                if (obj instanceof String) {
                    stmt.setString(i, (String) obj);
                } else if (obj instanceof Boolean) {
                    stmt.setBoolean(i, (Boolean) obj);
                } else if (obj instanceof Integer) {
                    stmt.setInt(i, (Integer) obj);
                }
                i++;
            }
            ResultSet set = stmt.executeQuery();
            callback.execute(set);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void insertUser(String uuid, String username, String gender, int purse_coins, int bank_coins, String rank, String userclass, int kingdom_id, int level, int xp) {
        String query = "INSERT INTO `users`(`uuid`,`username`,`gender`,`purse_coins`,`bank_coins`,`rank`,`class`, `kingdom_id`, `level`,`xp`)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, uuid);
            stmt.setString(2, username);
            stmt.setString(3, gender);
            stmt.setInt(4, purse_coins);
            stmt.setInt(5, bank_coins);
            stmt.setString(6, rank);
            stmt.setString(7, userclass);
            stmt.setInt(8, kingdom_id);
            stmt.setInt(9, level);
            stmt.setInt(10, xp);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateUserRank(String uuid, String name, int coins, int tokens, String rank, String tag, int level, String clan) {
        String query = "UPDATE `users`(`uuid`,`name`,`coins`,`tokens`,`rank`,`tag`,`level`, `clan`, `stats`)" +
                "VALUES(?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, uuid);
            stmt.setString(2, name);
            stmt.setInt(3, coins);
            stmt.setInt(4, tokens);
            stmt.setString(5, rank);
            stmt.setString(6, tag);
            stmt.setInt(7, level);
            stmt.setString(8, clan);
            stmt.setString(9, new JSONObject().toJSONString());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertClan(String name, String leader, String description, String discord, boolean open, int level) {
        String query = "INSERT INTO `clans`(`name`,`leader`,`description`,`discord`,`level`,`open`,`members`)" +
                "VALUES(?,?,?,?,?,?,?);";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, leader);
            stmt.setString(3, description);
            stmt.setString(4, discord);
            stmt.setInt(5, level);
            stmt.setString(6, "no");
            stmt.setString(7, new JSONObject().toJSONString());
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertBan(String uuid, String start, String end, String staff, String reason) {
        String query = "INSERT INTO `bans`(`uuid`,`start`,`end`,`staff`,`reason`)" +
                "VALUES(?,?,?,?,?);";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, uuid);
            stmt.setString(2, start);
            stmt.setString(3, end);
            stmt.setString(4, staff);
            stmt.setString(5, reason);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateBan(String uuid, String start, String end, String staff, String reason) {
        String query = "UPDATE `bans` SET uuid = ? start = ? `end` = ?, staff = ?, reason = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, uuid);
            stmt.setString(2, start);
            stmt.setString(3, end);
            stmt.setString(4, staff);
            stmt.setString(5, reason);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertMute(String uuid, String start, String end, String staff, String reason) {
        String query = "INSERT INTO `mutes`(`uuid`,`start`,`end`,`staff`,`reason`)" +
                "VALUES(?,?,?,?,?);";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, uuid);
            stmt.setString(2, start);
            stmt.setString(3, end);
            stmt.setString(4, staff);
            stmt.setString(5, reason);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateMute(String uuid, String start, String end, String staff, String reason) {
        String query = "UPDATE `mutes` SET uuid = ? start = ? `end` = ?, staff = ?, reason = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, uuid);
            stmt.setString(2, start);
            stmt.setString(3, end);
            stmt.setString(4, staff);
            stmt.setString(5, reason);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void executeUpdate(String query) {
        try {
            Statement stmt = getConnection().createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void openConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            connection = ds.getConnection();
        }
    }


    public Connection getConnection() {
        try {
            if (connection == null || !connection.isValid(0)) {
                connection = ds.getConnection();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

}
