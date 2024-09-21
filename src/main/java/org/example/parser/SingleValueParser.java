package org.example.parser;

import java.util.Map;

public class SingleValueParser implements Parser{
    int lowerBound;
    int upperBound;
    private Map<String, Integer> symbolicMapper;
    public SingleValueParser(int lowerBound, int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    public SingleValueParser(int lowerBound, int upperBound, Map<String, Integer> symbolicMapper){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.symbolicMapper = symbolicMapper;
    }


    @Override
    public boolean validate(String expression) {
        if(symbolicMapper != null && symbolicMapper.containsKey(expression)){
            expression = String.valueOf(symbolicMapper.get(expression));
        }
        return isValidNumber(expression, lowerBound, upperBound);
    }

    @Override
    public String interpret(String expression) {
        if(symbolicMapper != null && symbolicMapper.containsKey(expression)){
            expression = String.valueOf(symbolicMapper.get(expression));
        }
        return expression;
    }
}
