package org.example.parser;


import java.util.HashMap;
import java.util.Map;

public class MinuteAndHourExpressionProcessor implements CronExpressionProcessor {

    private Map<String, Parser> parserStrategies;

    public MinuteAndHourExpressionProcessor(int lowerBound, int upperBound) {
        parserStrategies = new HashMap<>();
        parserStrategies.put(CronConstants.STEP, new StepParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.RANGE, new RangeParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.WILDCARD, new WildcardParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.LIST, new ListParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.RANGE_AND_STEP, new StepAndRangeParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.SINGLE_VALUE, new SingleValueParser(lowerBound, upperBound));

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
        } else{
            return validateAndGetValue(expression, CronConstants.SINGLE_VALUE, parserStrategies);
        }
    }





}

