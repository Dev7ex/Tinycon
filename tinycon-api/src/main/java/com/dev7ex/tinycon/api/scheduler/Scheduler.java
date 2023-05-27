package com.dev7ex.tinycon.api.scheduler;

import com.dev7ex.tinycon.api.scheduler.task.DelayedTask;
import com.dev7ex.tinycon.api.scheduler.task.RepeatingTask;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
public class Scheduler implements Tickable {

    private final List<Tickable> tickables = new ArrayList<>();
    private final Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();
    private final Map<Integer, DelayedTask> delayedTasks = new HashMap<>();
    private final LinkedList<RepeatingTask> repeatingTasks = new LinkedList<>();
    private int taskId = 0;

    @Override
    public void tick() {
        while (!this.taskQueue.isEmpty()) {
            this.taskQueue.poll().run();
        }

        for (final Tickable tickable : this.tickables) {
            tickable.tick();
        }

        final List<Runnable> runnables = new ArrayList<>();

        synchronized (this.delayedTasks) {
            final Iterator<Map.Entry<Integer, DelayedTask>> delayedTaskIterator = this.delayedTasks.entrySet().iterator();
            while (delayedTaskIterator.hasNext()) {
                final Map.Entry<Integer, DelayedTask> nextTask = delayedTaskIterator.next();
                if (System.currentTimeMillis() >= nextTask.getValue().getRunTime()) {
                    runnables.add(nextTask.getValue().getRunnable());
                    delayedTaskIterator.remove();
                }
            }
        }
        runnables.forEach(Runnable::run);

        final long currentTime = System.currentTimeMillis();
        final Iterator<RepeatingTask> repeatingTaskIterator = this.repeatingTasks.iterator();

        if (repeatingTaskIterator.hasNext()) {
            final RepeatingTask nextTask = repeatingTaskIterator.next();

            if (currentTime >= nextTask.getCallTime()) {
                nextTask.getRunnable().run();
            }

            if (nextTask.getRepeatPeriod() == 0L) {
                repeatingTaskIterator.remove();

            } else {
                nextTask.setCallTime(currentTime + nextTask.getRepeatPeriod());
            }
        }
    }

    public void runTaskLater(final Runnable runnable, final long delay) {
        final int taskIdentification = this.taskId++;
        final DelayedTask delayedTask = new DelayedTask(runnable, System.currentTimeMillis() + delay);

        synchronized (this.delayedTasks) {
            this.delayedTasks.put(taskIdentification, delayedTask);
        }
    }

    public void runTaskAsynchronously(final Runnable runnable, final long delay) {
        Executors.newCachedThreadPool().submit(runnable);
    }

    public void runTaskTimer(final Runnable runnable, final long delay, final long repeatPeriod) {
        final RepeatingTask repeatingTask = new RepeatingTask(runnable, repeatPeriod);
        repeatingTask.setCallTime(System.currentTimeMillis() + delay);
        this.repeatingTasks.add(repeatingTask);
    }

    public void runTaskTimer(final Runnable runnable, final long repeatPeriod) {
        final RepeatingTask repeatingTask = new RepeatingTask(runnable, repeatPeriod);
        this.repeatingTasks.add(repeatingTask);
    }

}
