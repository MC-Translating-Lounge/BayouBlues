package com.teamaurora.bayou_blues.common.world.biome;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.teamaurora.bayou_blues.core.BayouBlues;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBiomes;
import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BayouBlues.MODID)
public class BayouBluesBiomeFeatures {

    @SubscribeEvent
    public static void addFeatures(BiomeLoadingEvent event) {
        ResourceLocation biomeName = event.getName();

        if (DataUtil.matchesKeys(biomeName, BayouBluesBiomes.BAYOU.getKey(), BayouBluesBiomes.BAYOU_HILLS.getKey())) {
            withBayouFeatures(event.getGeneration(), event.getSpawns());
        } else if (DataUtil.matchesKeys(biomeName, Biomes.SWAMP, Biomes.SWAMP_HILLS)) {
            withLilyFlowers(event.getGeneration());
        }
    }

    public static void withBayouFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnInfoBuilder spawns) {
        DefaultBiomeFeatures.withStrongholdAndMineshaft(builder);
        builder.withStructure(StructureFeatures.RUINED_PORTAL_SWAMP);
        DefaultBiomeFeatures.withCavesAndCanyons(builder);
        DefaultBiomeFeatures.withLavaAndWaterLakes(builder);
        DefaultBiomeFeatures.withMonsterRoom(builder);

        DefaultBiomeFeatures.withCommonOverworldBlocks(builder);
        DefaultBiomeFeatures.withOverworldOres(builder);
        DefaultBiomeFeatures.withDisks(builder);

        builder.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, BayouBluesFeatures.Configured.PODZOL);

        withBayouVegetation(builder);

        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_SWAMP);

        DefaultBiomeFeatures.withNormalMushroomGeneration(builder);
        DefaultBiomeFeatures.withSwampSugarcaneAndPumpkin(builder);
        DefaultBiomeFeatures.withLavaAndWaterSprings(builder);
        DefaultBiomeFeatures.withFrozenTopLayer(builder);

        DefaultBiomeFeatures.withBatsAndHostiles(spawns);
        DefaultBiomeFeatures.withPassiveMobs(spawns);
        spawns.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 1, 1, 1));
    }

    private static void withBayouVegetation(BiomeGenerationSettingsBuilder builder) {
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BayouBluesFeatures.Configured.ALGAE);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BayouBluesFeatures.Configured.TREES_BAYOU);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BayouBluesFeatures.Configured.TREES_BAYOU_WATER);
        withBayouGrasses(builder);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BayouBluesFeatures.Configured.FALLEN_CYPRESS_LEAVES);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.FLOWER_SWAMP);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_WATERLILLY);
        withLilyFlowers(builder);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.BROWN_MUSHROOM_SWAMP);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.RED_MUSHROOM_SWAMP);
    }

    private static void withBayouGrasses(BiomeGenerationSettingsBuilder builder) {
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_GRASS);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_FERN);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_GIANT_FERN);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_TALL_GRASS);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LARGE_FERN);
    }

    private static void withLilyFlowers(BiomeGenerationSettingsBuilder builder) {
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_COOL);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_NEUTRAL);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_WARM);
    }
}
