package archiV3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import commands.CMDArchitekt;
import commands.CMDBuilder;
import commands.CMDCommandExecute;
import commands.CMDFreeze;
import commands.CMDGamemode;
import commands.CMDLetsBuild;
import commands.CMDSetWarp;
import commands.CMDSpeed;
import commands.CMDSun;
import commands.CMDTeleport;
import commands.CMDTime;
import commands.CMDWarp;

import events.InterEVT;
import events.Sign;
import events.build;
import events.changeWorld;
import events.cmdprozess;
import events.join;

public class main extends JavaPlugin {

	CommandSender cs = Bukkit.getConsoleSender();
	PluginManager pm = getServer().getPluginManager();
	public static String prefix = "§8[§3§lBuild§8] §e";
	public static HashMap<Player, Boolean> allow = new HashMap<>();

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		loadConfig();
		registerCommands();
		cs.sendMessage(prefix + "Commands gelanden!");
		registerEvents();
		cs.sendMessage(prefix + "Events registriert!");
		loadWorlds();
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

		cs.sendMessage(prefix + "§aErfolgreich gelanden!");
		super.onEnable();
	}

	private List<String> worlds = new ArrayList<>();

	private void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		worlds = getConfig().getStringList("Worlds");
	}

	private void loadWorlds() {
		// TODO Auto-generated method stub

		for (String s : worlds) {

			Bukkit.createWorld(new WorldCreator(s));
			cs.sendMessage(main.prefix + "Die Welt " + s + " wurde geladen!");
		}
	}

	private void registerEvents() {
		// TODO Auto-generated method stub
		pm.registerEvents(new build(), this);
		pm.registerEvents(new changeWorld(), this);

		pm.registerEvents(new cmdprozess(), this);

		pm.registerEvents(new join(), this);

		pm.registerEvents(new Sign(), this);
		pm.registerEvents(new InterEVT(), this);
	}

	private void registerCommands() {
		// TODO Auto-generated method stub
		getCommand("architekt").setExecutor(new CMDArchitekt());
		getCommand("gamemode").setExecutor(new CMDGamemode());
		getCommand("gm").setExecutor(new CMDGamemode());
		getCommand("warp").setExecutor(new CMDWarp());
		getCommand("w").setExecutor(new CMDWarp());
		getCommand("setwarp").setExecutor(new CMDSetWarp());
		getCommand("builder").setExecutor(new CMDBuilder());
		getCommand("t").setExecutor(new CMDTime());
		getCommand("time").setExecutor(new CMDTime());
		getCommand("freeze").setExecutor(new CMDFreeze());
		getCommand("speed").setExecutor(new CMDSpeed());
		getCommand("s").setExecutor(new CMDSpeed());
		getCommand("lb").setExecutor(new CMDLetsBuild());
		getCommand("letsbuild").setExecutor(new CMDLetsBuild());
		getCommand("ce").setExecutor(new CMDCommandExecute());
		getCommand("commandexecutor").setExecutor(new CMDCommandExecute());
		getCommand("tp").setExecutor(new CMDTeleport());
		getCommand("sun").setExecutor(new CMDSun());
	}
}
