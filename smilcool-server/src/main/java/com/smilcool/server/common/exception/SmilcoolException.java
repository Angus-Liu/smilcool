package com.smilcool.server.common.exception;

import lombok.NoArgsConstructor;

/**
 * Smilcool Exception
 */
@NoArgsConstructor
public class SmilcoolException extends RuntimeException {

    public SmilcoolException(String message) {
        super(message);
    }

    public SmilcoolException(Throwable cause) {
        super(cause);
    }
}
