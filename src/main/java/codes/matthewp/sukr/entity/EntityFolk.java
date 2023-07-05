package codes.matthewp.sukr.entity;

import codes.matthewp.sukr.data.folk.FolkData;
import codes.matthewp.sukr.data.folk.FolkDataSerializer;
import codes.matthewp.sukr.data.folk.FolkNameData;
import codes.matthewp.sukr.init.EntityInit;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
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

    public static final EntityDataAccessor<Integer> SKIN_ID = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<String> FIRST_NAME = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> LAST_NAME = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> LEVEL_FOOD = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Float> LEVEL_BUILDING = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Float> LEVEL_MINING = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Float> LEVEL_SOLIDER = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.FLOAT);
  //  public static final EntityDataAccessor<BlockPos> JOB_SITE = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.BLOCK_POS);
 //   public static final EntityDataAccessor<BlockPos> HOME = SynchedEntityData.defineId(EntityFolk.class, EntityDataSerializers.BLOCK_POS);


    public FolkData folkData;

 //   private static final EntityDataAccessor<FolkData> TEST_VAR = SynchedEntityData.defineId(EntityFolk.class, new FolkDataSerializer<>());

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
        this.folkData = FolkData.generateRandomFolk();
        ((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
        // TODO
        this.entityData.set(SKIN_ID, 1);
        this.entityData.set(FIRST_NAME, FolkNameData.getMaleNames().get(0));
        this.entityData.set(LAST_NAME, FolkNameData.getLastNames().get(0));
        this.entityData.set(GENDER, 0);
        this.entityData.set(AGE, 18);
        this.entityData.set(LEVEL_SOLIDER, 1f);
        this.entityData.set(LEVEL_MINING, 1f);
        this.entityData.set(LEVEL_BUILDING, 1f);
        this.entityData.set(LEVEL_FOOD, 10);
     //   this.entityData.set(JOB_SITE, new BlockPos(0, -999, 0));
    //    this.entityData.set(HOME, new BlockPos(0, -999, 0));
  //      this.entityData.set(TEST_VAR, this.folkData);
    }

    @Override
    public void kill() {
        super.kill();
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
        this.entityData.set(FIRST_NAME, tag.getString("first_name"));
        this.entityData.set(LAST_NAME, tag.getString("last_name"));
        this.entityData.set(AGE, tag.getInt("age"));
        this.entityData.set(GENDER, tag.getInt("gender"));
        this.entityData.set(LEVEL_FOOD, tag.getInt("food"));
        this.entityData.set(LEVEL_BUILDING, tag.getFloat("food"));
        this.entityData.set(LEVEL_MINING, tag.getFloat("food"));
        this.entityData.set(LEVEL_SOLIDER, tag.getFloat("food"));

     //   this.entityData.set(JOB_SITE, NbtUtils.readBlockPos((CompoundTag) tag.get("job_site")));
      //  this.entityData.set(HOME, NbtUtils.readBlockPos((CompoundTag) tag.get("home")));
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
     //   tag.put("folk_data", this.entityData.get(TEST_VAR));
    //    tag.put("job_site", NbtUtils.writeBlockPos(this.entityData.get(JOB_SITE)));
    //    tag.put("home", NbtUtils.writeBlockPos(this.entityData.get(HOME)));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
      //  this.entityData.define(TEST_VAR, FolkData.generateRandomFolk());
        this.entityData.define(SKIN_ID, 0);
        this.entityData.define(FIRST_NAME, "first_name");
        this.entityData.define(LAST_NAME, "last_name");
        this.entityData.define(AGE, 1);
        this.entityData.define(GENDER, 2);
        this.entityData.define(LEVEL_FOOD, 3);
        this.entityData.define(LEVEL_BUILDING, 0.0f);
        this.entityData.define(LEVEL_MINING, 1.0f);
        this.entityData.define(LEVEL_SOLIDER, 2.0f);
       // this.entityData.define(JOB_SITE, new BlockPos(0,0,0));
    //    this.entityData.define(HOME, new BlockPos(1, 0, 0));
    }
}
