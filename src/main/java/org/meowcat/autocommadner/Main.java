package org.meowcat.autocommadner;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main {
	public static final String MODID = "autocommander";
	public static final String NAME = "Auto Commander";
	public static final String VERSION = "1.0.0";
	public static Logger logger;
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		new ConfigLoader(event);
		logger = event.getModLog();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		new JoinEvent();
		MinecraftForge.EVENT_BUS.register(this);
		logger.info("Mod inited.");
	}
}
