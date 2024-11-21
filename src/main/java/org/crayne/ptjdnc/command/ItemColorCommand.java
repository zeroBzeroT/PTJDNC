package org.crayne.ptjdnc.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.crayne.ptjdnc.NameColorPlugin;
import org.crayne.ptjdnc.api.NameStyle;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static org.crayne.ptjdnc.command.NameColorCommand.*;

public class ItemColorCommand implements CommandExecutor {

    public boolean onCommand(@NotNull final CommandSender commandSender, @NotNull final Command command,
                             @NotNull final String label, @NotNull final String @NotNull [] args) {
        if (!(commandSender instanceof final Player p)) {
            commandSender.sendMessage(Component.text("Only players may use this command.").color(COLOR_RED));
            return false;
        }
        if (!p.isOp() && !NameColorPlugin.config().getBoolean("enable_itemcolor")) {
            commandSender.sendMessage(Component.text("You do not have access to this command.").color(COLOR_RED));
            return false;
        }
        if (args.length == 0) {
            sendAvailableColors(p, p);
            return true;
        }
        final ItemStack mainhandItem = p.getInventory().getItemInMainHand();
        if (mainhandItem.getAmount() == 0 || mainhandItem.getType() == Material.AIR) {
            p.sendMessage(deserialize("ic_no_item_held", s -> s));
            return false;
        }
        final PlainTextComponentSerializer plainText = PlainTextComponentSerializer.plainText();
        final ItemMeta meta = mainhandItem.getItemMeta();
        final Component defaultDisplayComponent = Component.translatable(mainhandItem.translationKey());
        final Component displayName = Optional.ofNullable(meta.displayName()).orElse(defaultDisplayComponent);
        final String defaultDisplayName = plainText.serialize(defaultDisplayComponent);

        if (args.length == 1 && args[0].equals("reset")) {
            final String strippedColorName = plainText.serialize(displayName);
            final Component strippedColorComponent = defaultDisplayName.equals(strippedColorName) ? null : Component.text(strippedColorName);

            meta.displayName(strippedColorComponent);
            mainhandItem.setItemMeta(meta);
            p.sendMessage(deserialize("ic_success", s -> s));
            return true;
        }
        final NameColorCommandResult nameColorParsed = parseNameColor(args, p, null);
        final Component errorMessage = nameColorParsed.message();
        if (errorMessage != null) {
            p.sendMessage(errorMessage);
            return false;
        }
        final NameStyle newNameStyle = nameColorParsed.toMetaNameColor();

        meta.displayName(newNameStyle.stylize(displayName));
        mainhandItem.setItemMeta(meta);
        p.sendMessage(deserialize("ic_success", s -> s));
        return true;
    }
}
