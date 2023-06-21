package ua.com.foxminded.exception;

@SuppressWarnings("serial")
public class BadArgsException extends Exception {
    
    private final String message;
    
    public BadArgsException(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
