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
            () -> new RevealItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.CURED_MEAT).stacksTo(20)));
    public static final RegistryObject<Item> CHICKEN_FRIED_STEAK = ITEMS.register("chicken_fried_steak",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.CHICKEN_FRIED_STEAK).stacksTo(16)));
    public static final RegistryObject<Item> FRESH_LOAF = ITEMS.register("fresh_loaf",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.FRESH_LOAF).stacksTo(20)));
    public static final RegistryObject<Item> GLAZED_RABBIT = ITEMS.register("glazed_rabbit",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.GLAZED_RABBIT).stacksTo(20)));
    public static final RegistryObject<Item> ROAST = ITEMS.register("roast",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.ROAST).stacksTo(16)));
    public static final RegistryObject<Item> NUTRITIOUS_SOUP  = ITEMS.register("nutritious_soup",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.NUTRITIOUS_SOUP).stacksTo(4)));
    public static final RegistryObject<Item> ROAST_BEEF  = ITEMS.register("roast_beef",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.ROAST_BEEF).stacksTo(1)));
    public static final RegistryObject<Item> SPICY_CHICKEN_SANDWICH  = ITEMS.register("spicy_chicken_sandwich",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.SPICY_CHICKEN_SANDWICH).stacksTo(16)));
    public static final RegistryObject<Item> BACON_AND_EGGS  = ITEMS.register("bacon_and_eggs",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(ModFoods.BACON_AND_EGGS).stacksTo(8)));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
