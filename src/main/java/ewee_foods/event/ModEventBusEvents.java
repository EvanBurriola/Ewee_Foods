package ewee_foods.event;

import ewee_foods.Ewee_FoodsMain;
import ewee_foods.event.loot.CookBookShipwreckModifier;
import ewee_foods.recipe.CrockpotRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;


@Mod.EventBusSubscriber(modid = Ewee_FoodsMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, CrockpotRecipe.Type.ID, CrockpotRecipe.Type.INSTANCE);
    }
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new CookBookShipwreckModifier.Serializer().setRegistryName
                        (new ResourceLocation(Ewee_FoodsMain.MOD_ID,"cook_book_shipwreck_modifier"))
        );
    }
}
