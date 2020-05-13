package com.kingdomsofargus.kingdoms.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.kingdomsofargus.kingdoms.gui.InfoGUI;

import net.citizensnpcs.api.event.NPCRightClickEvent;

public class NPCEvent implements Listener {

	@EventHandler
	public void onClick(NPCRightClickEvent event) {
		if (event.getNPC().getName().contains("INFO")) {
			InfoGUI.apply(event.getClicker());
		}
	}
	
}
