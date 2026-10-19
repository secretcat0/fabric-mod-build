package com.triper.gauntlet;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public final class ModItems {
    private ModItems() {}

    private static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(TriperGauntletMod.MOD_ID, name));
    }

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, itemKey(name), item);
    }

    public static final Item TRIPER_GAUNTLET = register(
            "triper_gauntlet",
            new GauntletItem(new Item.Properties().setId(itemKey("triper_gauntlet")).stacksTo(1))
    );

    public static final Item BLACK_SUBSTANCE = register(
            "black_substance",
            new Item(new Item.Properties().setId(itemKey("black_substance")))
    );

    public static void initialize() {}
}
