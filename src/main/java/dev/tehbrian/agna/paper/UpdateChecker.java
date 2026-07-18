package dev.tehbrian.agna.paper;

import org.bukkit.plugin.java.JavaPlugin;

public final class UpdateChecker {

	private final JavaPlugin plugin;
	private final String resourceName;

	public UpdateChecker(final JavaPlugin plugin, final String resourceName) {
		this.plugin = plugin;
		this.resourceName = resourceName;
	}

	public void checkForUpdates() {
		this.plugin.getSLF4JLogger().debug(
				"Update checking is disabled for this build."
		);
	}
}
