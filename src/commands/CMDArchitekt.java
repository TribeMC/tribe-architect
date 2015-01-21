package commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import archiV3.main;

public class CMDArchitekt implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 1) {
			if (!cs.hasPermission("build.create")) {
				cs.sendMessage(main.prefix + "Dazu hast du keine Rechte!");
			}
			try {
				if (Bukkit.getWorld(args[0].toLowerCase()) != null) {
					cs.sendMessage(main.prefix + "Welt existiert bereits!");
					return false;
				}

				cs.sendMessage(main.prefix + "Die Welt wird nun erstellt!");
				WorldCreator nw = new WorldCreator(args[0].toLowerCase());
				nw.type(WorldType.FLAT).generateStructures(false)
						.environment(Environment.NORMAL);
				nw.createWorld();
				cs.sendMessage(main.prefix + "Die Welt wurde erstellt!");

				World w = Bukkit.getWorld(args[0].toLowerCase());
				w.setDifficulty(Difficulty.PEACEFUL);
				w.setPVP(false);
				w.setGameRuleValue("doMobSpawning", "false");
				w.setSpawnLocation(0, 6, 0);

				File f = new File(args[0].toLowerCase() + "/build.yml");
				f.createNewFile();
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
				String loc = createString(Bukkit
						.getWorld(args[0].toLowerCase()).getSpawnLocation());
				cfg.set("Warps.default", loc);
				cfg.set("Warps.north", "0,6,100,0.0,0.0");
				cfg.set("Warps.south", "0,6,-100,0.0,0.0");
				cfg.set("Warps.west", "-100,6,0,0.0,0.0");
				cfg.set("Warps.east", "100,6,0,0.0,0.0");
				List<String> builder = new ArrayList<>();
				builder.add(args[0].toLowerCase());
				cfg.set("Builder", builder);
				cfg.save(f);
				if (cs instanceof Player) {
					((Player) cs).teleport(Bukkit.getWorld(
							args[0].toLowerCase()).getSpawnLocation());
				}
			} catch (Exception e) {

				cs.sendMessage(main.prefix + "§cEs ist ein Fehler aufgetreten!");
				e.printStackTrace();
			}

		}
		return false;
	}

	private String createString(Location loc) {
		// TODO Auto-generated method stub
		String s = loc.getBlockX() + "," + loc.getBlockY() + ","
				+ loc.getBlockZ() + "," + loc.getYaw() + "," + loc.getPitch();
		return s;
	}

}
