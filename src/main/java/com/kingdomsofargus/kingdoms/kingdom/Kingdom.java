package com.kingdomsofargus.kingdoms.kingdom;

public class Kingdom {

    /**
     * Faction Information
     */
    private String name;
    private String leader;
    private String announcement;
    private String invites;
    private String members;
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
    private String allies;
    private String neutral;
    private String enemy;
    private String in_War;
    private int bank;

    public Kingdom(String leader, String name, int id) {
        this.leader = leader;
        this.name = name;
        this.id = id;
        announcement = "";
        tag = "";
        bank = 0;
        members = leader;
        knights = "";
        archers = "";
        engineers = "";
        blacksmiths = "";
        excavators = "";
        farmers = "";
        magicians = "";

        invites = "";


        //members.add(leader);

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

    public String getInvites() {
        return invites;
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

    public String getAllies() {
        return allies;
    }

    public String getNeutral() {
        return neutral;
    }

    public String getEnemy() {
        return enemy;
    }

    public String getIn_War() {
        return in_War;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
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

    public void setInvites(String invites) {
        this.invites = invites;
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

    public void setAllies(String allies) {
        this.allies = allies;
    }

    public void setNeutral(String neutral) {
        this.neutral = neutral;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
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
