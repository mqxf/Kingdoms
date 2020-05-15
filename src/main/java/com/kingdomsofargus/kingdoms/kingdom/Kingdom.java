package com.kingdomsofargus.kingdoms.kingdom;

import java.util.ArrayList;
import java.util.List;

public class Kingdom {

    /**
     * Faction Information
     */
    private String name;
    private String leader;
    private String announcement;
    public List<String> members;
    public String memberString;
    private String tag;
    private int id;

    /**
     * Classes
     */
    private String knights;
    private String archers;
    private String engineers;
    private String blacksmiths;
    private String excavators;
    private String farmers;
    private String magicians;


    /**
     * Relationships
     */

    private String alliesString;
    private String neutralString;
    private String enemyString;
    public List<String> allies;
    public List<String> neutral;
    public List<String> enemy;
    private String in_War;
    private int bank;

    public Kingdom(String leader, String name, int id) {
        this.leader = leader;
        this.name = name;
        this.id = id;
        bank = 0;
    }

    public String membersToString() {
        StringBuilder builder = new StringBuilder();
        for (String s : members) {
            builder.append(s);
            builder.append(":");
        }
        return builder.toString();
    }

    public String alliesToString() {
        StringBuilder builder = new StringBuilder();
        for (String s : allies) {
            builder.append(s);
            builder.append(":");
        }
        return builder.toString();
    }

    public String enemysToString() {
        StringBuilder builder = new StringBuilder();
        for (String s : enemy) {
            builder.append(s);
            builder.append(":");
        }
        return builder.toString();
    }

    public String neutralToString() {
        StringBuilder builder = new StringBuilder();
        for (String s : neutral) {
            builder.append(s);
            builder.append(":");
        }
        return builder.toString();
    }


    public String getName() {
        return name;
    }

    public String getLeader() {
        return leader;
    }

    public String getAnnouncement() {
        return announcement;
    }


    public int getId() {
        return id;
    }

    public String getKnights() {
        return knights;
    }

    public String getArchers() {
        return archers;
    }

    public String getEngineers() {
        return engineers;
    }

    public String getBlacksmiths() {
        return blacksmiths;
    }

    public String getExcavators() {
        return excavators;
    }

    public String getFarmers() {
        return farmers;
    }

    public String getMagicians() {
        return magicians;
    }

    public String getAlliesString() {
        return alliesString;
    }

    public String getNeutralString() {
        return neutralString;
    }

    public String getEnemyString() {
        return enemyString;
    }

    public String getMemberString() {
        return memberString;
    }


    public String getIn_War() {
        return in_War;
    }

    public void loadMembers(String s) {
        members = new ArrayList<>();
        memberString = s;
        if (s != null) {
            String[] parts = s.split(":");
            for (String part : parts) {
                if (part != null) {
                    members.add(part);
                }
            }
        }
    }

    public void reloadMembers() {
        loadMembers(memberString);
    }

    public void addMember(String perm) {
        if (!members.contains(perm)) {
            memberString = memberString + ":" + perm;
            members.add(perm);
        }
    }

    public void loadAllies(String s) {
        allies = new ArrayList<>();
        alliesString = s;
        if (s != null) {
            String[] parts = s.split(":");
            for (String part : parts) {
                if (part != null) {
                    allies.add(part);
                }
            }
        }
    }

    public void reloadAllies() {
        loadAllies(alliesString);
    }

    public void addAlly(String perm) {
        if (!allies.contains(perm)) {
            alliesString = alliesString + ":" + perm;
            allies.add(perm);
        }
    }

    public void loadEnemys(String s) {
        enemy = new ArrayList<>();
        enemyString = s;
        if (s != null) {
            String[] parts = s.split(":");
            for (String part : parts) {
                if (part != null) {
                    enemy.add(part);
                }
            }
        }
    }

    public void reloadEnemys() {
        loadEnemys(enemyString);
    }

    public void addEnemy(String perm) {
        if (!enemy.contains(perm)) {
            enemyString = enemyString + ":" + perm;
            enemy.add(perm);
        }
    }

    public void loadNeutral(String s) {
        neutral = new ArrayList<>();
        neutralString = s;
        if (s != null) {
            String[] parts = s.split(":");
            for (String part : parts) {
                if (part != null) {
                    neutral.add(part);
                }
            }
        }
    }

    public void reloadNeutral() {
        loadNeutral(neutralString);
    }

    public void addNeutral(String perm) {
        if (!neutral.contains(perm)) {
            neutralString = neutralString + ":" + perm;
            neutral.add(perm);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setKnights(String knights) {
        this.knights = knights;
    }

    public void setArchers(String archers) {
        this.archers = archers;
    }

    public void setEngineers(String engineers) {
        this.engineers = engineers;
    }

    public void setBlacksmiths(String blacksmiths) {
        this.blacksmiths = blacksmiths;
    }

    public void setExcavators(String excavators) {
        this.excavators = excavators;
    }

    public void setFarmers(String farmers) {
        this.farmers = farmers;
    }

    public void setMagicians(String magicians) {
        this.magicians = magicians;
    }


    public void setIn_War(String in_War) {
        this.in_War = in_War;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public int getBank() {
        return bank;
    }
}
