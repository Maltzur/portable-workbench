package com.mitchellmarsden.portableworkbench.client;

import com.mitchellmarsden.portableworkbench.helpers.CraftingScreenHelper;
import com.mitchellmarsden.portableworkbench.keyboard.KeyListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.UUID;

@Environment(EnvType.CLIENT)
public class ClientProxy {

    public static void init() {
        KeyListener.init();
    }

    public static void registerKeyBindings() {
        KeyListener.register("portableworkbench.key.open", "key.keyboard.v")
                .onKeyPressHandler(ClientProxy::openCraftingScreen);
    }

    private static void openCraftingScreen() {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        World world = minecraft.getServer().getOverworld();
        UUID playerUuid = minecraft.player.getUuid();
        if (world != null && playerUuid != null) {
            PlayerEntity player = world.getPlayerByUuid(playerUuid);
            CraftingScreenHelper.openCraftingScreen(world, player);
        }
    }
}
