package com.example.demosecurityjwt.exception;

public class UserNotAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    private String id;

    public UserNotAuthorizedException() {
        super();
    }

    public UserNotAuthorizedException(String message) {
        super(message);
        this.message = message;
    }

    public UserNotAuthorizedException(String message, String id) {
        super(message);
        this.message = message;
        this.id = id;
    }

    public UserNotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public UserNotAuthorizedException(Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

}
