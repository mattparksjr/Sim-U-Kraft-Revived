package codes.matthewp.sukr.net;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.net.packet.*;
import codes.matthewp.sukr.net.packet.sync.*;
import codes.matthewp.sukr.net.packet.update.FireWorkerC2SPacket;
import codes.matthewp.sukr.net.packet.update.SetWorkerC2SPacket;
import codes.matthewp.sukr.net.packet.update.WorkerSetS2CPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.commands.FillBiomeCommand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel INSTANCE;

    private static int packetID = 0;

    private static int id() {
        return packetID++;
    }

    public static void init() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(SimUKraft.MODID, "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        INSTANCE.messageBuilder(GamemodeCheckC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(GamemodeCheckC2SPacket::new)
                .encoder(GamemodeCheckC2SPacket::toBytes)
                .consumerMainThread(GamemodeCheckC2SPacket::handle)
                .add();
        INSTANCE.messageBuilder(SyncGamemodeS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncGamemodeS2CPacket::new)
                .encoder(SyncGamemodeS2CPacket::toBytes)
                .consumerMainThread(SyncGamemodeS2CPacket::handle)
                .add();
        INSTANCE.messageBuilder(SetGamemodeC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SetGamemodeC2SPacket::new)
                .encoder(SetGamemodeC2SPacket::toBytes)
                .consumerMainThread(SetGamemodeC2SPacket::handle)
                .add();
        INSTANCE.messageBuilder(FolkSpawnedS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FolkSpawnedS2CPacket::new)
                .encoder(FolkSpawnedS2CPacket::toBytes)
                .consumerMainThread(FolkSpawnedS2CPacket::handle)
                .add();
        INSTANCE.messageBuilder(CreateFactionC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CreateFactionC2SPacket::new)
                .encoder(CreateFactionC2SPacket::toBytes)
                .consumerMainThread(CreateFactionC2SPacket::handle)
                .add();
        INSTANCE.messageBuilder(FactionAddedS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FactionAddedS2CPacket::new)
                .encoder(FactionAddedS2CPacket::toBytes)
                .consumerMainThread(FactionAddedS2CPacket::handle)
                .add();
        INSTANCE.messageBuilder(RequestHireBuilderInfoC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(RequestHireBuilderInfoC2SPacket::new)
                .encoder(RequestHireBuilderInfoC2SPacket::toBytes)
                .consumerMainThread(RequestHireBuilderInfoC2SPacket::handle)
                .add();
        INSTANCE.messageBuilder(SyncAvailableBuildersS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncAvailableBuildersS2CPacket::new)
                .encoder(SyncAvailableBuildersS2CPacket::toBytes)
                .consumerMainThread(SyncAvailableBuildersS2CPacket::handle)
                .add();
        INSTANCE.messageBuilder(SetWorkerC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SetWorkerC2SPacket::new)
                .encoder(SetWorkerC2SPacket::toBytes)
                .consumerMainThread(SetWorkerC2SPacket::handle)
                .add();
        INSTANCE.messageBuilder(WorkerSetS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(WorkerSetS2CPacket::new)
                .encoder(WorkerSetS2CPacket::toBytes)
                .consumerMainThread(WorkerSetS2CPacket::handle)
                .add();
        INSTANCE.messageBuilder(RequestStructureListC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(RequestStructureListC2SPacket::new)
                .encoder(RequestStructureListC2SPacket::toBytes)
                .consumerMainThread(RequestStructureListC2SPacket::handle)
                .add();
        INSTANCE.messageBuilder(SyncStructuresS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncStructuresS2CPacket::new)
                .encoder(SyncStructuresS2CPacket::toBytes)
                .consumerMainThread(SyncStructuresS2CPacket::handle)
                .add();
        INSTANCE.messageBuilder(FireWorkerC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(FireWorkerC2SPacket::new)
                .encoder(FireWorkerC2SPacket::toBytes)
                .consumerMainThread(FireWorkerC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG msg) {
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToAllPlayers(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
