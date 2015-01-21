package commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import archiV3.main;

public class CMDSetWarp implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		if (cs instanceof ConsoleCommandSender) {
			cs.sendMessage(main.prefix + "Only Player");
			return false;
		}
		Player p = (Player) cs;
		if (args.length == 1) {
			setWarp(p, args[0].toLowerCase());
		} else {
			p.sendMessage(main.prefix + "Nutze /setwarp [Name]");
		}
		return false;
	}

	private void setWarp(Player p, String s) {
		File f = new File(p.getWorld().getName().toLowerCase() + "/build.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		if (cfg.contains("Warps." + s)) {
			p.sendMessage(main.prefix + "Dieser Warp exisitiert bereits!");
			return;
		}
		cfg.set("Warps." + s, createString(p.getLocation()));
		try {
			cfg.save(f);
			p.sendMessage(main.prefix + "Der Warppunkt " + s
					+ " wurde gesetzt!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			p.sendMessage(main.prefix + "§cEs ist ein Fehler aufgetreten!");
		}
	}

	private String createString(Location loc) {
		// TODO Auto-generated method stub
		String s = loc.getBlockX() + "," + loc.getBlockY() + ","
				+ loc.getBlockZ() + "," + loc.getYaw() + "," + loc.getPitch();
		return s;
	}
}