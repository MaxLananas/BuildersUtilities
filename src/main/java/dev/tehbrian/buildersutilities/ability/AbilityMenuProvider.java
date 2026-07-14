package dev.tehbrian.buildersutilities.ability;

import com.google.inject.Inject;
import dev.tehbrian.buildersutilities.config.LangConfig;
import dev.tehbrian.buildersutilities.user.User;
import dev.tehbrian.buildersutilities.util.ChestSize;
import dev.tehbrian.buildersutilities.util.MenuItems;
import dev.tehbrian.buildersutilities.util.Permissions;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.NodePath;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static dev.tehbrian.buildersutilities.util.ItemModifier.itemModifier;
import static io.papermc.paper.datacomponent.DataComponentTypes.ITEM_NAME;
import static io.papermc.paper.datacomponent.DataComponentTypes.LORE;
import static io.papermc.paper.datacomponent.item.ItemLore.lore;

public final class AbilityMenuProvider {

	private static final ItemStack GREEN = itemModifier(ItemType.LIME_STAINED_GLASS_PANE)
			.unset(ITEM_NAME)
			.yank();
	private static final ItemStack ORANGE = itemModifier(ItemType.ORANGE_STAINED_GLASS_PANE)
			.unset(ITEM_NAME)
			.yank();
	private static final ItemStack RED = itemModifier(ItemType.RED_STAINED_GLASS_PANE)
			.unset(ITEM_NAME)
			.yank();

	private final LangConfig langConfig;

	@Inject
	public AbilityMenuProvider(
			final LangConfig langConfig
	) {
		this.langConfig = langConfig;
	}

	public Inventory generate(final User user) {
		final Inventory inv = Bukkit.createInventory(
				null,
				ChestSize.SINGLE,
				this.langConfig.c(NodePath.path("menus", "ability", "inventory-name"))
		);

		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, MenuItems.BACKGROUND);
		}

		this.update(inv, user);

		return inv;
	}

	public void update(final Inventory inv, final User user) {
		final @Nullable Player p = user.getPlayer();
		Objects.requireNonNull(p);

		this.drawAbility(
				inv,
				1,
				ItemType.IRON_TRAPDOOR,
				"iron-door-toggle",
				p.hasPermission(Permissions.IRON_DOOR_TOGGLE),
				user.ironDoorToggleEnabled()
		);

		this.drawAbility(
				inv,
				2,
				ItemType.STONE_SLAB,
				"double-slab-break",
				p.hasPermission(Permissions.DOUBLE_SLAB_BREAK),
				user.doubleSlabBreakEnabled()
		);

		this.drawAbility(
				inv,
				3,
				ItemType.LIGHT_BLUE_GLAZED_TERRACOTTA,
				"glazed-terracotta-rotate",
				p.hasPermission(Permissions.GLAZED_TERRACOTTA_ROTATE),
				user.glazedTerracottaRotateEnabled()
		);

		this.drawAbility(
				inv,
				5,
				ItemType.ENDER_EYE,
				"night-vision",
				p.hasPermission(Permissions.NIGHT_VISION),
				user.nightVisionEnabled()
		);

		this.drawAbility(
				inv,
				6,
				ItemType.COMPASS,
				"noclip",
				p.hasPermission(Permissions.NOCLIP),
				user.noclipEnabled()
		);

		this.drawAbility(
				inv,
				7,
				ItemType.FEATHER,
				"advanced-fly",
				p.hasPermission(Permissions.ADVANCED_FLY),
				user.advancedFlyEnabled()
		);
	}

	private ItemStack createAbilityItem(final ItemType itemType, final String abilityKey, final String statusKey) {
		final List<Component> lore = new ArrayList<>();
		lore.addAll(this.langConfig.cl(NodePath.path("menus", "ability", abilityKey, "description")));
		lore.addAll(this.langConfig.cl(NodePath.path("menus", "ability", "status", statusKey)));

		return itemModifier(itemType)
				.set(ITEM_NAME, this.langConfig.c(NodePath.path("menus", "ability", abilityKey, "name")))
				.set(LORE, lore(lore))
				.yank();
	}

	private void drawAbility(
			final Inventory inv,
			final int row,
			final ItemType itemType,
			final String abilityKey,
			final boolean hasPermission,
			final boolean isEnabled
	) {
		if (!hasPermission) {
			inv.setItem(row, ORANGE);
			inv.setItem(row + 9, this.createAbilityItem(itemType, abilityKey, "no-permission"));
			inv.setItem(row + 18, ORANGE);
			return;
		}

		if (isEnabled) {
			inv.setItem(row, GREEN);
			inv.setItem(row + 9, this.createAbilityItem(itemType, abilityKey, "enabled"));
			inv.setItem(row + 18, GREEN);
		} else {
			inv.setItem(row, RED);
			inv.setItem(row + 9, this.createAbilityItem(itemType, abilityKey, "disabled"));
			inv.setItem(row + 18, RED);
		}
	}

}
