package com.events.business.utlis;

import com.events.business.dto.EventDTO;
import com.events.business.model.Event;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String YYYMMDD_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static Timestamp createdAt(EventDTO body) {
        Timestamp createdAtTimeStamp;
        try {
            createdAtTimeStamp = new Timestamp(DATE_FORMAT.parse(body.getCreatedAt()).getTime());
        } catch (ParseException e) {
            createdAtTimeStamp = new Timestamp(Instant.now().toEpochMilli());
        }
        return createdAtTimeStamp;
    }

    public static String getCreatedAtAsFormatString(Event event) {
        return new SimpleDateFormat(YYYMMDD_PATTERN).format(
                new Date(event.getCreatedAt().getTime())
        );
    }
}
