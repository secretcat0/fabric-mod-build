package com.triper.gauntlet;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class ModBlocks {
    private ModBlocks() {}

    public static final ResourceKey<Block> BLACK_SUBSTANCE_BLOCK_KEY = ResourceKey.create(
            Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(TriperGauntletMod.MOD_ID, "black_substance_block"));

    public static final Block BLACK_SUBSTANCE_BLOCK = Registry.register(
            BuiltInRegistries.BLOCK,
            BLACK_SUBSTANCE_BLOCK_KEY,
            new Block(BlockBehaviour.Properties.of().setId(BLACK_SUBSTANCE_BLOCK_KEY).strength(5.0f, 6.0f)));

    public static void initialize() {
        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(TriperGauntletMod.MOD_ID, "black_substance_block"));
        Registry.register(BuiltInRegistries.ITEM, itemKey,
                new BlockItem(BLACK_SUBSTANCE_BLOCK, new Item.Properties().setId(itemKey)));
    }
}
