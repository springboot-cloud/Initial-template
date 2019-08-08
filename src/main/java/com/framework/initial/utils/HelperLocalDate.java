package com.framework.initial.utils;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author: litao
 * @description:
 * @createTime: 2019/4/16 0016 10:03
 */
public class HelperLocalDate {
    public final static String FORMAT_PATTERN1 = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_PATTERN = "yyyy-MM-dd HH:mm";
    public final static String FORMAT_PATTERN2 = "yyyyMMddHHmmss";
    public final static String FORMAT_PATTERN3 = "yyyy-MM-dd";
    public final static String FORMAT_PATTERN4 = "yyyyMMdd";
    public final static String FORMAT_PATTERN5 = "yyyy-MM-ddTHH:mm:ssZ";

    /**
     * @return String 返回类型(yyyy-MM-dd)
     * @throws
     * @Title: getNowDate
     * @Description: 获取String类型的当前日期
     */
    public static String getNowDate() {
        return localDateFormat(LocalDate.now(), FORMAT_PATTERN3);
    }

    /**
     * @return String 返回类型(yyyy-MM-dd)
     * @throws
     * @Title: getNowDate
     * @Description: 获取String类型的当前日期
     */
    public static String getNowDate(ZoneId zone) {
        return localDateFormat(LocalDate.now(zone), FORMAT_PATTERN3);
    }

    /**
     * @return String 返回类型 (yyyy-MM-dd HH:mm:ss)
     * @throws
     * @Title: getNowDateTime
     * @Description: 获取String类型的当前日期
     */
    public static String getNowDateTime() {
        return localDateTimeFormat(LocalDateTime.now(), FORMAT_PATTERN1);
    }

    /**
     * @return String 返回类型(yyyy-MM-dd HH:mm:ss)
     * @throws
     * @Title: getNowDateTime
     * @Description: 获取String类型的当前日期
     */
    public static String getNowDateTime(ZoneId zone) {
        return localDateTimeFormat(LocalDateTime.now(zone), FORMAT_PATTERN1);
    }

    /**
     * 获取当前时间，如：10:23:41.602
     * 时区为系统默认时区
     *
     * @return
     */
    public static LocalTime getNowTime() {
        return LocalTime.now();
    }

    /**
     * 获取zone对应时区的时间
     * 例如：getNowTime(ZoneId.of("UTC+2")) 获取东二区时间 如：4:23:41.602
     * getNowTime(ZoneId.of("UTC+8")) 获取东八区时间(北京上海时间) 如：10:23:41.602
     *
     * @param zone
     * @return
     */
    public static LocalTime getNowTime(ZoneId zone) {
        return LocalTime.now(zone);
    }

    /**
     * 取得当月的最后一天
     */
    public static LocalDate getLastDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 取得当月的最后一天
     */
    public static String getLastDayStrOfMonth(LocalDate localDate) {
        return localDateFormat(getLastDayOfMonth(localDate), FORMAT_PATTERN3);
    }

    /**
     * 取得当月的第一天
     */
    public static LocalDate getFirstDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 取得当月的第一天
     */
    public static String getFirstDayStrOfMonth(LocalDate localDate) {
        return localDateFormat(getFirstDayOfMonth(localDate), FORMAT_PATTERN3);
    }

    /**
     * 获取日期相差天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDateMinusDays(LocalDateTime startDate, LocalDateTime endDate) {
        Duration duration = Duration.between(startDate, endDate);
//        long hours = duration.toHours();//相差的小时数
//        long minutes = duration.toMinutes();//相差的分钟数
//        long millis = duration.toMillis();//相差毫秒数
//        long nanos = duration.toNanos();//相差的纳秒数
        return duration.toDays(); //相差的天数
    }

    /**
     * 获取日期相差天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDateMinusDays(LocalDate startDate, LocalDate endDate) {
        Duration duration = Duration.between(startDate, endDate);
//        long hours = duration.toHours();//相差的小时数
//        long minutes = duration.toMinutes();//相差的分钟数
//        long millis = duration.toMillis();//相差毫秒数
//        long nanos = duration.toNanos();//相差的纳秒数
        return duration.toDays(); //相差的天数
    }

    /**
     * @param @param  birthday
     * @param @return 设定文件
     * @return Short 返回类型
     * @throws
     * @Title: getAgeByBirthday
     * @Description: 根据生日获取年龄
     */
    public static int getAgeByBirthday(LocalDate birthday) {
        int birthdayYear = birthday.getYear();
        int currYear = LocalDate.now().getYear();
        int age = currYear - birthdayYear;
        return (age > 0 ? age + 1 : 0);
    }

