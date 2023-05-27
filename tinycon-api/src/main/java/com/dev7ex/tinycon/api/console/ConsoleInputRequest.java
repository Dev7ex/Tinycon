package com.dev7ex.tinycon.api.console;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
public interface ConsoleInputRequest {

    void onRequest(final String line, final ConsoleInputOption option);

}
