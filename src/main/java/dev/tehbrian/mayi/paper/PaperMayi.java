package dev.tehbrian.mayi.paper;

import dev.tehbrian.mayi.core.ActionType;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public final class PaperMayi {

	private final List<RestrictionCheck> checks = new ArrayList<>();

	public void addCheck(final RestrictionCheck check) {
		this.checks.add(check);
	}

	public boolean checkRestrictions(
			final Player player,
			final Location location,
			final ActionType action
	) {
		for (final RestrictionCheck check : this.checks) {
			if (!check.isAllowed(player, location, action)) {
				return false;
			}
		}
		return true;
	}

	@FunctionalInterface
	public interface RestrictionCheck {
		boolean isAllowed(Player player, Location location, ActionType action);
	}
}
