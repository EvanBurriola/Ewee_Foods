package ewee_foods.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> SALT_MINERAL_PLACED = PlacementUtils.register("salt_mineral_placed",
            ModConfiguredFeatures.SALT_MINERAL, ModOrePlacement.commonOrePlacement(9, //VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.BOTTOM,VerticalAnchor.TOP)));
}
//Another type of generation
//triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))