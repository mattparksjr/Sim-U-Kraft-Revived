package codes.matthewp.sukr;

import codes.matthewp.sukr.event.ForgeClientEvents;
import codes.matthewp.sukr.event.ForgeCommonEvents;
import codes.matthewp.sukr.event.ModClientEvents;
import codes.matthewp.sukr.event.ModCommonEvents;
import codes.matthewp.sukr.init.*;
import codes.matthewp.sukr.net.PacketHandler;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(SimUKraft.MODID)
public class SimUKraft {

    public static final String MODID = "simukraftr";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("simukrafttab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ItemInit.ITEM_GAMEMODE.get().getDefaultInstance())
            .title(Component.translatable(MODID + ".tab"))
            .displayItems((parameters, output) -> {
                output.accept(ItemInit.ITEM_GAMEMODE.get());
                output.accept(BlockInit.BLOCK_CONSTRUCTOR.get());
            }).build());

    public SimUKraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        EntityInit.ENTITIES.register(modEventBus);
        BlockEntityInit.BLOCK_ENTITIES.register(modEventBus);
        SoundInit.SOUNDS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ForgeClientEvents());
        MinecraftForge.EVENT_BUS.register(new ForgeCommonEvents());
        modEventBus.register(new ModCommonEvents());
        modEventBus.register(new ModClientEvents());
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ItemInit.ITEM_CHEESE);
            event.accept(ItemInit.ITEM_BURGER);
            event.accept(ItemInit.ITEM_CHEESEBURGER);
            event.accept(ItemInit.ITEM_FRIES);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.debug("Common setup running...");
        PacketHandler.init();
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.debug("Server startup running...");
    }

}

