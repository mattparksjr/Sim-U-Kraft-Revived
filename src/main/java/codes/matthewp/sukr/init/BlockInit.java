package codes.matthewp.sukr.init;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.block.BlockConstructor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SimUKraft.MODID);
    public static final RegistryObject<Block> BLOCK_CONSTRUCTOR = BLOCKS.register("block_constructor", BlockConstructor::new);
    public static final RegistryObject<Item> BLOCK_CONSTRUCTOR_ITEM = ItemInit.ITEMS.register("block_constructor", () -> new BlockItem(BLOCK_CONSTRUCTOR.get(), new Item.Properties()));

}
