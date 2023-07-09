package codes.matthewp.sukr.init;

import codes.matthewp.sukr.SimUKraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundInit {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SimUKraft.MODID);

    public static final RegistryObject<SoundEvent> CONSTRUCTOR_ACTIVATED = registerSound("constructor_activated");
    public static final RegistryObject<SoundEvent> CONTROLLER_BEEP = registerSound("controller_beep");

    public static final ForgeSoundType CONSTRUCTOR_SOUNDS = new ForgeSoundType(
            1f, 1.3f,
            () -> SoundEvents.WOOD_BREAK,
            () -> SoundEvents.WOOD_STEP,
            CONSTRUCTOR_ACTIVATED,
            () -> SoundEvents.WOOD_HIT,
            () -> SoundEvents.WOOD_FALL);

    public static final ForgeSoundType CONTROLLER_SOUNDS = new ForgeSoundType(
            1f, 1f,
            () -> SoundEvents.WOOD_BREAK,
            () -> SoundEvents.WOOD_STEP,
            () -> SoundEvents.WOOD_BREAK,
            () -> SoundEvents.WOOD_HIT,
            () -> SoundEvents.WOOD_FALL);


    private static RegistryObject<SoundEvent> registerSound(String name) {
        ResourceLocation id = new ResourceLocation(SimUKraft.MODID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
