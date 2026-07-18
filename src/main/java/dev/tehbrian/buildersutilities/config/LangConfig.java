package dev.tehbrian.buildersutilities.config;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import dev.tehbrian.agna.paper.configurate.AbstractLangConfig;
import net.kyori.adventure.text.Component;
import org.spongepowered.configurate.NodePath;

import java.nio.file.Path;
import java.util.List;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public final class LangConfig extends AbstractLangConfig<YamlConfigurateWrapper> {

	private String prefix = "";

	@Inject
	public LangConfig(final @Named("dataFolder") Path dataFolder) {
		super(new YamlConfigurateWrapper(dataFolder.resolve("lang.yml")));
	}

	private String prefix() {
		if (this.prefix.isEmpty()) {
			final String p = this.wrapper().rootNode()
					.node("prefix").getString();
			if (p != null) {
				this.prefix = p;
			}
		}
		return this.prefix;
	}

	@Override
	protected String getAndVerifyString(final NodePath path) {
		final String raw = super.getAndVerifyString(path);
		return raw.replace("{prefix}", this.prefix());
	}

	/**
	 * Splits the input string by line and parses each line individually.
	 *
	 * <p>This method is useful for item lore because that requires a list of
	 * components rather than a single component with newlines.</p>
	 *
	 * @param path the config path
	 * @return the component
	 */
	public List<Component> cl(final NodePath path) {
		return this.getAndVerifyString(path).lines()
				.map(miniMessage()::deserialize).toList();
	}
}
