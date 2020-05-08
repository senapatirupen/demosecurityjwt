package com.example.demosecurityjwt.exception;

public class UserExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    private String id;

    public UserExistException() {
        super();
    }

    public UserExistException(String message) {
        super(message);
        this.message = message;
    }

    public UserExistException(String message, String id) {
        super(message);
        this.message = message;
        this.id = id;
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public UserExistException(Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

}
