package me.sablednah.legendquest.playercharacters;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import me.sablednah.legendquest.Main;
import me.sablednah.legendquest.classes.ClassType;
import me.sablednah.legendquest.races.Race;
import me.sablednah.legendquest.utils.SetExp;

public class PC {
	public Main						lq;

	public String					charname;
	public String					player;
	public Race						race;
	public ClassType				mainClass;
	public ClassType				subClass;
	public HashMap<String, Integer>	xpEarnt	= new HashMap<String, Integer>();
	public int						maxHP;
	public int						health;
	public int						skillpoints;

	public boolean					raceChanged;

	public int						statStr;
	public int						statDex;
	public int						statInt;
	public int						statWis;
	public int						statCon;
	public int						statChr;

	public int						currentXP;

	public PC(Main plugin, String pName) {
		this.lq = plugin;

		this.player = pName;
		this.charname = pName;
		this.mainClass = this.lq.classes.defaultClass;
		this.race = this.lq.races.defaultRace;
		this.raceChanged = false;
		this.subClass = null;
		this.maxHP = 20;
		this.health = 20;
		this.skillpoints = 0;
		this.currentXP = 0;
		if (!lq.configMain.randomStats) {
			statStr = statDex = statInt = statWis = statCon = statChr = 12;
		} else {
			int[] statline = { 16, 14, 13, 12, 11, 10 };
			Random r = new Random(pName.hashCode());
			for (int i = 0; i < statline.length; i++) {
				int position = i + r.nextInt(statline.length - i);
				int temp = statline[i];
				statline[i] = statline[position];
				statline[position] = temp;
			}
			statStr = statline[0];
			statDex = statline[1];
			statInt = statline[2];
			statWis = statline[3];
			statCon = statline[4];
			statChr = statline[5];
		}
	}

	/**
	 * @return the statStr
	 */
	public int getStatStr() {
		int stat;
		stat = statStr;
		if (race != null) {
			stat += race.statStr;
		}
		if (mainClass != null) {
			if (subClass != null) {
				int classboost = 0;
				if (mainClass.statStr > -1 && subClass.statStr > -1) {
					// both positive (ok 0+)
					classboost = Math.max(mainClass.statStr, subClass.statStr);
				} else if (mainClass.statStr < 1 && subClass.statStr < 1) {
					// both negative (ok 0+)
					classboost = Math.max(mainClass.statStr, subClass.statStr);
				} else {
					classboost = mainClass.statStr + subClass.statStr;
				}
				stat += classboost;
			} else {
				stat += mainClass.statStr;
			}
		}
		return stat;
	}

	/**
	 * @return the statDex
	 */
	public int getStatDex() {
		int stat;
		stat = statDex;
		if (race != null) {
			stat += race.statDex;
		}
		if (mainClass != null) {
			if (subClass != null) {
				int classboost = 0;
				if (mainClass.statDex > -1 && subClass.statDex > -1) {
					// both positive (ok 0+)
					classboost = Math.max(mainClass.statDex, subClass.statDex);
				} else if (mainClass.statDex < 1 && subClass.statDex < 1) {
					// both negative (ok 0+)
					classboost = Math.max(mainClass.statDex, subClass.statDex);
				} else {
					classboost = mainClass.statDex + subClass.statDex;
				}
				stat += classboost;
			} else {
				stat += mainClass.statDex;
			}
		}
		return stat;
	}

	/**
	 * @return the statInt
	 */
	public int getStatInt() {
		int stat;
		stat = statInt;
		if (race != null) {
			stat += race.statInt;
		}
		if (mainClass != null) {
			if (subClass != null) {
				int classboost = 0;
				if (mainClass.statInt > -1 && subClass.statInt > -1) {
					// both positive (ok 0+)
					classboost = Math.max(mainClass.statInt, subClass.statInt);
				} else if (mainClass.statInt < 1 && subClass.statInt < 1) {
					// both negative (ok 0+)
					classboost = Math.max(mainClass.statInt, subClass.statInt);
				} else {
					classboost = mainClass.statInt + subClass.statInt;
				}
				stat += classboost;
			} else {
				stat += mainClass.statInt;
			}
		}
		return stat;
	}

	/**
	 * @return the statWis
	 */
	public int getStatWis() {
		int stat;
		stat = statStr;
		if (race != null) {
			stat += race.statWis;
		}
		if (mainClass != null) {
			if (subClass != null) {
				int classboost = 0;
				if (mainClass.statWis > -1 && subClass.statWis > -1) {
					// both positive (ok 0+)
					classboost = Math.max(mainClass.statWis, subClass.statWis);
				} else if (mainClass.statWis < 1 && subClass.statWis < 1) {
					// both negative (ok 0+)
					classboost = Math.max(mainClass.statWis, subClass.statWis);
				} else {
					classboost = mainClass.statWis + subClass.statWis;
				}
				stat += classboost;
			} else {
				stat += mainClass.statWis;
			}
		}
		return stat;
	}

