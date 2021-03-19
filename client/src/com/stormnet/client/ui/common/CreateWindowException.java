package com.stormnet.client.ui.common;

public class CreateWindowException extends RuntimeException {

    public CreateWindowException(Throwable cause) {
        super("Error occurred during creation Window: ", cause);
    }
}
