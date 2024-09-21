package org.example.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StepParser implements Parser{
    private final int lowerBound;
    private final int upperBound;

    private Map<String, Integer> symbolicMapper;
    public StepParser(int lowerBound, int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    public StepParser(int lowerBound, int upperBound, Map<String, Integer> symbolicMapper){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.symbolicMapper = symbolicMapper;
    }

    @Override
    public boolean validate(String expression) {
        String[] parts = expression.split(CronConstants.STEP);
        if (parts.length != 2) return false;

        return (parts[0].equals(CronConstants.WILDCARD) || isValidNumber(parts[0], lowerBound, upperBound)
        || symbolicMapper.containsKey(parts[0]) && isPositiveNumber(parts[1]));
    }

    @Override
    public String interpret(String expression) {
        String[] parts = expression.split(CronConstants.STEP);
        int step = Integer.parseInt(parts[1]);
        int start = parts[0].equals(CronConstants.WILDCARD) ? 0 :
                    symbolicMapper.containsKey(parts[0]) ? symbolicMapper.get(parts[0]) :
                            Integer.parseInt(parts[0]);

        List<String> result = new ArrayList<>();
        for (int i = start; i <= upperBound; i += step) {
            result.add(String.valueOf(i));
        }
        return String.join(" ", result);
    }
}
