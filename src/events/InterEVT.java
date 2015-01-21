package events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InterEVT implements Listener {

	@EventHandler
	public void onInter(PlayerInteractEvent e) {
		if (e.getItem().getType().equals(Material.GLOWSTONE_DUST)
				&& e.getItem().getItemMeta().getDisplayName() != null) {
			e.getPlayer()
					.chat("/" + e.getItem().getItemMeta().getDisplayName());
			e.setCancelled(true);
		}
	}
}
