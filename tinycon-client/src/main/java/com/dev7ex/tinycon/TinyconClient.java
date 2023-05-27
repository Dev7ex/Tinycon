package com.dev7ex.tinycon;

import com.dev7ex.tinycon.api.TinyconApi;
import com.dev7ex.tinycon.api.TinyconProvider;
import com.dev7ex.tinycon.api.command.CommandManager;
import com.dev7ex.tinycon.api.console.ConsoleInputReader;
import com.dev7ex.tinycon.api.logging.LoggingOutputStream;
import com.dev7ex.tinycon.api.logging.TinyconLogger;
import com.dev7ex.tinycon.api.scheduler.Scheduler;
import com.dev7ex.tinycon.command.HelpCommand;
import com.dev7ex.tinycon.command.PingCommand;
import com.dev7ex.tinycon.command.StopCommand;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.PrintStream;
import java.util.logging.Level;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
@Getter(AccessLevel.PUBLIC)
public class TinyconClient implements TinyconApi, Runnable {

    @Getter(AccessLevel.PUBLIC)
    private static TinyconClient instance;

    private final TinyconLogger logger;
    private final CommandManager commandManager;
    private final ConsoleInputReader consoleInputReader;
    private boolean running = false;
    private Thread mainThread;
    private Thread readerThread;
    private Scheduler scheduler;

    public TinyconClient() {
        if (TinyconClient.instance == null) {
            TinyconClient.instance = this;
        }

        this.logger = new TinyconLogger("TinyconLogger");
        this.commandManager = new CommandManager();
        this.consoleInputReader = new ConsoleInputReader();
        this.scheduler = new Scheduler();

        System.setOut(new PrintStream(new LoggingOutputStream(this.logger, Level.ALL), true));
    }

    public void bootstrap() {
        TinyconProvider.registerApi(this);

        this.mainThread = new Thread(this);
        this.mainThread.start();
        this.running = this.mainThread.isAlive();

        this.readerThread = new Thread(this.consoleInputReader);
        this.readerThread.start();

        this.registerCommands();
    }

    public void registerCommands() {
        this.commandManager.registerCommand(new HelpCommand());
        this.commandManager.registerCommand(new PingCommand());
        this.commandManager.registerCommand(new StopCommand());
    }

    @Override
    public void run() {
        try {
            long lastTime = System.currentTimeMillis();
            long overloadedTime = 0L;

            while (this.running) {
                final long currentTime = System.currentTimeMillis();
                long pastTime = currentTime - lastTime;

                if (pastTime > 1000) {
                    this.logger.warning("The Client hangs " + pastTime + "ms behind");
                    pastTime = 0L;
                }
                overloadedTime += pastTime;
                lastTime = currentTime;
                while (overloadedTime > 50L) {
                    overloadedTime -= 50L;
                    this.tick();
                }
                Thread.sleep(Math.max(1L, 50L - overloadedTime));
            }

        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }

    public void tick() {
        this.scheduler.tick();
    }

    public void shutdown() {
        TinyconProvider.unregisterApi();

        this.running = false;
        this.mainThread.interrupt();
        this.readerThread.interrupt();

        this.logger.info("Client stopped!");
    }

}
