package codes.matthewp.sukr.command;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class FactionCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("faction")
                .executes(context -> sendMessage(context, "Command works!"));
        dispatcher.register(command);
    }

    private static int sendMessage(CommandContext<CommandSourceStack> commandContext, String message) throws CommandSyntaxException {
        Entity entity = commandContext.getSource().getEntity();
        if(entity != null) {
            commandContext.getSource().getServer().getPlayerList().broadcastChatMessage(PlayerChatMessage.system(message), new ServerPlayer(commandContext.getSource().getServer(), commandContext.getSource().getLevel(), new GameProfile(entity.getUUID(), entity.getName().toString())), ChatType.bind(ChatType.SAY_COMMAND, commandContext.getSource()));
        } else {
            commandContext.getSource().getServer().getPlayerList().broadcastChatMessage(PlayerChatMessage.system(message), commandContext.getSource(), ChatType.bind(ChatType.SAY_COMMAND, commandContext.getSource()));
        }
        return 1;
    }

}
