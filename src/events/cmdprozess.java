package events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import archiV3.main;

public class cmdprozess implements Listener {

	@EventHandler
	public void onCMD(PlayerCommandPreprocessEvent e) {
		if (!main.allow.get(e.getPlayer()) && !e.getMessage().startsWith("/w")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(
					main.prefix + "§cDu darfst in dieser Welt nichts bauen!");
		}
	}
}
