package com.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateUtil {

    public static String convertInstantToFormattedDate(Instant instant) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
        return df.format(Date.from(instant).getTime());
    }

}
