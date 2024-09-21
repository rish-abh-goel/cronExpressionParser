package org.example.parser;

import java.util.Map;

public class StepAndRangeParser implements Parser{
    private final int lowerBound;
    private final int upperBound;
    private final Map<String, Integer> symbolicMapping;

    // Constructor for numeric fields (Minute, Hour)
    public StepAndRangeParser(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.symbolicMapping = null;  // No symbolic mapping for numeric fields
    }

    // Constructor for symbolic fields (Month, Day of Week)
    public StepAndRangeParser(Map<String, Integer> symbolicMapping) {
        this.symbolicMapping = symbolicMapping;
        this.lowerBound = -1;  // No numeric bounds for symbolic fields
        this.upperBound = -1;
    }
    @Override
    public boolean validate(String expression) {
        // Split the expression by "/"
        if (expression.contains(CronConstants.STEP)) {
            String[] parts = expression.split(CronConstants.STEP);

            // Ensure there is a range and a step value
            if (parts.length == 2) {
                String rangePart = parts[0];
                String stepPart = parts[1];

                // Validate the range part (numeric or symbolic)
                if (!validateRange(rangePart)) {
                    return false;
                }

                // Validate the step part (must be a positive number)
                return isPositiveNumber(stepPart);
            }
        }
        return false;  // If the expression doesn't contain a valid step and range
    }

    // Check if the range is valid (e.g., 0-30 or JAN-MAR)
    private boolean validateRange(String range) {
        if (range.contains(CronConstants.RANGE)) {
            String[] limits = range.split(CronConstants.RANGE);
            if (symbolicMapping != null && symbolicMapping.containsKey(limits[0]) &&
                    symbolicMapping.containsKey(limits[1])) {
                return true;
            } else {
                // Numeric field (e.g., 0-30)
               return checkUpperAndLowerBound(lowerBound, limits[0], upperBound, limits[1]);
            }
        }
        return false;
    }

    @Override
    public String interpret(String expression) {
        if (expression.contains(CronConstants.STEP)) {
            String[] parts = expression.split(CronConstants.STEP);

            // Interpret the range and step part
            String range = parts[0];
            int step = Integer.parseInt(parts[1]);

            return interpretStepAndRange(range, step);
        }

        return "invalid";
    }

    private String interpretStepAndRange(String range, int step) {
        if (range.contains(CronConstants.RANGE)) {
            String[] limits = range.split(CronConstants.RANGE);

            int start, end;
            if (symbolicMapping != null && symbolicMapping.containsKey(limits[0]) &&
                    symbolicMapping.containsKey(limits[1])) {
                // Handle symbolic mapping (e.g., JAN-MAR)
                start = symbolicMapping.getOrDefault(limits[0], -1);
                end = symbolicMapping.getOrDefault(limits[1], -1);
            } else {
                // Handle numeric range (e.g., 0-30)
                start = Integer.parseInt(limits[0]);
                end = Integer.parseInt(limits[1]);
            }

            // Ensure the start and end values are valid
            if (start == -1 || end == -1 || start > end) {
                return "invalid";
            }

            // Generate the output based on the step
            StringBuilder result = new StringBuilder();
            for (int i = start; i <= end; i += step) {
                result.append(i).append(" ");
            }
            return result.toString().trim();
        }

        return "invalid";
    }
}
