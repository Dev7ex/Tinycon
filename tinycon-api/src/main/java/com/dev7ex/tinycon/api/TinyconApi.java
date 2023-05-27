package com.dev7ex.tinycon.api;

import com.dev7ex.tinycon.api.command.CommandManager;
import com.dev7ex.tinycon.api.console.ConsoleInputReader;
import com.dev7ex.tinycon.api.logging.TinyconLogger;
import com.dev7ex.tinycon.api.scheduler.Scheduler;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
public interface TinyconApi {

    Thread getMainThread();

    Thread getReaderThread();

    TinyconLogger getLogger();

    Scheduler getScheduler();

    boolean isRunning();

    CommandManager getCommandManager();

    ConsoleInputReader getConsoleInputReader();

}
