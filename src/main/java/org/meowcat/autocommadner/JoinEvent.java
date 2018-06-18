package org.meowcat.autocommadner;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Properties;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class JoinEvent {
	private Properties prop = new Properties();

	public JoinEvent() {
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void PlayerJoinGame(RenderGameOverlayEvent.Pre event) throws IOException {
	    try{
	        Class mc = Class.forName(Minecraft.class.getName());
	        Method[] methods = mc.getMethods();
	        for (Method method : methods){
                Main.logger.info(method.getName());
            }
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        }
		File file = new File("config/user.properties");
		if (!file.exists()) {
            Minecraft.getMinecraft().shutdown();
		}
        InputStream in = new BufferedInputStream(new FileInputStream("config/user.properties"));
        prop.load(in);
        String password = prop.getProperty("Password");
        String registered = prop.getProperty("IsRegistered");
        if (!registered.equals("true")) {
           Minecraft.getMinecraft().player.sendChatMessage("/" + ConfigLoader.RegisterCommand + " " + password + " " + password);
        } else {
           Minecraft.getMinecraft().player.sendChatMessage("/" + ConfigLoader.LoginCommand + " " + password);
        }
	}
}
