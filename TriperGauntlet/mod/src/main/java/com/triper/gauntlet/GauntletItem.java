package com.triper.gauntlet;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

/**
 * Prototype controls: right-click with a Nether Star in the offhand to load one charge;
 * right-click with an empty/non-star offhand to consume one charge. No projectile is spawned.
 */
public final class GauntletItem extends Item {
    private static final String CHARGES = "TriperCharges";
    private static final int MAX_CHARGES = 64;

    public GauntletItem(Properties properties) {
        super(properties);
    }

    private static int getCharges(ItemStack stack) {
        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        if (data == null) return 0;
        CompoundTag tag = data.copyTag();
        return Math.max(0, Math.min(MAX_CHARGES, tag.getInt(CHARGES).orElse(0)));
    }

    private static void setCharges(ItemStack stack, int charges) {
        CompoundTag tag = new CompoundTag();
        CustomData old = stack.get(DataComponents.CUSTOM_DATA);
        if (old != null) tag = old.copyTag();
        tag.putInt(CHARGES, Math.max(0, Math.min(MAX_CHARGES, charges)));
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack gauntlet = player.getItemInHand(hand);
        ItemStack offhand = player.getOffhandItem();

        if (level.isClientSide()) return InteractionResult.SUCCESS;

        if (offhand.is(Items.NETHER_STAR)) {
            int charges = getCharges(gauntlet);
            if (charges >= MAX_CHARGES) return InteractionResult.FAIL;
            if (!player.getAbilities().instabuild) offhand.shrink(1);
            setCharges(gauntlet, charges + 1);
            player.getCooldowns().addCooldown(this, 5);
            return InteractionResult.SUCCESS;
        }

        int charges = getCharges(gauntlet);
        if (charges <= 0) return InteractionResult.FAIL;
        setCharges(gauntlet, charges - 1);
        player.getCooldowns().addCooldown(this, 10);
        // TODO: trigger teleport effect here. This prototype deliberately spawns no projectile.
        return InteractionResult.SUCCESS;
    }
}
