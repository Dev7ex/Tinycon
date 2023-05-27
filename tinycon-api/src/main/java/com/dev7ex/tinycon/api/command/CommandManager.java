package com.dev7ex.tinycon.api.command;

import com.dev7ex.tinycon.api.TinyconProvider;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
public class CommandManager {

    @Getter(AccessLevel.PUBLIC)
    private final Map<String, Command> commands = new HashMap<>();

    public void registerCommand(final Command command) {
        this.commands.put(command.getName().toLowerCase(), command);
    }

    public final void executeCommand(final String command) {
        final String[] arguments = command.split(" ");

        if(!this.commands.containsKey(arguments[0].toLowerCase())) {
            TinyconProvider.getTinyconApi().getLogger().warning("Unknown Command");
            return;
        }

        this.commands.get(arguments[0].toLowerCase()).execute(arguments);
    }

    public Command getCommand(final String name) {
        return this.commands.get(name);
    }

    @Deprecated
    public Command getCommand(final Class<? extends Command> commandClazz) {
        return this.commands.values().stream()
                .filter(command -> command.getClass().getSimpleName().equalsIgnoreCase(commandClazz.getSimpleName()))
                .findFirst()
                .orElseThrow();
    }

}
