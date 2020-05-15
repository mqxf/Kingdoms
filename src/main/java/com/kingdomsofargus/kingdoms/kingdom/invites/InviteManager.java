package com.kingdomsofargus.kingdoms.kingdom.invites;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.Kingdom;
import com.kingdomsofargus.kingdoms.sql.Callback;
import com.kingdomsofargus.kingdoms.sql.Database;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InviteManager {

        private Database db;
        private Kingdoms core;
        private HashMap<String, Invite> invites = new HashMap<String, Invite>();
        public InviteManager(Kingdoms core, Database db) {
            this.core = core;
            this.db = db;
        }

        public Invite getInvite(Player player) {
            if (invites.isEmpty()) {
                fetchInvite(player.getUniqueId());
            }
            if (invites.keySet().contains(player.getUniqueId().toString())) {
                return invites.get(player.getUniqueId().toString());
            } else {
                fetchInvite(player.getUniqueId());
                if (invites.keySet().contains(player.getUniqueId().toString())) {
                    return invites.get(player.getUniqueId().toString());
                }
            }
            return null;
        }

        public void fetchInvite(UUID uuid) {
            db.executeQuery("SELECT * FROM invites WHERE uuid = ?", new Callback<ResultSet>() {
                @Override
                public void execute(ResultSet response) {
                    try {
                        while (response.next()) {
                            Invite invite = new Invite(response.getInt("kingdom_id"), uuid);
                            invite.setInvitedToKingdom(response.getInt("kingdom_id"));
                            invite.setUUID(uuid);
                            invites.put(response.getString("uuid"), invite);
                        }


                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }, uuid.toString());
        }


        public void saveInvites() {
            for (Invite i : invites.values()) {
                saveInvite(false, i);
            }
        }

        public boolean inviteExists(UUID uuid) {
            ExecutorService service = Executors.newSingleThreadExecutor();
            Future<Boolean> fut = service.submit(() -> {
                try {
                    PreparedStatement statement = db.getConnection().prepareStatement("select * from invites where uuid = ?");
                    statement.setString(1, uuid.toString());
                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        if (rs.getString("uuid") != null) {
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



        private void saveInvite(boolean single, Invite invite) {
            try {
                PreparedStatement stmt = db.getConnection().prepareStatement("UPDATE invites SET `uuid` = ?, `kingdom_id` = ? WHERE uuid = ?;");
                stmt.setString(1, invite.getUUID());
                stmt.setInt(2, invite.getInvitedToKingdom());
                stmt.setString(3, invite.getUUID());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (single) {
                invites.remove(invite.getUUID());
            }
        }

        public void createNewInvite(Player p, Kingdom kingdom) {
            db.insertInvite(p.getUniqueId().toString(), kingdom.getId());
            fetchInvite(p.getUniqueId());
        }

    public boolean isUserInvited(UUID uuid) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Boolean> fut = service.submit(() -> {
            try {
                PreparedStatement statement = db.getConnection().prepareStatement("select * from invites where uuid = ?");
                statement.setString(1, uuid.toString());
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    if (rs.getString("uuid") != null) {
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

    public boolean uninviteUser(String uuid) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Boolean> fut = service.submit(() -> {
            try {
                PreparedStatement statement = db.getConnection().prepareStatement("delete from invites where uuid = ?");
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

        public HashMap<String, Invite> getInvites() {
            return invites;
        }
}
