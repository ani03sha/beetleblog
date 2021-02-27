package com.beetleblog.app.exceptions;

import java.io.Serial;

public class CustomMongoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7254190063990066515L;

    public CustomMongoException(String message) {
        super(message);
    }
}
