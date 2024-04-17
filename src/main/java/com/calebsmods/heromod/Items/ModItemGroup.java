package com.calebsmods.heromod.Items;



import com.calebsmods.heromod.HeroMod;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
public class ModItemGroup {
    public static final ItemGroup HERO_GROUP = Registry.register(Registries.ITEM_GROUP, 
    new Identifier(HeroMod.MODID, "hero_group"), FabricItemGroup.builder().displayName(Text.translatable("itemGroup.hero_group"))
    .icon(() -> new ItemStack(ModItems.FLASH_EMBLEM)).entries((displayContext, entries) -> {
        entries.add(ModItems.FLASH_EMBLEM);
    }).build());



    public static void RegisterModItems(){
        HeroMod.LOGGER.info("Registering Mod Items");
    }
}