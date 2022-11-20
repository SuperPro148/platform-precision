package io.github.superpro148.platformprecision;

import static io.github.superpro148.platformprecision.config.PlatformPrecisionConfig.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.Blocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

public class PlatformPrecision implements ClientModInitializer {

    private static final KeyBinding positionKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.platformprecision.align_position",InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F7, "category.platformprecision.platformprecision"));

    @Override
    public void onInitializeClient() {
        CONFIG_LIST.readConfig();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (positionKeyBind.wasPressed()) {
                boolean standOnBlock = client.world.getBlockState(client.player.getBlockPos().add(0, -1, 0)).getBlock() != Blocks.AIR;
                float yaw = MathHelper.wrapDegrees(client.player.getHeadYaw());
                if (standOnBlock || !CHECK_VOID.getValue()) {
                    if (ALIGN_PITCH.getValue()) client.player.setPitch(ALIGN_PITCH_TO.getValue() - 90);
                    if (ALIGN_YAW.getValue()) {
                        for (float i = -180; i < 180; i += YAW_INTERVAL.getValue()) {
                            alignYaw(client.player, yaw, i, ((float) YAW_INTERVAL.getValue()) / 2);
                        }
                    }
                    if (MOVE_POSITION.getValue() && YAW_INTERVAL.getValue() == 90) {
                        movePos(client.player, yaw);
                    }
                }
            }
        });
    }

    private void alignYaw(PlayerEntity player, float yaw, float target, float range) {
        if (yaw > 180 - range) yaw -= 360;
        if (yaw > target - range && yaw < target + range) {
            player.setYaw(target);
        }
    }

    private void movePos(PlayerEntity player, float yaw) {
        if (yaw > -45 && yaw < 45) {
            player.setPos(player.getBlockX() + 0.5, player.getY(), player.getBlockZ() - 0.2);
        } else if (yaw > 45 && yaw < 135) {
            player.setPos(player.getBlockX() + 1.2, player.getY(), player.getBlockZ() + 0.5);
        } else if (yaw < -135 || yaw > 135) {
            player.setPos(player.getBlockX() + 0.5, player.getY(), player.getBlockZ() + 1.2);
        } else if (yaw > -135 && yaw < -45) {
            player.setPos(player.getBlockX() - 0.2, player.getY(), player.getBlockZ() + 0.5);
        }
    }
}
