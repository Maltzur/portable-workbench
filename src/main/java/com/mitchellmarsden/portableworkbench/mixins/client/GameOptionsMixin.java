package com.mitchellmarsden.portableworkbench.mixins.client;

import com.mitchellmarsden.portableworkbench.client.ClientProxy;
import com.mitchellmarsden.portableworkbench.keyboard.KeyListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
    @Mutable
    @Final
    @Shadow
    public KeyBinding[] keysAll;

    @Shadow
    public abstract void load();

    @Inject(at = @At("RETURN"), method = "<init>")
    private void init(CallbackInfo ci) {
        ClientProxy.registerKeyBindings();
        keysAll = ArrayUtils.addAll(keysAll, KeyListener.keyBindings());
        this.load();
    }
}
