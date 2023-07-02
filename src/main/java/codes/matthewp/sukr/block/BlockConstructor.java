package codes.matthewp.sukr.block;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.init.BlockEntityInit;
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
        super(Properties.of().sound(SoundType.WOOD).strength(1f, 1f));
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
            if(blockEntity instanceof BlockConstructorEntity blockConstructorEntity) {
                blockConstructorEntity.tick();
            }
        };
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(level.isClientSide) {
            ClientUtil.showConstructor((BlockConstructorEntity) level.getBlockEntity(pos));
        }
        return super.use(state, level, pos, player, hand, result);
    }
}
