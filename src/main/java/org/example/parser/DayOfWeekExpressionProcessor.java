package org.example.parser;

import java.util.HashMap;
import java.util.Map;

public class DayOfWeekExpressionProcessor implements CronExpressionProcessor {
    private Map<String, Parser> parserStrategies;

    public DayOfWeekExpressionProcessor(int lowerBound, int upperBound){
        Map<String, Integer> weekMapper = new HashMap<>();
        weekMapper.put("SUN", 0);
        weekMapper.put("MON", 1);
        weekMapper.put("TUE", 2);
        weekMapper.put("WED", 3);
        weekMapper.put("THU", 4);
        weekMapper.put("FRI", 5);
        weekMapper.put("SAT", 6);

        parserStrategies = new HashMap<>();
        parserStrategies.put(CronConstants.STEP, new StepParser(lowerBound, upperBound, weekMapper));
        parserStrategies.put(CronConstants.RANGE, new RangeParser(lowerBound, upperBound, weekMapper));
        parserStrategies.put(CronConstants.WILDCARD, new WildcardParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.LIST, new ListParser(lowerBound, upperBound, weekMapper));
        parserStrategies.put(CronConstants.RANGE_AND_STEP, new StepAndRangeParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.SINGLE_VALUE, new SingleValueParser(lowerBound, upperBound, weekMapper));
        parserStrategies.put(CronConstants.LAST_DAY, new LastDayParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.CALENDER_DAY, new CalenderDayParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.NTH_DAY, new NthDayWeekParser(lowerBound, upperBound));
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
        } else if(expression.contains(CronConstants.NTH_DAY)){
            return validateAndGetValue(expression, CronConstants.NTH_DAY, parserStrategies);
        } else if(expression.equals(CronConstants.NO_SPECIFIC_VALUE)) {
            return CronConstants.NO_SPECIFIC_VALUE;
        } else{
            return validateAndGetValue(expression, CronConstants.SINGLE_VALUE, parserStrategies);
        }
    }
}
