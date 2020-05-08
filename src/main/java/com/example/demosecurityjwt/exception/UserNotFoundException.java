package com.example.demosecurityjwt.exception;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    private String id;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public UserNotFoundException(String message, String id) {
        super(message);
        this.message = message;
        this.id = id;
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

}
