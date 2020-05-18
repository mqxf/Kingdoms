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

    public Kingdom(String leader, String name, int id) {
        this.leader = leader;
        this.name = name;
        this.id = id;
        
        announcement = "test";

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

}
