package com.mitchellmarsden.portableworkbench.helpers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CraftingScreenHelper {
    private static final Text TITLE = new TranslatableText("container.crafting");

    public static void openCraftingScreen(World world, PlayerEntity player) {
        if (!world.isClient()) {
            player.openHandledScreen(createScreenHandlerFactory(world, player.getBlockPos()));
            player.incrementStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
        }
    }

    private static NamedScreenHandlerFactory createScreenHandlerFactory(World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) ->
                new CraftingScreenHandler(i, playerInventory, ScreenHandlerContext.create(world, pos)), TITLE);
    }
}
