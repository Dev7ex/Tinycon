package com.dev7ex.tinycon.command;

import com.dev7ex.tinycon.TinyconClient;
import com.dev7ex.tinycon.api.command.Command;
import com.dev7ex.tinycon.api.command.CommandProperties;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
@CommandProperties(name = StopCommand.NAME)
public class StopCommand implements Command {

    public static final String NAME = "Stop";

    @Override
    public void execute(final String[] arguments) {
        TinyconClient.getInstance().getLogger().info("Trying to stop the client...");

        System.exit(0);
    }

}
