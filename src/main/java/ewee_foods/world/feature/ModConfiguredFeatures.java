package ewee_foods.world.feature;

import ewee_foods.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

public class ModConfiguredFeatures {
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_SALT_MINERAL = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SALT_MINERAL.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> SALT_MINERAL = FeatureUtils.register("salt_mineral",
            Feature.ORE, new OreConfiguration(OVERWORLD_SALT_MINERAL,9));
}
