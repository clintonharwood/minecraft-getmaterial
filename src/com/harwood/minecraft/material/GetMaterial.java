package com.harwood.minecraft.material;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * A mod that will insert into a player's inventory x amount of material requested.
 * @author charwood
 */
public class GetMaterial extends JavaPlugin {
	
	public static Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onLoad() {
		log.info("[Get Material] loaded.");
	}
	
	@Override
	public void onEnable() {
		log.info("[Get Material] starting up.");
	}
	
	@Override
	public void onDisable() {
		log.info("[Get Material] stopping.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if (commandLabel.equalsIgnoreCase("getmaterial")) {
			if (sender instanceof Player) {
				
				Player player = (Player) sender;
				String materialName = null;
				int amount = 0;
				
				if (args.length == 2) {
					materialName = args[0];
					amount = Integer.parseInt(args[1]);
				} else {
					player.sendMessage("usage /getmaterial material_name amount\ne.g. /getmaterial dirt 10");
					return true;
				}

				Material material = null;
				try {
				   // may throw an IllegalArgumentException if this enum type has no constant with the specified name	
				   material = Material.valueOf(materialName.toUpperCase());
				   
				} catch (IllegalArgumentException ex) {
					log.info("Invalid material name entered");
				}
				
				if (material == null) {
					player.sendMessage("Invalid material name, refer to documentation for valid material names");
				} else {
					player.getInventory().addItem(new ItemStack(material, amount));
					player.sendMessage("Material: " + materialName + "\nAmount: " + amount + " added to inventory");
				}

				return true;
			}
		}

		return false;
	}
}