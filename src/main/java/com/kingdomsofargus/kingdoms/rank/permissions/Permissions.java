package com.kingdomsofargus.kingdoms.rank.permissions;

import com.kingdomsofargus.kingdoms.Kingdoms;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class Permissions {

	public static HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<UUID, PermissionAttachment>();
	
	public static void setupPermissions(Player player) {
		PermissionAttachment permissionAttachment = player.addAttachment(Kingdoms.getCore());
		playerPermissions.put(player.getUniqueId(), permissionAttachment);
	}
	
	public void permissionsSetter(UUID uuid) {
		PermissionAttachment permissionAttachment = playerPermissions.get(uuid);
		
		for (String ranks : Kingdoms.getCore().getConfig().getConfigurationSection("ranks").getKeys(false)) {
			for (String permissions : Kingdoms.getCore().getConfig().getStringList("ranks." + ranks + ".permissions")) {
				permissionAttachment.setPermission(permissions, true);
			}
		}
	}
}
