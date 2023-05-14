package ewee_foods.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class RevealItem extends Item {
    private static final double RANGE = 20;
    private static final Vec3 RADIUS = new Vec3(RANGE,RANGE,RANGE);
    public RevealItem(Properties pProperties) {
        super(pProperties);
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        revealEntities(pLevel,pLivingEntity);
        return this.isEdible() ? pLivingEntity.eat(pLevel, pStack) : pStack;
    }

    //Reveals living entities in a bounding box around the entity that ate this food.
    public void revealEntities(Level pLevel, LivingEntity pLivingEntity){
        Vec3 topCorner = pLivingEntity.position().add(RADIUS);
        Vec3 bottomCorner = pLivingEntity.position().subtract(RADIUS);
        AABB boundingBox = new AABB(topCorner,bottomCorner);
        List<Entity> entities = pLevel.getEntities(null,boundingBox);
        for(Entity e : entities){
            if(e instanceof LivingEntity && !(e instanceof Player))
                ((LivingEntity) e).addEffect(new MobEffectInstance(MobEffects.GLOWING, 600,1));
        }
    }
}
