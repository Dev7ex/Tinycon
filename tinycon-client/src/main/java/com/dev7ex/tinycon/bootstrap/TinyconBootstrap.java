package com.dev7ex.tinycon.bootstrap;

import com.dev7ex.tinycon.TinyconClient;
import com.dev7ex.tinycon.util.ClientShutdownThread;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
public class TinyconBootstrap {

    public static void main(final String[] args) {
        System.out.println("  _______ _                             \n" +
                " |__   __(_)                            \n" +
                "    | |   _ _ __  _   _  ___ ___  _ __  \n" +
                "    | |  | | '_ \\| | | |/ __/ _ \\| '_ \\ \n" +
                "    | |  | | | | | |_| | (_| (_) | | | |\n" +
                "    |_|  |_|_| |_|\\__, |\\___\\___/|_| |_|\n" +
                "                   __/ |                \n");
        System.out.println("Copyright (c) 2022 by Justin");
        System.out.println("Java version: " + System.getProperty("java.version") + ", Platform: " + System.getProperty("os.name"));
        System.out.println("");

        final TinyconClient client = new TinyconClient();
        client.bootstrap();

        Runtime.getRuntime().addShutdownHook(new ClientShutdownThread(client));
    }

}
