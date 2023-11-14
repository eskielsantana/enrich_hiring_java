package com.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");

    public static String convertInstantToFormattedDate(Instant instant) {
        return DATE_FORMAT.format(Date.from(instant).getTime());
    }
}
