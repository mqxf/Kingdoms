package com.kingdomsofargus.kingdoms.kingdom;

import java.util.ArrayList;
import java.util.UUID;

public enum Chat {
	
	PUBLIC(),
	ALLY(),
	KINGDOM();
	
	public static ArrayList<UUID> inKingdomChat = new ArrayList<UUID>();
	public static ArrayList<UUID> inAllyChat = new ArrayList<UUID>();

}
