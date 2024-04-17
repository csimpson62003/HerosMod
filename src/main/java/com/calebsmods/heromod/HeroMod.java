package com.calebsmods.heromod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.calebsmods.heromod.Items.ModItemGroup;
import com.calebsmods.heromod.Items.ModItems;

import net.fabricmc.api.ModInitializer;
public class HeroMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	public static final String MODID = "heromod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		ModItems.RegisterModItems();
		ModItemGroup.RegisterModItems(); // Use the imported class
		
		LOGGER.info("Hello Fabric world!");
	}
}