package com.kingdomsofargus.kingdoms.rank.classes;

import lombok.Getter;

public enum Classes {

	WANDERER("Wanderer", "&7[Wanderer]"),
	MAGICIAN("Magician", "&1[Magician]"),
	ENGINERER("Enginerer", "&3[Enginerer]"),
	FARMER("Farmer", "&2[Farmer]"),
	BLACKSMITH("Blacksmith", "&8[Blacksmith]"),
	ARCHER("Archer", "&c[Archer]"),
	KNIGHT("Knight", "&9[Knight]"),
	QUEEN("Queen", "&5[Queen]"),
	KING("King", "&5[King]"),
	EMPEROR("Emperor", "&5&l[EMPEROR]");
	
	@Getter public String suffix;
	@Getter public String name;
	@Getter public static Classes instance;

	private Classes(String name, String suffix) {
		this.suffix = suffix;
		this.name = name;
	}
}
