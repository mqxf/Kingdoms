package com.kingdomsofargus.kingdoms.gui.kingdom;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.utils.Color;
import com.kingdomsofargus.kingdoms.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUITemplate implements Listener  {

    private Kingdoms core;

    public GUITemplate(Kingdoms core) {
        this.core = core;
        Bukkit.getServer().getPluginManager().registerEvents(this, core);
    }

    /**
     *
     * @param player
     * @param target
     * If a second player is not needed then just remove the Player target
     */

    public static void openGUI(Player player, Player target) {

        Inventory inventory = Bukkit.createInventory(null, 54, Color.color("&7Menu Name"));

        /**
         * @implNote Create Items
         */
        ItemStack item = new ItemBuilder(Material.DIAMOND_ORE, 1)
                .setName(Color.color("&cName"))
                .setLore("Lore line 1")
                .addLoreLine("Lore line 2")
                .addLoreLine("Lore line 3 etc.")
                .toItemStack();

        /**
         * @implNote Add items to the inventory
         * @param Slot
         * @param ItemStack
         */
        inventory.setItem(0, item);
    }


}
