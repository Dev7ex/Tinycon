package com.dev7ex.tinycon.api.logging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Justin
 * @since 28.03.2022
 *
 * Custom OutputStream to change System.out
 */
public class LoggingOutputStream extends ByteArrayOutputStream {

    private final Logger logger;
    private final Level level;

    public LoggingOutputStream(final Logger logger, final Level level) {
        this.logger = logger;
        this.level = level;
    }

    @Override
    public void flush() throws IOException {
        final String content = super.toString(StandardCharsets.UTF_8.name());
        super.reset();
        if(!(content.isEmpty()) && (!content.equals(System.getProperty("line.separator")))) {
            this.logger.logp(this.level, "", "", content);
        }
    }

}
