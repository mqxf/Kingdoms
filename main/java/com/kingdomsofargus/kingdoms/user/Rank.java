package com.kingdomsofargus.kingdoms.user;

import org.bukkit.ChatColor;

import java.util.List;

public class Rank {
    private String id;

    private String prefix;

    private ChatColor color;

    private List<String> permissions;

    private List<String> inherits;

    public Rank(String id, String prefix, ChatColor color, List<String> perms) {
        this.id = id;
        this.prefix = prefix;
        this.color = color;
        this.permissions = perms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getInherits() {
        return inherits;
    }

    public void setInherits(List<String> inherits) {
        this.inherits = inherits;
    }
}
