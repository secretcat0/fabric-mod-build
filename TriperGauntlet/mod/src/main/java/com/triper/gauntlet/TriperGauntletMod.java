package com.triper.gauntlet;

import net.fabricmc.api.ModInitializer;

public final class TriperGauntletMod implements ModInitializer {
    public static final String MOD_ID = "triper";

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModBlocks.initialize();
    }
}