	/**
	 * @return the statCon
	 */
	public int getStatCon() {
		int stat;
		stat = statStr;
		if (race != null) {
			stat += race.statCon;
		}
		if (mainClass != null) {
			if (subClass != null) {
				int classboost = 0;
				if (mainClass.statCon > -1 && subClass.statCon > -1) {
					// both positive (ok 0+)
					classboost = Math.max(mainClass.statCon, subClass.statCon);
				} else if (mainClass.statCon < 1 && subClass.statCon < 1) {
					// both negative (ok 0+)
					classboost = Math.max(mainClass.statCon, subClass.statCon);
				} else {
					classboost = mainClass.statCon + subClass.statCon;
				}
				stat += classboost;
			} else {
				stat += mainClass.statCon;
			}
		}
		return stat;
	}

	/**
	 * @return the statChr
	 */
	public int getStatChr() {
		int stat;
		stat = statStr;
		if (race != null) {
			stat += race.statChr;
		}
		if (mainClass != null) {
			if (subClass != null) {
				int classboost = 0;
				if (mainClass.statChr > -1 && subClass.statChr > -1) {
					// both positive (ok 0+)statChr
					classboost = Math.max(mainClass.statChr, subClass.statStr);
				} else if (mainClass.statChr < 1 && subClass.statChr < 1) {
					// both negative (ok 0+)
					classboost = Math.max(mainClass.statChr, subClass.statChr);
				} else {
					classboost = mainClass.statChr + subClass.statChr;
				}
				stat += classboost;
			} else {
				stat += mainClass.statChr;
			}
		}
		return stat;
	}

	public int getMaxHealth() {
		Player p = Bukkit.getServer().getPlayer(this.player);
		if (p != null) {
			int hp, level;
			double result, perlevel;

			hp = race.baseHealth;
			level = p.getLevel();
			if (subClass != null) {
				perlevel = Math.max(mainClass.healthPerLevel, subClass.healthPerLevel);
			} else {
				perlevel = mainClass.healthPerLevel;
			}
			result = (hp + (level * perlevel));
			this.maxHP = (int) result;
		}
		return this.maxHP;
	}

	public void healthCheck() {
		Player p = Bukkit.getServer().getPlayer(this.player);
		if (p != null) {
			getMaxHealth();

			this.health = p.getHealth();
			if (this.health > this.maxHP) {
				this.health = this.maxHP;
			}
			p.setMaxHealth(this.maxHP);
			p.setHealth(this.health);

			lq.debug.fine("SHC � HP: " + p.getHealth() + " | pHP: " + this.health + " | p.max: " + p.getMaxHealth() + " | pc.max: " + this.maxHP);
			p.sendMessage("SHC � HP: " + p.getHealth() + " | pHP: " + this.health + " | p.max: " + p.getMaxHealth() + " | pc.max: " + this.maxHP);
		}
	}

	public void scheduleHealthCheck() {
		Bukkit.getServer().getScheduler().runTaskLater(lq, new DelayedCheck(), 2L);
	}

	public class DelayedCheck implements Runnable {
		@Override
		public void run() {
			healthCheck();
		}
	}

	public void setXP(int newXP) {
		xpEarnt.put(mainClass.name.toLowerCase(), newXP);
		if (subClass != null) {
			xpEarnt.put(subClass.name.toLowerCase(), newXP);
		}
		currentXP = newXP;
		Player p = Bukkit.getServer().getPlayer(player);
		if (p != null) {
			SetExp.setTotalExperience(p, newXP);
		}
	}

	public boolean hasMastered(String className) {
		lq.logger.info("className (" + className + ")...");
		if (xpEarnt.containsKey(className.toLowerCase())) {
			lq.logger.info("className (" + className + "): " + xpEarnt.get(className.toLowerCase()));
			if (xpEarnt.get(className.toLowerCase()) >= Main.MAX_XP) {
				return true;
			}
		}
		return false;
	}

	public boolean allowedArmour(int id) {
		Boolean valid = false;
		if (id == 0 ){ 
			valid = true;
			lq.debug.fine("Naked is valid armour");
		}
		if (mainClass.allowedArmour.contains(id)) {
			valid = true;
			lq.debug.fine(id + " is valid armour for class: "+mainClass.name);
		}
		if (race.allowedArmour.contains(id)) {
			valid = true;
			lq.debug.fine(id + " is valid armour for race: "+race.name);
		}
		if (subClass != null && subClass.allowedArmour.contains(id)) {
			valid=true;
			lq.debug.fine(id + " is valid armour for sub-class: "+subClass.name);
		}
		if (mainClass.dissallowedArmour.contains(id)) {
			valid = false;
			lq.debug.fine(id + " is INvalid armour for class: "+mainClass.name);
		}
		if (race.dissallowedArmour.contains(id)) {
			valid = false;
			lq.debug.fine(id + " is INvalid armour for race: "+race.name);
		}
		if (subClass != null && subClass.dissallowedArmour.contains(id)) {
			valid=false;
			lq.debug.fine(id + " is INvalid armour for sub-class: "+subClass.name);
		}
		return valid;
	}

