package dev.tehbrian.buildersutilities.command;

import com.google.inject.Inject;
import dev.tehbrian.buildersutilities.config.LangConfig;
import dev.tehbrian.buildersutilities.waypoint.Waypoint;
import dev.tehbrian.buildersutilities.waypoint.WaypointService;
import dev.tehbrian.buildersutilities.util.Permissions;
import org.bukkit.Location;
import org.incendo.cloud.paper.PaperCommandManager;
import org.incendo.cloud.paper.util.sender.PlayerSource;
import org.incendo.cloud.paper.util.sender.Source;
import org.spongepowered.configurate.NodePath;

import static org.incendo.cloud.description.Description.description;
import static org.incendo.cloud.parser.standard.StringParser.greedyStringParser;

public final class WaypointCommand {

	private final WaypointService waypointService;
	private final LangConfig langConfig;

	@Inject
	public WaypointCommand(
			final WaypointService waypointService,
			final LangConfig langConfig
	) {
		this.waypointService = waypointService;
		this.langConfig = langConfig;
	}

	public void register(
			final PaperCommandManager<Source> commandManager
	) {
		final var root = commandManager.commandBuilder(
				"waypoint", "wp"
		)
				.commandDescription(description("Waypoint commands."))
				.permission(Permissions.WAYPOINT)
				.senderType(PlayerSource.class);

		final var add = root.literal(
				"add", description("Save a waypoint.")
		)
				.required("name", greedyStringParser())
				.handler(c -> {
					final var sender = c.sender().source();
					final String name = c.get("name");
					final Location loc = sender.getLocation();
					this.waypointService.add(
							sender.getUniqueId(),
							name,
							Waypoint.fromLocation(loc)
					);
					sender.sendMessage(this.langConfig.c(
							NodePath.path(
									"commands", "waypoint", "added"
							)
					).replaceText(
							b -> b.match("{name}").replacement(name)
					));
				});

		final var tp = root.literal(
				"tp", description("Teleport to a waypoint.")
		)
				.required("name", greedyStringParser())
				.handler(c -> {
					final var sender = c.sender().source();
					final String name = c.get("name");
					final Waypoint wp = this.waypointService.get(
							sender.getUniqueId(), name
					);
					if (wp == null) {
						sender.sendMessage(this.langConfig.c(
								NodePath.path(
										"commands", "waypoint",
										"not-found"
								)
						).replaceText(
								b -> b.match("{name}").replacement(name)
						));
						return;
					}
					final Location loc = wp.toLocation();
					if (loc == null) {
						sender.sendMessage(this.langConfig.c(
								NodePath.path(
										"commands", "waypoint",
										"not-found"
								)
						).replaceText(
								b -> b.match("{name}").replacement(name)
						));
						return;
					}
					sender.teleport(loc);
					sender.sendMessage(this.langConfig.c(
							NodePath.path(
									"commands", "waypoint",
									"teleported"
							)
					).replaceText(
							b -> b.match("{name}").replacement(name)
					));
				});

		final var remove = root.literal(
				"remove", description("Delete a waypoint.")
		)
				.required("name", greedyStringParser())
				.handler(c -> {
					final var sender = c.sender().source();
					final String name = c.get("name");
					final Waypoint wp = this.waypointService.get(
							sender.getUniqueId(), name
					);
					if (wp == null) {
						sender.sendMessage(this.langConfig.c(
								NodePath.path(
										"commands", "waypoint",
										"not-found"
								)
						).replaceText(
								b -> b.match("{name}").replacement(name)
						));
						return;
					}
					this.waypointService.remove(
							sender.getUniqueId(), name
					);
					sender.sendMessage(this.langConfig.c(
							NodePath.path(
									"commands", "waypoint", "removed"
							)
					).replaceText(
							b -> b.match("{name}").replacement(name)
					));
				});

		final var list = root.literal(
				"list", description("List your waypoints.")
		)
				.handler(c -> {
					final var sender = c.sender().source();
					final var waypoints = this.waypointService.getAll(
							sender.getUniqueId()
					);
					if (waypoints.isEmpty()) {
						sender.sendMessage(this.langConfig.c(
								NodePath.path(
										"commands", "waypoint",
										"list-empty"
								)
						));
						return;
					}
					sender.sendMessage(this.langConfig.c(
							NodePath.path(
									"commands", "waypoint",
									"list-header"
							)
					));
					for (final var entry : waypoints.entrySet()) {
						final Waypoint wp = entry.getValue();
						sender.sendMessage(this.langConfig.c(
								NodePath.path(
										"commands", "waypoint",
										"list-entry"
								)
						)
								.replaceText(b -> b.match("{name}")
										.replacement(entry.getKey()))
								.replaceText(b -> b.match("{x}")
										.replacement(
												String.valueOf(
														(int) wp.x()
												)
										))
								.replaceText(b -> b.match("{y}")
										.replacement(
												String.valueOf(
														(int) wp.y()
												)
										))
								.replaceText(b -> b.match("{z}")
										.replacement(
												String.valueOf(
														(int) wp.z()
												)
										))
								.replaceText(b -> b.match("{world}")
										.replacement(wp.world()))
						);
					}
				});

		commandManager.command(add);
		commandManager.command(tp);
		commandManager.command(remove);
		commandManager.command(list);
	}
}
