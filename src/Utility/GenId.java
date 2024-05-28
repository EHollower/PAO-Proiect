package Utility;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenId {
    private static GenId instance;
    private Set<String> generatedIds;
    private Random random;

    private GenId() {
        generatedIds = new HashSet<>();
        random = new Random();
    }

    // Static method to provide access to the singleton instance
    public static synchronized GenId getInstance() {
        if (instance == null) {
            instance = new GenId();
        }
        return instance;
    }

    // Method to generate a unique random ID
    public synchronized String generateUniqueId(int length) {
        if (length <= 0) {
            length = random.nextInt(7);
        }

        if (length == 0) ++length;

        StringBuilder sb = new StringBuilder();
        while (true) {
            for (int i = 0; i < length; i++) {
                char digit = (char) ('0' + random.nextInt(10));
                sb.append(digit);
            }
            String id = sb.toString();
            if (!generatedIds.contains(id)) {
                generatedIds.add(id);
                return id;
            }
            sb.setLength(0); // Clear StringBuilder for next iteration
        }
    }
}
