package ru.shadowfiend;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

/**
 * Shadow Fiend-inspired monster. It uses vanilla zombie movement and combat AI,
 * while the client renderer supplies the custom dark/red texture.
 */
public class ShadowFiendEntity extends ZombieEntity {
    public ShadowFiendEntity(EntityType<? extends ZombieEntity> type, World world) {
        super(type, world);
        this.setCanPickUpLoot(false);
    }
}
