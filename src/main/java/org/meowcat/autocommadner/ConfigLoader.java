package org.meowcat.autocommadner;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigLoader {
	public static String LoginCommand;
	public static String RegisterCommand;

	public ConfigLoader(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		LoginCommand = config.getString("Command", "Login", "login", "Command to login.");
		RegisterCommand = config.getString("Command", "Register", "register", "Command to register.");
		config.save();
	}

}
