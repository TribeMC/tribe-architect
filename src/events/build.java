package events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import archiV3.main;

public class build implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (!main.allow.get(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(
					main.prefix + "§cDu darfst in dieser Welt nichts bauen!");
		}
	}

	@EventHandler
	public void onBuild(BlockPlaceEvent e) {
		if (!main.allow.get(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(
					main.prefix + "§cDu darfst in dieser Welt nichts bauen!");
		}
	}

	@EventHandler
	public void onBukketEmpty(PlayerBucketEmptyEvent e) {
		if (!main.allow.get(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(
					main.prefix + "§cDu darfst in dieser Welt nichts bauen!");
		}
	}

	@EventHandler
	public void onBukketFill(PlayerBucketFillEvent e) {
		if (!main.allow.get(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(
					main.prefix + "§cDu darfst in dieser Welt nichts bauen!");
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if (!main.allow.get(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(
					main.prefix + "§cDu darfst in dieser Welt nichts bauen!");
		}
	}

	@EventHandler
	public void onPickUp(PlayerPickupItemEvent e) {
		if (!main.allow.get(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(
					main.prefix + "§cDu darfst in dieser Welt nichts bauen!");
		}
	}

}
