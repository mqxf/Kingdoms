package com.kingdomsofargus.kingdoms.api.events;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UserCoinAddedEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Player user;

    public UserCoinAddedEvent(Player user) {
        Validate.notNull(user);
        this.user = user;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getUser() {
        return user;
    }

    public void setUser(Player user) {
        this.user = user;
    }
}