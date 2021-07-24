package com.kingdomsofargus.kingdoms.gui.bans;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.kingdomsofargus.kingdoms.utils.Utils;

@SuppressWarnings("deprecation")
public class CheatBanGUI {

public static void applyBanUI(Player player, Player banner, int page) {
		
		String name = player.getDisplayName();
		ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (byte) SkullType.PLAYER.ordinal());
		SkullMeta sm = (SkullMeta) head.getItemMeta();
		List<String> sl = new ArrayList<>();
		sm.setOwner(name);
		sm.setDisplayName(Utils.chat("&a" + name));
		sm.setLore(sl);
		head.setItemMeta(sm);
		
		ItemStack next = new ItemStack(Material.ARROW, 1);
		ItemMeta nm = next.getItemMeta();
		List<String> nl = new ArrayList<>();
		nl.add(Utils.chat("&71/2"));
		nm.setDisplayName(Utils.chat("&7Next page"));
		nm.setLore(nl);
		next.setItemMeta(nm);
		
		ItemStack back = new ItemStack(Material.ARROW, 1);
		ItemMeta bm = back.getItemMeta();
		List<String> bls = new ArrayList<>();
		bls.add(Utils.chat("&72/2"));
		bm.setDisplayName(Utils.chat("&7Previous page"));
		bm.setLore(bls);
		back.setItemMeta(bm);
		
		ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
		ItemMeta pm = pane.getItemMeta();
		pm.setDisplayName(" ");
		pane.setItemMeta(pm);
		
		ItemStack close = new ItemStack(Material.BARRIER, 1);
		ItemMeta cm = close.getItemMeta();
		cm.setDisplayName(Utils.chat("&cClose"));
		close.setItemMeta(cm);
		
		
		if (page == 1) {
			
			Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.RED + "Ban " + ChatColor.AQUA + player.getName());
			
			inventory.setItem(0, pane);
			inventory.setItem(1, pane);
			inventory.setItem(2, pane);
			inventory.setItem(3, pane);
			inventory.setItem(4, head);
			inventory.setItem(5, pane);
			inventory.setItem(6, pane);
			inventory.setItem(7, pane);
			inventory.setItem(8, pane);
			inventory.setItem(9, pane);
			inventory.setItem(17, pane);
			inventory.setItem(18, pane);
			inventory.setItem(26, pane);
			inventory.setItem(27, pane);
			inventory.setItem(35, pane);
			inventory.setItem(36, pane);
			inventory.setItem(44, pane);
			inventory.setItem(46, pane);
			inventory.setItem(47, pane);
			inventory.setItem(48, pane);
			inventory.setItem(49, close);
			inventory.setItem(50, pane);
			inventory.setItem(51, pane);
			inventory.setItem(52, pane);
			inventory.setItem(45, pane);
			inventory.setItem(53, next);
			
			//combat
			
			ItemStack ka = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = ka.getItemMeta();
			meta.setDisplayName(Utils.chat("&cKillAura"));
			ka.setItemMeta(meta);
			
			ItemStack ca = new ItemStack(Material.REDSTONE_BLOCK);
			meta = ca.getItemMeta();
			meta.setDisplayName(Utils.chat("&cClickAura"));
			ca.setItemMeta(meta);
			
			ItemStack ta = new ItemStack(Material.REDSTONE_BLOCK);
			meta = ta.getItemMeta();
			meta.setDisplayName(Utils.chat("&cTpAura"));
			ta.setItemMeta(meta);
			
			ItemStack a = new ItemStack(Material.REDSTONE_BLOCK);
			meta = a.getItemMeta();
			meta.setDisplayName(Utils.chat("&cAimbot"));
			a.setItemMeta(meta);
			
			ItemStack fb = new ItemStack(Material.REDSTONE_BLOCK);
			meta = fb.getItemMeta();
			meta.setDisplayName(Utils.chat("&cFightbot"));
			fb.setItemMeta(meta);
			
			ItemStack fa = new ItemStack(Material.REDSTONE_BLOCK);
			meta = fa.getItemMeta();
			meta.setDisplayName(Utils.chat("&cFastbow"));
			fa.setItemMeta(meta);
			
