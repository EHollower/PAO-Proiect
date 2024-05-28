package MyExceptions;

public class InvalidPassportNumberException extends Exception {
    private final String invalidPassportNumber;

    public InvalidPassportNumberException(String invalidPassportNumber) {
        super("Invalid passport number format.");
        this.invalidPassportNumber = invalidPassportNumber;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " Provided number: " + invalidPassportNumber;
    }
}
