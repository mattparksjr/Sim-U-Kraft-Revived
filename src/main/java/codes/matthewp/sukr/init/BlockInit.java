package codes.matthewp.sukr.init;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.block.BlockConstructor;
import codes.matthewp.sukr.block.BlockController;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SimUKraft.MODID);
    public static final RegistryObject<Block> BLOCK_CONSTRUCTOR = BLOCKS.register("block_constructor", BlockConstructor::new);
    public static final RegistryObject<Block> BLOCK_CONTROLLER = BLOCKS.register("block_controller", BlockController::new);
    public static final RegistryObject<Block> BLOCK_LIGHT = BLOCKS.register("block_light", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.SNOW)
            .lightLevel((e) -> 10)));
    public static final RegistryObject<Block> BLOCK_LIGHT_RED = BLOCKS.register("block_light_red", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.SNOW)
            .lightLevel((e) -> 10)));
    public static final RegistryObject<Block> BLOCK_LIGHT_GREEN = BLOCKS.register("block_light_green", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.SNOW)
            .lightLevel((e) -> 10)));
    public static final RegistryObject<Block> BLOCK_LIGHT_BLUE = BLOCKS.register("block_light_blue", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.SNOW)
            .lightLevel((e) -> 10)));
    public static final RegistryObject<Block> BLOCK_LIGHT_ORANGE = BLOCKS.register("block_light_orange", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.SNOW)
            .lightLevel((e) -> 10)));
    public static final RegistryObject<Block> BLOCK_LIGHT_PURPLE = BLOCKS.register("block_light_purple", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.SNOW)
            .lightLevel((e) -> 10)));
    public static final RegistryObject<Block> BLOCK_LIGHT_YELLOW = BLOCKS.register("block_light_yellow", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.SNOW)
            .lightLevel((e) -> 10)));

    public static final RegistryObject<Block> BLOCK_LIGHT_RAINBOW = BLOCKS.register("block_light_rainbow", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.SNOW)
            .lightLevel((e) -> 10)));


    public static final RegistryObject<Item> BLOCK_CONSTRUCTOR_ITEM = ItemInit.ITEMS.register("block_constructor", () -> new BlockItem(BLOCK_CONSTRUCTOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_CONTROLLER_ITEM = ItemInit.ITEMS.register("block_controller", () -> new BlockItem(BLOCK_CONTROLLER.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_LIGHT_ITEM = ItemInit.ITEMS.register("block_light", () -> new BlockItem(BLOCK_LIGHT.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_LIGHT_ITEM_RED = ItemInit.ITEMS.register("block_light_red", () -> new BlockItem(BLOCK_LIGHT_RED.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_LIGHT_ITEM_GREEN = ItemInit.ITEMS.register("block_light_green", () -> new BlockItem(BLOCK_LIGHT_GREEN.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_LIGHT_ITEM_BLUE = ItemInit.ITEMS.register("block_light_blue", () -> new BlockItem(BLOCK_LIGHT_BLUE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_LIGHT_ITEM_ORANGE = ItemInit.ITEMS.register("block_light_orange", () -> new BlockItem(BLOCK_LIGHT_ORANGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_LIGHT_ITEM_PURPLE = ItemInit.ITEMS.register("block_light_purple", () -> new BlockItem(BLOCK_LIGHT_PURPLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_LIGHT_ITEM_YELLOW = ItemInit.ITEMS.register("block_light_yellow", () -> new BlockItem(BLOCK_LIGHT_YELLOW.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_LIGHT_ITEM_RAINBOW = ItemInit.ITEMS.register("block_light_rainbow", () -> new BlockItem(BLOCK_LIGHT_RAINBOW.get(), new Item.Properties()));
}