			ItemStack ab = new ItemStack(Material.REDSTONE_BLOCK);
			meta = ab.getItemMeta();
			meta.setDisplayName(Utils.chat("&cAntiKnockback"));
			ab.setItemMeta(meta);
			
			ItemStack re = new ItemStack(Material.REDSTONE_BLOCK);
			meta = re.getItemMeta();
			meta.setDisplayName(Utils.chat("&cReach"));
			re.setItemMeta(meta);
			
			ItemStack cr = new ItemStack(Material.REDSTONE_BLOCK);
			meta = cr.getItemMeta();
			meta.setDisplayName(Utils.chat("&cCriticals"));
			cr.setItemMeta(meta);
			
			ItemStack ac = new ItemStack(Material.REDSTONE_BLOCK);
			meta = ac.getItemMeta();
			meta.setDisplayName(Utils.chat("&cAutoClicker"));
			ac.setItemMeta(meta);
			
			//movement
			
			ItemStack fl = new ItemStack(Material.DIAMOND_BLOCK);
			meta = fl.getItemMeta();
			meta.setDisplayName(Utils.chat("&bFly"));
			fl.setItemMeta(meta);
			
			ItemStack gl = new ItemStack(Material.DIAMOND_BLOCK);
			meta = gl.getItemMeta();
			meta.setDisplayName(Utils.chat("&bGlide"));
			gl.setItemMeta(meta);
			
			ItemStack sp = new ItemStack(Material.DIAMOND_BLOCK);
			meta = sp.getItemMeta();
			meta.setDisplayName(Utils.chat("&bSpeed"));
			sp.setItemMeta(meta);
			
			ItemStack hj = new ItemStack(Material.DIAMOND_BLOCK);
			meta = hj.getItemMeta();
			meta.setDisplayName(Utils.chat("&bHighJump"));
			hj.setItemMeta(meta);
			
			ItemStack ff = new ItemStack(Material.DIAMOND_BLOCK);
			meta = ff.getItemMeta();
			meta.setDisplayName(Utils.chat("&bFastFall"));
			ff.setItemMeta(meta);
			
			ItemStack nf = new ItemStack(Material.DIAMOND_BLOCK);
			meta = nf.getItemMeta();
			meta.setDisplayName(Utils.chat("&bNoFall"));
			nf.setItemMeta(meta);
			
			ItemStack s = new ItemStack(Material.DIAMOND_BLOCK);
			meta = s.getItemMeta();
			meta.setDisplayName(Utils.chat("&bSpider"));
			s.setItemMeta(meta);
			
			ItemStack lj = new ItemStack(Material.DIAMOND_BLOCK);
			meta = lj.getItemMeta();
			meta.setDisplayName(Utils.chat("&bLongJump"));
			lj.setItemMeta(meta);
			
			ItemStack je = new ItemStack(Material.DIAMOND_BLOCK);
			meta = je.getItemMeta();
			meta.setDisplayName(Utils.chat("&bJesus"));
			je.setItemMeta(meta);
			
			ItemStack ti = new ItemStack(Material.DIAMOND_BLOCK);
			meta = ti.getItemMeta();
			meta.setDisplayName(Utils.chat("&bTimer"));
			ti.setItemMeta(meta);
			
			ItemStack bl = new ItemStack(Material.DIAMOND_BLOCK);
			meta = bl.getItemMeta();
			meta.setDisplayName(Utils.chat("&bBlink"));
			bl.setItemMeta(meta);
			
			ItemStack st = new ItemStack(Material.DIAMOND_BLOCK);
			meta = st.getItemMeta();
			meta.setDisplayName(Utils.chat("&bStep"));
			st.setItemMeta(meta);
			
			ItemStack sd = new ItemStack(Material.DIAMOND_BLOCK);
			meta = sd.getItemMeta();
			meta.setDisplayName(Utils.chat("&bSelfDamage"));
			sd.setItemMeta(meta);
			
			ItemStack mj = new ItemStack(Material.DIAMOND_BLOCK);
			meta = mj.getItemMeta();
			meta.setDisplayName(Utils.chat("&bMiniJump"));
			mj.setItemMeta(meta);
			
