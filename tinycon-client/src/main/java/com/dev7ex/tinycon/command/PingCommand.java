package com.dev7ex.tinycon.command;

import com.dev7ex.tinycon.TinyconClient;
import com.dev7ex.tinycon.api.command.Command;
import com.dev7ex.tinycon.api.command.CommandProperties;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
@CommandProperties(name = PingCommand.NAME)
public class PingCommand implements Command {

    public static final String NAME = "Ping";

    @Override
    public void execute(final String[] arguments) {
        if (arguments.length != 2) {
            this.getLogger().warning("Usage: /ping <Address>");
            return;
        }

        if (arguments[1].equalsIgnoreCase("me")) {
            arguments[1] = "localhost";
        }

        this.getScheduler().runTaskAsynchronously(() -> {

            try {
                final InetAddress address = InetAddress.getByName(arguments[1]);
                this.getLogger().info("Sending Ping Request to: " + address.getHostAddress());
                this.getLogger().info(address.isReachable(2000) ? "Host is reachable" : "Host is not reachable");

            } catch (final IOException exception) {
                if (exception instanceof UnknownHostException) {
                    this.getLogger().info("Unknown Host");
                    return;
                }
                exception.printStackTrace();
            }

        }, 0L);

    }

}
