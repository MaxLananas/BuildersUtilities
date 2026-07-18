package dev.tehbrian.agna.paper;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginUtils {

	private PluginUtils() {
	}

	public static void disableSelf(final JavaPlugin plugin) {
		plugin.getServer().getPluginManager().disablePlugin(plugin);
	}

	public static void registerListeners(
			final JavaPlugin plugin,
			final Listener... listeners
	) {
		for (final Listener listener : listeners) {
			plugin.getServer().getPluginManager()
					.registerEvents(listener, plugin);
		}
	}
}
