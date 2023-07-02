package codes.matthewp.sukr.item;

import codes.matthewp.sukr.data.ClientSimData;
import codes.matthewp.sukr.gui.ScreenGamemode;
import codes.matthewp.sukr.util.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedGoldenAppleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ItemGamemodeSelect extends Item {

    public ItemGamemodeSelect() {
            super(new Item.Properties().setNoRepair());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if(level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            if(ClientSimData.getGamemode() == -1) {
                player.startUsingItem(hand);
                ClientUtil.showGamemodeSelect();
                return InteractionResultHolder.consume(player.getItemInHand(hand));
            }
        }

        return InteractionResultHolder.fail(player.getItemInHand(hand));
    }


    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
}
