package codes.matthewp.sukr.net;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.net.packet.*;
import codes.matthewp.sukr.net.packet.sync.RequestHireBuilderInfoC2SPacket;
import codes.matthewp.sukr.net.packet.sync.SyncAvailableBuildersS2CPacket;
import codes.matthewp.sukr.net.packet.sync.SyncGamemodeS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

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

        INSTANCE.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ExampleC2SPacket::new)
                .encoder(ExampleC2SPacket::toBytes)
                .consumerMainThread(ExampleC2SPacket::handle)
                .add();
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
