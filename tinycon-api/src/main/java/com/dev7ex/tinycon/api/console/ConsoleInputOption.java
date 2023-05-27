package com.dev7ex.tinycon.api.console;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ConsoleInputOption {

    private boolean cancel = false;
    private boolean removeRequest = false;

}
