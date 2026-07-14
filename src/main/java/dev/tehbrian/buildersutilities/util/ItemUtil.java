package dev.tehbrian.buildersutilities.util;

import com.destroystokyo.paper.profile.ProfileProperty;
import io.papermc.paper.datacomponent.item.ResolvableProfile;

import static io.papermc.paper.datacomponent.item.ResolvableProfile.resolvableProfile;

public final class ItemUtil {

	private ItemUtil() {
	}

	public static ResolvableProfile texturedProfile(final String data) {
		return resolvableProfile().addProperty(new ProfileProperty("textures", data)).build();
	}

}
