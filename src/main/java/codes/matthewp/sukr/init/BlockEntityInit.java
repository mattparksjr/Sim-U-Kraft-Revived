package codes.matthewp.sukr.init;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.block.entity.BlockControllerEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SimUKraft.MODID);

    public static final RegistryObject<BlockEntityType<BlockConstructorEntity>> CONSTRUCTOR = BLOCK_ENTITIES.register("block_constructor",
            () -> BlockEntityType.Builder.of(BlockConstructorEntity::new, BlockInit.BLOCK_CONSTRUCTOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlockControllerEntity>> CONTROLLER = BLOCK_ENTITIES.register("block_controller",
            () -> BlockEntityType.Builder.of(BlockControllerEntity::new, BlockInit.BLOCK_CONTROLLER.get()).build(null));
}
