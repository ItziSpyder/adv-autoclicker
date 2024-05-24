package io.github.itzispyder.autoclicker.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MinecraftClient.class)
public interface AccessorMinecraftClient {

    @Mutable
    @Invoker("doAttack")
    boolean leftClick();

    @Mutable
    @Invoker("doItemUse")
    void rightClick();

    @Mutable
    @Invoker("doItemPick")
    void middleClick();
}
