package app.models.enums;

import java.util.Random;

public enum Status {
    NEW, FOR_SALE, SOLD, PAID, DELIVERED, CLOSED, EXPIRED, WITHDRAWN;

    private static final Random random = new Random();

    public static Status randomStatus()  {
        Status[] statuses = values();
        return statuses[random.nextInt(statuses.length)];
    }

    public static boolean isValidStatus(Status status) {
        for (Status c : Status.values()) {
            if (c.name().equals(status.name())) {
                return true;
            }
        }
        return false;
    }
}
