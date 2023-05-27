package com.dev7ex.tinycon.api.console;

import com.dev7ex.tinycon.api.TinyconApi;
import com.dev7ex.tinycon.api.TinyconProvider;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ConsoleInputReader implements Runnable {

    private ConsoleInputRequest inputRequest;

    @Override
    public void run() {
        final TinyconApi tinyconApi = TinyconProvider.getTinyconApi();

        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String currentLine = "";

            while ((tinyconApi.isRunning()) && ((currentLine = bufferedReader.readLine()) != null)) {

                if (this.inputRequest != null) {
                    final ConsoleInputOption option = new ConsoleInputOption();

                    this.inputRequest.onRequest(currentLine, option);

                    if (option.isRemoveRequest()) {
                        this.inputRequest = null;
                    }

                    if (option.isCancel()) {
                        continue;
                    }
                }
                tinyconApi.getCommandManager().executeCommand(currentLine);
            }


        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }

}
