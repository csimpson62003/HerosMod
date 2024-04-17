package com.calebsmods.heromod.Items.SpecialItems;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlashEmblem extends Item {

    private boolean isActive = false;
    private int duration = 0;

    public FlashEmblem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 100));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 100, 1));
        user.getStackInHand(hand).damage(1, user,
            playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        isActive = true;
        duration = 300; // Set the duration to match the speed effect
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    private final Map<BlockPos, Integer> iceBlocks = new HashMap<>();

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (isActive && entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            BlockPos pos = player.getBlockPos().down(); // Get the block beneath the player
            BlockState state = world.getBlockState(pos);
            if (state.getBlock() == Blocks.WATER) { // If the block is water
                world.setBlockState(pos, Blocks.ICE.getDefaultState()); // Change it to ice
                iceBlocks.put(pos, duration); // Store the position of the ice block, with the same duration as the speed effect
            }
        }

        // Decrease the delay for all stored ice blocks
        iceBlocks.replaceAll((pos, delay) -> delay - 1);

        // Turn back into water any ice blocks whose delay has reached 0
        iceBlocks.entrySet().removeIf(entry -> {
            BlockPos pos = entry.getKey();
            if (entry.getValue() <= 0 && world.getBlockState(pos).getBlock() == Blocks.ICE) {
                world.setBlockState(pos, Blocks.WATER.getDefaultState());
                return true;
            }
            return false;
        });

        // Decrease the duration and deactivate the item if the duration has reached 0
        if (--duration <= 0) {
            isActive = false;
        }
    }
}