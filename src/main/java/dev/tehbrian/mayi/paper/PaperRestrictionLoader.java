package dev.tehbrian.mayi.paper;

import dev.tehbrian.mayi.core.ActionType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.slf4j.Logger;

import java.util.List;

public final class PaperRestrictionLoader {

	private final Logger logger;
	private final List<Plugin> plugins;
	private final List<Class<? extends RestrictionAdapter>> adapterClasses;

	public PaperRestrictionLoader(
			final Logger logger,
			final List<Plugin> plugins,
			final List<Class<? extends RestrictionAdapter>> adapterClasses
	) {
		this.logger = logger;
		this.plugins = plugins;
		this.adapterClasses = adapterClasses;
	}

	public void load(final PaperMayi paperMayi) {
		for (final Class<? extends RestrictionAdapter> cls : this.adapterClasses) {
			try {
				final RestrictionAdapter adapter = cls.getDeclaredConstructor()
						.newInstance();
				final String name = adapter.pluginName();
				final boolean present = this.plugins.stream()
						.anyMatch(p -> p.getName().equals(name));
				if (present) {
					paperMayi.addCheck(adapter);
					this.logger.info("Loaded restriction adapter for {}", name);
				}
			} catch (final Exception e) {
				this.logger.warn(
						"Failed to load restriction adapter {}",
						cls.getSimpleName(), e
				);
			}
		}
	}

	public interface RestrictionAdapter extends PaperMayi.RestrictionCheck {
		String pluginName();

		@Override
		default boolean isAllowed(
				final Player player,
				final Location location,
				final ActionType action
		) {
			return true;
		}
	}
}
