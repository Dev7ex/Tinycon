package com.dev7ex.tinycon.command;

import com.dev7ex.tinycon.TinyconClient;
import com.dev7ex.tinycon.api.command.Command;
import com.dev7ex.tinycon.api.command.CommandProperties;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
@CommandProperties(name = HelpCommand.NAME)
public class HelpCommand implements Command {

    public static final String NAME = "Help";

    public void execute(final String[] arguments) {
        for (final Command command : TinyconClient.getInstance().getCommandManager().getCommands().values()) {
            this.getLogger().info(command.getName());
        }
    }

}
