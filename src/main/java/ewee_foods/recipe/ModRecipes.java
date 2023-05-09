package ewee_foods.recipe;

import ewee_foods.Ewee_FoodsMain;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Ewee_FoodsMain.MOD_ID);

    public static final RegistryObject<RecipeSerializer<CrockpotRecipe>> CROCKPOT_SERIALIZER =
            SERIALIZERS.register("crockpot", () -> CrockpotRecipe.Serializer.INSTANCE);
    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
