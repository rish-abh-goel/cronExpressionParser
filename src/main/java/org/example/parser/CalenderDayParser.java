package org.example.parser;

public class CalenderDayParser implements Parser{

    int lowerBound;
    int upperBound;

    public CalenderDayParser(int lowerBound, int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    @Override
    public boolean validate(String expression) {
        // 'C' must follow a numeric value, e.g., '5C'
        String prefix = expression.substring(0, expression.length() - 1);
        return expression.endsWith(CronConstants.CALENDER_DAY) &&
                isValidNumber(prefix, lowerBound, upperBound);
    }


    @Override
    public String interpret(String expression) {
        String suffix = expression.substring(0, expression.length() - 1);  // Get the numeric prefix
        return "nearest weekday to day " + suffix;  // E.g., '5C' -> nearest weekday to the 5th of the month
    }
}
