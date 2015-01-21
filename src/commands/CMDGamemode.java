package commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import archiV3.main;

public class CMDGamemode implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {

		if (cs instanceof ConsoleCommandSender) {
			cs.sendMessage(main.prefix + "Only Player");
			return false;
		}
		Player p = (Player) cs;
		if (args.length == 0) {
			p.setGameMode(GameMode.CREATIVE);
			p.sendMessage(main.prefix
					+ "Dein Gamemode wurde auf §a§lCreative §egesetzt!");
		} else if (args.length == 1) {
			String s = args[0];
			try {
				GameMode gm = GameMode.valueOf(s);
				p.setGameMode(gm);
			} catch (Exception e) {
				p.sendMessage(main.prefix
						+ "Nutze /gm [survival|creative|adventure]");
			}
		} else {
			p.sendMessage(main.prefix
					+ "Nutze /gm [survival|creative|adventure]");
		}
		return false;
	}
}
