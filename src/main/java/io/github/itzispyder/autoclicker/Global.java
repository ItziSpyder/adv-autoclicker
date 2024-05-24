package io.github.itzispyder.autoclicker;

import net.minecraft.client.MinecraftClient;

public interface Global {

    MinecraftClient mc = MinecraftClient.getInstance();
    String modId = "autoclicker";
    String[] screens = {
        "assets/autoclicker/improperui/screen.ui"
    };
}