	public boolean allowedWeapon(int id) {
		Boolean valid = false;
		
		if (id == 0 ){ 
			valid = true;
			lq.debug.fine("fist is valid weapon");
		}
		if (mainClass.allowedWeapons.contains(id)) {
			valid = true;
			lq.debug.fine(id + " is valid weapon for class: "+mainClass.name);
		}
		if (race.allowedWeapons.contains(id)) {
			valid = true;
			lq.debug.fine(id + " is valid weapon for race: "+race.name);
		}
		if (subClass != null && subClass.allowedWeapons.contains(id)) {
			valid=true;
			lq.debug.fine(id + " is valid weapon for sub-class: "+subClass.name);
		}
		if (mainClass.dissallowedWeapons.contains(id)) {
			valid = false;
			lq.debug.fine(id + " is INvalid weapon for class: "+mainClass.name);
		}
		if (race.dissallowedWeapons.contains(id)) {
			valid = false;
			lq.debug.fine(id + " is INvalid weapon for race: "+race.name);
		}
		if (subClass != null && subClass.dissallowedWeapons.contains(id)) {
			valid=false;
			lq.debug.fine(id + " is INvalid weapon for sub-class: "+subClass.name);
		}
		return valid;
	}

	public boolean allowedTool(int id) {
		Boolean valid = false;
	
		if (id == 0 ){ 
			valid = true;
			lq.debug.fine("fist is valid tool");
		}
		if (mainClass.allowedTools.contains(id)) {
			valid = true;
			lq.debug.fine(id + " is valid tool for class: "+mainClass.name);
		}
		if (race.allowedTools.contains(id)) {
			valid = true;
			lq.debug.fine(id + " is valid tool for race: "+race.name);
		}
		if (subClass != null && subClass.allowedTools.contains(id)) {
			valid=true;
			lq.debug.fine(id + " is valid tool for sub-class: "+subClass.name);
		}
		if (mainClass.dissallowedTools.contains(id)) {
			valid = false;
			lq.debug.fine(id + " is INvalid tool for class: "+mainClass.name);
		}
		if (race.dissallowedTools.contains(id)) {
			valid = false;
			lq.debug.fine(id + " is INvalid tool for race: "+race.name);
		}
		if (subClass != null && subClass.dissallowedTools.contains(id)) {
			valid=false;
			lq.debug.fine(id + " is INvalid tool for sub-class: "+subClass.name);
		}
		return valid;
	}

	@SuppressWarnings("deprecation")
	public void checkInv() {
		Player p = lq.getServer().getPlayer(player);
		PlayerInventory i = p.getInventory();

		if (!(allowedArmour(i.getHelmet().getTypeId()))) {
			p.sendMessage(lq.configLang.cantEquipArmour);
			lq.debug.fine("Removed helmet " + (i.getHelmet().getTypeId()) + " from " + p.getName() + ".");
			p.getWorld().dropItemNaturally(p.getLocation(), i.getHelmet());
			i.setHelmet(null);
		}
		if (!(allowedArmour(i.getChestplate().getTypeId()))) {
			p.sendMessage(lq.configLang.cantEquipArmour);
			lq.debug.fine("Removed chestplate " + (i.getChestplate().getTypeId()) + " from " + p.getName() + ".");
			p.getWorld().dropItemNaturally(p.getLocation(), i.getChestplate());
			i.setChestplate(null);
		}
		if (!(allowedArmour(i.getLeggings().getTypeId()))) {
			p.sendMessage(lq.configLang.cantEquipArmour);
			lq.debug.fine("Removed leggings " + (i.getHelmet().getTypeId()) + " from " + p.getName() + ".");
			p.getWorld().dropItemNaturally(p.getLocation(), i.getLeggings());
			i.setLeggings(null);
		}
		if (!(allowedArmour(i.getBoots().getTypeId()))) {
			p.sendMessage(lq.configLang.cantEquipArmour);
			lq.debug.fine("Removed boots " + (i.getHelmet().getTypeId()) + " from " + p.getName() + ".");
			p.getWorld().dropItemNaturally(p.getLocation(), i.getBoots());
			i.setBoots(null);
		}
		p.updateInventory();
	}
}