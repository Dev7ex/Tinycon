package com.dev7ex.tinycon.api;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author Dev7ex
 * @since 26.05.2023
 */
public class TinyconProvider {

    @Getter(AccessLevel.PUBLIC)
    private static TinyconApi tinyconApi;

    public static void registerApi(final TinyconApi tinyconApi) {
        TinyconProvider.tinyconApi = tinyconApi;
    }

    public static void unregisterApi() {
        TinyconProvider.tinyconApi = null;
    }

}
