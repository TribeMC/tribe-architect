package commands;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import archiV3.main;

public class CMDLetsBuild implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		if (cs instanceof ConsoleCommandSender) {
			cs.sendMessage(main.prefix + "Nur Spieler!");
			return false;
		}
		final Player p = (Player) cs;
		if (!p.hasPermission("build.letsbuild")) {
			p.sendMessage(main.prefix + "Keine Rechte!");
			return false;
		}
		if (args.length != 2) {
			p.sendMessage(main.prefix
					+ "Nutze: /letsbuild [Player] [Zeit in Minuten]");
			return false;
		}
		final String s = args[0];
		try {
			addUser(p, p.getWorld(), s);
			final int t = Integer.valueOf(args[1]);
			portBuild(p, s);
			setWarp(p, s);
			Bukkit.getScheduler().scheduleSyncDelayedTask(
					main.getPlugin(main.class), new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								@SuppressWarnings("deprecation")
								final Player tar = Bukkit.getPlayer(s);

								tar.teleport(p);
								tar.sendMessage(main.prefix + "Du hast nun §c"
										+ t
										+ " §eMinuten Zeit, etwas vorzubauen!");
								tar.sendMessage(main.prefix
										+ "Du kommst jederzeit mit /w "
										+ tar.getName().toLowerCase()
										+ " hierhin zurück!");
								Bukkit.getScheduler().scheduleSyncDelayedTask(
										main.getPlugin(main.class),
										new Runnable() {

											@Override
											public void run() {
												if (p.isOnline()) {
													removeUser(p, p.getWorld(),
															s);
													p.sendMessage(main.prefix
															+ "Der Spieler "
															+ tar.getName()
															+ " ist fertig! Schaue mit /w world:"
															+ tar.getName()
																	.toLowerCase()
															+ " was daraus geworden ist!");
												} else {
													removeUser(null,
															p.getWorld(), s);
												}

												if (tar.isOnline()) {
													tar.sendMessage(main.prefix
															+ "Deine Zeit ist nun vorbei!");
													portLobby(tar);
												}

											}

											private void portLobby(Player p) {
												ByteArrayOutputStream b = new ByteArrayOutputStream();
												DataOutputStream d = new DataOutputStream(
														b);

												try {
													d.writeUTF("Connect");
													d.writeUTF("lobby"); // ?
													p.sendPluginMessage(
															main.getPlugin(main.class),
															"BungeeCord",
															b.toByteArray());
													b.close();
													d.close();
												} catch (IOException ex) {
													p.sendMessage(ChatColor.DARK_RED
															+ "Es ist ein Fehler aufgetreten!");
												}
											}
										}, 20 * 60 * t);
							} catch (Exception e) {
								p.sendMessage(main.prefix
										+ "Konnte keinen Spieler mit dem Namen "
										+ s + " finden!");
								removeUser(p, p.getWorld(), s);
							}
						}
					}, 20 * 2);

		} catch (Exception e) {
			p.sendMessage(main.prefix + "Dies ist keine gültige Zeit!");
			return false;
		}

		return false;
	}

	private void removeUser(Player p, World w, String s) {
		File f = new File(w.getName().toLowerCase() + "/build.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		if (!cfg.getStringList("Builder").contains(s.toLowerCase())) {
			if (p != null) {
				p.sendMessage(main.prefix
						+ "Dieser Spieler darf hier nicht bauen!");
			}
		} else {
			List<String> temp = cfg.getStringList("Builder");
			temp.remove(s.toLowerCase());
			cfg.set("Builder", temp);
			try {
				cfg.save(f);
				if (p != null) {

					p.sendMessage(main.prefix + "Der User " + s
							+ " wurde aus Welt " + w.getName() + " entfernt!");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				if (p != null) {

					p.sendMessage(main.prefix
							+ "§cEs ist ein Fehler aufgetreten!");
				}
			}
		}

	}

	private void addUser(Player p, World w, String s) {
		File f = new File(w.getName().toLowerCase() + "/build.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		if (cfg.getStringList("Builder").contains(s.toLowerCase())) {
			p.sendMessage(main.prefix + "Dieser Spieler darf bereits bauen!");
		} else {
			List<String> temp = cfg.getStringList("Builder");
			temp.add(s.toLowerCase());
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

	private void setWarp(Player p, String s) {

		File f = new File(p.getWorld().getName().toLowerCase() + "/build.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		if (cfg.contains("Warps." + s.toLowerCase())) {
			p.sendMessage(main.prefix + "Dieser Warp exisitiert bereits!");
			return;
		}
		cfg.set("Warps." + s.toLowerCase(), createString(p.getLocation()));
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

	private void portBuild(Player p, String s) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream d = new DataOutputStream(b);

		try {
			d.writeUTF("ConnectOther");
			d.writeUTF(s);
			d.writeUTF("bau"); // ?
			p.sendPluginMessage(main.getPlugin(main.class), "BungeeCord",
					b.toByteArray());
			b.close();
			d.close();
		} catch (IOException ex) {
			p.sendMessage(ChatColor.DARK_RED + "Es ist ein Fehler aufgetreten!");
		}
	}

}
