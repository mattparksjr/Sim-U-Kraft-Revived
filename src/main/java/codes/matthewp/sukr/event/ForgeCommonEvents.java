package codes.matthewp.sukr.event;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.command.FactionCommand;
import codes.matthewp.sukr.data.SimDataManager;
import codes.matthewp.sukr.data.folk.FolkNameData;
import codes.matthewp.sukr.data.player.PlayerDataProvider;
import codes.matthewp.sukr.data.structure.StructureData;
import codes.matthewp.sukr.entity.EntityFolk;
import codes.matthewp.sukr.init.BlockInit;
import codes.matthewp.sukr.init.ItemInit;
import codes.matthewp.sukr.net.PacketHandler;
import codes.matthewp.sukr.net.packet.FactionAddedS2CPacket;
import codes.matthewp.sukr.net.packet.sync.SyncGamemodeS2CPacket;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Set;

@Mod.EventBusSubscriber(modid = SimUKraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeCommonEvents {

    @SubscribeEvent
    public static void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity().level().isClientSide) return;

        // Sync
        if (SimDataManager.get(event.getEntity().getServer().overworld()).getData().getPlayerFaction(event.getEntity().getUUID()) != null) {
            PacketHandler.sendToPlayer(new FactionAddedS2CPacket(SimDataManager.get(event.getEntity().getServer().overworld()).getData().getPlayerFaction(event.getEntity().getUUID())), (ServerPlayer) event.getEntity());
        }
        PacketHandler.sendToPlayer(new SyncGamemodeS2CPacket(SimDataManager.get(event.getEntity().getServer().overworld()).getGamemode(), false), (ServerPlayer) event.getEntity());

        // Debug
        ItemHandlerHelper.giveItemToPlayer(event.getEntity(), new ItemStack(BlockInit.BLOCK_CONSTRUCTOR_ITEM.get()));

        // Gameplay related.
        if (SimDataManager.get(event.getEntity().getServer().overworld()).getGamemode() == -1) {
            if (!event.getEntity().getInventory().hasAnyOf(Set.of(ItemInit.ITEM_GAMEMODE.get()))) {
                ItemHandlerHelper.giveItemToPlayer(event.getEntity(), new ItemStack(ItemInit.ITEM_GAMEMODE.get()));
            }
        }
    }

    @SubscribeEvent
    public static void despawn(MobSpawnEvent.AllowDespawn event) {
        if (event.getEntity() instanceof EntityFolk) {
            event.setResult(Event.Result.DENY);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void dataReloadEvent(AddReloadListenerEvent event) {
        event.addListener(new FolkNameData());
        event.addListener(new StructureData());
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            TickHandler.handleTick(event.getServer());
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerDataProvider.PLAYER_DATA).isPresent()) {
                event.addCapability(new ResourceLocation(SimUKraft.MODID, "properties"), new PlayerDataProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(oldStore -> {
            event.getEntity().getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(newStore -> {
                newStore.copyFrom(oldStore);
            });
        });
        event.getOriginal().invalidateCaps();
    }

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> commandDispatcher = event.getDispatcher();
        FactionCommand.register(commandDispatcher);
    }
}
