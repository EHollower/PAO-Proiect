package MyExceptions;

public class OverweightBaggageException extends Exception {
    private final int totalWeight;
    private final int capacity;

    public OverweightBaggageException(int totalWeight, int capacity) {
        super("Total weight exceeds aircraft capacity.");
        this.totalWeight = totalWeight;
        this.capacity = capacity;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " Total weight: " + totalWeight + " kg, current weight: " + capacity + " kg";
    }
}
