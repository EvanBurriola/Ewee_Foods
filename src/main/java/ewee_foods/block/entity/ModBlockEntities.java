package ewee_foods.block.entity;

import ewee_foods.Ewee_FoodsMain;
import ewee_foods.block.ModBlocks;
import ewee_foods.block.entity.custom.CrockpotBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Ewee_FoodsMain.MOD_ID);
    public static final RegistryObject<BlockEntityType<CrockpotBlockEntity>> CROCKPOT_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("crockpot_block_entity", () ->
                    BlockEntityType.Builder.of(CrockpotBlockEntity::new,
                            ModBlocks.CROCKPOT.get()).build(null));
    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
