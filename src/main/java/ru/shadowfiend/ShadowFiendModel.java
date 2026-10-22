package ru.shadowfiend;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;

/**
 * Custom humanoid model based on Minecraft's zombie rig, with a crown,
 * long curved-looking horns, shoulder spikes and a small idle animation.
 */
public class ShadowFiendModel extends ZombieEntityModel<ZombieEntityRenderState> {
    private final ModelPart leftHorn;
    private final ModelPart rightHorn;
    private final ModelPart crown;

    public ShadowFiendModel(ModelPart root) {
        super(root);
        ModelPart head = root.getChild("head");
        this.leftHorn = head.getChild("left_horn");
        this.rightHorn = head.getChild("right_horn");
        this.crown = head.getChild("crown");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = BipedEntityModel.getModelData(Dilation.NONE, 0.0F);
        ModelPartData root = data.getRoot();
        ModelPartData head = root.getChild("head");

        // Horns grow upward and outward from both sides of the head.
        head.addChild("left_horn",
            ModelPartBuilder.create()
                .uv(0, 32).cuboid(-7.0F, -10.0F, -1.0F, 3.0F, 5.0F, 3.0F)
                .uv(0, 40).cuboid(-9.0F, -14.0F, -1.0F, 3.0F, 5.0F, 3.0F)
                .uv(0, 48).cuboid(-10.0F, -17.0F, -0.5F, 2.0F, 4.0F, 2.0F),
            ModelTransform.NONE);

        head.addChild("right_horn",
            ModelPartBuilder.create()
                .uv(12, 32).cuboid(4.0F, -10.0F, -1.0F, 3.0F, 5.0F, 3.0F)
                .uv(12, 40).cuboid(6.0F, -14.0F, -1.0F, 3.0F, 5.0F, 3.0F)
                .uv(12, 48).cuboid(8.0F, -17.0F, -0.5F, 2.0F, 4.0F, 2.0F),
            ModelTransform.NONE);

        head.addChild("crown",
            ModelPartBuilder.create()
                .uv(24, 32).cuboid(-4.0F, -9.0F, -3.0F, 8.0F, 2.0F, 6.0F)
                .uv(24, 40).cuboid(-2.0F, -11.0F, -2.0F, 4.0F, 2.0F, 4.0F),
            ModelTransform.NONE);

        ModelPartData body = root.getChild("body");
        body.addChild("left_shoulder_spike",
            ModelPartBuilder.create().uv(36, 32)
                .cuboid(0.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F),
            ModelTransform.pivot(3.0F, -1.0F, 0.0F));
        body.addChild("right_shoulder_spike",
            ModelPartBuilder.create().uv(36, 40)
                .cuboid(-4.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F),
            ModelTransform.pivot(-3.0F, -1.0F, 0.0F));

        return TexturedModelData.of(data, 64, 64);
    }

    @Override
    public void setAngles(ZombieEntityRenderState state) {
        super.setAngles(state);
        float t = state.age * 0.08F;
        this.leftHorn.roll = -0.10F + (float)Math.sin(t) * 0.035F;
        this.rightHorn.roll = 0.10F - (float)Math.sin(t) * 0.035F;
        this.crown.yaw = (float)Math.sin(t * 0.7F) * 0.025F;
    }
}
