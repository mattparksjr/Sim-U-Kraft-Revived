package codes.matthewp.sukr.init;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.entity.EntityFolk;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SimUKraft.MODID);

    public static final RegistryObject<EntityType<EntityFolk>> FOLK = ENTITIES.register("folk", () -> EntityType.Builder.of(EntityFolk::new, MobCategory.CREATURE).build(SimUKraft.MODID + ":folk"));

}
