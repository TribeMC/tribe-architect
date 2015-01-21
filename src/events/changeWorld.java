package events;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import archiV3.main;

public class changeWorld implements Listener {

	@EventHandler
	public void onChange(PlayerChangedWorldEvent e) {

		if (canBuild(e.getPlayer())) {
			main.allow.put(e.getPlayer(), true);
		} else {
			main.allow.put(e.getPlayer(), false);

		}
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
