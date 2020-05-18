package com.kingdomsofargus.kingdoms.sql;

public interface Callback<T> {
    public void execute(T response);
}
