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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `users` (`uuid` varchar(255), `username` varchar(255), `purse_coins` INT, `bank_coins` INT, `gender` varchar(255), `rank` varchar(255), `class` varchar(255), `skills` varchar(255), `kingdom_id` INT, `level` INT, `xp` INT, `perms` TEXT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `kingdoms` (`id` INT, `name` varchar(255), `leader` varchar(255), `tag` varchar(255), `bank` INT, `members` varchar(255), `announcement` varchar(255),  `enemy` varchar(255),  `allies` varchar(255),  `neutral` varchar(255), `invites` varchar(255))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `invites` (`uuid` varchar(255), `kingdom_id` INT)");
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

    public void insertKingdom(int id, String leader, String name, String tag, int bank) {
        String query = "INSERT INTO `kingdoms`(`id`,`leader`,`name`,`tag`,`bank`,`members`)" +
                "VALUES(?,?,?,?,?,?);";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, leader);
            stmt.setString(3, name);
            stmt.setString(4, tag);
            stmt.setInt(5, bank);
            stmt.setString(6, leader);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertInvite(String uuid, int kingdom_id) {
        String query = "INSERT INTO `invites`(`uuid`,`kingdom_id`)" +
                "VALUES(?,?);";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, uuid);
            stmt.setInt(2, kingdom_id);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public void updateUserRank(String uuid, String name, int coins, int tokens, String rank, String tag, int level, String clan) {
        String query = "UPDATE `users`(`uuid`,`username`,`coins`,`tokens`,`rank`,`tag`,`level`, `clan`, `stats`)" +
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
