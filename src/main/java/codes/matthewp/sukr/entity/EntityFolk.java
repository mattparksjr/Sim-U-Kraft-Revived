package codes.matthewp.sukr.entity;

import codes.matthewp.sukr.data.folk.FolkData;
import codes.matthewp.sukr.init.EntityInit;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityFolk extends AgeableMob {

    public static final EntityDataAccessor<Integer> SKIN_ID = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<String> FIRST_NAME = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> LAST_NAME = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> LEVEL_FOOD = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Float> LEVEL_BUILDING = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Float> LEVEL_MINING = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Float> LEVEL_SOLIDER = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<BlockPos> JOB_SITE = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.BLOCK_POS);
    public static final EntityDataAccessor<BlockPos> HOME = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.BLOCK_POS);

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
        ((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
    }

    @Override
    public void kill() {
        super.kill();
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor levelAccessor, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType type, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        FolkData data = FolkData.generateRandomFolk();

        this.entityData.set(SKIN_ID, data.skinID);
        this.entityData.set(FIRST_NAME, data.firstName);
        this.entityData.set(LAST_NAME, data.lastName);
        this.entityData.set(GENDER, data.gender);
        if (type.equals(MobSpawnType.BREEDING)) {
            this.entityData.set(AGE, 1);
        } else {
            this.entityData.set(AGE, data.age);
        }
        this.entityData.set(LEVEL_FOOD, data.foodLevel);
        this.entityData.set(LEVEL_BUILDING, data.skillBuild);
        this.entityData.set(LEVEL_MINING, data.skillMine);
        this.entityData.set(LEVEL_SOLIDER, data.skillSoldier);
        return super.finalizeSpawn(levelAccessor, difficulty, type, spawnData, tag);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder getFolkAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.MOVEMENT_SPEED, 0.5D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob mob) {
        return EntityInit.FOLK.get().create(level);
    }

    @Override
    public void onItemPickup(ItemEntity itemEntity) {
        super.onItemPickup(itemEntity);
        setItemInHand(InteractionHand.MAIN_HAND, itemEntity.getItem());
    }

    @Override
    public @NotNull InteractionResult interactAt(@NotNull Player player, @NotNull Vec3 loc, @NotNull InteractionHand hand) {
        if (this.level().isClientSide) return InteractionResult.PASS;
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Data: " + this.entityData.get(SKIN_ID) + " , " + this.entityData.get(HOME)));
        return super.interactAt(player, loc, hand);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(SKIN_ID, tag.getInt("skin_id"));
        this.entityData.set(FIRST_NAME, tag.getString("first_name"));
        this.entityData.set(LAST_NAME, tag.getString("last_name"));
        this.entityData.set(AGE, tag.getInt("age"));
        this.entityData.set(GENDER, tag.getInt("gender"));
        this.entityData.set(LEVEL_FOOD, tag.getInt("food"));
        this.entityData.set(LEVEL_BUILDING, tag.getFloat("food"));
        this.entityData.set(LEVEL_MINING, tag.getFloat("food"));
        this.entityData.set(LEVEL_SOLIDER, tag.getFloat("food"));
        if (!(tag.get("job_site") == null)) {
            this.entityData.set(JOB_SITE, NbtUtils.readBlockPos((CompoundTag) tag.get("job_site")));
        } else {
            this.entityData.set(JOB_SITE, new BlockPos(0, 999, 0));
        }

        if (!(tag.get("home") == null)) {
            this.entityData.set(HOME, NbtUtils.readBlockPos((CompoundTag) tag.get("home")));
        } else {
            this.entityData.set(HOME, new BlockPos(0, 999, 0));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("skin_id", this.entityData.get(SKIN_ID));
        tag.putString("first_name", this.entityData.get(FIRST_NAME));
        tag.putString("last_name", this.entityData.get(LAST_NAME));
        tag.putInt("age", this.entityData.get(AGE));
        tag.putInt("gender", this.entityData.get(GENDER));
        tag.putInt("food", this.entityData.get(LEVEL_FOOD));
        tag.putFloat("lvl_building", this.entityData.get(LEVEL_BUILDING));
        tag.putFloat("lvl_mining", this.entityData.get(LEVEL_MINING));
        tag.putFloat("lvl_solider", this.entityData.get(LEVEL_SOLIDER));
        tag.put("job_site", NbtUtils.writeBlockPos(this.entityData.get(JOB_SITE)));
        tag.put("home", NbtUtils.writeBlockPos(this.entityData.get(HOME)));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SKIN_ID, 999);
        this.entityData.define(FIRST_NAME, "first_name");
        this.entityData.define(LAST_NAME, "last_name");
        this.entityData.define(AGE, 999);
        this.entityData.define(GENDER, 999);
        this.entityData.define(LEVEL_FOOD, 999);
        this.entityData.define(LEVEL_BUILDING, 999f);
        this.entityData.define(LEVEL_MINING, 999f);
        this.entityData.define(LEVEL_SOLIDER, 99f);
        this.entityData.define(JOB_SITE, new BlockPos(0, 0, 0));
        this.entityData.define(HOME, new BlockPos(0, 0, 0));
    }
}
