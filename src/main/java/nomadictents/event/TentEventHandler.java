package nomadictents.event;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ChorusFruitItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import nomadictents.dimension.TentManager;
import nomadictents.dimension.TentTeleporter;
import nomadictents.init.TentConfig;
import nomadictents.item.ItemTent;
import nomadictents.structure.util.TentData;

public class TentEventHandler {
	
	@SubscribeEvent
	public void onServerStarting(final FMLServerStartingEvent event) {
		System.out.println("yurtmod: FMLServerStartingEvent");
		TentManager.registerDimension();
	}
	
	/**
	 * This code is called AFTER a player wakes up but BEFORE any subsequent
	 * code has been called. Should be ok to change time values here.
	 * Used to sync world time in Overworld and Tent Dimension when a player 
	 * sleeps and wakes up in a Tent
	 **/
	@SubscribeEvent
	public void onPlayerWake(final PlayerWakeUpEvent event) {
		if(!event.getPlayer().getEntityWorld().isRemote 
				&& event.getPlayer().getEntityWorld().getGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE)) {
			final MinecraftServer server = event.getPlayer().getServer();
			final ServerWorld overworld = TentManager.getOverworld(server);
			final ServerWorld tentDim = TentManager.getTentWorld(server);
			// only run this code for players waking up in a Tent
			if(TentManager.isTent(event.getPlayer().getEntityWorld())) {
				boolean shouldChangeTime = TentConfig.CONFIG.ALLOW_SLEEP_TENT_DIM.get();
				// if config requires, check both overworld and tent players
				if(TentConfig.CONFIG.IS_SLEEPING_STRICT.get()) {
					// find out if ALL players in BOTH dimensions are sleeping
					for(PlayerEntity p : overworld.getPlayers()) {
						// (except for the one who just woke up, of course)
						if(p != event.getPlayer()) {
							shouldChangeTime &= p.isSleeping();
						}
					}
				}
				if(shouldChangeTime) {
					// the time just as the player wakes up, before it is changed to day
					long currentTime = overworld.getWorldInfo().getDayTime();
					overworld.getWorldInfo().setDayTime(currentTime - currentTime % 24000L);
				}
			}
			// sleeping anywhere should always sync tent to overworld
			tentDim.getWorldInfo().setDayTime(overworld.getDayTime());
			// update sleeping flags
			overworld.updateAllPlayersSleepingFlag();
			tentDim.updateAllPlayersSleepingFlag();
		}
		
	}

	/** Makes Tent items fireproof if enabled **/
	@SubscribeEvent
	public void onSpawnEntity(EntityJoinWorldEvent event) {
		if (TentConfig.CONFIG.IS_TENT_FIREPROOF.get() && event.getEntity() instanceof ItemEntity) {
			ItemStack stack = ((ItemEntity) event.getEntity()).getItem();
			if (stack != null && stack.getItem() instanceof ItemTent) {
				event.getEntity().setInvulnerable(true);
			}
		}
	}
	
	/**
	 * Cancel non-creative player teleportation using Chorus Fruit, if config requires
	 **/
	@SubscribeEvent
	public void onItemUse(LivingEntityUseItemEvent.Start event) {
		if(event.getEntityLiving() instanceof PlayerEntity && !event.getItem().isEmpty() 
				&& event.getItem().getItem() instanceof ChorusFruitItem) {
			PlayerEntity player = (PlayerEntity)event.getEntityLiving();
			if(canCancelTeleport(player)) {
				event.setDuration(-100);
				player.sendStatusMessage(new TranslationTextComponent("chat.no_teleport").applyTextStyle(TextFormatting.RED), true);
			}
		}
	}

	/**
	 * Cancel all non-creative player teleportation in tent dimension, if config requires
	 **/
	@SubscribeEvent
	public void onTeleport(final EnderTeleportEvent event) {
		if(event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)event.getEntityLiving();
			if(canCancelTeleport(player)) {
				event.setCanceled(true);
				player.sendStatusMessage(new TranslationTextComponent("chat.no_teleport").applyTextStyle(TextFormatting.RED), true);
			}
		}
	}
	
	/** @return whether the teleporting should be canceled according to conditions and config **/
	private static boolean canCancelTeleport(final PlayerEntity player) {
		return TentConfig.CONFIG.RESTRICT_TELEPORT_TENT_DIM.get() && TentManager.isTent(player.getEntityWorld()) 
				&& !player.isCreative();
	}

	/**
	 * EXPERIMENTAL Used to stop players who die in Tent Dimension, without beds,
	 * from falling forever into the void
	 **/
	@SubscribeEvent
	public void onPlayerRespawn(final PlayerRespawnEvent event) {
		if (event.getPlayer() instanceof ServerPlayerEntity && !event.getPlayer().getEntityWorld().isRemote) {
			final DimensionType TENTDIM = TentManager.getTentDim();
			final DimensionType RESPAWN = TentManager.getOverworldDim();
			final DimensionType CUR_DIM = event.getPlayer().getEntityWorld().getDimension().getType();
			ServerPlayerEntity playerMP = (ServerPlayerEntity) event.getPlayer();
			final ServerWorld overworld = playerMP.getServer().getWorld(RESPAWN);
			// do all kind of checks to make sure you need to run this code...
			if (TentConfig.CONFIG.ALLOW_RESPAWN_INTERCEPT.get() && CUR_DIM.getId() == TENTDIM.getId()) {
				BlockPos bedPos = playerMP.getBedLocation(TENTDIM);
				BlockPos respawnPos = bedPos != null ? event.getPlayer().getBedLocation(TENTDIM)
						/*PlayerEntity.getBedLocation(tentServer, bedPos, false)*/ : null;
				if (null == respawnPos) {
					// player respawned in tent dimension without a bed here
					// this likely means they're falling to their death in the void
					// let's do something about that
					// first:  try to find their overworld bed
					bedPos = playerMP.getBedLocation(RESPAWN);
					respawnPos = bedPos != null ? event.getPlayer().getBedLocation(RESPAWN)
							/* PlayerEntity.getBedLocation(overworld, bedPos, false) */ : null;
					if (respawnPos == null) {
						// they have no bed at all, send them to world spawn
						respawnPos = overworld.getSpawnPoint();
					}
					// transfer player using Teleporter
					final TentTeleporter tel = new TentTeleporter(playerMP.getServer(), TENTDIM, RESPAWN,
							new BlockPos(0, 0, 0), null, respawnPos.getX(), respawnPos.getY(), respawnPos.getZ(),
							event.getPlayer().rotationYaw, new TentData());
					
					tel.teleport(playerMP);
					
					// TODO
					// mcServer.getPlayerList().transferPlayerToDimension(playerMP, RESPAWN, tel);
					event.getPlayer().setPositionAndUpdate(respawnPos.getX(), respawnPos.getY(), respawnPos.getZ());
				}
			} 
		}
	}
	
	@SubscribeEvent
	public void onNameFormat(final PlayerEvent.NameFormat event) {
		String PREFIX = "[Nomad King] ";
		String GOLD = "";
		String RESET = "";
		// attempt to avoid crashing on dedicated server
		try {
			GOLD = net.minecraft.util.text.TextFormatting.GOLD.toString();
			RESET = net.minecraft.util.text.TextFormatting.RESET.toString();
		} catch(Exception e) { }
		
		if("skyjay1".equals(event.getUsername())) {
			String special = GOLD + PREFIX + RESET;
			event.setDisplayname(special.concat(event.getDisplayname()));
		}
	}
}