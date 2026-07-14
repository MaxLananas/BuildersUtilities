package dev.tehbrian.buildersutilities.armorcolor;

import com.google.inject.Inject;
import dev.tehbrian.buildersutilities.config.ConfigConfig;
import dev.tehbrian.buildersutilities.config.LangConfig;
import dev.tehbrian.buildersutilities.util.ChestSize;
import dev.tehbrian.buildersutilities.util.MenuItems;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemType;
import org.spongepowered.configurate.NodePath;

import java.util.List;

import static dev.tehbrian.buildersutilities.util.ItemModifier.itemModifier;
import static dev.tehbrian.buildersutilities.util.ItemUtil.texturedProfile;
import static io.papermc.paper.datacomponent.DataComponentTypes.DYED_COLOR;
import static io.papermc.paper.datacomponent.DataComponentTypes.ITEM_NAME;
import static io.papermc.paper.datacomponent.DataComponentTypes.LORE;
import static io.papermc.paper.datacomponent.DataComponentTypes.PROFILE;
import static io.papermc.paper.datacomponent.item.DyedItemColor.dyedItemColor;
import static io.papermc.paper.datacomponent.item.ItemLore.lore;
import static java.util.Objects.requireNonNull;

public final class ArmorColorMenuProvider {

	private final LangConfig langConfig;
	private final ConfigConfig configConfig;

	@Inject
	public ArmorColorMenuProvider(
			final LangConfig langConfig,
			final ConfigConfig configConfig
	) {
		this.langConfig = langConfig;
		this.configConfig = configConfig;
	}

	public Inventory generate() {
		final Inventory inv = Bukkit.createInventory(
				null,
				ChestSize.DOUBLE,
				this.langConfig.c(NodePath.path("menus", "armor-color", "inventory-name"))
		);

		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, MenuItems.BACKGROUND);
		}

		inv.setItem(
				10,
				itemModifier(ItemType.LEATHER_HELMET)
						.set(ITEM_NAME, this.langConfig.c(NodePath.path("menus", "armor-color", "get-helmet")))
						.yank()
		);
		inv.setItem(
				19,
				itemModifier(ItemType.LEATHER_CHESTPLATE)
						.set(ITEM_NAME, this.langConfig.c(NodePath.path("menus", "armor-color", "get-chestplate")))
						.yank()
		);
		inv.setItem(
				28,
				itemModifier(ItemType.LEATHER_LEGGINGS)
						.set(ITEM_NAME, this.langConfig.c(NodePath.path("menus", "armor-color", "get-leggings")))
						.yank()
		);
		inv.setItem(
				37,
				itemModifier(ItemType.LEATHER_BOOTS)
						.set(ITEM_NAME, this.langConfig.c(NodePath.path("menus", "armor-color", "get-boots")))
						.yank()
		);

		final List<Component> changeLore = this.langConfig.cl(NodePath.path("menus", "armor-color", "change"));

		inv.setItem(
				22,
				itemModifier(ItemType.PLAYER_HEAD)
						.set(ITEM_NAME, this.langConfig.c(NodePath.path("menus", "armor-color", "randomize-red")))
						.set(PROFILE, texturedProfile(this.configConfig.data().heads().armorColor().randomizeRed()))
						.yank()
		);
		inv.setItem(
				23,
				itemModifier(ItemType.PLAYER_HEAD)
						.set(ITEM_NAME, this.langConfig.c(NodePath.path("menus", "armor-color", "randomize-green")))
						.set(PROFILE, texturedProfile(this.configConfig.data().heads().armorColor().randomizeGreen()))
						.yank()
		);
		inv.setItem(
				24,
				itemModifier(ItemType.PLAYER_HEAD)
						.set(ITEM_NAME, this.langConfig.c(NodePath.path("menus", "armor-color", "randomize-blue")))
						.set(PROFILE, texturedProfile(this.configConfig.data().heads().armorColor().randomizeBlue()))
						.yank()
		);
		inv.setItem(
				31,
				itemModifier(ItemType.PLAYER_HEAD).amount(16)
						.set(ITEM_NAME, this.langConfig.c(NodePath.path("menus", "armor-color", "red")))
						.set(LORE, lore(changeLore))
						.set(PROFILE, texturedProfile(this.configConfig.data().heads().armorColor().red()))
						.yank()
		);
		inv.setItem(
				32,
				itemModifier(ItemType.PLAYER_HEAD).amount(16)
						.set(ITEM_NAME, this.langConfig.c(NodePath.path("menus", "armor-color", "green")))
						.set(LORE, lore(changeLore))
						.set(PROFILE, texturedProfile(this.configConfig.data().heads().armorColor().green()))
						.yank()
		);
		inv.setItem(
				33,
				itemModifier(ItemType.PLAYER_HEAD).amount(16)
						.set(ITEM_NAME, this.langConfig.c(NodePath.path("menus", "armor-color", "blue")))
						.set(LORE, lore(changeLore))
						.set(PROFILE, texturedProfile(this.configConfig.data().heads().armorColor().blue()))
						.yank()
		);

		this.update(inv);

		return inv;
	}

	public void update(final Inventory inv) {
		int r = (requireNonNull(inv.getItem(31)).getAmount() - 1) * 8;
		int g = (requireNonNull(inv.getItem(32)).getAmount() - 1) * 8;
		int b = (requireNonNull(inv.getItem(33)).getAmount() - 1) * 8;

		if (r == 256) {
			r = 255;
		}

		if (g == 256) {
			g = 255;
		}

		if (b == 256) {
			b = 255;
		}

		final Color finalColor = Color.fromRGB(r, g, b);

		inv.setItem(10, itemModifier(requireNonNull(inv.getItem(10))).set(DYED_COLOR, dyedItemColor(finalColor)).yank());
		inv.setItem(19, itemModifier(requireNonNull(inv.getItem(19))).set(DYED_COLOR, dyedItemColor(finalColor)).yank());
		inv.setItem(28, itemModifier(requireNonNull(inv.getItem(28))).set(DYED_COLOR, dyedItemColor(finalColor)).yank());
		inv.setItem(37, itemModifier(requireNonNull(inv.getItem(37))).set(DYED_COLOR, dyedItemColor(finalColor)).yank());
	}

}
