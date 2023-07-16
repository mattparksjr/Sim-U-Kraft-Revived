package codes.matthewp.sukr.block;

import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.init.BlockEntityInit;
import codes.matthewp.sukr.init.SoundInit;
import codes.matthewp.sukr.util.ClientUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockConstructor extends Block implements EntityBlock {

    public BlockConstructor() {
        super(Properties.of().sound(SoundType.WOOD).strength(2.0f, 1.0f).sound(SoundInit.CONSTRUCTOR_SOUNDS));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return BlockEntityInit.CONSTRUCTOR.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return level.isClientSide ? null : ($0, pos, $1, blockEntity) -> {
            if (blockEntity instanceof BlockConstructorEntity blockConstructorEntity) {
                blockConstructorEntity.tick();
            }
        };
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        if (level.isClientSide) {
            ClientUtil.showConstructor((BlockConstructorEntity) level.getBlockEntity(pos), player);
        }
        return super.use(state, level, pos, player, hand, result);
    }
}
