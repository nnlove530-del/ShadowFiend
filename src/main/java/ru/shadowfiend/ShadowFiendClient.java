package ru.shadowfiend;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class ShadowFiendClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_LAYER =
        new EntityModelLayer(Identifier.of(ShadowFiendMod.MOD_ID, "shadow_fiend"), "main");

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(
            MODEL_LAYER, ShadowFiendModel::getTexturedModelData
        );

        EntityRendererRegistry.register(ShadowFiendMod.SHADOW_FIEND, context ->
            new ZombieEntityRenderer(
                context,
                MODEL_LAYER,
                EntityModelLayers.ZOMBIE_INNER_ARMOR,
                EntityModelLayers.ZOMBIE_OUTER_ARMOR,
                EntityModelLayers.ZOMBIE_INNER_ARMOR,
                EntityModelLayers.ZOMBIE_OUTER_ARMOR,
                EntityModelLayers.ZOMBIE
            ) {
                @Override
                public Identifier getTexture(
                    net.minecraft.client.render.entity.state.ZombieEntityRenderState state
                ) {
                    return Identifier.of(ShadowFiendMod.MOD_ID, "textures/entity/shadow_fiend.png");
                }
            }
        );
    }
}
