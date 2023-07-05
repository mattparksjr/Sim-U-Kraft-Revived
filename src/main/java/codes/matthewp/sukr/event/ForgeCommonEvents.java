package codes.matthewp.sukr.event;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.SimDataManager;
import codes.matthewp.sukr.data.folk.FolkNameData;
import codes.matthewp.sukr.entity.EntityFolk;
import codes.matthewp.sukr.init.EntityInit;
import codes.matthewp.sukr.init.ItemInit;
import codes.matthewp.sukr.net.PacketHandler;
import codes.matthewp.sukr.net.packet.SyncGamemodeS2CPacket;
import net.minecraft.client.telemetry.events.WorldLoadEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;

@Mod.EventBusSubscriber(modid = SimUKraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeCommonEvents {

    @SubscribeEvent
    public static void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
        PacketHandler.sendToPlayer(new SyncGamemodeS2CPacket(SimDataManager.get(event.getEntity().getServer().overworld()).getGamemode(), false), (ServerPlayer) event.getEntity());
        if (SimDataManager.get(event.getEntity().getServer().overworld()).getGamemode() == -1) {
            ItemHandlerHelper.giveItemToPlayer(event.getEntity(), new ItemStack(ItemInit.ITEM_GAMEMODE.get()));
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
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if(event.phase == TickEvent.Phase.END) {
            TickHandler.handleTick(event.getServer());
        }
    }
}
