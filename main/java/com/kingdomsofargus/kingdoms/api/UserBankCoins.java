package com.kingdomsofargus.kingdoms.api;

import com.kingdomsofargus.kingdoms.user.User;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class UserBankCoins {
    private static final HandlerList HANDLERS = new HandlerList();

    private Player user;
    private User profile = (User) user;
    public UserBankCoins(Player user) {
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

    public int getCoins() { return (int) profile.getBank_coins(); }

    public void setUser(Player user) {
        this.user = user;
    }
}
