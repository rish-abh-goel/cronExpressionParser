package org.example.parser;

public interface Parser {

    boolean validate(String expression);
    String interpret(String expression);

    default boolean checkUpperAndLowerBound(int lowerBound, String start, int upperBound,
                                            String end){
        // Validate numeric range (e.g., 1-5)
        try {
            int startInt = Integer.parseInt(start);
            int endInt = Integer.parseInt(end);
            return startInt >= lowerBound && endInt <= upperBound && startInt <= endInt;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Check if the step value is a valid positive number
    default boolean isPositiveNumber(String s) {
        try {
            return Integer.parseInt(s) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    default boolean isValidNumber(String value, int lowerBound, int upperBound) {
        try {
            int num = Integer.parseInt(value);
            return num >= lowerBound && num <= upperBound;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
