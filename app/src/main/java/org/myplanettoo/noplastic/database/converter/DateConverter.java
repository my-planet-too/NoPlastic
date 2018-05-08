package org.myplanettoo.noplastic.database.converter;

import android.arch.persistence.room.TypeConverter;

import org.apache.commons.lang3.time.DateParser;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateConverter {
    private static final DateParser DATABASE_FAST_PARSER;
    private static final SimpleDateFormat DATABASE_DATE_FORMAT;

    static {
        DATABASE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        DATABASE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        DATABASE_FAST_PARSER = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("UTC"), Locale.getDefault());
    }

    private DateConverter() {
    }

    @TypeConverter
    public static Date toDate(String timestamp) {
        try {
            return timestamp == null ? null : DATABASE_FAST_PARSER.parse(timestamp);
        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    @TypeConverter
    public static String toTimestamp(Date date) {
        return date == null ? null : DATABASE_DATE_FORMAT.format(date);
    }
}