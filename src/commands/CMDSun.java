package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import archiV3.main;

public class CMDSun implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (cs instanceof ConsoleCommandSender) {
			cs.sendMessage(main.prefix + "Only Player");
			return false;
		}
		Player p = (Player) cs;

		p.getWorld().setThundering(false);
		p.getWorld().setStorm(false);
		p.sendMessage(main.prefix + "In der Welt scheint nun die Sonne!");
		return false;

	}

}
