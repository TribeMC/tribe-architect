package commands;

import java.io.File;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import archiV3.main;

public class CMDWarp implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (cs instanceof ConsoleCommandSender) {
			cs.sendMessage(main.prefix + "Only Player");
			return false;
		}
		Player p = (Player) cs;
		if (args.length == 0) {

			World w = Bukkit.getWorld(p.getName().toLowerCase());
			if (w == null) {
				w = Bukkit.getWorld("world");
			}
			warp(p, w, "default");
			setDefaultWarps(p, w);
		} else if (args.length == 1) {
			if (args[0].contains(":")) {
				String[] s = args[0].split(":");
				if (Bukkit.getWorld(s[0].toLowerCase()) != null) {

					if (s.length == 1) {
						warp(p, Bukkit.getWorld(s[0].toLowerCase()), "default");

					} else {
						warp(p, Bukkit.getWorld(s[0].toLowerCase()),
								s[1].toLowerCase());

					}
				} else {
					cs.sendMessage(main.prefix + "Diese Welt existiert nicht!");
				}

			} else {
				warp(p, p.getWorld(), args[0].toLowerCase());

			}
		}
		return false;
	}

	private void setDefaultWarps(Player p, World w) {
		File f = new File(w.getName().toLowerCase() + "/build.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		Set<String> s = cfg.getConfigurationSection("Warps.").getKeys(false);
		String warps = "";
		for (String s1 : s) {
			warps = warps + s1 + ", ";
		}
		p.sendMessage(main.prefix + "Die Welt " + w.getName()
				+ " hat folgende Warps:");
		p.sendMessage(main.prefix + warps);
	}

	private void warp(Player p, World w, String s) {
		File f = new File(w.getName().toLowerCase() + "/build.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		String lo = cfg.getString("Warps." + s.toLowerCase());
		if (lo == null) {
			p.sendMessage(main.prefix + "Der Warppunkt existiert nicht!");
			return;
		}
		String[] temp = lo.split(",");
		Location loc = new Location(w, Integer.valueOf(temp[0]),
				Integer.valueOf(temp[1]), Integer.valueOf(temp[2]),
				Float.valueOf(temp[3]), Float.valueOf(temp[4]));
		p.teleport(loc);
		p.sendMessage(main.prefix + "Du wurdest zu " + s + " teleportiert! ("
				+ w.getName() + ")");
	}
}
