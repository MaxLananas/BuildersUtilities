package dev.tehbrian.buildersutilities.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;

import static dev.tehbrian.buildersutilities.util.ItemModifier.itemModifier;
import static io.papermc.paper.datacomponent.DataComponentTypes.ITEM_NAME;

public final class MenuItems {

	public static final ItemStack BACKGROUND = itemModifier(ItemType.LIGHT_GRAY_STAINED_GLASS_PANE)
			.unset(ITEM_NAME)
			.yank();

	private MenuItems() {
	}

}
