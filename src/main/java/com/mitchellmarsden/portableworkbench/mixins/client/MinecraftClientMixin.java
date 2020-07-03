package com.mitchellmarsden.portableworkbench.mixins.client;

import com.mitchellmarsden.portableworkbench.client.ClientProxy;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(at = @At("RETURN"), method = "<init>")
    private void init(CallbackInfo ci) {
        ClientProxy.init();
    }
}
