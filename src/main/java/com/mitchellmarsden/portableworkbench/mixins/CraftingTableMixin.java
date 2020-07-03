package com.mitchellmarsden.portableworkbench.mixins;

import com.mitchellmarsden.portableworkbench.items.PortableWorkbenchItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.screen.CraftingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingScreenHandler.class)
public abstract class CraftingTableMixin {
    @Inject(at = @At("HEAD"), method = "canUse", cancellable = true)
    public void canUse(PlayerEntity player, CallbackInfoReturnable<Boolean> ci) {
        for (int i = 0; i < player.inventory.size(); i++) {
            Item item = player.inventory.getStack(i).getItem();
            if (item instanceof PortableWorkbenchItem) {
                ci.setReturnValue(true);
            }
        }
    }
}
