package dev.tehbrian.buildersutilities.banner;

import dev.tehbrian.buildersutilities.config.ConfigConfig;
import dev.tehbrian.buildersutilities.config.LangConfig;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.spongepowered.configurate.NodePath;

import static dev.tehbrian.buildersutilities.util.ItemModifier.itemModifier;
import static dev.tehbrian.buildersutilities.util.ItemUtil.texturedProfile;
import static io.papermc.paper.datacomponent.DataComponentTypes.ITEM_NAME;
import static io.papermc.paper.datacomponent.DataComponentTypes.PROFILE;

public final class Buttons {

	public static final int UNDO_SLOT = 0;
	public static final int RANDOM_SLOT = 3;
	public static final int BANNER_SLOT = 5;
	public static final int RESET_SLOT = 8;

	private Buttons() {
	}

	public static ItemStack randomize(final LangConfig langConfig, final ConfigConfig configConfig) {
		return itemModifier(ItemType.PLAYER_HEAD)
				.set(ITEM_NAME, langConfig.c(NodePath.path("menus", "banner", "randomize")))
				.set(PROFILE, texturedProfile(configConfig.data().heads().banner().randomize()))
				.yank();
	}

	public static ItemStack reset(final LangConfig langConfig) {
		return itemModifier(ItemType.BARRIER)
				.set(ITEM_NAME, langConfig.c(NodePath.path("menus", "banner", "reset")))
				.yank();
	}

	public static ItemStack undo(final LangConfig langConfig, final ConfigConfig configConfig) {
		return itemModifier(ItemType.PLAYER_HEAD)
				.set(ITEM_NAME, langConfig.c(NodePath.path("menus", "banner", "undo")))
				.set(PROFILE, texturedProfile(configConfig.data().heads().banner().undo()))
				.yank();
	}

	public static void addToolbar(
			final Inventory inv,
			final LangConfig langConfig,
			final ConfigConfig configConfig,
			final ItemStack interfaceBanner
	) {
		inv.setItem(Buttons.UNDO_SLOT, undo(langConfig, configConfig));
		inv.setItem(Buttons.RANDOM_SLOT, randomize(langConfig, configConfig));
		inv.setItem(Buttons.BANNER_SLOT, interfaceBanner);
		inv.setItem(Buttons.RESET_SLOT, reset(langConfig));
	}

}
