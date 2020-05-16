package com.kingdomsofargus.kingdoms.user;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.api.events.UserBankCoinAddEvent;
import com.kingdomsofargus.kingdoms.api.events.UserBankCoinAddedEvent;
import com.kingdomsofargus.kingdoms.api.events.UserCoinAddEvent;
import com.kingdomsofargus.kingdoms.api.events.UserCoinAddedEvent;
import org.bukkit.Bukkit;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    // User Info

    private String uuid;

    private String username;

    private String gender;

    private double purse_coins;

    private double bank_coins;

    private String rank;

    private String uClass;

    private int kingdom_id;

    private int level;

    private int xp;

    private int first_join; // TODO

    private String skills;


    private List<String> perms;

    private PermissionAttachment attachment;

    private boolean hasLoadedPerms = false;

    private String permString;

    public User(String uuid) {
        setUuid(uuid);
    }


    public void addCoins(int amount) {
        UserCoinAddEvent event = new UserCoinAddEvent(Bukkit.getPlayer(UUID.fromString(getUuid())), amount);
        Bukkit.getPluginManager().callEvent(event);
        setPurse_coins(getPurse_coins() + event.getCoinsAdded());
        UserCoinAddedEvent event2 = new UserCoinAddedEvent(Bukkit.getPlayer(UUID.fromString(getUuid())));
        Bukkit.getPluginManager().callEvent(event2);
    }

    public void addBankCoins(int amount) {
        UserBankCoinAddEvent event = new UserBankCoinAddEvent(Bukkit.getPlayer(UUID.fromString(getUuid())), amount);
        Bukkit.getPluginManager().callEvent(event);
        setBank_coins(getBank_coins() + event.getTokensAdded());
        UserBankCoinAddedEvent event2 = new UserBankCoinAddedEvent(Bukkit.getPlayer(UUID.fromString(getUuid())));
        Bukkit.getPluginManager().callEvent(event2);
    }


    public void removeCoins(int amount) {
        setPurse_coins(getPurse_coins() - amount);
    }


    public void removeBankCoins(int amount) {
        setBank_coins(getBank_coins() - amount);
    }

    public String permsToStr() {
        StringBuilder builder = new StringBuilder();
        for (String s : perms) {
            builder.append(s);
            builder.append(":");
        }
        return builder.toString();
    }

    public void loadPerms(String s) {
        perms = new ArrayList<>();
        permString = s;
        if (s != null) {
            String[] parts = s.split(":");
            for (String part : parts) {
                if (part != null) {
                    perms.add(part);
                }
            }
        }
        if (Bukkit.getServer().getPlayer(UUID.fromString(getUuid())) != null) {
            attachment = Bukkit.getServer().getPlayer(UUID.fromString(getUuid())).addAttachment(Kingdoms.getInstance());
            if (!perms.isEmpty()) {
                for (String str : perms) {
                    attachment.setPermission(str, true);
                }
            }
            if (Kingdoms.getInstance().getIdToRank().containsKey(getRank())) {
                for (String perm : Kingdoms.getInstance().getIdToRank().get(getRank()).getPermissions()) {
                    if (perm.equalsIgnoreCase("*")) {
                        Bukkit.getServer().getPlayer(UUID.fromString(getUuid())).setOp(true);
                    } else {
                        attachment.setPermission(perm, true);
                    }
                }
                if (Kingdoms.getInstance().getIdToRank().get(getRank()).getInherits() != null) {
                    for (String inheritRank : Kingdoms.getInstance().getIdToRank().get(getRank()).getInherits()) {
                        for (String inhereRankPerm : Kingdoms.getInstance().getIdToRank().get(inheritRank).getPermissions()) {
                            attachment.setPermission(inhereRankPerm, true);
                        }
                        if (Kingdoms.getInstance().getIdToRank().get(inheritRank).getInherits() != null) {
                            for (String tmp : Kingdoms.getInstance().getIdToRank().get(inheritRank).getPermissions()) {
                                attachment.setPermission(tmp, true);
                            }
                            if (Kingdoms.getInstance().getIdToRank().get(inheritRank).getInherits() != null) {
                                for (String inhereRank : Kingdoms.getInstance().getIdToRank().get(inheritRank).getInherits()) {
                                    if (Kingdoms.getInstance().getIdToRank().get(inhereRank).getPermissions() != null) {
                                        for (String inhereRankPerm : Kingdoms.getInstance().getIdToRank().get(inhereRank).getPermissions()) {
                                            if (inhereRankPerm != null) {
                                                attachment.setPermission(inhereRankPerm, true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                System.out.println("User loaded from database has a rank that is not loaded in-game. Their UUID is: " + getUuid());
            }
            hasLoadedPerms = true;
        }
    }

    public void reloadPerms() {
        loadPerms(permString);
    }

    public void addPermission(String perm) {
        if (!perms.contains(perm)) {
            permString = permString + ":" + perm;
            perms.add(perm);
            attachment.setPermission(perm, true);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPurse_coins() {
        return purse_coins;
    }

    public void setPurse_coins(double purse_coins) {
        this.purse_coins = purse_coins;
    }

    public double getBank_coins() {
        return bank_coins;
    }

    public void setBank_coins(double bank_coins) {
        this.bank_coins = bank_coins;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getuClass() {
        return uClass;
    }

    public void setuClass(String uClass) {
        this.uClass = uClass;
    }

    public int getKingdom_id() {
        return kingdom_id;
    }

    public void setKingdom_id(int kingdom_id) {
        this.kingdom_id = kingdom_id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getFirst_join() {
        return first_join;
    }

    public void setFirst_join(int first_join) {
        this.first_join = first_join;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public List<String> getPerms() {
        return perms;
    }

    public void setPerms(List<String> perms) {
        this.perms = perms;
    }

    public PermissionAttachment getAttachment() {
        return attachment;
    }

    public void setAttachment(PermissionAttachment attachment) {
        this.attachment = attachment;
    }

    public boolean isHasLoadedPerms() {
        return hasLoadedPerms;
    }

    public void setHasLoadedPerms(boolean hasLoadedPerms) {
        this.hasLoadedPerms = hasLoadedPerms;
    }

    public String getPermString() {
        return permString;
    }

    public void setPermString(String permString) {
        this.permString = permString;
    }

}
