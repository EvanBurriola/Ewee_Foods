package ewee_foods.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties ENCHANTED_GOLDEN_APPLE = (new FoodProperties.Builder()).nutrition(4)
            .saturationMod(1.2F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3), 1.0F)
            .alwaysEat()
            .build();
    public static final FoodProperties COOKED_BEEF = (new FoodProperties.Builder())
            .nutrition(8)
            .saturationMod(0.8F)
            .meat()
            .build();
    public static final FoodProperties CURED_MEAT = (new FoodProperties.Builder())
            .nutrition(4)
            .saturationMod(1.5F)
            .meat()
            .fast()
            .build();


}
