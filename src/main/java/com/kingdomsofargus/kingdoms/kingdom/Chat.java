package com.kingdomsofargus.kingdoms.kingdom;

import java.util.ArrayList;
import java.util.UUID;

public enum Chat {
	
	PUBLIC(),
	ALLY(),
	KINGDOM(),
	MESSAGE(),
	STAFF(),
	ADMIN(),
	BUILDER();
	
	public static ArrayList<UUID> inKingdomChat = new ArrayList<UUID>();
	public static ArrayList<UUID> inAllyChat = new ArrayList<UUID>();
	public static ArrayList<UUID> inPublicChat = new ArrayList<UUID>();
	public static ArrayList<UUID> inStaffChat = new ArrayList<UUID>();
	public static ArrayList<UUID> inAdminChat = new ArrayList<UUID>();
	public static ArrayList<UUID> inBuilderChat = new ArrayList<UUID>();

}
