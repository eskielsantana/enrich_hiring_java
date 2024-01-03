package domain.helper;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateHelper {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");

    private DateHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String convertInstantToFormattedDate(Instant instant) {
        return DATE_FORMAT.format(Date.from(instant).getTime());
    }
}
