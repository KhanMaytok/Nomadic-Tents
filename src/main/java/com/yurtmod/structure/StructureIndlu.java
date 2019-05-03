package com.yurtmod.structure;

import com.yurtmod.dimension.TentDimension;
import com.yurtmod.structure.util.Blueprint;
import com.yurtmod.structure.util.StructureTent;
import com.yurtmod.structure.util.StructureWidth;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StructureIndlu extends StructureBase {

	@Override
	public StructureTent getTentType() {
		return StructureTent.INDLU;
	}
	
	@Override
	public boolean generate(World worldIn, BlockPos doorBase, EnumFacing dirForward, StructureWidth size, 
			IBlockState doorBlock, IBlockState wallBlock, IBlockState roofBlock) {
		boolean tentDim = TentDimension.isTentDimension(worldIn);
		Blueprint bp = getBlueprints(size);
		if(bp == null) {
			return false;
		}
		// build all relevant layers
		buildLayer(worldIn, doorBase, dirForward, wallBlock, bp.getWallCoords());
		buildLayer(worldIn, doorBase, dirForward, roofBlock, bp.getRoofCoords());
		// make door
		buildDoor(worldIn, doorBase, doorBlock, dirForward);
		// add dimension-only features
		if (tentDim && wallBlock.getMaterial() != Material.AIR) {
			final int sizeNum = Math.floorDiv(size.getSquareWidth(), 2);
			BlockPos pos = getPosFromDoor(doorBase, sizeNum, -1, 0, dirForward);
			worldIn.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState(), 2);
			worldIn.setBlockState(pos.up(), Blocks.TORCH.getDefaultState(), 2);
		}
		return !bp.isEmpty();
	}

	public static Blueprint makeBlueprints(final StructureWidth size) {
		final Blueprint bp = new Blueprint();
		switch (size) {
		case MEGA:
			bp.addWallCoords(new int[][] {
				// layers 1 and 2
				{ 0, 0, -2 }, { 0, 0, -1 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 2 }, { 1, 0, 3 }, { 1, 0, 4 }, { 2, 0, 5 }, { 3, 0, 6 }, { 4, 0, 6 },
				{ 5, 0, 7 }, { 6, 0, 7 }, { 7, 0, 7 }, { 8, 0, 7 }, { 9, 0, 7 }, { 10, 0, 6 }, { 11, 0, 6 }, { 12, 0, 5 }, { 13, 0, 4 }, { 13, 0, 3 },
				{ 14, 0, 2 }, { 14, 0, 1 }, { 14, 0, 0 }, { 14, 0, -1 }, { 14, 0, -2 }, { 13, 0, -3 }, { 13, 0, -4 }, { 12, 0, -5 }, { 11, 0, -6 }, { 10, 0, -6 },
				{ 9, 0, -7 }, { 8, 0, -7 }, { 7, 0, -7 }, { 6, 0, -7 }, { 5, 0, -7 }, { 4, 0, -6 }, { 3, 0, -6 }, { 2, 0, -5 }, { 1, 0, -4 }, { 1, 0, -3 },
				{ 0, 1, -2 }, { 0, 1, -1 }, { 0, 1, 0 }, { 0, 1, 1 }, { 0, 1, 2 }, { 1, 1, 3 }, { 1, 1, 4 }, { 2, 1, 5 }, { 3, 1, 6 }, { 4, 1, 6 },
				{ 5, 1, 7 }, { 6, 1, 7 }, { 7, 1, 7 }, { 8, 1, 7 }, { 9, 1, 7 }, { 10, 1, 6 }, { 11, 1, 6 }, { 12, 1, 5 }, { 13, 1, 4 }, { 13, 1, 3 },
				{ 14, 1, 2 }, { 14, 1, 1 }, { 14, 1, 0 }, { 14, 1, -1 }, { 14, 1, -2 }, { 13, 1, -3 }, { 13, 1, -4 }, { 12, 1, -5 }, { 11, 1, -6 }, { 10, 1, -6 },
				{ 9, 1, -7 }, { 8, 1, -7 }, { 7, 1, -7 }, { 6, 1, -7 }, { 5, 1, -7 }, { 4, 1, -6 }, { 3, 1, -6 }, { 2, 1, -5 }, { 1, 1, -4 }, { 1, 1, -3 },
				// layer 3
				{ 1, 2, -2 }, { 1, 2, -1 }, { 0, 2, 0 }, { 1, 2, 1 }, { 1, 2, 2 }, 
				{ 2, 2, 3 }, { 2, 2, 4 }, { 3, 2, 5 }, { 4, 2, 5 }, { 5, 2, 6 }, { 6, 2, 6 }, { 7, 2, 7 },
				{ 8, 2, 6 }, { 9, 2, 6 }, { 10, 2, 5 }, { 11, 2, 5 }, { 12, 2, 4 }, { 12, 2, 3 }, { 13, 2, 2 },
				{ 13, 2, 1 }, { 14, 2, 0 }, { 13, 2, -1 }, { 13, 2, -2 }, { 12, 2, -3 }, { 12, 2, -4 },
				{ 11, 2, -5 }, { 10, 2, -5 }, { 9, 2, -6 }, { 8, 2, -6 }, { 7, 2, -7 }, { 6, 2, -6 }, { 5, 2, -6 },
				{ 4, 2, -5 }, { 3, 2, -5 }, { 2, 2, -4 }, { 2, 2, -3 },
				// layers 4 to 6
				{ 2, 3, -2 }, { 2, 3, -1 }, { 2, 3, 1 }, { 2, 3, 2 }, { 3, 3, 2 }, { 3, 3, 3 }, { 3, 3, 4 },
				{ 4, 3, 4 }, { 5, 3, 4 }, { 5, 3, 5 }, { 6, 3, 5 }, { 8, 3, 5 }, { 9, 3, 5 }, { 9, 3, 4 },
				{ 10, 3, 4 }, { 11, 3, 4 }, { 11, 3, 3 }, { 11, 3, 2 }, { 12, 3, 2 }, { 12, 3, 1 }, { 12, 3, -1 },
				{ 12, 3, -2 }, { 11, 3, -2 }, { 11, 3, -3 }, { 11, 3, -4 }, { 10, 3, -4 }, { 9, 3, -4 },
				{ 9, 3, -5 }, { 8, 3, -5 }, { 6, 3, -5 }, { 5, 3, -5 }, { 5, 3, -4 }, { 4, 3, -4 }, { 3, 3, -4 },
				{ 3, 3, -3 }, { 3, 3, -2 }, { 1, 3, 0 }, { 7, 3, 6 }, { 7, 3, -6 }, { 13, 3, 0 }, { 3, 4, -1 },
				{ 3, 4, 0 }, { 3, 4, 1 }, { 4, 4, 1 }, { 4, 4, 2 }, { 4, 4, 3 }, { 5, 4, 3 }, { 6, 4, 3 },
				{ 6, 4, 4 }, { 7, 4, 4 }, { 8, 4, 4 }, { 8, 4, 3 }, { 9, 4, 3 }, { 10, 4, 3 }, { 10, 4, 2 },
				{ 10, 4, 1 }, { 11, 4, 1 }, { 11, 4, 0 }, { 11, 4, -1 }, { 10, 4, -1 }, { 10, 4, -2 },
				{ 10, 4, -3 }, { 9, 4, -3 }, { 8, 4, -3 }, { 8, 4, -4 }, { 7, 4, -4 }, { 6, 4, -4 }, { 6, 4, -3 },
				{ 5, 4, -3 }, { 4, 4, -3 }, { 4, 4, -2 }, { 4, 4, -1 }, { 2, 4, 0 }, { 7, 4, 5 }, { 7, 4, -5 },
				{ 12, 4, 0 }, { 4, 5, 0 }, { 7, 5, 3 }, { 7, 5, -3 }, { 10, 5, 0 }, { 5, 5, -2 }, { 5, 5, -1 },
				{ 5, 5, 0 }, { 5, 5, 1 }, { 5, 5, 2 }, { 6, 5, 2 }, { 7, 5, 2 }, { 8, 5, 2 }, { 9, 5, 2 },
				{ 9, 5, 1 }, { 9, 5, 0 }, { 9, 5, -1 }, { 9, 5, -2 }, { 8, 5, -2 }, { 7, 5, -2 }, { 6, 5, -2 }		
			});
			bp.addRoofCoords(new int[][] {
				// layer 7
				{ 6, 6, -1 }, { 6, 6, 0 }, { 6, 6, 1 },  { 7, 6, 0 }, { 7, 6, 1 }, 
				{ 8, 6, 1 }, { 8, 6, 0 }, { 8, 6, -1 }, { 7, 6, -1 }
			});
			break;
		case GIANT:
			bp.addWallCoords(new int[][] {
				// layer 1
				{ 0, 0, -2 }, { 0, 0, -1 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 2 }, { 1, 0, 3 }, { 1, 0, 4 }, { 2, 0, 5 }, { 3, 0, 5 },
				{ 4, 0, 6 }, { 5, 0, 6 }, { 6, 0, 6 }, { 7, 0, 6 }, { 8, 0, 6 }, { 9, 0, 5 }, { 10, 0, 5 }, { 11, 0, 4 }, { 11, 0, 3 },
				{ 12, 0, 2 }, { 12, 0, 1 }, { 12, 0, 0 }, { 12, 0, -1 }, { 12, 0, -2 }, { 11, 0, -3 }, { 11, 0, -4 }, { 10, 0, -5 }, { 9, 0, -5 },
				{ 9, 0, -6 }, { 8, 0, -6 }, { 7, 0, -6 }, { 6, 0, -6 }, { 5, 0, -6 }, { 4, 0, -6 }, { 3, 0, -5 }, { 2, 0, -5 }, { 1, 0, -4 }, { 1, 0, -3 },
				// layer 2
				{ 0, 1, -2 }, { 0, 1, -1 }, { 0, 1, 0 }, { 0, 1, 1 }, { 0, 1, 2 }, { 1, 1, 3 }, { 1, 1, 4 }, { 2, 1, 5 }, { 3, 1, 5 },
				{ 4, 1, 6 }, { 5, 1, 6 }, { 6, 1, 6 }, { 7, 1, 6 }, { 8, 1, 6 }, { 9, 1, 5 }, { 10, 1, 5 }, { 11, 1, 4 }, { 11, 1, 3 },
				{ 12, 1, 2 }, { 12, 1, 1 }, { 12, 1, 0 }, { 12, 1, -1 }, { 12, 1, -2 }, { 11, 1, -3 }, { 11, 1, -4 }, { 10, 1, -5 }, { 9, 1, -5 },
				{ 9, 1, -6 }, { 8, 1, -6 }, { 7, 1, -6 }, { 6, 1, -6 }, { 5, 1, -6 }, { 4, 1, -6 }, { 3, 1, -5 }, { 2, 1, -5 }, { 1, 1, -4 }, { 1, 1, -3 },
				// layer 3
				{ 1, 2, -2 }, { 1, 2, -1 }, { 1, 2, 1 }, { 1, 2, 2 }, { 2, 2, 2 }, { 2, 2, 3 }, { 2, 2, 4 }, { 3, 2, 4 }, { 4, 2, 4 },
				{ 4, 2, 5 }, { 5, 2, 5 }, { 7, 2, 5 }, { 8, 2, 5 }, { 8, 2, 4 }, { 9, 2, 4 }, { 10, 2, 4 }, { 10, 2, 3 }, { 10, 2, 2 }, 
				{ 11, 2, 2 }, { 11, 2, 1 }, { 11, 2, -1 }, { 11, 2, -2 }, { 10, 2, -2 }, { 10, 2, -3 }, { 10, 2, -4 }, { 9, 2, -4 }, { 8, 2, -4 }, 
				{ 8, 2, -5 }, { 7, 2, -5 }, { 5, 2, -5 }, { 4, 2, -5 }, { 4, 2, -4 }, { 3, 2, -4 }, { 2, 2, -4 }, { 2, 2, -3 }, { 2, 2, -2 },
				{ 0, 2, 0 }, { 6, 2, 6 }, { 6, 2, -6 }, { 12, 2, 0 },
				// layer 4
				{ 2, 3, -1 }, { 2, 3, 0 }, { 2, 3, 1 }, { 3, 3, 1 }, { 3, 3, 2 }, { 3, 3, 3 }, { 4, 3, 3 }, { 5, 3, 3 }, 
				{ 5, 3, 4 }, { 6, 3, 4 }, { 7, 3, 4 }, { 7, 3, 3 }, { 8, 3, 3 }, { 9, 3, 3 }, { 9, 3, 2 }, { 9, 3, 1 },
				{ 10, 3, 1 }, { 10, 3, 0 }, { 10, 3, -1 }, { 9, 3, -1 }, { 9, 3, -2 }, { 9, 3, -3 }, { 8, 3, -3 }, { 7, 3, -3 },
				{ 7, 3, -4 }, { 6, 3, -4 }, { 5, 3, -4 }, { 5, 3, -3 }, { 4, 3, -3 }, { 3, 3, -3 }, { 3, 3, -2 }, { 3, 3, -1 },
				{ 1, 3, 0 }, { 6, 3, 5 }, { 6, 3, -5 }, { 11, 3, 0 },
				// layer 5
				{ 3, 4, 0 }, { 6, 4, 3 }, { 6, 4, -3 }, { 9, 4, 0 },
				{ 4, 4, -2 }, { 4, 4, -1 }, { 4, 4, 0 }, { 4, 4, 1 }, { 4, 4, 2 }, { 5, 4, 2 }, { 6, 4, 2 }, { 7, 4, 2 }, 
				{ 8, 4, 2 }, { 8, 4, 1 }, { 8, 4, 0 }, { 8, 4, -1 }, { 8, 4, -2 }, { 7, 4, -2 }, { 6, 4, -2 }, { 5, 4, -2 }
			});
			bp.addRoofCoords(new int[][] {
				// layer 6
				{ 5, 5, -1 }, { 5, 5, 0 }, { 5, 5, 1 },  { 6, 5, 0 }, { 6, 5, 1 }, 
				{ 7, 5, 1 }, { 7, 5, 0 }, { 7, 5, -1 }, { 6, 5, -1 }
			});
			break;
		case HUGE:
			bp.addWallCoords(new int[][] {
				// layer 1
				{ 0, 0, -2 }, { 0, 0, -1 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 2 }, { 1, 0, 3 }, { 2, 0, 4 },
				{ 3, 0, 5 }, { 4, 0, 5 }, { 5, 0, 5 }, { 6, 0, 5 }, { 7, 0, 5 }, { 8, 0, 4 }, { 9, 0, 3 },
				{ 10, 0, 2 }, { 10, 0, 1 }, { 10, 0, 0 }, { 10, 0, -1 }, { 10, 0, -2 }, { 9, 0, -3 }, { 8, 0, -4 },
				{ 7, 0, -5 }, { 6, 0, -5 }, { 5, 0, -5 }, { 4, 0, -5 }, { 3, 0, -5 }, { 2, 0, -4 }, { 1, 0, -3 },
				// layer 2
				{ 0, 1, -2 }, { 0, 1, -1 }, { 0, 1, 0 }, { 0, 1, 1 }, { 0, 1, 2 }, { 1, 1, 3 }, { 2, 1, 4 },
				{ 3, 1, 5 }, { 4, 1, 5 }, { 5, 1, 5 }, { 6, 1, 5 }, { 7, 1, 5 }, { 8, 1, 4 }, { 9, 1, 3 },
				{ 10, 1, 2 }, { 10, 1, 1 }, { 10, 1, 0 }, { 10, 1, -1 }, { 10, 1, -2 }, { 9, 1, -3 }, { 8, 1, -4 },
				{ 7, 1, -5 }, { 6, 1, -5 }, { 5, 1, -5 }, { 4, 1, -5 }, { 3, 1, -5 }, { 2, 1, -4 }, { 1, 1, -3 },
				// layer 3
				{ 0, 2, 0 }, { 1, 2, 1 }, { 1, 2, 2 }, { 2, 2, 2 }, { 2, 2, 3 }, { 3, 2, 3 }, { 3, 2, 4 }, { 4, 2, 4 },
				{ 5, 2, 5 }, { 6, 2, 4 }, { 7, 2, 4 }, { 7, 2, 3 }, { 8, 2, 3 }, { 8, 2, 2 }, { 9, 2, 2 }, { 9, 2, 1 },
				{ 10, 2, 0 }, { 9, 2, -1 }, { 9, 2, -2 }, { 8, 2, -2 }, { 8, 2, -3 }, { 7, 2, -3 }, { 7, 2, -4 }, { 6, 2, -4 },
				{ 5, 2, -5 }, { 4, 2, -4 }, { 3, 2, -4 }, { 3, 2, -3 }, { 2, 2, -3 }, { 2, 2, -2 }, { 1, 2, -2 }, { 1, 2, -1 },
				// layer 4
				{ 2, 3, -1 }, { 2, 3, 0 }, { 1, 3, 0 }, { 2, 3, 1 }, { 3, 3, 2 },
				{ 4, 3, 3 }, { 5, 3, 3 }, { 5, 3, 4 }, { 6, 3, 3 }, { 7, 3, 2 },
				{ 8, 3, 1 }, { 8, 3, 0 }, { 9, 3, 0 }, { 8, 3, -1 }, { 7, 3, -2 },
				{ 6, 3, -3 }, { 5, 3, -3 }, { 5, 3, -4 }, { 4, 3, -3 }, { 3, 3, -2 },
				// layer 5
				{ 3, 4, -1 }, { 3, 4, 0 }, { 3, 4, 1 }, { 4, 4, 1 }, { 4, 4, 2 }, { 5, 4, 2 }, { 6, 4, 1 }, { 6, 4, 2 },
				{ 7, 4, 1 }, { 7, 4, 0 }, { 7, 4, -1 }, { 6, 4, -1 }, { 6, 4, -2 }, { 5, 4, -2 }, { 4, 4, -2 }, { 4, 4, -1 }
			});
			bp.addRoofCoords(new int[][] {
				{ 4, 5, 0 }, { 5, 5, -1 }, { 5, 5, 0 }, { 5, 5, 1 }, { 6, 5, 0 }
			});
			break;
		case LARGE:
			bp.addWallCoords(new int[][] {
				// layer 1
				{ 0, 0, -2 }, { 0, 0, -1 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 2 }, { 1, 0, 3 }, { 2, 0, 4 },
				{ 3, 0, 4 }, { 4, 0, 4 }, { 5, 0, 4 }, { 6, 0, 4 }, { 7, 0, 3 }, { 8, 0, 2 }, { 8, 0, 1 },
				{ 8, 0, 0 }, { 8, 0, -1 }, { 8, 0, -2 }, { 7, 0, -3 }, { 6, 0, -4 }, { 5, 0, -4 }, { 4, 0, -4 },
				{ 3, 0, -4 }, { 2, 0, -4 }, { 1, 0, -3 },
				// layer 2
				{ 0, 1, -2 }, { 0, 1, -1 }, { 0, 1, 0 }, { 0, 1, 1 }, { 0, 1, 2 }, { 1, 1, 3 }, { 2, 1, 4 },
				{ 3, 1, 4 }, { 4, 1, 4 }, { 5, 1, 4 }, { 6, 1, 4 }, { 7, 1, 3 }, { 8, 1, 2 }, { 8, 1, 1 },
				{ 8, 1, 0 }, { 8, 1, -1 }, { 8, 1, -2 }, { 7, 1, -3 }, { 6, 1, -4 }, { 5, 1, -4 }, { 4, 1, -4 },
				{ 3, 1, -4 }, { 2, 1, -4 }, { 1, 1, -3 },
				// layer 3
				{ 0, 2, 0 }, { 1, 2, 1 }, {1, 2, 2 }, { 2, 2, 3 }, { 3, 2, 3 }, 
				{ 4, 2, 4 }, { 5, 2, 3 }, { 6, 2, 3 }, { 7, 2, 2 }, { 7, 2, 1 },
				{ 8, 2, 0 }, { 7, 2, -1 }, { 7, 2, -2 }, { 6, 2, -3 }, { 5, 2, -3 },
				{ 4, 2, -4 }, { 3, 2, -3 }, { 2, 2, -3 }, { 1, 2, -2 }, { 1, 2, -1},
				// layer 4
				{ 1, 3, 0 }, { 2, 3, 1 }, { 2, 3, 2 }, { 3, 3, 2 }, { 4, 3, 3 }, { 5, 3, 2 }, { 6, 3, 2 }, { 6, 3, 1 },
				{ 7, 3, 0 }, { 6, 3, -1 }, { 6, 3, -2 }, { 5, 3, -2 }, { 4, 3, -3 }, { 3, 3, -2 }, { 2, 3, -2 }, { 2, 3, -1 },
				// layer 5
				{ 2, 4, 0 }, { 3, 4, 1 }, { 4, 4, 2 }, { 5, 4, 1 }, { 6, 4, 0 }, { 5, 4, -1 }, { 4, 4, -2 }, { 3, 4, -1 } });
			bp.addRoofCoords(new int[][] {
				{ 4, 5, 1 }, { 5, 5, 0 }, { 4, 5, 0 }, { 3, 5, 0 }, { 4, 5, -1 } });
			break;
		case MEDIUM:
			bp.addWallCoords(new int[][] {
					// layer 1
					{ 0, 0, -1 }, { 0, 0, 0 }, { 0, 0, 1 }, { 1, 0, 2 }, { 2, 0, 3 }, { 3, 0, 3 }, { 4, 0, 3 },
					{ 5, 0, 2 }, { 6, 0, 1 }, { 6, 0, 0 }, { 6, 0, -1 }, { 5, 0, -2 }, { 4, 0, -3 }, { 3, 0, -3 },
					{ 2, 0, -3 }, { 1, 0, -2 },
					// layer 2
					{ 0, 1, -1 }, { 0, 1, 0 }, { 0, 1, 1 }, { 1, 1, 2 }, { 2, 1, 3 }, { 3, 1, 3 }, { 4, 1, 3 },
					{ 5, 1, 2 }, { 6, 1, 1 }, { 6, 1, 0 }, { 6, 1, -1 }, { 5, 1, -2 }, { 4, 1, -3 }, { 3, 1, -3 },
					{ 2, 1, -3 }, { 1, 1, -2 },
					// layer 3
					{ 0, 2, 0 }, { 1, 2, 1 }, { 2, 2, 2 }, { 3, 2, 3 }, { 4, 2, 2 }, { 5, 2, 1 }, { 6, 2, 0 },
					{ 5, 2, -1 }, { 4, 2, -2 }, { 3, 2, -3 }, { 2, 2, -2}, { 1, 2, -1 },
					// layer 4
					{ 1, 3, 0 }, { 2, 3, 1 }, { 3, 3, 2 }, { 4, 3, 1 }, { 5, 3, 0 }, 
					{ 4, 3, -1 }, { 3, 3, -2 }, { 2, 3, -1 } });
			bp.addRoofCoords(new int[][] {
					// layer 5
					{ 3, 4, 1 }, { 4, 4, 0 }, { 3, 4, 0 }, { 2, 4, 0 }, { 3, 4, -1 } });
			
			break;
		case SMALL:
			bp.addWallCoords(new int[][] {
					// layer 1
					{ 0, 0, 1 }, { 0, 0, 0 }, { 0, 0, -1 }, { 1, 0, -2 }, { 2, 0, -2 }, { 3, 0, -2 }, { 4, 0, -1 },
					{ 4, 0, 0 }, { 4, 0, 1 }, { 3, 0, 2 }, { 2, 0, 2 }, { 1, 0, 2 },
					// layer 2
					{ 0, 1, 1 }, { 0, 1, 0 }, { 0, 1, -1 }, { 1, 1, -2 }, { 2, 1, -2 }, { 3, 1, -2 }, { 4, 1, -1 },
					{ 4, 1, 0 }, { 4, 1, 1 }, { 3, 1, 2 }, { 2, 1, 2 }, { 1, 1, 2 },
					// layer 3
					{ 0, 2, 0 }, { 1, 2, -1 }, { 2, 2, -2 }, { 3, 2, -1 }, { 4, 2, 0 }, { 3, 2, 1 }, { 2, 2, 2 }, { 1, 2, 1 } });
			bp.addRoofCoords(new int[][] {
					// layer 4
					{ 2, 3, 1 }, { 3, 3, 0 }, { 2, 3, 0 }, { 1, 3, 0 }, { 2, 3, -1 }
			});
			break;
		}
		return bp;
	}
}
