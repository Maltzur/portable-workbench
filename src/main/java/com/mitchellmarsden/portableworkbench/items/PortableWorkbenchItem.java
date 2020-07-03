package com.mitchellmarsden.portableworkbench.items;

import com.mitchellmarsden.portableworkbench.helpers.CraftingScreenHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PortableWorkbenchItem extends Item {

    public PortableWorkbenchItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        CraftingScreenHelper.openCraftingScreen(world, player);
        return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }
}
