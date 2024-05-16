package org.crayne.ptjdnc.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OpNameColorTabCompleter extends NameColorTabCompleter {

    @Nullable
    public List<String> onTabComplete(@NotNull final CommandSender commandSender,
                                      @NotNull final Command command,
                                      @NotNull final String label,
                                      @NotNull final String @NotNull [] args) {
        if (!commandSender.isOp()) return Collections.emptyList();
        if (args.length <= 1) return null;

        final List<String> argsList = Arrays.stream(args).toList().subList(1, args.length - 1);
        return tabCompleteFor(null, argsList);
    }

}
