package nomadictents.structure;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nomadictents.dimension.TentManager;
import nomadictents.init.Content;
import nomadictents.structure.util.Blueprint;
import nomadictents.structure.util.StructureTent;
import nomadictents.structure.util.StructureWidth;

public class StructureYurt extends StructureBase {

	@Override
	public StructureTent getTentType() {
		return StructureTent.YURT;
	}

	@Override
	public boolean generate(World worldIn, BlockPos doorBase, Direction dirForward, StructureWidth size, 
			BlockState doorBlock, BlockState wallBlock, BlockState roofBlock) {
		boolean tentDim = TentManager.isTent(worldIn);
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
			if (sizeNum > 2 && (worldIn.getBlockState(pos).getBlock() == Blocks.DIRT || worldIn.isAirBlock(pos))
					&& worldIn.isAirBlock(pos.up(1))) {
				worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState(), 2);
				worldIn.setBlockState(pos.up(), Blocks.FIRE.getDefaultState(), 3);
			}
			buildLayer(worldIn, doorBase, dirForward, Content.TENT_BARRIER.getDefaultState(), bp.getBarrierCoords());
		}
		return !bp.isEmpty();
	}

	public static Blueprint makeBlueprints(final StructureWidth size) {
		final Blueprint bp = new Blueprint();
		switch (size) {
		case MEGA:
			bp.addWallCoords(new int[][] {
				// layers 1, 2, and 3
				{ 0, 0, -2 }, { 0, 0, -1 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 2 }, { 1, 0, 3 }, { 1, 0, 4 }, { 2, 0, 5 }, { 3, 0, 6 }, { 4, 0, 6 },
				{ 5, 0, 7 }, { 6, 0, 7 }, { 7, 0, 7 }, { 8, 0, 7 }, { 9, 0, 7 }, { 10, 0, 6 }, { 11, 0, 6 }, { 12, 0, 5 }, { 13, 0, 4 }, { 13, 0, 3 },
				{ 14, 0, 2 }, { 14, 0, 1 }, { 14, 0, 0 }, { 14, 0, -1 }, { 14, 0, -2 }, { 13, 0, -3 }, { 13, 0, -4 }, { 12, 0, -5 }, { 11, 0, -6 }, { 10, 0, -6 },
				{ 9, 0, -7 }, { 8, 0, -7 }, { 7, 0, -7 }, { 6, 0, -7 }, { 5, 0, -7 }, { 4, 0, -6 }, { 3, 0, -6 }, { 2, 0, -5 }, { 1, 0, -4 }, { 1, 0, -3 },
				{ 0, 1, -2 }, { 0, 1, -1 }, { 0, 1, 0 }, { 0, 1, 1 }, { 0, 1, 2 }, { 1, 1, 3 }, { 1, 1, 4 }, { 2, 1, 5 }, { 3, 1, 6 }, { 4, 1, 6 },
				{ 5, 1, 7 }, { 6, 1, 7 }, { 7, 1, 7 }, { 8, 1, 7 }, { 9, 1, 7 }, { 10, 1, 6 }, { 11, 1, 6 }, { 12, 1, 5 }, { 13, 1, 4 }, { 13, 1, 3 },
				{ 14, 1, 2 }, { 14, 1, 1 }, { 14, 1, 0 }, { 14, 1, -1 }, { 14, 1, -2 }, { 13, 1, -3 }, { 13, 1, -4 }, { 12, 1, -5 }, { 11, 1, -6 }, { 10, 1, -6 },
				{ 9, 1, -7 }, { 8, 1, -7 }, { 7, 1, -7 }, { 6, 1, -7 }, { 5, 1, -7 }, { 4, 1, -6 }, { 3, 1, -6 }, { 2, 1, -5 }, { 1, 1, -4 }, { 1, 1, -3 },
				{ 0, 2, -2 }, { 0, 2, -1 }, { 0, 2, 0 }, { 0, 2, 1 }, { 0, 2, 2 }, { 1, 2, 3 }, { 1, 2, 4 }, { 2, 2, 5 }, { 3, 2, 6 }, { 4, 2, 6 },
				{ 5, 2, 7 }, { 6, 2, 7 }, { 7, 2, 7 }, { 8, 2, 7 }, { 9, 2, 7 }, { 10, 2, 6 }, { 11, 2, 6 }, { 12, 2, 5 }, { 13, 2, 4 }, { 13, 2, 3 },
				{ 14, 2, 2 }, { 14, 2, 1 }, { 14, 2, 0 }, { 14, 2, -1 }, { 14, 2, -2 }, { 13, 2, -3 }, { 13, 2, -4 }, { 12, 2, -5 }, { 11, 2, -6 }, { 10, 2, -6 },
				{ 9, 2, -7 }, { 8, 2, -7 }, { 7, 2, -7 }, { 6, 2, -7 }, { 5, 2, -7 }, { 4, 2, -6 }, { 3, 2, -6 }, { 2, 2, -5 }, { 1, 2, -4 }, { 1, 2, -3 }
				
			});
			bp.addRoofCoords(new int[][] {
				// layer 4
				{ 1, 3, -2 }, { 1, 3, -1 }, { 1, 3, 0 }, { 1, 3, 1 }, { 1, 3, 2 }, { 2, 3, 3 }, { 2, 3, 4 },
				{ 3, 3, 5 }, { 4, 3, 5 }, { 5, 3, 6 }, { 6, 3, 6 }, { 7, 3, 6 }, { 8, 3, 6 }, { 9, 3, 6 },
				{ 10, 3, 5 }, { 11, 3, 5 }, { 12, 3, 4 }, { 12, 3, 3 }, { 13, 3, 2 }, { 13, 3, 1 }, { 13, 3, 0 },
				{ 13, 3, -1 }, { 13, 3, -2 }, { 12, 3, -3 }, { 12, 3, -4 }, { 11, 3, -5 }, { 10, 3, -5 },
				{ 10, 3, -6 }, { 9, 3, -6 }, { 8, 3, -6 }, { 7, 3, -6 }, { 6, 3, -6 }, { 5, 3, -6 }, { 4, 3, -5 },
				{ 3, 3, -5 }, { 2, 3, -4 }, { 2, 3, -3 },
				// layer 5
				{ 2, 4, -2 }, { 2, 4, -1 }, { 2, 4, 0 }, { 2, 4, 1 }, { 2, 4, 2 }, { 3, 4, 2 }, { 3, 4, 3 },
				{ 3, 4, 4 }, { 4, 4, 4 }, { 5, 4, 4 }, { 5, 4, 5 }, { 6, 4, 5 }, { 7, 4, 5 }, { 8, 4, 5 },
				{ 9, 4, 5 }, { 9, 4, 4 }, { 10, 4, 4 }, { 11, 4, 4 }, { 11, 4, 3 }, { 11, 4, 2 }, { 12, 4, 2 },
				{ 12, 4, 1 }, { 12, 4, 0 }, { 12, 4, -1 }, { 12, 4, -2 }, { 11, 4, -2 }, { 11, 4, -3 },
				{ 11, 4, -4 }, { 10, 4, -4 }, { 9, 4, -4 }, { 9, 4, -5 }, { 8, 4, -5 }, { 7, 4, -5 }, { 6, 4, -5 },
				{ 5, 4, -5 }, { 5, 4, -4 }, { 4, 4, -4 }, { 3, 4, -4 }, { 3, 4, -3 }, { 3, 4, -2 },
				// layer 6
				{ 3, 5, -1 }, { 3, 5, 0 }, { 3, 5, 1 }, { 4, 5, 1 }, { 4, 5, 2 }, { 4, 5, 3 }, { 5, 5, 3 },
				{ 6, 5, 3 }, { 6, 5, 4 }, { 7, 5, 4 }, { 8, 5, 4 }, { 8, 5, 3 }, { 9, 5, 3 }, { 10, 5, 3 },
				{ 10, 5, 2 }, { 10, 5, 1 }, { 11, 5, 1 }, { 11, 5, 0 }, { 11, 5, -1 }, { 10, 5, -1 }, { 10, 5, -2 },
				{ 10, 5, -3 }, { 9, 5, -3 }, { 8, 5, -3 }, { 8, 5, -4 }, { 7, 5, -4 }, { 6, 5, -4 }, { 6, 5, -3 },
				{ 5, 5, -3 }, { 4, 5, -3 }, { 4, 5, -2 }, { 4, 5, -1 },
				{ 4, 5, 0 }, { 5, 5, -2 }, { 5, 5, 2 }, { 7, 5, -3 }, { 7, 5, 3 }, { 9, 5, -2 }, { 9, 5, 2 }, { 10, 5, 0 },
				// layer 7
				{ 5, 6, -1 }, { 5, 6, 0 }, { 5, 6, 1 }, { 6, 6, 2 }, { 7, 6, 2 }, { 8, 6, 2 }, { 9, 6, 1 },
				{ 9, 6, 0 }, { 9, 6, -1 }, { 8, 6, -2 }, { 7, 6, -2 }, { 6, 6, -2 }, { 5, 6, -1 }, { 5, 6, 0 },
				{ 5, 6, 1 }, { 6, 6, 2 }, { 7, 6, 2 }, { 8, 6, 2 }, { 9, 6, 1 }, { 9, 6, 0 }, { 9, 6, -1 },
				{ 8, 6, -2 }, { 7, 6, -2 }, { 6, 6, -2 }, { 6, 7, -1 }, { 6, 7, 0 }, { 6, 7, 1 }, { 7, 7, 1 },
				{ 8, 7, 1 }, { 8, 7, 0 }, { 8, 7, -1 }, { 7, 7, -1 }	
			});
			bp.addBarrierCoords(new int[][] { { 7, 8, 0 } });
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
				{ 0, 2, -2 }, { 0, 2, -1 }, { 0, 2, 0 }, { 0, 2, 1 }, { 0, 2, 2 }, { 1, 2, 3 }, { 1, 2, 4 }, { 2, 2, 5 }, { 3, 2, 5 },
				{ 4, 2, 6 }, { 5, 2, 6 }, { 6, 2, 6 }, { 7, 2, 6 }, { 8, 2, 6 }, { 9, 2, 5 }, { 10, 2, 5 }, { 11, 2, 4 }, { 11, 2, 3 },
				{ 12, 2, 2 }, { 12, 2, 1 }, { 12, 2, 0 }, { 12, 2, -1 }, { 12, 2, -2 }, { 11, 2, -3 }, { 11, 2, -4 }, { 10, 2, -5 }, { 9, 2, -5 },
				{ 9, 2, -6 }, { 8, 2, -6 }, { 7, 2, -6 }, { 6, 2, -6 }, { 5, 2, -6 }, { 4, 2, -6 }, { 3, 2, -5 }, { 2, 2, -5 }, { 1, 2, -4 }, { 1, 2, -3 },
			});
			bp.addRoofCoords(new int[][] {
				// layer 4
				{ 1, 3, -2 }, { 1, 3, -1 }, { 1, 3, 0 }, { 1, 3, 1 }, { 1, 3, 2 }, { 2, 3, 2 }, { 2, 3, 3 }, { 2, 3, 4 }, { 3, 3, 4 }, { 4, 3, 4 },
				{ 4, 3, 5 }, { 5, 3, 5 }, { 6, 3, 5 }, { 7, 3, 5 }, { 8, 3, 5 }, { 8, 3, 4 }, { 9, 3, 4 }, { 10, 3, 4 }, { 10, 3, 3 }, { 10, 3, 2 }, 
				{ 11, 3, 2 }, { 11, 3, 1 }, { 11, 3, 0 }, { 11, 3, -1 }, { 11, 3, -2 }, { 10, 3, -2 }, { 10, 3, -3 }, { 10, 3, -4 }, { 9, 3, -4 }, { 8, 3, -4 }, 
				{ 8, 3, -5 }, { 7, 3, -5 },	{ 6, 3, -5 }, { 5, 3, -5 }, { 4, 3, -5 }, { 4, 3, -4 }, { 3, 3, -4 }, { 2, 3, -4 }, { 2, 3, -3 }, { 2, 3, -2 },
				// layer 5
				{ 2, 4, -1 }, { 2, 4, 0 }, { 2, 4, 1 }, { 3, 4, 1 }, { 3, 4, 2 }, { 3, 4, 3 }, { 4, 4, 3 }, { 5, 4, 3 }, 
				{ 5, 4, 4 }, { 6, 4, 4 }, { 7, 4, 4 }, { 7, 4, 3 }, { 8, 4, 3 }, { 9, 4, 3 }, { 9, 4, 2 }, { 9, 4, 1 },
				{ 10, 4, 1 }, { 10, 4, 0 }, { 10, 4, -1 }, { 9, 4, -1 }, { 9, 4, -2 }, { 9, 4, -3 }, { 8, 4, -3 }, { 7, 4, -3 },
				{ 7, 4, -4 }, { 6, 4, -4 }, { 5, 4, -4 }, { 5, 4, -3 }, { 4, 4, -3 }, { 3, 4, -3 }, { 3, 4, -2 }, { 3, 4, -1 },
				// layer 6
				{ 3, 5, 0 }, { 6, 5, 3 }, { 6, 5, -3 }, { 9, 5, 0 },
				{ 4, 5, -2 }, { 4, 5, -1 }, { 4, 5, 0 }, { 4, 5, 1 }, { 4, 5, 2 }, { 5, 5, 2 }, { 6, 5, 2 }, { 7, 5, 2 }, 
				{ 8, 5, 2 }, { 8, 5, 1 }, { 8, 5, 0 }, { 8, 5, -1 }, { 8, 5, -2 }, { 7, 5, -2 }, { 6, 5, -2 }, { 5, 5, -2 },
				// layer 7
				{ 5, 6, -1 }, { 5, 6, 0 }, { 5, 6, 1 }, { 6, 6, 1 }, { 7, 6, 1 }, { 7, 6, 0 }, { 7, 6, -1 }, { 6, 6, -1 }	
			});
			bp.addBarrierCoords(new int[][] { { 6, 7, 0 } });
			break;
		case HUGE:
			bp.addWallCoords(new int[][] {
				// layer 1
				{ 2, 0, -4 }, { 1, 0, -3 }, { 0, 0, -2 }, { 0, 0, -1 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 2},
				{ 1, 0, 3 }, { 2, 0, 4 }, { 3, 0, 5 }, { 4, 0, 5 }, { 5, 0, 5 }, { 6, 0, 5 }, { 7, 0, 5},
				{ 8, 0, 4 }, {9, 0, 3 }, { 10, 0, 2 }, {10, 0, 1 }, { 10, 0 , 0 }, { 10, 0, -1 }, { 10, 0, -2},
				{ 9, 0, -3 }, { 8, 0, -4 }, { 7, 0, -5 }, { 6, 0, -5 }, { 5, 0, -5 }, { 4, 0, -5 }, { 3, 0, -5 },
				// layer 2
				{ 2, 1, -4 }, { 1, 1, -3 }, { 0, 1, -2 }, { 0, 1, -1 }, { 0, 1, 0 }, { 0, 1, 1 }, { 0, 1, 2},
				{ 1, 1, 3 }, { 2, 1, 4 }, { 3, 1, 5 }, { 4, 1, 5 }, { 5, 1, 5 }, { 6, 1, 5 }, { 7, 1, 5},
				{ 8, 1, 4 }, { 9, 1, 3 }, { 10, 1, 2 }, { 10, 1, 1 }, { 10, 1, 0 }, { 10, 1, -1 }, { 10, 1, -2},
				{ 9, 1, -3 }, { 8, 1, -4 }, { 7, 1, -5 }, { 6, 1, -5 }, { 5, 1, -5 }, { 4, 1, -5 }, { 3, 1, -5 },
				// layer 3
				{ 2, 2, -4 }, { 1, 2, -3 }, { 0, 2, -2 }, { 0, 2, -1 }, { 0, 2, 0 }, { 0, 2, 1 }, { 0, 2, 2},
				{ 1, 2, 3 }, { 2, 2, 4 }, { 3, 2, 5 }, { 4, 2, 5 }, { 5, 2, 5 }, { 6, 2, 5 }, { 7, 2, 5},
				{ 8, 2, 4 }, {9, 2, 3 }, { 10, 2, 2 }, {10, 2, 1 }, { 10, 2, 0 }, { 10, 2, -1 }, { 10, 2, -2},
				{ 9, 2, -3 }, { 8, 2, -4 }, { 7, 2, -5 }, { 6, 2, -5 }, { 5, 2, -5 }, { 4, 2, -5 }, { 3, 2, -5 }
			});
			bp.addRoofCoords(new int[][] {
				// layer 4
				{ 1, 3, -2 }, { 1, 3, -1 }, { 1, 3, 0 }, { 1, 3, 1 }, { 1, 3, 2 }, { 2, 3, 2 }, { 2, 3, 3 }, { 3, 3, 3 },
				{ 3, 3, 4 }, { 4, 3, 4 }, { 5, 3, 4 }, { 6, 3, 4 }, { 7, 3, 4 }, { 7, 3, 3 }, { 8, 3, 3 }, { 8, 3, 2 },
				{ 9, 3, 2 }, { 9, 3, 1 }, { 9, 3, 0 }, { 9, 3, -1 }, { 9, 3, -2 }, { 8, 3, -2 }, { 8, 3, -3 }, { 7, 3, -3},
				{ 7, 3, -4 }, { 6, 3, -4 }, { 5, 3, -4 }, { 4, 3, -4 }, { 3, 3, -4 }, { 3, 3, -3 }, { 2, 3, -3 }, { 2, 3, -2 },
				// layer 5
				{ 2, 4, -1 }, { 2, 4, 0 }, { 2, 4, 1 }, { 3, 4, 2 }, { 4, 4, 3 }, { 5, 4, 3 }, { 6, 4, 3 }, { 7, 4, 2 },
				{ 8, 4, 1 }, { 8, 4, 0 }, { 8, 4, -1 }, { 7, 4, -2 }, { 6, 4, -3 }, { 5, 4, -3 }, { 4, 4, -3 }, { 3, 4, -2 },
				// layer 6
				{ 3, 5, -1 }, { 3, 5, 0 }, { 3, 5, 1 }, { 4, 5, 2 }, { 5, 5, 2 }, { 6, 5, 2 },
				{ 7, 5, 1 }, { 7, 5, 0 }, { 7, 5, -1 }, { 6, 5, -2 }, { 5, 5, -2 }, { 4, 5, -2 },
				// layer 7
				{ 4, 6, -1 }, { 4, 6, 0 }, { 4, 6, 1 }, { 5, 6, 1 }, { 6, 6, 1 }, { 6, 6, 0 }, { 6, 6, -1 }, { 5, 6, -1 }
			});
			bp.addBarrierCoords(new int [][] { { 5, 7, 0 } });
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
					{ 0, 2, -2 }, { 0, 2, -1 }, { 0, 2, 0 }, { 0, 2, 1 }, { 0, 2, 2 }, { 1, 2, 3 }, { 2, 2, 4 },
					{ 3, 2, 4 }, { 4, 2, 4 }, { 5, 2, 4 }, { 6, 2, 4 }, { 7, 2, 3 }, { 8, 2, 2 }, { 8, 2, 1 },
					{ 8, 2, 0 }, { 8, 2, -1 }, { 8, 2, -2 }, { 7, 2, -3 }, { 6, 2, -4 }, { 5, 2, -4 }, { 4, 2, -4 },
					{ 3, 2, -4 }, { 2, 2, -4 }, { 1, 2, -3 } });
			bp.addRoofCoords(new int[][] {
					// layer 1
					{ 1, 3, -2 }, { 1, 3, -1 }, { 1, 3, 0 }, { 1, 3, 1 }, { 1, 3, 2 }, { 2, 3, 2 }, { 2, 3, 3 },
					{ 3, 3, 3 }, { 4, 3, 3 }, { 5, 3, 3 }, { 6, 3, 3 }, { 6, 3, 2 }, { 7, 3, 2 }, { 7, 3, 1 },
					{ 7, 3, 0 }, { 7, 3, -1 }, { 7, 3, -2 }, { 6, 3, -2 }, { 6, 3, -3 }, { 5, 3, -3 }, { 4, 3, -3 },
					{ 3, 3, -3 }, { 2, 3, -3 }, { 2, 3, -2 },
					// layer 2
					{ 2, 4, -1 }, { 2, 4, 0 }, { 2, 4, 1 }, { 3, 4, 1 }, { 3, 4, 2 }, { 4, 4, 2 }, { 5, 4, 2 },
					{ 5, 4, 1 }, { 6, 4, 1 }, { 6, 4, 0 }, { 6, 4, -1 }, { 5, 4, -1 }, { 5, 4, -2 }, { 4, 4, -2 },
					{ 3, 4, -2 }, { 3, 4, -1 },
					// layer 3
					{ 3, 5, 0 }, { 4, 5, 1 }, { 5, 5, 0 }, { 4, 5, -1 } });
			bp.addBarrierCoords(new int[][] { { 4, 6, 0 } });
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
					{ 0, 2, -1 }, { 0, 2, 0 }, { 0, 2, 1 }, { 1, 2, 2 }, { 2, 2, 3 }, { 3, 2, 3 }, { 4, 2, 3 },
					{ 5, 2, 2 }, { 6, 2, 1 }, { 6, 2, 0 }, { 6, 2, -1 }, { 5, 2, -2 }, { 4, 2, -3 }, { 3, 2, -3 },
					{ 2, 2, -3 }, { 1, 2, -2 } });
			bp.addRoofCoords(new int[][] {
					// layer 1
					{ 1, 3, -1 }, { 1, 3, 0 }, { 1, 3, 1 }, { 2, 3, 2 }, { 3, 3, 2 }, { 4, 3, 2 }, { 5, 3, 1 },
					{ 5, 3, 0 }, { 5, 3, -1 }, { 4, 3, -2 }, { 3, 3, -2 }, { 2, 3, -2 },
					// layer 2
					{ 2, 4, -1 }, { 2, 4, 0 }, { 2, 4, 1 }, { 3, 4, 1 }, { 4, 4, 1 }, { 4, 4, 0 }, { 4, 4, -1 },
					{ 3, 4, -1 } });
			bp.addBarrierCoords(new int[][] { { 3, 5, 0 } });
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
					{ 0, 2, 1 }, { 0, 2, 0 }, { 0, 2, -1 }, { 1, 2, -2 }, { 2, 2, -2 }, { 3, 2, -2 }, { 4, 2, -1 },
					{ 4, 2, 0 }, { 4, 2, 1 }, { 3, 2, 2 }, { 2, 2, 2 }, { 1, 2, 2 } });
			bp.addRoofCoords(new int[][] { { 1, 3, 1 }, { 1, 3, 0 }, { 1, 3, -1 }, { 2, 3, -1 }, { 3, 3, -1 },
					{ 3, 3, 0 }, { 3, 3, 1 }, { 2, 3, 1 }, { 0, 3, 0 }, { 2, 3, -2 }, { 4, 3, 0 }, { 2, 3, 2 } });
			bp.addBarrierCoords(new int[][] { { 2, 4, 0 } });
			break;
		}
		return bp;
	}
}
