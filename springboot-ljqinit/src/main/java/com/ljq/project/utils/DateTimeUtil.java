package com.ljq.project.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 *
 * <p>
 * 时间处理方法
 * </p>
 *
 * @author wlj
 * @since 2021/3/9
 */
public class DateTimeUtil {

    public static Long getNowSecond() {
        return getNowSecond(0);
    }

    public static Long getNowSecond(Integer timezone) {
        ZoneOffset zoneOffset = parseTimezone(timezone);
        return LocalDateTime.now(zoneOffset).toEpochSecond(zoneOffset);
    }

    public static Long getNowMilliSecond(Integer timezone) {
        ZoneOffset zoneOffset = parseTimezone(timezone);
        return LocalDateTime.now(zoneOffset).toInstant(zoneOffset).toEpochMilli();
    }

    public static String getNowDatetime(String format, Integer timezone) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        ZoneOffset zoneOffset = parseTimezone(timezone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.now(zoneOffset).format(formatter);
    }

    public static String second2Datetime(Long seconds, String format, Integer timezone) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        ZoneOffset zoneOffset = parseTimezone(timezone);
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(seconds, 0, zoneOffset);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

    public static Long datetime2Second(String date_str, String format, Integer timezone) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        ZoneOffset zoneOffset = parseTimezone(timezone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.parse(date_str, formatter);
        return dateTime.toEpochSecond(zoneOffset);
    }

    public static String millisecond2Datetime(Long ms, String format, Integer timezone) {
        if (format == null || format.isEmpty()) {
            format = "yyyyMMddHHmmssSSS";
        }

        ZoneOffset zoneOffset = parseTimezone(timezone);
        Long seconds = ms / 1000L;
        Integer nano = Math.toIntExact(ms % 1000L * 1000000L);
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(seconds, nano, zoneOffset);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

    public static Long datetime2Millisecond(String date_str, String format1, Integer timezone) {
        if (format1 == null || format1.isEmpty()) {
            format1 = "yyyyMMddHHmmss";
        }

        ZoneOffset zoneOffset = parseTimezone(timezone);
        DateTimeFormatter formatter = (new DateTimeFormatterBuilder()).appendPattern(format1)
            .appendValue(ChronoField.MILLI_OF_SECOND, 3).toFormatter();
        LocalDateTime dateTime = LocalDateTime.parse(date_str, formatter);
        return dateTime.toInstant(zoneOffset).toEpochMilli();
    }

    private static ZoneOffset parseTimezone(Integer timezone) {
        ZoneOffset zoneOffset = ZoneOffset.UTC;
        if (timezone == null) {
            timezone = 0;
        }

        if (timezone > 0) {
            zoneOffset = ZoneOffset.of("+" + timezone);
        } else if (timezone < 0) {
            zoneOffset = ZoneOffset.of(String.valueOf(timezone));
        }

        return zoneOffset;
    }

    public static LocalDateTime second2LocalDateTime(Integer seconds, Integer timezone) {
        ZoneOffset zoneOffset = parseTimezone(timezone);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(seconds, 0, zoneOffset);

        return localDateTime;
    }

}
