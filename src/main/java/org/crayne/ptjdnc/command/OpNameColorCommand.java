package org.crayne.ptjdnc.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.crayne.ptjdnc.api.NameStyle;
import org.crayne.ptjdnc.api.profile.GlobalNameStyleProfile;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.crayne.ptjdnc.command.NameColorCommand.*;

public class OpNameColorCommand implements CommandExecutor {

    @NotNull
    protected static final TextColor COLOR_RED = TextColor.color(255, 0, 0);

    public boolean onCommand(@NotNull final CommandSender commandSender, @NotNull final Command command,
                             @NotNull final String label, @NotNull final String @NotNull [] args) {
        if (!commandSender.isOp()) {
            commandSender.sendMessage(Component.text("Only operators may use this command.").color(COLOR_RED));
            return false;
        }
        if (args.length == 0) {
            commandSender.sendMessage(Component.text("Expected player as the first argument. " +
                    "Usage: /opnc <player> <namecolors...>").color(COLOR_RED));
            return false;
        }
        final String targetName = args[0];
        final Player target = Bukkit.getPlayer(targetName);

        if (target == null) {
            commandSender.sendMessage(Component.text("The player was not found " +
                    "on the server.").color(COLOR_RED));
            return false;
        }
        if (args.length == 1) {
            sendAvailableColors(commandSender, target);
            return true;
        }
        final String[] nameColorArgs = List.of(args).subList(1, args.length).toArray(String[]::new);

        final GlobalNameStyleProfile globalNameStyleProfile = GlobalNameStyleProfile.INSTANCE;

        final NameStyle oldNameStyle = globalNameStyleProfile.nameStyle(target);
        final NameColorCommand.NameColorCommandResult nameColorParsed = parseNameColor(nameColorArgs, null, oldNameStyle);
        return updateNameColor(nameColorParsed, commandSender, target);
    }

}