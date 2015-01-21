package commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import archiV3.main;

public class CMDCommandExecute implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (cs instanceof ConsoleCommandSender) {
			cs.sendMessage(main.prefix + "Du kannst dies nicht nutzen!");
			return false;
		}
		if (args.length != 1) {
			cs.sendMessage(main.prefix + "Nutze /ce [command]");
			return false;
		}
		Player p = (Player) cs;
		ItemStack i = new ItemStack(Material.GLOWSTONE_DUST, 1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(args[0]);
		List<String> lore = new ArrayList<>();
		lore.add("§eInteragiere mit diesem Item um den Command auszuführen!");
		lore.add("§8Dieses Item führt folgendes aus: §c/" + args[0]);
		im.setLore(lore);
		i.setItemMeta(im);
		p.getInventory().addItem(i);
		p.sendMessage(main.prefix
				+ "Das Item wurde deinem Inventar hinzugefügt!");
		return false;
	}
}
