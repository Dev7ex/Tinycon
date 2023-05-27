package com.dev7ex.tinycon.util;

import com.dev7ex.tinycon.TinyconClient;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
public class ClientShutdownThread extends Thread {

    private final TinyconClient client;

    public ClientShutdownThread(final TinyconClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        this.client.shutdown();
    }

}
