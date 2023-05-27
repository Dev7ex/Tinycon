package com.dev7ex.tinycon.api.command;

import com.dev7ex.tinycon.api.TinyconProvider;
import com.dev7ex.tinycon.api.scheduler.Scheduler;

import java.util.logging.Logger;

/**
 * @author Dev7ex
 * @since 26.05.2023
 *
 * Represents an executable command
 */
public interface Command {

    void execute(final String[] arguments);

    default String getName() {
        return this.getClass().getAnnotation(CommandProperties.class).name();
    }

    default Logger getLogger() {
        return TinyconProvider.getTinyconApi().getLogger();
    }

    default Scheduler getScheduler() {
        return TinyconProvider.getTinyconApi().getScheduler();
    }

}
