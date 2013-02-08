package me.sablednah.legendquest.listeners;

import me.sablednah.legendquest.Main;
import me.sablednah.legendquest.events.LevelUpEvent;
import me.sablednah.legendquest.playercharacters.PC;
import me.sablednah.legendquest.utils.SetExp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerEvents implements Listener {

	public Main	lq;

	public PlayerEvents(Main p) {
		this.lq = p;
	}

	// set to monitor - we're not gonna change the login, only load our data
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		String pName = p.getName();
		PC pc = lq.players.getPC(pName);
		lq.players.addPlayer(pName, pc);
		p.setTotalExperience(pc.currentXP);
		p.setMaxHealth(pc.maxHP);
		p.setHealth(pc.health);
		pc.healthCheck();
	}

	// set to monitor - we can't change the quit - just want to clean our data up.
	@EventHandler(priority = EventPriority.MONITOR)
	public void onQuit(PlayerQuitEvent event) {
		String pName = event.getPlayer().getName();
		lq.players.removePlayer(pName);
	}

	// track EXP changes - and halve then if dual class
	@EventHandler(priority = EventPriority.LOW)
	public void onXPChange(PlayerExpChangeEvent event) {
		int xpAmount = event.getAmount();
		Player p = event.getPlayer();
		String pName = p.getName();
		PC pc = lq.players.getPC(pName);

		// half xp gain for dual class
		if (pc.subClass != null) {
			xpAmount = xpAmount / 2;
		}
		pc.setXP(SetExp.getTotalExperience(p) + xpAmount);
		lq.players.addPlayer(pName, pc);
		lq.players.savePlayer(pc);

		if (xpAmount >= p.getExpToLevel()) {
			pc.scheduleHealthCheck();
			LevelUpEvent e = new LevelUpEvent(p, p.getLevel() + 1, pc);
			Bukkit.getServer().getPluginManager().callEvent(e);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onRespawn(PlayerRespawnEvent event) {
		Player p = event.getPlayer();
		String pName = p.getName();
		PC pc = lq.players.getPC(pName);
		lq.logWarn("currentXP: " + pc.currentXP);
		int currentXP = SetExp.getTotalExperience(p);
		lq.logWarn("totxp: " + currentXP);
		int xpLoss = (int) (currentXP * (lq.configMain.percentXpLossRespawn / 100));
		lq.logWarn("xpLoss: " + xpLoss);
		int newXp = currentXP - xpLoss;
		pc.setXP(newXp);
		lq.players.savePlayer(pc);
		lq.getServer().getScheduler().runTaskLater(lq, new delayedSpawn(newXp, p), 5);
	}

	public class delayedSpawn implements Runnable {
		public int		xp;
		public Player	player;

		public delayedSpawn(int xp, Player player) {
			this.xp = xp;
			this.player = player;
		}

		public void run() {
			String pName = player.getName();
			PC pc = lq.players.getPC(pName);
			pc.setXP(xp);
			lq.players.savePlayer(pc);
		}
	}

	// preserve XP on death...
	@EventHandler(priority = EventPriority.LOW)
	public void onDeath(PlayerDeathEvent event) {
		event.setDroppedExp(0);
		event.setKeepLevel(true);
	}
	
}