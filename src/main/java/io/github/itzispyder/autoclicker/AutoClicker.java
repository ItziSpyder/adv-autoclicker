package io.github.itzispyder.autoclicker;

import io.github.itzispyder.autoclicker.ui.Config;
import io.github.itzispyder.autoclicker.ui.Events;
import io.github.itzispyder.improperui.ImproperUIAPI;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class AutoClicker implements ModInitializer, Global {

    public static final KeyBinding BIND = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "binds.autoclicker.menu",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_0,
            "binds.autoclicker"
    ));

    @Override
    public void onInitialize() {
        ImproperUIAPI.init(modId, AutoClicker.class, screens);

        Config.update();
        Events.distributeNoise(Config.leftCps, Config.leftChance, Events.noiseMapLeft);
        Events.distributeNoise(Config.rightCps, Config.rightChance, Events.noiseMapRight);

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            Config.update();
            Events.onTick();
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (BIND.wasPressed()) {
                ImproperUIAPI.parseAndRunFile(modId, "screen.ui");
            }
        });
    }
}
