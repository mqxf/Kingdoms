package com.kingdomsofargus.kingdoms.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UserBankCoinAddEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();


    private Player user;

    private int tokensAdded;

    public UserBankCoinAddEvent(Player user, int coinsAdded) {
        this.user = user;
        this.tokensAdded = coinsAdded;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public int getTokensAdded() {
        return tokensAdded;
    }

    public Player getUser() {
        return user;
    }
}