			ItemStack sn = new ItemStack(Material.DIAMOND_BLOCK);
			meta = sn.getItemMeta();
			meta.setDisplayName(Utils.chat("&bSneak"));
			sn.setItemMeta(meta);
			
			ItemStack ph = new ItemStack(Material.DIAMOND_BLOCK);
			meta = ph.getItemMeta();
			meta.setDisplayName(Utils.chat("&bPhase/Clip"));
			ph.setItemMeta(meta);
			
			ItemStack ql = new ItemStack(Material.DIAMOND_BLOCK);
			meta = ql.getItemMeta();
			meta.setDisplayName(Utils.chat("&bQuickLadder"));
			ql.setItemMeta(meta);
			
			ItemStack de = new ItemStack(Material.DIAMOND_BLOCK);
			meta = de.getItemMeta();
			meta.setDisplayName(Utils.chat("&bDerp"));
			de.setItemMeta(meta);
			
			inventory.setItem(10, ka);
			inventory.setItem(11, ca);
			inventory.setItem(12, ta);
			inventory.setItem(13, a);
			inventory.setItem(14, fb);
			inventory.setItem(15, fa);
			inventory.setItem(16, ab);
			inventory.setItem(19, re);
			inventory.setItem(20, cr);
			inventory.setItem(21, ac);
			
