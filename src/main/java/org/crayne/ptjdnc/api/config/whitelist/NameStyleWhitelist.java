package org.crayne.ptjdnc.api.config.whitelist;

import org.crayne.ptjdnc.api.config.palette.ColorPalette;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public interface NameStyleWhitelist {

    boolean nameColorAccessible(@NotNull final UUID uuid, @NotNull final String colorName);

    @NotNull
    static Set<NameStyleWhitelist> loadWhitelists(@NotNull final ColorPalette palette) {
        final Set<NameStyleWhitelist> whitelists = new HashSet<>(ConfigurableWhitelist.loadWhitelists(palette));
        DonatorWhitelist.loadDonatorWhitelist(palette).ifPresent(whitelists::add);

        return whitelists;
    }

}
