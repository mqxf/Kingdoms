package com.kingdomsofargus.kingdoms.utils;

import org.bukkit.inventory.ItemStack;

public class MergeResult {
   private ItemStack item;
   private int extraRepairCost;

   public MergeResult(ItemStack item) {
      this.item = item;
      this.extraRepairCost = 0;
   }

   public ItemStack getItem() {
      return this.item;
   }

   public int getExtraRepairCost() {
      return this.extraRepairCost;
   }

   public void addRepairCost(int cost) {
      this.extraRepairCost += cost;
   }
}
