package net.linkedto.stack_config;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class Config {
    private static Path configPath;
    private static Map<String, Integer> rawEntries = new LinkedHashMap<>();
    private static Map<Item, Integer> resolvedSizes = new HashMap<>();

    public static void setConfigDir(Path dir) {
        configPath = dir.resolve("stack_config.json");
    }

    public static int getConfiguredSize(Item item) {
        if (resolvedSizes.isEmpty() && !rawEntries.isEmpty()) {
            resolveEntries();
        }
        return resolvedSizes.getOrDefault(item, -1);
    }

    public static void load() {
        if (configPath == null) {
            throw new IllegalStateException("Config directory not set. Call setConfigDir before init.");
        }
        rawEntries = new LinkedHashMap<>();
        if (Files.exists(configPath)) {
            try (var reader = Files.newBufferedReader(configPath)) {
                Type type = new TypeToken<Map<String, Double>>(){}.getType();
                Map<String, Double> loaded = new com.google.gson.Gson().fromJson(reader, type);
                if (loaded != null) {
                    for (var entry : loaded.entrySet()) {
                        rawEntries.put(entry.getKey(), entry.getValue().intValue());
                    }
                }
            } catch (Exception e) {
                System.err.println("[Stack Config] Error loading config: " + e.getMessage());
            }
        }
        if (rawEntries.isEmpty()) {
            setDefaults();
            save();
        }
    }

    private static void setDefaults() {
        for (var entry : SIZE_16_ITEMS) rawEntries.put(entry, 16);
        for (var entry : SIZE_64_ITEMS) rawEntries.put(entry, 64);
    }

    private static void resolveEntries() {
        resolvedSizes = new HashMap<>();
        var registry = BuiltInRegistries.ITEM;
        for (var entry : rawEntries.entrySet()) {
            String key = entry.getKey();
            int size = entry.getValue();
            if (key.startsWith("#")) {
                ResourceLocation tagId = ResourceLocation.tryParse(key.substring(1));
                if (tagId != null) {
                    TagKey<Item> tagKey = TagKey.create(registry.key(), tagId);
                    registry.getTag(tagKey).ifPresent(tag -> {
                        for (Holder<Item> holder : tag) {
                            resolvedSizes.put(holder.value(), size);
                        }
                    });
                }
            } else {
                ResourceLocation id = ResourceLocation.tryParse(key);
                if (id != null) {
                    Item item = registry.get(id);
                    if (item != null) {
                        resolvedSizes.put(item, size);
                    }
                }
            }
        }
    }

    public static void save() {
        try {
            Files.createDirectories(configPath.getParent());
            try (var writer = Files.newBufferedWriter(configPath)) {
                new GsonBuilder().setPrettyPrinting().create().toJson(rawEntries, writer);
            }
        } catch (IOException e) {
            System.err.println("[Stack Config] Error saving config: " + e.getMessage());
        }
    }

    private static final List<String> SIZE_16_ITEMS = List.of(
        "minecraft:potion",
        "minecraft:lingering_potion",
        "minecraft:splash_potion",
        "minecraft:saddle",
        "#minecraft:boats",
        "#minecraft:beds",
        "#minecraft:music_discs",
        "minecraft:enchanted_book",
        "minecraft:bucket",
        "minecraft:water_bucket",
        "minecraft:lava_bucket",
        "minecraft:milk_bucket",
        "minecraft:beetroot_soup",
        "minecraft:suspicious_stew",
        "minecraft:mushroom_stew",
        "minecraft:rabbit_stew",
        "minecraft:leather_horse_armor",
        "minecraft:iron_horse_armor",
        "minecraft:golden_horse_armor",
        "minecraft:diamond_horse_armor",
        "minecraft:cake",
        "minecraft:chest_minecart",
        "minecraft:command_block_minecart",
        "minecraft:furnace_minecart",
        "minecraft:hopper_minecart",
        "minecraft:minecart",
        "minecraft:tnt_minecart"
    );

    private static final List<String> SIZE_64_ITEMS = List.of(
        "minecraft:armor_stand",
        "minecraft:decorated_pot",
        "minecraft:ender_pearl",
        "#minecraft:banners",
        "minecraft:egg",
        "minecraft:snowball",
        "minecraft:honey_bottle",
        "minecraft:creeper_banner_pattern",
        "minecraft:flower_banner_pattern",
        "minecraft:globe_banner_pattern",
        "minecraft:mojang_banner_pattern",
        "minecraft:piglin_banner_pattern",
        "minecraft:skull_banner_pattern",
        "#minecraft:signs",
        "minecraft:writable_book"
    );
}
