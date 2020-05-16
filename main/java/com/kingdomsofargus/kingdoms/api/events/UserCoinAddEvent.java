package com.kingdomsofargus.kingdoms.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UserCoinAddEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();


    private Player user;

    private int coinsAdded;

    public UserCoinAddEvent(Player user, int coinsAdded) {
        this.user = user;
        this.coinsAdded = coinsAdded;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public int getCoinsAdded() {
        return coinsAdded;
    }

    public Player getUser() {
        return user;
    }
}