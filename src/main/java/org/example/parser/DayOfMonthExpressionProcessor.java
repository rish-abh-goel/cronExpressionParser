package org.example.parser;

import java.util.HashMap;
import java.util.Map;

public class DayOfMonthExpressionProcessor implements CronExpressionProcessor {

    private final Map<String, Parser> parserStrategies;
    public DayOfMonthExpressionProcessor(int lowerBound, int upperBound) {
        parserStrategies = new HashMap<>();
        parserStrategies.put(CronConstants.STEP, new StepParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.RANGE, new RangeParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.WILDCARD, new WildcardParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.LIST, new ListParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.RANGE_AND_STEP, new StepAndRangeParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.SINGLE_VALUE, new SingleValueParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.LAST_DAY, new LastDayParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.CALENDER_DAY, new CalenderDayParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.WEEKDAY_NEAREST, new WeekdayNearestParser(lowerBound, upperBound));

    }

    @Override
    public String parseExpression(String expression) {
        // Select and apply the appropriate parser strategy
        if(expression.contains(CronConstants.STEP) && expression.contains(CronConstants.RANGE)){
            return validateAndGetValue(expression, CronConstants.RANGE_AND_STEP, parserStrategies);
        } else if (expression.contains(CronConstants.STEP)) {
            return validateAndGetValue(expression, CronConstants.STEP, parserStrategies);
        } else if (expression.contains(CronConstants.RANGE)) {
            return validateAndGetValue(expression, CronConstants.RANGE, parserStrategies);
        } else if (expression.contains(CronConstants.LIST)) {
            return validateAndGetValue(expression, CronConstants.LIST, parserStrategies);
        } else if(expression.equals(CronConstants.WILDCARD)){
            return validateAndGetValue(expression, CronConstants.WILDCARD, parserStrategies);
        } else if(expression.contains(CronConstants.LAST_DAY)){
            return validateAndGetValue(expression, CronConstants.LAST_DAY, parserStrategies);
        } else if(expression.contains(CronConstants.CALENDER_DAY)){
            return validateAndGetValue(expression, CronConstants.CALENDER_DAY, parserStrategies);
        } else if(expression.contains(CronConstants.WEEKDAY_NEAREST)){
            return validateAndGetValue(expression, CronConstants.WEEKDAY_NEAREST, parserStrategies);
        } else if(expression.equals(CronConstants.NO_SPECIFIC_VALUE)) {
            return CronConstants.NO_SPECIFIC_VALUE;
        } else{
            return validateAndGetValue(expression, CronConstants.SINGLE_VALUE, parserStrategies);
        }
    }


}
