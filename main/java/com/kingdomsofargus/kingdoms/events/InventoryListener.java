package com.kingdomsofargus.kingdoms.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener; 
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_15_R1.EntityBee.e;
import net.royawesome.jlibnoise.module.combiner.Add;
import scala.annotation.meta.getter;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.gui.kingdom.KingdomCreateGUI;
import com.kingdomsofargus.kingdoms.gui.kingdom.KingdomCreatePlayerUI;
import com.kingdomsofargus.kingdoms.kingdom.KingdomManager;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.MergeResult;
import com.kingdomsofargus.kingdoms.utils.Utils;

public class InventoryListener implements Listener {
	
	@EventHandler
	public void onClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (event.getView().getTitle().contains("Gender Picker")) {
			event.setCancelled(true);
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&9King"))) {
				KingdomPlayer.setGender(player.getUniqueId(), "Male");
				player.sendMessage(Utils.chat("&eYou are now a &9&lKing"));
				player.closeInventory();
			}  
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&cQueen"))) {
				KingdomPlayer.setGender(player.getUniqueId(), "Female"); 
				player.sendMessage(Utils.chat("&eYou are now a &c&lQueen"));
				player.closeInventory();
			}
			event.setCancelled(true);
		}
		
		if (event.getView().getTitle().contains("Confirm gender")) {
			event.setCancelled(true);
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&9King"))) {
				KingdomPlayer.setGender(player.getUniqueId(), "Male");
				KingdomCreatePlayerUI.menuCountdown(player);
			}  
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&cQueen"))) {
				KingdomPlayer.setGender(player.getUniqueId(), "Female");
				KingdomCreatePlayerUI.menuCountdown(player);
			}
		}
		
		if (event.getView().getTitle().contains("Please confirm your details.")) {
			String name = event.getCurrentItem().getItemMeta().getDisplayName();
			event.setCancelled(true);
			if (name.contains("Create")) {
				String kingdom = Kingdoms.getInstance().kingdom.get(player);
				player.closeInventory();
				KingdomManager.createKingdom(kingdom, player.getName());
		        KingdomPlayer.setKingdom(player.getUniqueId(), kingdom);
		        
		        
		        if (KingdomPlayer.getGender(player.getUniqueId()).equals("Male")) {
		        	KingdomPlayer.setClass(player.getUniqueId(), "King");
		        } else if (KingdomPlayer.getGender(player.getUniqueId()).equals("Female")) {
		        	KingdomPlayer.setClass(player.getUniqueId(), "Queen");
		        } else {
		        	player.sendMessage("No Gender Specified");
		        }
		        
		        player.sendMessage(ChatColor.YELLOW + "Successfully created kingdom " + ChatColor.GRAY + kingdom);
		        Bukkit.broadcastMessage(Utils.chat("&eThe kingdom &6&l" + kingdom + "&e has been created by &6&l" + player.getName()));
		        player.sendMessage(Utils.chat("&7&oSet a kingdom tag with: &r&e/k tag"));
			}
			if (name.contains("gender")) {
				KingdomCreateGUI.applyGenderUI(player);
			}
		}
		
		if (event.getView().getTitle().contains("Are you sure?")) {
			event.setCancelled(true);
			ItemStack item = event.getCurrentItem();
			String name = item.getItemMeta().getDisplayName();
			if (name.contains("Yes")) {
				String kingdom = KingdomPlayer.getKingdom(player.getUniqueId());
				player.closeInventory();
				Bukkit.broadcastMessage(Utils.chat("&eThe kingdom &6&l" + kingdom + "&e has been disbanded by &6&l" + player.getName()));
		        player.sendMessage(ChatColor.YELLOW + "Successfully disbanded kingdom " + ChatColor.GRAY + kingdom);
	        	KingdomManager.deleteKingdom(kingdom);
	        	KingdomPlayer.setKingdom(player.getUniqueId(), "NONE");
	        	KingdomPlayer.setClass(player.getUniqueId(), "Wanderer");
			}
			if (name.contains("No")) {
				player.closeInventory();
			}
		}
		
		if (event.getView().getTitle().contains("Anvil")) {
			ItemStack r = new ItemStack(Material.RED_STAINED_GLASS_PANE);
			ItemMeta rMeta = r.getItemMeta();
			rMeta.setDisplayName(Utils.chat("&cPlace item below"));
			List<String> rLore = new ArrayList<String>();
			rLore.add(Utils.chat("&7Place an item below"));
			rLore.add(Utils.chat("&7the you want to combine"));
			rMeta.setLore(rLore);
			r.setItemMeta(rMeta);
			
			ItemStack g = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
			ItemMeta gMeta = g.getItemMeta();
			gMeta.setDisplayName(Utils.chat("&aItem registered"));
			List<String> gLore = new ArrayList<String>();
			gLore.add(Utils.chat("&7The item below is the"));
			gLore.add(Utils.chat("&7item you will combine"));
			gMeta.setLore(gLore);
			g.setItemMeta(gMeta);
			
			ItemStack invalid = new ItemStack(Material.BARRIER);
			ItemMeta iMeta = invalid.getItemMeta();
			iMeta.setDisplayName(Utils.chat("&cInvalid"));
			rLore.clear();
			rLore.add(Utils.chat("&7Please place"));
			rLore.add(Utils.chat("&7two items you"));
			rLore.add(Utils.chat("&7want to combine"));
			rLore.add(Utils.chat("&7in the slots"));
			rLore.add(Utils.chat("&7below."));
			invalid.setItemMeta(iMeta);
			
			ItemStack combine = new ItemStack(Material.ANVIL);
			ItemMeta paneMeta = combine.getItemMeta();
			paneMeta.setDisplayName(Utils.chat("&aCombine!"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utils.chat("&7Click me to"));
			lore.add(Utils.chat("&7Combine the two"));
			lore.add(Utils.chat("&7items below"));
			paneMeta.setLore(lore);
			combine.setItemMeta(paneMeta);
			
			ItemStack ecombine = new ItemStack(Material.ANVIL);
			paneMeta = ecombine.getItemMeta();
			paneMeta.setDisplayName(Utils.chat("&aCombine!"));
			List<String> elore = new ArrayList<String>();
			elore.add(Utils.chat("&7Click me to"));
			elore.add(Utils.chat("&7Combine the two"));
			elore.add(Utils.chat("&7items below"));
			paneMeta.setLore(elore);
			paneMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			paneMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			ecombine.setItemMeta(paneMeta);
			
			if (event.getCurrentItem() != null) {
				ItemStack clicked = event.getCurrentItem();
				String name = clicked.getItemMeta().getDisplayName();
				if (name.contains("Invalid") || name.contains("Place item below") || name.contains("Item registered")) {
					event.setCancelled(true);
				}
				if (clicked.getType() == Material.RED_STAINED_GLASS_PANE || clicked.getType() == Material.GRAY_STAINED_GLASS_PANE || clicked.getType() == Material.LIME_STAINED_GLASS_PANE) {
					event.setCancelled(true);
				}
				if (name.contains("Close")) {
					event.setCancelled(true);
					player.closeInventory();
					return;
				}
				if (event.getSlot() == 13) {
					Inventory inv = event.getInventory();
					if (inv.getItem(29) != null || inv.getItem(33) != null || !Kingdoms.getInstance().combined.get(player)) {
						event.setCancelled(true);
					}
					else {
						Kingdoms.getInstance().combined.put(player, false);
						new BukkitRunnable() {
							
							@Override
							public void run() {
								event.getInventory().setItem(13, invalid);
								
							}
						}.runTaskLater(JavaPlugin.getPlugin(Kingdoms.class), 2L);
					}
				}
				if (name.contains("Combine")) {
					
					event.setCancelled(true);
					if (event.getInventory().getItem(29) != null && event.getInventory().getItem(33) != null) {
						event.getInventory().setItem(29, null);
						event.getInventory().setItem(33, null);
						event.getInventory().setItem(11, r);
						event.getInventory().setItem(12, r);
						event.getInventory().setItem(20, r);
						event.getInventory().setItem(14, r);
						event.getInventory().setItem(15, r);
						event.getInventory().setItem(24, r);
						Kingdoms.getInstance().combined.put(player, true);
						player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
						
						new BukkitRunnable() {
							
							@Override
							public void run() {
								event.getInventory().setItem(22, combine);
								
							}
						}.runTaskLater(JavaPlugin.getPlugin(Kingdoms.class), 2L);
						
					}
				}
				if (event.getSlot() == 29 || event.getSlot() == 33) {
					if (event.getSlot() == 29) {
						event.getInventory().setItem(11, r);
						event.getInventory().setItem(12, r);
						event.getInventory().setItem(20, r);
						event.getInventory().setItem(13, invalid);
						event.getInventory().setItem(22, combine);
						
					}
					else if (event.getSlot() == 33) {
						event.getInventory().setItem(14, r);
						event.getInventory().setItem(15, r);
						event.getInventory().setItem(24, r);
						event.getInventory().setItem(13, invalid);
						event.getInventory().setItem(22, combine);
						
					}
				}
			}
			else {
				if (event.getSlot() == 29 || event.getSlot() == 33) {
					if (event.getSlot() == 29) {
						if (Utils.getCombinable().contains(event.getCursor().getType())) {
							event.getInventory().setItem(11, g);
							event.getInventory().setItem(12, g);
							event.getInventory().setItem(20, g);
						}
						if (event.getCursor().getType() == event.getInventory().getItem(33).getType() || event.getInventory().getItem(33) == null || event.getInventory().getItem(33).getType() == Material.ENCHANTED_BOOK) {
							if (Utils.getCombinable().contains(event.getCursor().getType())) {
								//11, 12, 20
								
								
								if (event.getInventory().getItem(33) != null) {
									if (event.getInventory().getItem(33).getType() == Material.ENCHANTED_BOOK || event.getCursor().getType() == event.getInventory().getItem(33).getType()) {
										
										if (event.getCursor().getType() == Material.ENCHANTED_BOOK
												&& event.getInventory().getItem(33).getType() == Material.ENCHANTED_BOOK) {
											
											MergeResult result = this.mergeEnchantedBooks(event.getCursor(), event.getInventory().getItem(33)); // ITEMSTACK LEFT, ITEMSTACK RIGHT
											event.getInventory().setItem(13, result.getItem());
											event.getInventory().setItem(22, ecombine);
										}
										else if (event.getCursor().getType() != Material.ENCHANTED_BOOK && event.getInventory().getItem(33).getType() == Material.ENCHANTED_BOOK) {
											
											MergeResult result = this.mergeBookAndItem(event.getCursor(), event.getInventory().getItem(33));
											event.getInventory().setItem(13, result.getItem());
											event.getInventory().setItem(22, ecombine);
										}
										else if (event.getCursor().getType() == event.getInventory().getItem(33).getType() && event.getCursor().getType() != Material.ENCHANTED_BOOK) {
											
											MergeResult result = this.mergeEnchantedItems(event.getInventory().getItem(33), event.getCursor());
											event.getInventory().setItem(13, result.getItem());
											event.getInventory().setItem(22, ecombine);
										}
									}
								}
							}
						}
					}
					else if (event.getSlot() == 33) {
						if (Utils.getCombinable().contains(event.getCursor().getType())) {
							event.getInventory().setItem(14, g);
							event.getInventory().setItem(15, g);
							event.getInventory().setItem(24, g);
						}
						if (event.getCursor().getType() == event.getInventory().getItem(29).getType() || event.getInventory().getItem(29) == null || event.getCursor().getType() == Material.ENCHANTED_BOOK) {
							if (Utils.getCombinable().contains(event.getInventory().getItem(29).getType())) {
								//11, 12, 20
								
								
								if (event.getInventory().getItem(29) != null) {
									
									if (event.getCursor().getType() == Material.ENCHANTED_BOOK
											&& event.getInventory().getItem(29).getType() == Material.ENCHANTED_BOOK) {
										
										MergeResult result = this.mergeEnchantedBooks(event.getCursor(), event.getInventory().getItem(29)); // ITEMSTACK LEFT, ITEMSTACK RIGHT
										event.getInventory().setItem(13, result.getItem());
										event.getInventory().setItem(22, ecombine);
									}
									else if (event.getCursor().getType() == Material.ENCHANTED_BOOK && event.getInventory().getItem(29).getType() != Material.ENCHANTED_BOOK) {
										
										MergeResult result = this.mergeBookAndItem(event.getInventory().getItem(29), event.getCursor());
										event.getInventory().setItem(13, result.getItem());
										event.getInventory().setItem(22, ecombine);
									}
									else if (event.getCursor().getType() == event.getInventory().getItem(29).getType() && event.getCursor().getType() != Material.ENCHANTED_BOOK) {
										
										MergeResult result = this.mergeEnchantedItems(event.getInventory().getItem(29), event.getCursor());
										event.getInventory().setItem(13, result.getItem());
										event.getInventory().setItem(22, ecombine);
									}
								}
							}
						}
					}
				}
				else {
					event.setCancelled(true);
				}
			}
			
		}
	}
	
	@EventHandler
	public void onInventoryMove(InventoryMoveItemEvent e) {
		if (e.getItem().getType() == Material.BARRIER) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		Player player = (Player) e.getPlayer();
		if (e.getView().getTitle().contains("Anvil")) {
			//13, 29, 33
			
			if (Kingdoms.getInstance().combined.get(player)) {
				player.getInventory().addItem(e.getInventory().getItem(13));
				player.getInventory().addItem(e.getInventory().getItem(29));
				player.getInventory().addItem(e.getInventory().getItem(33));
				Kingdoms.getInstance().combined.put(player, false);
			}
			else {
				player.getInventory().addItem(e.getInventory().getItem(29));
				player.getInventory().addItem(e.getInventory().getItem(33));
			}
			
		}
	}
	
	   private MergeResult mergeEnchantedBooks(ItemStack leftBook, ItemStack rightBook) {
		      ItemStack resultItem = new ItemStack(Material.ENCHANTED_BOOK);
		      MergeResult mergeResult = new MergeResult(resultItem);
		      EnchantmentStorageMeta resultMeta = (EnchantmentStorageMeta)resultItem.getItemMeta();
		      EnchantmentStorageMeta leftMeta = (EnchantmentStorageMeta)leftBook.getItemMeta();
		      EnchantmentStorageMeta rightMeta = (EnchantmentStorageMeta)rightBook.getItemMeta();
		      leftMeta.getStoredEnchants().forEach((enchant, level) -> {
		         int resultLevel = this.getResultLevel(level.intValue(), enchant, rightMeta);
		         mergeResult.addRepairCost(this.calculateExtraRepairCost(enchant, resultLevel));
		         resultMeta.addStoredEnchant(enchant, resultLevel, true);
		      });
		      rightMeta.getStoredEnchants().forEach((enchant, level) -> {
		         if(!resultMeta.getStoredEnchants().containsKey(enchant) && !resultMeta.hasConflictingStoredEnchant(enchant)) {
		            mergeResult.addRepairCost(this.calculateExtraRepairCost(enchant, level.intValue()));
		            resultMeta.addStoredEnchant(enchant, level.intValue(), true);
		         }

		      });
		      resultItem.setItemMeta(resultMeta);
		      return mergeResult;
		   }

	   private int getResultLevel(int value, Enchantment enchant, EnchantmentStorageMeta meta) {
		      int resultLevel = value;
		      if(meta.getStoredEnchants().containsKey(enchant) && enchant.getMaxLevel() != 1) {
		         int rightLevel = ((Integer)meta.getStoredEnchants().get(enchant)).intValue();
		         if(rightLevel == value) {
		        	 if (value < enchant.getMaxLevel()) {
		        		 resultLevel = value + 1;
		        	 }
		         } else if(rightLevel > value) {
		            resultLevel = rightLevel;
		         }
		      }

		      return resultLevel;
		   }
	   
	   private int getResultLevel(int value, Enchantment enchant, ItemMeta meta) {
		    int resultLevel = value;
		    if (meta.getEnchants().containsKey(enchant) && enchant.getMaxLevel() != 1) {
		      int rightLevel = ((Integer)meta.getEnchants().get(enchant)).intValue();
		      if (rightLevel == value) {
		    	  if (value < enchant.getMaxLevel()) {
		    		  resultLevel++;
		    	  }
		        
		      } else if (rightLevel > value) {
		        resultLevel = rightLevel;
		      } 
		    } 
		    return resultLevel;
		  }
	   
	   private MergeResult mergeEnchantedItems(ItemStack leftItem, ItemStack rightItem) {
		      ItemStack resultItem = new ItemStack(leftItem.getType());
		      MergeResult mergeResult = new MergeResult(resultItem);
		      ItemMeta rightMeta = rightItem.getItemMeta();
		      rightMeta.getEnchants().forEach((enchant, level) -> {
		         int resultLevel = this.getResultLevel(level.intValue(), enchant, rightMeta);
		         mergeResult.addRepairCost(this.calculateExtraRepairCost(enchant, resultLevel));
		         resultItem.addUnsafeEnchantment(enchant, resultLevel);
		      });
		      rightMeta.getEnchants().forEach((enchant, level) -> {
		         if(!resultItem.getEnchantments().containsKey(enchant) && !resultItem.getItemMeta().hasConflictingEnchant(enchant)) {
		            mergeResult.addRepairCost(this.calculateExtraRepairCost(enchant, level.intValue()));
		            resultItem.addUnsafeEnchantment(enchant, level.intValue());
		         }

		      });
		      return mergeResult;
		   }

		   private MergeResult mergeBookAndItem(ItemStack item, ItemStack book) {
		      ItemStack resultItem = new ItemStack(item.getType());
		      MergeResult mergeResult = new MergeResult(resultItem);
		      EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta)book.getItemMeta();
		      item.getEnchantments().forEach((enchant, level) -> {
		         int resultLevel = this.getResultLevel(level.intValue(), enchant, bookMeta);
		         mergeResult.addRepairCost(this.calculateExtraRepairCost(enchant, resultLevel));
		         resultItem.addUnsafeEnchantment(enchant, resultLevel);
		      });
		      bookMeta.getStoredEnchants().forEach((enchant, level) -> {
		         if(!resultItem.getEnchantments().containsKey(enchant) && !resultItem.getItemMeta().hasConflictingEnchant(enchant)) {
		            mergeResult.addRepairCost(this.calculateExtraRepairCost(enchant, level.intValue()));
		            resultItem.addUnsafeEnchantment(enchant, level.intValue());
		         }

		      });
		      return mergeResult;
		   }


	   private int calculateExtraRepairCost(Enchantment enchant, int level) {
	      return level <= enchant.getMaxLevel()?0:(level - enchant.getMaxLevel()) * 2;
	   }

}
