package Status;

import java.util.Comparator;

public enum FlightStatus {
    SCHEDULED,
    DELAYED,
    CANCELLED;

    public static final Comparator<FlightStatus> statusOrder = new Comparator<FlightStatus>() {
        @Override
        public int compare(FlightStatus status1, FlightStatus status2) {
            return status1.ordinal() - status2.ordinal();
        }
    };
}
