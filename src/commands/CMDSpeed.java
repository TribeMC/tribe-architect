package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import archiV3.main;

public class CMDSpeed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (cs instanceof ConsoleCommandSender) {
			cs.sendMessage(main.prefix + "Nur für Spieler!");
			return false;
		}
		Player p = (Player) cs;
		if (args.length == 0) {
			p.setWalkSpeed(0.2F);
			p.setFlySpeed(0.1F);
			p.sendMessage(main.prefix
					+ "Deine Geschwindigkeit ist wieder normal!");
		} else if (args.length == 1) {
			double f = Double.valueOf(args[0]);
			if (p.isFlying()) {
				String s = f / 10 + "";
				try {
					p.setFlySpeed(Float.valueOf(s));
					p.sendMessage(main.prefix
							+ "Deine Fly-Geschwindigkeit ist nun : §c"
							+ args[0]);
				} catch (Exception e) {
					p.sendMessage(main.prefix
							+ "Dies ist keine Geschwindigkeitsangabe!");
				}
			} else {
				try {
					String s = f / 5 + "";
					p.setWalkSpeed(Float.valueOf(s));
					p.sendMessage(main.prefix
							+ "Deine Geh-Geschwindigkeit ist nun : §c"
							+ args[0]);
				} catch (Exception e) {
					p.sendMessage(main.prefix
							+ "Dies ist keine Geschwindigkeitsangabe!");
				}
			}
		} else {

		}
		return false;
	}
}
