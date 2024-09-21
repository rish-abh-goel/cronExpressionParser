package org.example.parser;

import static org.example.parser.CronConstants.*;

public class CronExpressionProcessorFactory {
    public static CronExpressionProcessor getProcessor(String field) {

        switch (field) {
            case MINUTE:
                return new MinuteAndHourExpressionProcessor(0, 59);
            case HOUR:
                return new MinuteAndHourExpressionProcessor(0, 23);
            case MONTH:
                return new MonthExpressionProcessor(1, 12);
            case DAY_OF_WEEK:
                return new DayOfWeekExpressionProcessor(0, 6);
            case DAY_OF_MONTH:
                return new DayOfMonthExpressionProcessor(1, 31);
            case COMMAND:
                return new CommandExpressionProcessor();
            default:
                throw new IllegalArgumentException("Unknown field: " + field);
        }
    }
}
