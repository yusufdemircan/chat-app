package com.yedy.chat_app.exception;

public class YedyException extends RuntimeException {
    public YedyException(Throwable cause) {
        super(cause);
    }

    public YedyException(String message) {
        super(message);
    }
}