package commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import archiV3.main;

public class CMDBuilder implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {

		if (cs instanceof ConsoleCommandSender) {
			cs.sendMessage(main.prefix + "Only Player");
			return false;
		}
		Player p = (Player) cs;
		if (args.length == 1) {
			// list
			if (args[0].equalsIgnoreCase("info")
					|| args[0].equalsIgnoreCase("list")) {
				sendInfo(p, p.getWorld());
			} else {
				sendHelp(p);
			}
		} else if (args.length == 2) {
			// add
			if (args[0].equalsIgnoreCase("add")) {
				addUser(p, p.getWorld(), args[1].toLowerCase());
			} else if (args[0].equalsIgnoreCase("remove")) {
				removeUser(p, p.getWorld(), args[1].toLowerCase());

			} else {
				sendHelp(p);
			}
			// remove

		} else {
			sendHelp(p);
		}
		return false;
	}

	private void removeUser(Player p, World w, String s) {
		File f = new File(w.getName().toLowerCase() + "/build.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		if (!cfg.getStringList("Builder").contains(s)) {
			p.sendMessage(main.prefix + "Dieser Spieler darf hier nicht bauen!");
		} else {
			List<String> temp = cfg.getStringList("Builder");
			temp.remove(s);
			cfg.set("Builder", temp);
			try {
				cfg.save(f);
				p.sendMessage(main.prefix + "Der User " + s
						+ " wurde aus Welt " + w.getName() + " entfernt!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				p.sendMessage(main.prefix + "§cEs ist ein Fehler aufgetreten!");
			}
		}

	}

	private void addUser(Player p, World w, String s) {
		File f = new File(w.getName().toLowerCase() + "/build.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		if (cfg.getStringList("Builder").contains(s)) {
			p.sendMessage(main.prefix + "Dieser Spieler darf bereits bauen!");
		} else {
			List<String> temp = cfg.getStringList("Builder");
			temp.add(s);
			cfg.set("Builder", temp);

			try {
				cfg.save(f);
				p.sendMessage(main.prefix + "Der User " + s
						+ " wurde zur Welt " + w.getName() + " hinzugefügt!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				p.sendMessage(main.prefix + "§cEs ist ein Fehler aufgetreten!");
			}
		}

	}

	private void sendInfo(Player p, World w) {
		File f = new File(w.getName().toLowerCase() + "/build.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		List<String> temp = cfg.getStringList("Builder");
		String s = "Builder in der Welt " + w.getName() + ": ";
		for (String te : temp) {
			s = s + te + ", ";
		}
		p.sendMessage(main.prefix + s);

	}

	private void sendHelp(Player p) {
		p.sendMessage(main.prefix + "Nutze /builder [list| add| remove]");
	}

}
