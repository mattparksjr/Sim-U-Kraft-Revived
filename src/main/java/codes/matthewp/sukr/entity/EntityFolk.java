package codes.matthewp.sukr.entity;

import codes.matthewp.sukr.init.EntityInit;
import com.google.common.collect.ImmutableList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EntityFolk extends AgeableMob {

    private static final EntityDataAccessor<Integer> SKIN_ID = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.INT);

    private static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(
            MemoryModuleType.HOME, MemoryModuleType.JOB_SITE, MemoryModuleType.MEETING_POINT,
            MemoryModuleType.NEAREST_LIVING_ENTITIES, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_PLAYERS, MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM,
            MemoryModuleType.ITEM_PICKUP_COOLDOWN_TICKS, MemoryModuleType.WALK_TARGET,
            MemoryModuleType.LOOK_TARGET, MemoryModuleType.INTERACTION_TARGET,
            MemoryModuleType.BREED_TARGET, MemoryModuleType.PATH,
            MemoryModuleType.DOORS_TO_CLOSE, MemoryModuleType.NEAREST_BED,
            MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.NEAREST_HOSTILE, MemoryModuleType.HIDING_PLACE,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.LAST_SLEPT,
            MemoryModuleType.LAST_WOKEN, MemoryModuleType.LAST_WORKED_AT_POI);
    private static final ImmutableList<SensorType<? extends Sensor<? super EntityFolk>>> SENSOR_TYPES = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS,
            SensorType.NEAREST_ITEMS, SensorType.NEAREST_BED, SensorType.HURT_BY,
            SensorType.VILLAGER_HOSTILES);

    public EntityFolk(EntityType<? extends AgeableMob> entityType, Level level) {
        super(entityType, level);
        this.getNavigation().setCanFloat(true);
        this.setCanPickUpLoot(true);
        this.setCustomName(Component.literal("EEEEEE"));
        this.setCustomNameVisible(true);
        ((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder getFolkAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.MOVEMENT_SPEED, 0.5D);
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return EntityInit.FOLK.get().create(level);
    }



    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(SKIN_ID, tag.getInt("skin_id"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("skin_id", getSkinID());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SKIN_ID, 0);
    }

    public int getSkinID() {
        return this.entityData.get(SKIN_ID);
    }

    public void setSkinID(int id) {
        this.entityData.set(SKIN_ID, id);
    }
}
