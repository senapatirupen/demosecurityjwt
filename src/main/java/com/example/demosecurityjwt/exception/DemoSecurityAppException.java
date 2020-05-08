package com.example.demosecurityjwt.exception;

public class DemoSecurityAppException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    private String id;

    public DemoSecurityAppException() {
        super();
    }

    public DemoSecurityAppException(String message) {
        super(message);
        this.message = message;
    }

    public DemoSecurityAppException(String message, String id) {
        super(message);
        this.message = message;
        this.id = id;
    }

    public DemoSecurityAppException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public DemoSecurityAppException(Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

}
