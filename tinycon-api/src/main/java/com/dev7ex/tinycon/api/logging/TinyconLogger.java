package com.dev7ex.tinycon.api.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Justin
 * @since 28.03.2022
 */
public class TinyconLogger extends Logger {

    public TinyconLogger(final String loggerName) {
        super(loggerName, null);
        super.setLevel(Level.ALL);

        final ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new LoggingFormatter());
        super.addHandler(consoleHandler);
    }


}
