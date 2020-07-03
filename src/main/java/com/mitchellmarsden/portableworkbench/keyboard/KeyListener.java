package com.mitchellmarsden.portableworkbench.keyboard;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.HashSet;
import java.util.Set;

@Environment(EnvType.CLIENT)
public class KeyListener {
    private static final MinecraftClient minecraft = MinecraftClient.getInstance();
    private static long mainWindowHandle;
    private static final Set<Key> keys = new HashSet<>();
    private static final Set<CustomKeyBinding> keyBindings = new HashSet<>();
    public static final String Category = "Portable Workbench";

    public static void init() {
        mainWindowHandle = minecraft.getWindow().getHandle();
        GLFW.glfwSetKeyCallback(mainWindowHandle, KeyListener::onKeyEvent);
    }

    public static Key register(String description, String keyName) {
        InputUtil.Key input = InputUtil.fromTranslationKey(keyName);
        CustomKeyBinding keyBinding = new CustomKeyBinding(description, input.getCode());
        KeyBindingHelper.registerKeyBinding(keyBinding);
        keyBindings.add(keyBinding);

        Key key = keyBinding.getKey();
        keys.add(key);
        return key;
    }

    private static void onKeyEvent(long windowHandle, int keyCode, int scanCode, int action, int modifiers) {
        boolean isPressed = action > 0;
        if (windowHandle == mainWindowHandle &&
                minecraft.currentScreen == null &&
                keyCode != -1 &&
                !InputUtil.isKeyPressed(mainWindowHandle, 292) &&
                handleKeyEvent(keyCode, isPressed))
            return;
        minecraft.keyboard.onKey(windowHandle, keyCode, scanCode, action, modifiers);
    }

    private static boolean handleKeyEvent(int keyCode, boolean isPressed) {
        for (Key key : keys) {
            if (key.handleKeyEvent(keyCode, isPressed)) {
                return true;
            }
        }
        return false;
    }

    public static KeyBinding[] keyBindings() {
        return keyBindings.toArray(new KeyBinding[0]);
    }
}