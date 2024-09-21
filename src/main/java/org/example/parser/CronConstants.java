package org.example.parser;

public class CronConstants {

    public static final String MINUTE = "minute";
    public static final String HOUR = "hour";
    public static final String DAY_OF_WEEK = "day of week";
    public static final String MONTH = "month";
    public static final String DAY_OF_MONTH = "day of month";
    public static final String COMMAND = "command";
    public static final String WILDCARD = "*";
    public static final String SINGLE_VALUE = "number";
    public static final String STEP = "/";
    public static final String RANGE = "-";
    public static final String LIST = ",";
    public static final String CALENDER_DAY = "C";
    public static final String LAST_DAY = "L";
    public static final String NTH_DAY = "#";
    public static final String WEEKDAY_NEAREST = "W";
    public static final String NO_SPECIFIC_VALUE = "?";
    public static final String RANGE_AND_STEP = "-/" ;

    private static final String ALLOWED_CHARACTERS = "0123456789*/,-";

    public static final String POSITIVE_INTEGER_PATTERN = "^[0-9]\\d*$";

}
