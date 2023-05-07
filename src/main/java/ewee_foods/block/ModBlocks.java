package ewee_foods.block;

import ewee_foods.Ewee_FoodsMain;
import ewee_foods.block.custom.CrockpotBlock;
import ewee_foods.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Ewee_FoodsMain.MOD_ID);
    public static final RegistryObject<Block> SALT_MINERAL = registerBlock("salt_mineral",() -> new Block(BlockBehaviour.Properties.of(Material.STONE)
            .strength(9f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> CROCKPOT = registerBlock("crockpot",() -> new CrockpotBlock(BlockBehaviour.Properties.of(Material.STONE)
            .strength(2f).noOcclusion().requiresCorrectToolForDrops()), CreativeModeTab.TAB_MISC);

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private  static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
