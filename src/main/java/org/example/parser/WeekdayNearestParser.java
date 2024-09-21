package org.example.parser;

public class WeekdayNearestParser implements Parser{
    int lowerBound;
    int upperBound;

    public WeekdayNearestParser(int lowerBound, int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean validate(String expression) {
        String prefix = expression.substring(0, expression.length() - 1);
        // 'W' must follow a valid day of the month, e.g., '15W'
        return expression.endsWith(CronConstants.WEEKDAY_NEAREST) &&
                (prefix.isEmpty() || isValidNumber(prefix, lowerBound, upperBound));
    }

    private boolean isValidDayOfMonth(String expression) {
        // Ensure valid day of the month before 'W'
        String prefix = expression.substring(0, expression.length() - 1);
        try {
            int num = Integer.parseInt(prefix);
            return num >= lowerBound && num <= upperBound;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String interpret(String expression) {
        String prefix = expression.substring(0, expression.length() - 1);  // Get the numeric prefix
        return "weekday nearest to day " + prefix;  // E.g., '15W' -> nearest weekday to the 15th
    }
}
