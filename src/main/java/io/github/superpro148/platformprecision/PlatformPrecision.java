package io.github.superpro148.platformprecision;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.Blocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

public class PlatformPrecision implements ClientModInitializer {

    private static KeyBinding positionKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.platformprecision.align_position",InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F7, "category.platformprecision.platformprecision"));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (positionKeyBind.wasPressed()) {
                boolean standOnBlock = client.world.getBlockState(client.player.getBlockPos().add(0, -1, 0)).getBlock() != Blocks.AIR;
                float yaw = MathHelper.wrapDegrees(client.player.getHeadYaw());
                if (standOnBlock) {
                    client.player.setPitch(84);
                    if (yaw > -45 && yaw < 45) {
                        client.player.setYaw(0);
                        client.player.setPos(client.player.getBlockX() + 0.5, client.player.getY(), client.player.getBlockZ() - 0.2);
                    } else if (yaw > 45 && yaw < 135) {
                        client.player.setYaw(90);
                        client.player.setPos(client.player.getBlockX() + 1.2, client.player.getY(), client.player.getBlockZ() + 0.5);
                    } else if (yaw < -135 || yaw > 135) {
                        client.player.setYaw(180);
                        client.player.setPos(client.player.getBlockX() + 0.5, client.player.getY(), client.player.getBlockZ() + 1.2);
                    } else if (yaw > -135 && yaw < -45) {
                        client.player.setYaw(270);
                        client.player.setPos(client.player.getBlockX() - 0.2, client.player.getY(), client.player.getBlockZ() + 0.5);
                    }
                }
            }
        });
    }
}