    /**
     * 获取某个日期后N个月的日期
     *
     * @param currDate
     * @param timeInterval
     * @return
     */
    public static LocalDate getDateByMonth(LocalDate currDate, int timeInterval) {
        return currDate.plusMonths(timeInterval);
    }

    /**
     * 获得昨天
     *
     * @return
     */
    public static LocalDate getYesterday() {
        return LocalDate.now().minusDays(1);
    }

    /**
     * 获得N天前
     *
     * @param minusDays
     * @return
     */
    public static LocalDate getNDayAgo(int minusDays) {
        return LocalDate.now().minusDays(minusDays);
    }

    /**
     * 获取上周二的日期
     *
     * @return
     */
    public static LocalDate getLastThursday() {
        return LocalDate.now().minusWeeks(1)
                .with(DayOfWeek.THURSDAY);
    }

    /**
     * 返回周几
     *
     * @param localDate
     * @return
     */
    public static String getWeek(LocalDate localDate) {
        String[] weekDays = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        return weekDays[localDate.getDayOfWeek().getValue() - 1];
    }

    /**
     * 检查当前年是否为闰年
     *
     * @param localDate
     * @return
     */
    public static boolean isLeapyear(LocalDate localDate) {
        return localDate.isLeapYear();
    }

    /**
     * 将localDate 按照一定的格式转换成String
     * 如：2019-04
     *
     * @param localDate
     * @param pattern
     * @return
     */
    public static String localDateFormat(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将localDateTime 按照一定的格式转换成String
     * 如：2019-04-16T10:42:31.637
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String localDateTimeFormat(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将String 按照pattern 转换成LocalDate
     *
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDate localDateParse(String time, String pattern) {
        return LocalDate.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将String 按照pattern 转换成LocalDateTime
     *
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDateTime localDateTimeParse(String time, String pattern) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将date转换成String
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        return localDateTimeFormat(dateToLocalDateTime(date), pattern);
    }

    /**
     * 将LocalDate 转换成 Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        return localDateToDate(localDate, ZoneId.systemDefault());
    }

    /**
     * 将LocalDate 根据zone对应的时区转换成 Date
     *
     * @param localDate
     * @param zone
     * @return
     */
    public static Date localDateToDate(LocalDate localDate, ZoneId zone) {
        ZonedDateTime zdt = localDate.atStartOfDay(zone);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将 Date 转换成LocalDate
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        return dateToLocalDate(date, ZoneId.systemDefault());
    }

    /**
     * 将 Date 转换成LocalDate
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date, ZoneId zoneId) {
        Instant instant = date.toInstant();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * 将LocalDateTime 转换成 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return localDateTimeToDate(localDateTime, ZoneId.systemDefault());
    }

    /**
     * 将LocalDateTime 转换成 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime, ZoneId zone) {
        ZonedDateTime zdt = localDateTime.atZone(zone);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将 Date 转换成LocalDateTime, 使用系统默认时区
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return dateToLocalDateTime(date, ZoneId.systemDefault());
    }

    /**
     * 将 Date 转换成LocalDateTime
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date, ZoneId zone) {
        Instant instant = date.toInstant();
        return instant.atZone(zone).toLocalDateTime();
    }

    /**
     * 计算两个LocalDateTime 之间的毫秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalDateTime(LocalDateTime time1, LocalDateTime time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalTime 之间的毫秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalTime(LocalTime time1, LocalTime time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalDate 之间的毫秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalDate(LocalDate time1, LocalDate time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalDate 之间的Period
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Period periodLocalDate(LocalDate time1, LocalDate time2) {
        return Period.between(time1, time2);
    }

    /**
     * 计算两个Date 之间的Period
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Period periodDate(Date date1, Date date2) {
        return periodLocalDate(dateToLocalDate(date1), dateToLocalDate(date2));
    }

    /**
     * 计算两个Date之间的 Period
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsDate(Date time1, Date time2) {
        return minusToMillsLocalDateTime(dateToLocalDateTime(time1), dateToLocalDateTime(time2));
    }

    /**
     * 数据库中读出的时间字符串转LocalDateTime
     *
     * @param timestampStr
     * @return
     */
    public static final LocalDateTime timestampStr2LocalDateTime(String timestampStr) {
        return Timestamp.valueOf(timestampStr).toLocalDateTime();
    }

    /**
     * 时间戳转时间 秒
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 判断时间字符串（如:"07:00", "18:46"）是否有效
     *
     * @param time
     * @return
     */
    public static final boolean isValidLocalTime(String time) {
        try {
            LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {
        }
        return false;
    }

    /**
     * 将时间字符串（如:"07:00", "18:46"）转为LocalTime
     * @param time
     * @return
     */
    public static LocalTime toLocalTime(String time) {
        try {
            return LocalTime.parse(time);
        } catch (DateTimeParseException e) {
        }
        return null;
    }



}
