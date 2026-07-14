package dev.tehbrian.buildersutilities.banner;

import io.papermc.paper.registry.RegistryKey;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

import static io.papermc.paper.registry.RegistryAccess.registryAccess;

/**
 * Provides utilities for banner patterns and colors.
 */
public final class Sayge {

	public static final RandomGenerator RANDOM = RandomGenerator.getDefault();

	private static final Map<ItemType, DyeColor> ITEM_TYPE_TO_DYE_COLOR = new HashMap<>();
	private static final Map<DyeColor, ItemType> DYE_COLOR_TO_ITEM_TYPE = new HashMap<>();

	static {
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.WHITE_BANNER, DyeColor.WHITE);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.ORANGE_BANNER, DyeColor.ORANGE);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.MAGENTA_BANNER, DyeColor.MAGENTA);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.LIGHT_BLUE_BANNER, DyeColor.LIGHT_BLUE);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.YELLOW_BANNER, DyeColor.YELLOW);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.LIME_BANNER, DyeColor.LIME);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.PINK_BANNER, DyeColor.PINK);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.GRAY_BANNER, DyeColor.GRAY);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.LIGHT_GRAY_BANNER, DyeColor.LIGHT_GRAY);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.CYAN_BANNER, DyeColor.CYAN);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.PURPLE_BANNER, DyeColor.PURPLE);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.BLUE_BANNER, DyeColor.BLUE);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.BROWN_BANNER, DyeColor.BROWN);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.GREEN_BANNER, DyeColor.GREEN);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.RED_BANNER, DyeColor.RED);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.BLACK_BANNER, DyeColor.BLACK);

		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.WHITE_DYE, DyeColor.WHITE);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.ORANGE_DYE, DyeColor.ORANGE);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.MAGENTA_DYE, DyeColor.MAGENTA);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.LIGHT_BLUE_DYE, DyeColor.LIGHT_BLUE);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.YELLOW_DYE, DyeColor.YELLOW);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.LIME_DYE, DyeColor.LIME);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.PINK_DYE, DyeColor.PINK);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.GRAY_DYE, DyeColor.GRAY);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.LIGHT_GRAY_DYE, DyeColor.LIGHT_GRAY);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.CYAN_DYE, DyeColor.CYAN);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.PURPLE_DYE, DyeColor.PURPLE);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.BLUE_DYE, DyeColor.BLUE);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.BROWN_DYE, DyeColor.BROWN);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.GREEN_DYE, DyeColor.GREEN);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.RED_DYE, DyeColor.RED);
		ITEM_TYPE_TO_DYE_COLOR.put(ItemType.BLACK_DYE, DyeColor.BLACK);

		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.WHITE, ItemType.WHITE_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.ORANGE, ItemType.ORANGE_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.MAGENTA, ItemType.MAGENTA_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.LIGHT_BLUE, ItemType.LIGHT_BLUE_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.YELLOW, ItemType.YELLOW_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.LIME, ItemType.LIME_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.PINK, ItemType.PINK_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.GRAY, ItemType.GRAY_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.LIGHT_GRAY, ItemType.LIGHT_GRAY_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.CYAN, ItemType.CYAN_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.PURPLE, ItemType.PURPLE_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.BLUE, ItemType.BLUE_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.BROWN, ItemType.BROWN_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.GREEN, ItemType.GREEN_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.RED, ItemType.RED_BANNER);
		DYE_COLOR_TO_ITEM_TYPE.put(DyeColor.BLACK, ItemType.BLACK_BANNER);
	}

	private Sayge() {
	}

	public static ItemType bannerItemType(final DyeColor dyeColor) {
		return DYE_COLOR_TO_ITEM_TYPE.get(dyeColor);
	}

	public static DyeColor bannerDyeColor(final ItemType itemType) {
		return ITEM_TYPE_TO_DYE_COLOR.get(itemType);
	}

	public static List<DyeColor> dyeColors() {
		return Arrays.asList(DyeColor.values());
	}

	/**
	 * Compiles a list of all pattern types except {@link PatternType#BASE}.
	 *
	 * @return a list of all pattern types
	 */
	public static List<PatternType> patternTypes() {
		return registryAccess().getRegistry(RegistryKey.BANNER_PATTERN).stream()
				.filter(type -> type != PatternType.BASE).toList();
	}

	public static DyeColor randomDyeColor() {
		return dyeColors().get(RANDOM.nextInt(dyeColors().size()));
	}

	public static PatternType randomPatternType() {
		return patternTypes().get(RANDOM.nextInt(patternTypes().size()));
	}

}
