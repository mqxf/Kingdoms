package com.kingdomsofargus.kingdoms.gui.kingdom;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.gui.Menu;
import com.kingdomsofargus.kingdoms.kingdom.Kingdom;
import com.kingdomsofargus.kingdoms.user.User;
import com.kingdomsofargus.kingdoms.utils.Color;
import com.kingdomsofargus.kingdoms.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class KingdomDiplomacyGUI {

	private static Kingdoms core;

	public static KingdomDiplomacyGUI instance = new KingdomDiplomacyGUI(core);

	public KingdomDiplomacyGUI(Kingdoms core) {
		this.core = core;
	}
	
	public void openRelationshipMenu(Player player, Kingdom targetKingdom) {
		User user = Kingdoms.getCore().getUserManager().getUser(player);
		Kingdom userKingdom = Kingdoms.getCore().getKindomManager().getKingdom(user.getKingdom_id());

		String leaderName = getName(targetKingdom.getLeader());

		Menu menu = new Menu(core, "Diplomacy", 3);
		// 4, 11, 13, 15

		ItemStack fill = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setName("").toItemStack();

		ItemStack k = new ItemBuilder(Material.PLAYER_HEAD, 1)
				.setName(Color.color("&6" + targetKingdom.getName()))
				.setLore(Color.color("&eLeader: &7" + leaderName))
				.setSkullOwner(leaderName)
				.addLoreLine(Color.color("&eRelationship: &7" + userKingdom.getRelation(targetKingdom)))
				.toItemStack();

		ItemStack ally = new ItemBuilder(Material.MAGENTA_STAINED_GLASS_PANE,1)
				.setName(Color.color("&7Relation: &dAlly"))
				.setLore(Color.color("&eKingdom: &7" + targetKingdom.getName()))
				.addLoreLine("")
				.addLoreLine(Color.color("&7(Left Click) to proceed."))
				.toItemStack();

		ItemStack war = new ItemBuilder(Material.RED_STAINED_GLASS_PANE,1)
				.setName(Color.color("&cStart War!"))
				.setLore(Color.color("&eKingdom: &7" + targetKingdom.getName()))
				.addLoreLine("")
				.addLoreLine(Color.color("&7(Left Click) to proceed."))
				.toItemStack();

		ItemStack neutral = new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE,1)
				.setName(Color.color("&7Relation: &fNeutral"))
				.setLore(Color.color("&eKingdom: &7" + targetKingdom.getName()))
				.addLoreLine("")
				.addLoreLine(Color.color("&7(Left Click) to proceed."))
				.toItemStack();

		ItemStack enemy = new ItemBuilder(Material.RED_STAINED_GLASS_PANE,1)
				.setName(Color.color("&7Relation: &cEnemy"))
				.setLore(Color.color("&eKingdom: &7" + targetKingdom.getName()))
				.addLoreLine("")
				.addLoreLine(Color.color("&7(Left Click) to proceed."))
				.toItemStack();

		switch (userKingdom.getRelation(targetKingdom)) {
			case "Ally":
				menu.setItem(13, neutral);
				menu.setItem(11, enemy);
				menu.setItem(15, war);
				break;
			case "Enemy":
				menu.setItem(13, neutral);
				menu.setItem(11, ally);
				menu.setItem(15, war);
				break;
			case "Neutral":
				menu.setItem(13, enemy);
				menu.setItem(11, ally);
				menu.setItem(15, war);
				break;
			case "In War":
				menu.setItem(13, enemy);
				menu.setItem(11, ally);
				menu.setItem(15, neutral);
				break;
			default:
				break;
		}

		menu.setItem(12, fill);
		menu.setItem(4, k);
		menu.setItem(14, fill);
		// TODO ACTIONS

		menu.fillRange(0, 3, fill);
		menu.fillRange(5, 8, fill);
		menu.fillRange(9, 10, fill);
		menu.fillRange(16, 17, fill);
		menu.fillRange(18, 26, fill);
		menu.showMenu(player);
	}

	
	@SuppressWarnings("deprecation")
	public void applyMenu(Player player, Kingdom targetKingdom) {

		User user = Kingdoms.getCore().getUserManager().getUser(player);
		Kingdom userKingdom = Kingdoms.getCore().getKindomManager().getKingdom(user.getKingdom_id());

		String leaderName = getName(targetKingdom.getLeader());

		Menu menu = new Menu(core, "Diplomacy", 3);

		ItemStack fill = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setName("").toItemStack();

		ItemStack k = new ItemBuilder(Material.PLAYER_HEAD, 1)
				.setName(Color.color("&6" + targetKingdom.getName()))
				.setLore(Color.color("&eLeader: &7" + leaderName))
				.setSkullOwner(leaderName)
				.addLoreLine(Color.color("&eRelationship: &7" + userKingdom.getRelation(targetKingdom)))
				.addLoreLine(Color.color("&eTag: &7" + targetKingdom.getTag()))
				.addLoreLine(Color.color("&eRenown: &7"))
				.addLoreLine(Color.color("&eBank: &7" + targetKingdom.getBank()))
				.addLoreLine(Color.color("&eWars W/L: &7"))
				.toItemStack();

		ItemStack edit = new ItemBuilder(Material.RED_STAINED_GLASS_PANE,1)
				.setName(Color.color("&6Propose Relationship Change"))
				.setLore(Color.color("&eKingdom: &7" + targetKingdom.getName()))
				.addLoreLine("")
				.addLoreLine(Color.color("&7(Left Click) to proceed."))
				.toItemStack();

		menu.setItem(4, k);
		menu.setItem(13, edit);
		menu.setAction(13, new Menu.ItemAction() {
			public void run(Player player, Inventory inv, ItemStack item, int slot, InventoryAction action) {
				// this runs when a slot is clicked
				openRelationshipMenu(player, targetKingdom);
			}
		});

		// TODO ACTIONS

		menu.fillRange(0, 3, fill);
		menu.fillRange(5, 8, fill);
		menu.fillRange(9, 12, fill);
		menu.fillRange(14, 17, fill);
		menu.fillRange(18, 26, fill);
		menu.showMenu(player);
	}

	public String getName(String uuid) {
		String url = "https://api.mojang.com/user/profiles/"+uuid.replace("-", "")+"/names";
		try {
			@SuppressWarnings("deprecation")
			String nameJson = IOUtils.toString(new URL(url));
			JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
			String playerSlot = nameValue.get(nameValue.size()-1).toString();
			JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
			return nameObject.get("name").toString();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return "error";
	}

	public static KingdomDiplomacyGUI getInstance() {
		return instance;
	}
}
