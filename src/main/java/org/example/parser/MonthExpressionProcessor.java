package org.example.parser;

import java.util.HashMap;
import java.util.Map;

public class MonthExpressionProcessor implements CronExpressionProcessor {

    private Map<String, Parser> parserStrategies;
    public MonthExpressionProcessor(int lowerBound, int upperBound){
        Map<String, Integer> monthMappper = new HashMap<>();
        monthMappper.put("JAN", 1);
        monthMappper.put("FEB", 2);
        monthMappper.put("MAR", 3);
        monthMappper.put("APR", 4);
        monthMappper.put("MAY", 5);
        monthMappper.put("JUN", 6);
        monthMappper.put("JUL", 7);
        monthMappper.put("AUG", 8);
        monthMappper.put("SEP", 9);
        monthMappper.put("OCT", 10);
        monthMappper.put("NOV", 11);
        monthMappper.put("DEC", 12);

        parserStrategies = new HashMap<>();


        // Register parser strategies
        parserStrategies.put(CronConstants.STEP, new StepParser(lowerBound, upperBound, monthMappper));
        parserStrategies.put(CronConstants.RANGE, new RangeParser(lowerBound, upperBound, monthMappper));
        parserStrategies.put(CronConstants.WILDCARD, new WildcardParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.LIST, new ListParser(lowerBound, upperBound, monthMappper));
        parserStrategies.put(CronConstants.RANGE_AND_STEP, new StepAndRangeParser(lowerBound, upperBound));
        parserStrategies.put(CronConstants.SINGLE_VALUE, new SingleValueParser(lowerBound, upperBound, monthMappper));

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

