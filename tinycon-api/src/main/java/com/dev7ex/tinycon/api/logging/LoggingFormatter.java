package com.dev7ex.tinycon.api.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author Dev7ex
 * @since 26.05.2023
 *
 * Custom LoggingFormatter (Format: 01:01:2000 00:00:00 [LEVEL] [LOG])
 */
public class LoggingFormatter extends Formatter {

    private final DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy hh:mm:ss");

    @Override
    public final String format(final LogRecord record) {
        final StringBuilder formatBuilder = new StringBuilder();
        formatBuilder.append(this.dateFormat.format(new Date(record.getMillis())));
        formatBuilder.append(" [").append(record.getLevel()).append("] ");
        formatBuilder.append(super.formatMessage(record));
        formatBuilder.append("\n");
        return formatBuilder.toString();
    }

}
