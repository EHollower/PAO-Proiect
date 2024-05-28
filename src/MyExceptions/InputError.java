package MyExceptions;

public class InputError extends Exception {
    public InputError(String message) {
        super(message); // Call the super constructor to store the error message
    }
}
