package org.example.parser;

public class NthDayWeekParser implements Parser{
    int lowerBound;
    int upperBound;

    public NthDayWeekParser(int lowerBound, int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean validate(String expression) {
        // '#' must be used with a valid day of the week and a valid occurrence, e.g., '5#2' (2nd Friday)
        String[] parts = expression.split(CronConstants.NTH_DAY);
        if (parts.length != 2) return false;

        return isValidNumber(parts[0], lowerBound, upperBound) &&
                isValidNumber(parts[1], lowerBound, 5);
    }


    @Override
    public String interpret(String expression) {
        String[] parts = expression.split(CronConstants.NTH_DAY);  // Split by '#'
        String dayOfWeek = parts[0];
        String occurrence = parts[1];
        return occurrence + "th occurrence of day " + dayOfWeek + "th day of week";  // E.g., '5#2' -> 2nd Friday of the month
    }
}
