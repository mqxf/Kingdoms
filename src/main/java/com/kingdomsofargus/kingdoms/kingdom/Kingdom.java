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
    private List<String> invites;
    private String tag;
    private int id;
    /**
     * Classes
     */
    private List<String> knights;
    private List<String> archers;
    private List<String> engineers;
    private List<String> blacksmiths;
    private List<String> excavators;
    private List<String> farmers;
    private List<String> magicians;


    /**
     * Relationships
     */
    private List<Integer> allies;
    private List<Integer> neutral;
    private List<Integer> enemy;
    private List<Integer> in_War;
    private int bank;

    public Kingdom(String leader, String name, int id) {
        this.leader = leader;
        this.name = name;
        this.id = id;
        announcement = "test";
        tag = "";
        bank = 0;
        knights = new ArrayList<>();
        archers = new ArrayList<>();
        engineers = new ArrayList<>();
        blacksmiths = new ArrayList<>();
        excavators = new ArrayList<>();
        farmers = new ArrayList<>();
        magicians = new ArrayList<>();

        invites = new ArrayList<>();

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

    public List<String> getInvites() {
        return invites;
    }

    public int getId() {
        return id;
    }

    public List<String> getKnights() {
        return knights;
    }

    public List<String> getArchers() {
        return archers;
    }

    public List<String> getEngineers() {
        return engineers;
    }

    public List<String> getBlacksmiths() {
        return blacksmiths;
    }

    public List<String> getExcavators() {
        return excavators;
    }

    public List<String> getFarmers() {
        return farmers;
    }

    public List<String> getMagicians() {
        return magicians;
    }

    public List<Integer> getAllies() {
        return allies;
    }

    public List<Integer> getNeutral() {
        return neutral;
    }

    public List<Integer> getEnemy() {
        return enemy;
    }

    public List<Integer> getIn_War() {
        return in_War;
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

    public void setInvites(List<String> invites) {
        this.invites = invites;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKnights(List<String> knights) {
        this.knights = knights;
    }

    public void setArchers(List<String> archers) {
        this.archers = archers;
    }

    public void setEngineers(List<String> engineers) {
        this.engineers = engineers;
    }

    public void setBlacksmiths(List<String> blacksmiths) {
        this.blacksmiths = blacksmiths;
    }

    public void setExcavators(List<String> excavators) {
        this.excavators = excavators;
    }

    public void setFarmers(List<String> farmers) {
        this.farmers = farmers;
    }

    public void setMagicians(List<String> magicians) {
        this.magicians = magicians;
    }

    public void setAllies(List<Integer> allies) {
        this.allies = allies;
    }

    public void setNeutral(List<Integer> neutral) {
        this.neutral = neutral;
    }

    public void setEnemy(List<Integer> enemy) {
        this.enemy = enemy;
    }

    public void setIn_War(List<Integer> in_War) {
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
