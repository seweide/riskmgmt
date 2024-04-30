package com.haier.hairy.rmp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 * @Description:
 * @author: lizhihao
 * @date: 2018/5/7 15:04
 */
public class DateUtils {

    public final static String  SHORT_FORMAT          = "yyyyMMdd";
    public final static String  LONG_FORMAT           = "yyyyMMddHHmmss";
    public final static String  WEB_FORMAT            = "yyyy-MM-dd";
    public final static String  TIME_FORMAT           = "HHmmss";
    public final static String  MONTH_FORMAT          = "yyyyMM";
    public final static String  CHINESE_DT_FORMAT      = "yyyy年MM月dd日";
    public final static String  NEW_FORMAT            = "yyyy-MM-dd HH:mm:ss";
    public final static String  NO_SECOND_FORMAT       = "yyyy-MM-dd HH:mm";

    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SHORT_DATE_GBK_FORMAT = "yyyy年MM月dd日";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分";
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String LONG_DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String MAIL_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String MAIL_DATE_HHMM_FORMAT = "HH:mm";
    public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String FULL_DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";
    public static final String FULL_DATE_COMPACT_FORMAT = "yyyyMMddHHmmssSSS";
    public static final String LDAP_DATE_FORMAT = "yyyyMMddHHmm'Z'";
    public static final String US_LOCALE_DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";

    public static final String MAIL_DATE_DT_PART_FORMAT = "yyyyMMdd";
    public static final String MAIL_DATE_MD_PART_FORMAT = "MMdd";
    public static final String MAIL_DATE_HOUR_DT_PART_FORMAT = "yyyyMMddHH";
    public static final String MAIL_TIME_TM_PART_FORMAT = "HHmmss";
    public static final String LONG_DATE_TM_PART_FORMAT = "HH:mm:ss";
    public static final String LONG_DATE_TM_PART_GBK_FORMAT = "HH时mm分ss秒";
    public static final String MAIL_DATA_DTM_PART_FORMAT="MM月dd日HH:mm";
    public static final String POINT_DATA_DTM_PART_FORMAT="yyyy.MM.dd";

    public static String getDateTimeString(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getDateTimeString(Date dateTime, String pattern) {
        return getLocalDateTimeByDate(dateTime).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime getDateTimeFromString(String dateTime, String pattern) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern));
    }

    public static Date getDateFromString(String date, String pattern) {
        if(StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getDateByLocalDateTime(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Date date = Date.from(zonedDateTime.toInstant());
        return date;
    }
    public static LocalDateTime getLocalDateTimeByDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());

    }


}
