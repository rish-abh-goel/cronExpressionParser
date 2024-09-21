package org.example.parser;

public class LastDayParser implements Parser{
    int lowerBound;
    int upperBound;

    public LastDayParser(int lowerBound, int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean validate(String expression) {
        // 'L' is valid if it's used alone or in specific fields (Day of Month or Day of Week)
        String prefix = expression.substring(0, expression.length() - 1);
        return expression.equals(CronConstants.LAST_DAY) ||
                (expression.endsWith(CronConstants.LAST_DAY) &&
                        isValidNumber(prefix, lowerBound, upperBound));
    }

    @Override
    public String interpret(String expression) {
        if (expression.equals(CronConstants.LAST_DAY)) {
            return "last day of month";  // If 'L' is used alone, it's the last day of the month
        } else if (expression.endsWith(CronConstants.LAST_DAY)) {
            String prefix = expression.substring(0, expression.length() - 1);
            return "last occurrence of day " + prefix;  // E.g., '5L' -> last Friday of the month
        }
        return "invalid";  // Default to invalid if expression is not valid
    }
}
