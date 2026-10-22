package ru.shadowfiend;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ShadowFiendMod implements ModInitializer {
    public static final String MOD_ID = "shadowfiend";

    public static final EntityType<ShadowFiendEntity> SHADOW_FIEND = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier.of(MOD_ID, "shadow_fiend"),
        EntityType.Builder.create(ShadowFiendEntity::new, SpawnGroup.MONSTER)
            .dimensions(0.8f, 2.5f)
            .maxTrackingRange(8)
            .build()
    );

    public static final Item SHADOW_FIEND_SPAWN_EGG = Registry.register(
        Registries.ITEM,
        Identifier.of(MOD_ID, "shadow_fiend_spawn_egg"),
        new SpawnEggItem(SHADOW_FIEND, new Item.Settings())
    );

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS)
            .register(entries -> entries.add(SHADOW_FIEND_SPAWN_EGG));
    }
}
