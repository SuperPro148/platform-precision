package io.github.superpro148.platformprecision.config;

import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.controllers.BooleanController;
import dev.isxander.yacl.gui.controllers.slider.FloatSliderController;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import io.github.superpro148.configlib148.ConfigList;
import io.github.superpro148.configlib148.ConfigOption;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class PlatformPrecisionConfig {

    public static ConfigList CONFIG_LIST = new ConfigList("platformprecision");

    public static ConfigOption<Boolean> ALIGN_PITCH = CONFIG_LIST.register(new ConfigOption<>("align_pitch", Boolean.class, true));
    public static ConfigOption<Float> ALIGN_PITCH_TO = CONFIG_LIST.register(new ConfigOption<>("align_pitch_to", Float.class, 174.0f));
    public static ConfigOption<Boolean> ALIGN_YAW = CONFIG_LIST.register(new ConfigOption<>("align_yaw", Boolean.class, true));
    public static ConfigOption<Integer> YAW_INTERVAL = CONFIG_LIST.register(new ConfigOption<>("yaw_interval", Integer.class, 90));
    public static ConfigOption<Boolean> MOVE_POSITION = CONFIG_LIST.register(new ConfigOption<>("move_position", Boolean.class, true));
    public static ConfigOption<Boolean> CHECK_VOID = CONFIG_LIST.register(new ConfigOption<>("check_void", Boolean.class, true));

    public static void save() {
        CONFIG_LIST.saveConfig();
    }

    public static Screen createGui(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("category.platformprecision.platformprecision"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("category.platformprecision.platformprecision"))
                        .option(Option.createBuilder(Boolean.class)
                                .name(Text.translatable("option.platformprecision.align_pitch"))
                                .tooltip(Text.translatable("tooltip.platformprecision.align_pitch"))
                                .binding(
                                        true,
                                        () -> ALIGN_PITCH.getValue(),
                                        (newValue) -> ALIGN_PITCH.setValue(newValue)
                                )
                                .controller(BooleanController::new)
                                .build())
                        .option(Option.createBuilder(Float.class)
                                .name(Text.translatable("option.platformprecision.align_pitch_to"))
                                .tooltip(Text.translatable("tooltip.platformprecision.align_pitch_to"))
                                .binding(
                                        174.0f,
                                        () -> ALIGN_PITCH_TO.getValue(),
                                        (newValue) -> ALIGN_PITCH_TO.setValue(newValue)
                                )
                                .controller(option -> new FloatSliderController(option, 0, 180, 1))
                                .build())
                        .option(Option.createBuilder(Boolean.class)
                                .name(Text.translatable("option.platformprecision.align_yaw"))
                                .tooltip(Text.translatable("tooltip.platformprecision.align_yaw"))
                                .binding(
                                        true,
                                        () -> ALIGN_YAW.getValue(),
                                        (newValue) -> ALIGN_YAW.setValue(newValue)
                                )
                                .controller(BooleanController::new)
                                .build())
                        .option(Option.createBuilder(Integer.class)
                                .name(Text.translatable("option.platformprecision.yaw_interval"))
                                .tooltip(Text.translatable("tooltip.platformprecision.yaw_interval"))
                                .binding(
                                        90,
                                        () -> YAW_INTERVAL.getValue(),
                                        (newValue) -> YAW_INTERVAL.setValue(newValue)
                                )
                                .controller(option -> new IntegerSliderController(option, 1, 180, 1))
                                .build())
                        .option(Option.createBuilder(Boolean.class)
                                .name(Text.translatable("option.platformprecision.move_position"))
                                .tooltip(Text.translatable("tooltip.platformprecision.move_position"))
                                .binding(
                                        true,
                                        () -> MOVE_POSITION.getValue(),
                                        (newValue) -> MOVE_POSITION.setValue(newValue)
                                )
                                .controller(BooleanController::new)
                                .build())
                        .option(Option.createBuilder(Boolean.class)
                                .name(Text.translatable("option.platformprecision.check_void"))
                                .tooltip(Text.translatable("tooltip.platformprecision.check_void"))
                                .binding(
                                        true,
                                        () -> CHECK_VOID.getValue(),
                                        (newValue) -> CHECK_VOID.setValue(newValue)
                                )
                                .controller(BooleanController::new)
                                .build())
                        .build())
                .save(PlatformPrecisionConfig::save)
                .build()
                .generateScreen(parent);
    }
}
