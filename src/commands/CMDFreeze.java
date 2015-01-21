package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import archiV3.main;

public class CMDFreeze implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (cs instanceof ConsoleCommandSender) {
			cs.sendMessage(main.prefix + "Only Player");
			return false;
		}
		Player p = (Player) cs;
		if (p.getWorld().getGameRuleValue("doDaylightCycle").equals("true")) {
			p.getWorld().setGameRuleValue("doDaylightCycle", "false");
			p.sendMessage(main.prefix
					+ "Die Zeit in dieser Welt bleibt nun stehn!");
		} else {
			p.getWorld().setGameRuleValue("doDaylightCycle", "true");
			p.sendMessage(main.prefix + "Die Zeit in dieser Welt läuft!");
		}

		return false;
	}

}
