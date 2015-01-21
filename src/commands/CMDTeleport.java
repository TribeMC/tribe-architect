package commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import archiV3.main;

public class CMDTeleport implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (cs instanceof ConsoleCommandSender) {
			cs.sendMessage(main.prefix + "Only Player");
			return false;
		}
		Player p = (Player) cs;
		if (args.length == 1) {
			try {
				@SuppressWarnings("deprecation")
				Player tar = Bukkit.getPlayer(args[0]);
				p.teleport(tar);
				p.sendMessage(main.prefix + "Du wurdest zu §c" + tar.getName()
						+ " §eteleportiert!");
			} catch (Exception e) {
				p.sendMessage(main.prefix
						+ "§cDieser Spieler ist nicht Online!");
			}
		} else if (args.length == 2) {
			try {
				@SuppressWarnings("deprecation")
				Player tar = Bukkit.getPlayer(args[0]);
				@SuppressWarnings("deprecation")
				Player to = Bukkit.getPlayer(args[1]);
				tar.teleport(to);
				p.sendMessage(main.prefix + "Der Spieler §c" + tar.getName()
						+ " §ewurde zu §c" + to.getName() + "§e teleportiert!");
				tar.sendMessage(main.prefix + "Du wurdest von §c" + p.getName()
						+ "§e zu §c" + to.getName() + " §eteleportiert!");
			} catch (Exception e) {
				p.sendMessage(main.prefix
						+ "§cDiese Spieler sind nicht Online!s");
			}
		} else if (args.length == 3) {
			try {

				int x = Integer.valueOf(args[0]);
				int y = Integer.valueOf(args[1]);
				int z = Integer.valueOf(args[2]);
				p.teleport(new Location(p.getWorld(), x, y, z));
				p.sendMessage(main.prefix + "Du wurdest zu §c" + x + ", " + y
						+ ", " + z + "§e teleportiert!");
			} catch (Exception e) {
				p.sendMessage(main.prefix + "§cNutze /tp [X] [Y] [Z]");
			}
		} else {
			p.sendMessage(main.prefix
					+ "Nutze /tp [Spieler | /tp [Target] [Spieler] | /tp [x] [y] [z]");
		}
		return false;
	}

}
