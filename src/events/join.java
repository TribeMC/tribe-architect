package events;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import archiV3.main;

public class join implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage("§8[§a§l+§8] §7Der Spieler §e"
				+ e.getPlayer().getName() + " §7ist gejoint!");
		if (canBuild(e.getPlayer())) {
			main.allow.put(e.getPlayer(), true);
		} else {
			main.allow.put(e.getPlayer(), false);

		}

	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage("§8[§c§l-§8] §7Der Spieler §e"
				+ e.getPlayer().getName() + " §7hat den Server verlassen!");
		main.allow.remove(e.getPlayer());
	}

	@EventHandler
	public void onKick(PlayerKickEvent e) {
		e.setLeaveMessage("§8[§c§l-§8] §7Der Spieler §e"
				+ e.getPlayer().getName() + " §7hat den Server verlassen!");
		main.allow.remove(e.getPlayer());

	}

	private boolean canBuild(Player p) {
		if (p.hasPermission("Build.bypass")) {
			return true;
		} else {
			File f = new File(p.getWorld().getName().toLowerCase()
					+ "/build.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			if (cfg.getStringList("Builder")
					.contains(p.getName().toLowerCase())) {
				return true;
			} else {
				return false;
			}
		}
	}
}
