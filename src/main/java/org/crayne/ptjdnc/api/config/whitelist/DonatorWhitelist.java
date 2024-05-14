package org.crayne.ptjdnc.api.config.whitelist;

import org.bukkit.configuration.ConfigurationSection;
import org.crayne.ptjdnc.NameColorPlugin;
import org.crayne.ptjdnc.api.config.palette.ColorPalette;
import org.jetbrains.annotations.NotNull;
import org.zerobzerot.donationapi.DonationAPI;

import java.util.*;

public class DonatorWhitelist implements NameStyleWhitelist {

    @NotNull
    private final List<String> availableNameColors;

    public DonatorWhitelist(@NotNull final List<String> availableNameColors) {
        this.availableNameColors = new ArrayList<>(availableNameColors);
    }

    @NotNull
    public static Optional<DonatorWhitelist> loadDonatorWhitelist(@NotNull final ColorPalette palette) {
        final ConfigurationSection whitelistSection = NameColorPlugin.config().getConfigurationSection("whitelists");
        if (whitelistSection == null) return Optional.empty();

        final List<String> availableNameColors = ConfigurableWhitelist.loadWhitelistedPalette(whitelistSection, "donators", palette);
        return Optional.of(new DonatorWhitelist(availableNameColors));
    }

    public boolean nameColorAccessible(@NotNull final UUID uuid, @NotNull final String colorName) {
        return DonationAPI.Instance.isActiveDonor(uuid) && availableNameColors.contains(colorName);
    }

}
