package ewee_foods.item;

import ewee_foods.Ewee_FoodsMain;
import ewee_foods.item.custom.RevealItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Ewee_FoodsMain.MOD_ID);
    public static final RegistryObject<Item> SALT = ITEMS.register("salt",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> FLOUR = ITEMS.register("flour",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> FLOUR_BAG = ITEMS.register("flour_bag",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> CURED_MEAT = ITEMS.register("cured_meat",
            () -> new RevealItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.CURED_MEAT)));
    public static final RegistryObject<Item> CHICKEN_FRIED_STEAK = ITEMS.register("chicken_fried_steak",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.CHICKEN_FRIED_STEAK)));
    public static final RegistryObject<Item> FRESH_LOAF = ITEMS.register("fresh_loaf",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.FRESH_LOAF)));
    public static final RegistryObject<Item> GLAZED_RABBIT = ITEMS.register("glazed_rabbit",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.GLAZED_RABBIT)));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