			inventory.setItem(22, fl);
			inventory.setItem(23, gl);
			inventory.setItem(24, sp);
			inventory.setItem(25, hj);
			inventory.setItem(28, ff);
			inventory.setItem(29, nf);
			inventory.setItem(30, s);
			inventory.setItem(31, lj);
			inventory.setItem(32, je);
			inventory.setItem(33, ti);
			inventory.setItem(34, bl);
			inventory.setItem(37, st);
			inventory.setItem(38, sd);
			inventory.setItem(39, mj);
			inventory.setItem(40, sn);
			inventory.setItem(41, ph);
			inventory.setItem(42, ql);
			inventory.setItem(43, de);
			
			
			banner.openInventory(inventory);
			
		}
		else if (page == 2) {
			
			Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.RED + "Ban " + ChatColor.AQUA + player.getName());
			
			ItemStack ns = new ItemStack(Material.DIAMOND_BLOCK);
			ItemMeta meta = ns.getItemMeta();
			meta.setDisplayName(Utils.chat("&bNoSlowDown"));
			ns.setItemMeta(meta);
			
			ItemStack fu = new ItemStack(Material.DIAMOND_BLOCK);
			meta = fu.getItemMeta();
			meta.setDisplayName(Utils.chat("&bFastUse"));
			fu.setItemMeta(meta);
			
			ItemStack fb = new ItemStack(Material.GOLD_BLOCK);
			meta = fb.getItemMeta();
			meta.setDisplayName(Utils.chat("&6FastBreak"));
			fb.setItemMeta(meta);
			
			ItemStack fp = new ItemStack(Material.GOLD_BLOCK);
			meta = fp.getItemMeta();
			meta.setDisplayName(Utils.chat("&6FastPlace"));
			fp.setItemMeta(meta);
			
			ItemStack nu = new ItemStack(Material.GOLD_BLOCK);
			meta = nu.getItemMeta();
			meta.setDisplayName(Utils.chat("&6Nuker"));
			nu.setItemMeta(meta);
			
			ItemStack br = new ItemStack(Material.GOLD_BLOCK);
			meta = br.getItemMeta();
			meta.setDisplayName(Utils.chat("&6BlockReach"));
			br.setItemMeta(meta);
			
			ItemStack lq = new ItemStack(Material.GOLD_BLOCK);
			meta = lq.getItemMeta();
			meta.setDisplayName(Utils.chat("&6Liquids"));
			lq.setItemMeta(meta);
			
			ItemStack sc = new ItemStack(Material.GOLD_BLOCK);
			meta = sc.getItemMeta();
			meta.setDisplayName(Utils.chat("&6Scaffold"));
			sc.setItemMeta(meta);
			
			ItemStack xr = new ItemStack(Material.GOLD_BLOCK);
			meta = xr.getItemMeta();
			meta.setDisplayName(Utils.chat("&6Xray"));
			xr.setItemMeta(meta);
			
			ItemStack gh = new ItemStack(Material.GOLD_BLOCK);
			meta = gh.getItemMeta();
			meta.setDisplayName(Utils.chat("&6GhostHand"));
			gh.setItemMeta(meta);
			
			ItemStack fe = new ItemStack(Material.EMERALD_BLOCK);
			meta = fe.getItemMeta();
			meta.setDisplayName(Utils.chat("&aFastEat"));
			fe.setItemMeta(meta);
			
			ItemStack re = new ItemStack(Material.EMERALD_BLOCK);
			meta = re.getItemMeta();
			meta.setDisplayName(Utils.chat("&aRegen"));
			re.setItemMeta(meta);
			
			ItemStack nw = new ItemStack(Material.EMERALD_BLOCK);
			meta = nw.getItemMeta();
			meta.setDisplayName(Utils.chat("&aNoSwing"));
			nw.setItemMeta(meta);
			
			ItemStack ar = new ItemStack(Material.EMERALD_BLOCK);
			meta = ar.getItemMeta();
			meta.setDisplayName(Utils.chat("&aAutoRespawn"));
			ar.setItemMeta(meta);
			
			ItemStack im = new ItemStack(Material.LAPIS_BLOCK);
			meta = im.getItemMeta();
			meta.setDisplayName(Utils.chat("&9InventoryMove"));
			im.setItemMeta(meta);
			
			ItemStack id = new ItemStack(Material.LAPIS_BLOCK);
			meta = id.getItemMeta();
			meta.setDisplayName(Utils.chat("&9ItemDrops"));
			id.setItemMeta(meta);
			
			ItemStack al = new ItemStack(Material.LAPIS_BLOCK);
			meta = al.getItemMeta();
			meta.setDisplayName(Utils.chat("&9AutoLoot"));
			al.setItemMeta(meta);
			
			ItemStack pi = new ItemStack(Material.LAPIS_BLOCK);
			meta = pi.getItemMeta();
			meta.setDisplayName(Utils.chat("&9PortalInventory"));
			pi.setItemMeta(meta);
			
			ItemStack ei = new ItemStack(Material.LAPIS_BLOCK);
			meta = ei.getItemMeta();
			meta.setDisplayName(Utils.chat("&9ExtraInventory"));
			ei.setItemMeta(meta);
			
			inventory.setItem(0, pane);
			inventory.setItem(1, pane);
			inventory.setItem(2, pane);
			inventory.setItem(3, pane);
			inventory.setItem(4, head);
			inventory.setItem(5, pane);
			inventory.setItem(6, pane);
			inventory.setItem(7, pane);
			inventory.setItem(8, pane);
			inventory.setItem(9, pane);
			inventory.setItem(10, ns);
			inventory.setItem(11, fu);
			inventory.setItem(12, fb);
			inventory.setItem(13, fp);
			inventory.setItem(14, nu);
			inventory.setItem(15, br);
			inventory.setItem(16, lq);
			
			inventory.setItem(17, pane);
			inventory.setItem(18, pane);
			
			inventory.setItem(19, sc);
			inventory.setItem(20, xr);
			inventory.setItem(21, gh);
			inventory.setItem(22, fe);
			inventory.setItem(23, re);
			inventory.setItem(24, nw);
			inventory.setItem(25, ar);
			
			inventory.setItem(26, pane);
			inventory.setItem(27, pane);
			
			inventory.setItem(28, im);
			inventory.setItem(29, id);
			inventory.setItem(30, al);
			inventory.setItem(31, pi);
			inventory.setItem(32, ei);
			
			inventory.setItem(35, pane);
			inventory.setItem(36, pane);
			inventory.setItem(44, pane);
			inventory.setItem(46, pane);
			inventory.setItem(47, pane);
			inventory.setItem(48, pane);
			inventory.setItem(49, close);
			inventory.setItem(50, pane);
			inventory.setItem(51, pane);
			inventory.setItem(52, pane);
			inventory.setItem(45, back);
			inventory.setItem(53, pane);
			
			
			
			
			
			
			banner.openInventory(inventory);
		}
		
		
		
	}
	
}
