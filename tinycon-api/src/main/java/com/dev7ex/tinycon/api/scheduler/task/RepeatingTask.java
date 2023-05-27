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
public final class RepeatingTask {

    private Runnable runnable;
    private long repeatPeriod;
    private long callTime;

    public RepeatingTask(@NotNull final Runnable runnable, @NotNull final long repeatPeriod, @NotNull final long callTime) {
        this.runnable = runnable;
        this.repeatPeriod = repeatPeriod;
        this.callTime = callTime;
    }

    public RepeatingTask(@NotNull final Runnable runnable, @NotNull final long repeatPeriod) {
        this.runnable = runnable;
        this.repeatPeriod = repeatPeriod;
    }

}
