package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import archiV3.main;

public class CMDTime implements CommandExecutor {

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
			p.getWorld().setThundering(false);
			p.getWorld().setTime(0);
			p.getWorld().setStorm(false);
			p.sendMessage(main.prefix + "Ein neuer Tag beginnt!");
		} else if (args.length == 1) {
			try {
				String s = args[0];
				if (s.equalsIgnoreCase("day") || s.equalsIgnoreCase("d")) {
					s = "0";
				} else if (s.equalsIgnoreCase("night")
						|| s.equalsIgnoreCase("n")) {
					s = "18000";
				} else {

				}
				p.getWorld().setTime(Long.valueOf(s));
				p.sendMessage(main.prefix + "Die Zeit wurde gesetzt!");
			} catch (Exception e) {
				p.sendMessage(main.prefix
						+ "Dies ist keine gültige Zeitangabe!");
			}
		} else {
			sendHelp(cs);
		}
		return false;
	}

	private void sendHelp(CommandSender cs) {
		// TODO Auto-generated method stub
		cs.sendMessage(main.prefix + "Nutze: /t [wert]");
	}

}
