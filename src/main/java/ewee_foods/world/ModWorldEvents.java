package ewee_foods.world;

import ewee_foods.Ewee_FoodsMain;
import ewee_foods.world.gen.ModOreGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ewee_FoodsMain.MOD_ID)
public class ModWorldEvents {
    //This would generate salt_mineral into the world
//    @SubscribeEvent
//    public static void biomeLoadingEvent(final BiomeLoadingEvent event){
//        ModOreGeneration.generateOres(event);
//    }
}
