package com.smilcool.server.common.exception;

/**
 * @author Angus
 * @date 2019/3/20
 */
public class SmilcoolException extends RuntimeException {

    public SmilcoolException() {
    }

    public SmilcoolException(String message) {
        super(message);
    }

    public SmilcoolException(Throwable cause) {
        super(cause);
    }
}
