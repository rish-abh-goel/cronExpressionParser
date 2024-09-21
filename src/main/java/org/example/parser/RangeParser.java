package org.example.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RangeParser implements Parser{
    private final int lowerBound;
    private final int upperBound;
    private final Map<String, Integer> symbolicMapping;

    // Constructor for numeric ranges
    public RangeParser(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.symbolicMapping = null;
    }

    // Constructor for symbolic ranges (e.g., JAN-MAR)
    public RangeParser(int lowerBound, int upperBound, Map<String, Integer> symbolicMapping) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.symbolicMapping = symbolicMapping;
    }
    @Override
    public boolean validate(String expression) {
        if (expression.contains(CronConstants.RANGE)) {
            String[] limits = expression.split("-");
            if (limits.length == 2) {
                if (symbolicMapping != null && symbolicMapping.containsKey(limits[0]) &&
                        symbolicMapping.containsKey(limits[1])) {
                    return true;
                } else {
                    return checkUpperAndLowerBound(lowerBound, limits[0], upperBound, limits[1]);
                }
            }
        }
        return false;
    }

    @Override
    public String interpret(String expression) {
        String[] parts = expression.split(CronConstants.RANGE);
        int start;
        int end;
        try {
            start = Integer.parseInt(parts[0]);
            end = Integer.parseInt(parts[1]);
        }catch (NumberFormatException e){
            start = symbolicMapping.get(parts[0]);
            end = symbolicMapping.get(parts[1]);
        }
        List<String> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.add(String.valueOf(i));
        }
        return String.join(" ", result);
    }
}
