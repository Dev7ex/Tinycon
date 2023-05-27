package com.dev7ex.tinycon.api.scheduler.task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public final class DelayedTask {

    private Runnable runnable;
    private long runTime;

    public DelayedTask(@NotNull final Runnable runnable, @NotNull final long runTime) {
        this.runnable = runnable;
        this.runTime = runTime;
    }

}
