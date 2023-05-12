package ewee_foods.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    //600T = 30s
    //1200T = 1 min
    //6000T = 5 min
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
    public static final FoodProperties CHICKEN_FRIED_STEAK = (new FoodProperties.Builder())
            .nutrition(10)
            .saturationMod(.25F)
            .meat()
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 0), 1.0F)
            .build();
    public static final FoodProperties FRESH_LOAF = (new FoodProperties.Builder())
            .nutrition(5)
            .saturationMod(.40F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 0), 1.0F)
            .build();

    public static final FoodProperties GLAZED_RABBIT = (new FoodProperties.Builder())
            .nutrition(8)
            .saturationMod(.25F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 800, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.WEAKNESS, 800, 0), 0.5F)
            .build();


}
