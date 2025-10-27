package net.mobilelize.netprodis.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigMenu {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("Network Protocol Disconnect"))
                .setTransparentBackground(true)
                .setSavingRunnable(ConfigMenu::saveConfig);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory global = builder.getOrCreateCategory(Text.literal("Global"));

        global.addEntry(entryBuilder.startBooleanToggle(Text.literal("Stop Network Protocol Errors"), true)
                .setDefaultValue(true)
                .setTooltip(Text.literal("Enables the mod."))
                .setSaveConsumer(newValue -> ConfigManager.configData.modEnabled = newValue)
                .build());

        global.addEntry(entryBuilder.startBooleanToggle(Text.literal("Output Logs"), true)
                .setDefaultValue(true)
                .setTooltip(Text.literal("Sets if it should logs errors attempts."))
                .setSaveConsumer(newValue -> ConfigManager.configData.showLogs = newValue)
                .build());

        return builder.build();
    }

    public static void saveConfig(){
        ConfigManager.saveConfig();
    }
}
