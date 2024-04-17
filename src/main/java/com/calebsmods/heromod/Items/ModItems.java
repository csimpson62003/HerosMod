package com.calebsmods.heromod.Items;


import com.calebsmods.heromod.HeroMod;
import com.calebsmods.heromod.Items.SpecialItems.FlashEmblem;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems{

    public static final Item FLASH_EMBLEM = registerItem("flash_emblem", new FlashEmblem(new Item.Settings().maxDamage(10)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(HeroMod.MODID, name), item);
    }

    public static void RegisterModItems(){
        HeroMod.LOGGER.info("Registering Mod Items");
    }
}