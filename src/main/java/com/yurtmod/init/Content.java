package com.yurtmod.init;

import com.yurtmod.block.TileEntityTentDoor;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Content {
	
	//// UTILITY BLOCKS ////
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tentmod_barrier")
	public static Block TENT_BARRIER;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":super_dirt")
	public static Block SUPER_DIRT;
	
	//// YURT BLOCKS ////
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":yurt_roof")
	public static Block YURT_ROOF;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":yurt_wall_outer")
	public static Block YURT_WALL_OUTER;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":yurt_wall_inner")
	public static Block YURT_WALL_INNER;
	
	//// BEDOUIN BLOCKS ////
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":bed_wall")
	public static Block BEDOUIN_WALL;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":bed_roof")
	public static Block BEDOUIN_ROOF;
	
	//// INDLU BLOCKS ////
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":indlu_wall_outer")
	public static Block INDLU_WALL_OUTER;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":indlu_wall_inner")
	public static Block INDLU_WALL_INNER;
	
	//// TEPEE BLOCKS ////
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_blank")
	public static Block TEPEE_WALL_BLANK;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_black")
	public static Block TEPEE_WALL_BLACK;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_red")
	public static Block TEPEE_WALL_RED;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_yellow")
	public static Block TEPEE_WALL_YELLOW;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_orange")
	public static Block TEPEE_WALL_ORANGE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_white")
	public static Block TEPEE_WALL_WHITE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_hope")
	public static Block TEPEE_WALL_HOPE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_sun")
	public static Block TEPEE_WALL_SUN;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_creeper")
	public static Block TEPEE_WALL_CREEPER;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_universe")
	public static Block TEPEE_WALL_UNIVERSE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_eagle")
	public static Block TEPEE_WALL_EAGLE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_triforce")
	public static Block TEPEE_WALL_TRIFORCE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_dreamcatcher")
	public static Block TEPEE_WALL_DREAMCATCHER;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_rain")
	public static Block TEPEE_WALL_RAIN;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_magic")
	public static Block TEPEE_WALL_MAGIC;
	
	//// DOOR BLOCKS ////
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":yurt_door_0")
	public static Block YURT_DOOR_SML;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":yurt_door_1")
	public static Block YURT_DOOR_HGM;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_door_0")
	public static Block TEPEE_DOOR_SML;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_door_1")
	public static Block TEPEE_DOOR_HGM;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":bed_door_0")
	public static Block BEDOUIN_DOOR_SML;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":bed_door_1")
	public static Block BEDOUIN_DOOR_HGM;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":indlu_door_0")
	public static Block INDLU_DOOR_SML;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":indlu_door_1")
	public static Block INDLU_DOOR_HGM;
	
	//// FRAME BLOCKS ////
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":frame_yurt_wall")
	public static Block FRAME_YURT_WALL;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":frame_yurt_roof")
	public static Block FRAME_YURT_ROOF;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":frame_tepee_wall")
	public static Block FRAME_TEPEE_WALL;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":frame_bed_wall")
	public static Block FRAME_BEDOUIN_WALL;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":frame_bed_roof")
	public static Block FRAME_BEDOUIN_ROOF;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":frame_indlu_wall")
	public static Block FRAME_INDLU_WALL;
	
	// COSMETIC (breakable + craftable) BLOCKS ////
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_yurt_roof")
	public static Block COS_YURT_ROOF;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_yurt_wall_outer")
	public static Block COS_YURT_WALL_OUTER;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_yurt_wall_inner")
	public static Block COS_YURT_WALL_INNER;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_bed_wall")
	public static Block COS_BEDOUIN_WALL;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_bed_roof")
	public static Block COS_BEDOUIN_ROOF;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_indlu_wall_outer")
	public static Block COS_INDLU_WALL_OUTER;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_indlu_wall_inner")
	public static Block COS_INDLU_WALL_INNER;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_blank")
	public static Block COS_TEPEE_WALL_BLANK;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_black")
	public static Block COS_TEPEE_WALL_BLACK;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_red")
	public static Block COS_TEPEE_WALL_RED;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_yellow")
	public static Block COS_TEPEE_WALL_YELLOW;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_orange")
	public static Block COS_TEPEE_WALL_ORANGE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_white")
	public static Block COS_TEPEE_WALL_WHITE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_hope")
	public static Block COS_TEPEE_WALL_HOPE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_sun")
	public static Block COS_TEPEE_WALL_SUN;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_creeper")
	public static Block COS_TEPEE_WALL_CREEPER;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_universe")
	public static Block COS_TEPEE_WALL_UNIVERSE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_eagle")
	public static Block COS_TEPEE_WALL_EAGLE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_triforce")
	public static Block COS_TEPEE_WALL_TRIFORCE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_dreamcatcher")
	public static Block COS_TEPEE_WALL_DREAMCATCHER;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_rain")
	public static Block COS_TEPEE_WALL_RAIN;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":cos_tepee_wall_magic")
	public static Block COS_TEPEE_WALL_MAGIC;

	//// ITEMS ////
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tent")
	public static Item ITEM_TENT;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":mallet")
	public static Item ITEM_MALLET;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":super_mallet")
	public static Item ITEM_SUPER_MALLET;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tent_canvas")
	public static Item ITEM_TENT_CANVAS;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":yurt_wall_piece")
	public static Item ITEM_YURT_WALL;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tepee_wall_piece")
	public static Item ITEM_TEPEE_WALL;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":bed_wall_piece")
	public static Item ITEM_BEDOUIN_WALL;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":indlu_wall_piece")
	public static Item ITEM_INDLU_WALL;
	
	//// UPGRADES ////
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tent_upgrade_gold")
	public static Item ITEM_UPGRADE_GOLD;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tent_upgrade_obsidian")
	public static Item ITEM_UPGRADE_OBSDIDIAN;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":tent_upgrade_diamond")
	public static Item ITEM_UPGRADE_DIAMOND;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":depth_upgrade_stone")
	public static Item ITEM_DEPTH_UPGRADE_STONE;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":depth_upgrade_iron")
	public static Item ITEM_DEPTH_UPGRADE_IRON;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":depth_upgrade_gold")
	public static Item ITEM_DEPTH_UPGRADE_GOLD;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":depth_upgrade_obsidian")
	public static Item ITEM_DEPTH_UPGRADE_OBSIDIAN;
	@GameRegistry.ObjectHolder(NomadicTents.MODID + ":depth_upgrade_diamond")
	public static Item ITEM_DEPTH_UPGRADE_DIAMOND;

	public static void mainRegistry() {
		GameRegistry.registerTileEntity(TileEntityTentDoor.class,
				new ResourceLocation(NomadicTents.MODID, "TileEntityTentDoor"));
	}
}
