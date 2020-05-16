package com.kingdomsofargus.kingdoms.rank.permissions;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import com.kingdomsofargus.kingdoms.Kingdoms;

public class Permissions {

	public static HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<UUID, PermissionAttachment>();
	
	public static void setupPermissions(Player player) {
		PermissionAttachment permissionAttachment = player.addAttachment(Kingdoms.getInstance());
		playerPermissions.put(player.getUniqueId(), permissionAttachment);
	}
	
	public void permissionsSetter(UUID uuid) {
		PermissionAttachment permissionAttachment = playerPermissions.get(uuid);
		
		for (String ranks : Kingdoms.getInstance().getConfig().getConfigurationSection("ranks").getKeys(false)) {
			for (String permissions : Kingdoms.getInstance().getConfig().getStringList("ranks." + ranks + ".permissions")) {
				permissionAttachment.setPermission(permissions, true);
			}
		}
	}
}
