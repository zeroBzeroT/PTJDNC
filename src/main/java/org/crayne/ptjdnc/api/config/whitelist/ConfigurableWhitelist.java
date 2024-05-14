package org.crayne.ptjdnc.api.config.whitelist;

import org.bukkit.configuration.ConfigurationSection;
import org.crayne.ptjdnc.NameColorPlugin;
import org.crayne.ptjdnc.api.config.ConfigParseException;
import org.crayne.ptjdnc.api.config.palette.ColorPalette;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record ConfigurableWhitelist(@NotNull Set<UUID> whitelistedUsers,
                                    @NotNull List<String> whitelistedColors) implements NameStyleWhitelist {

    @NotNull
    public static Set<ConfigurableWhitelist> loadWhitelists(@NotNull final ColorPalette palette) {
        final ConfigurationSection whitelistSection = NameColorPlugin.config().getConfigurationSection("whitelists");
        if (whitelistSection == null) return new HashSet<>();

        return whitelistSection
                .getValues(true)
                .keySet()
                .stream()
                .map(whitelist -> new ConfigurableWhitelist(
                        loadWhitelistedUsers(whitelistSection, whitelist),
                        loadWhitelistedPalette(whitelistSection, whitelist, palette))
                )
                .collect(Collectors.toSet());
    }

    @NotNull
    public static Set<UUID> loadWhitelistedUsers(@NotNull final ConfigurationSection whitelistSection,
                                                 @NotNull final String whitelistName) {
        return whitelistSection.getStringList(whitelistName + ".whitelisted_users")
                .stream()
                .map(UUID::fromString)
                .collect(Collectors.toSet());
    }

    @NotNull
    public static List<String> loadWhitelistedPalette(@NotNull final ConfigurationSection whitelistSection,
                                                      @NotNull final String whitelistName,
                                                      @NotNull final ColorPalette palette) {
        return whitelistSection.getStringList(whitelistName + ".color_palette")
                .stream()
                .peek(s -> {
                    if (!palette.hasColor(s)) throw new ConfigParseException("Unknown color: " + s);
                })
                .toList();
    }

    public boolean nameColorAccessible(@NotNull final UUID uuid, @NotNull final String colorName) {
        return whitelistedUsers.contains(uuid) && whitelistedColors.contains(colorName);
    }

}
