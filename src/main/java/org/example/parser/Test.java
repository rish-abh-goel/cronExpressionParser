package org.example.parser;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String[] test1 = {"*/15", "0", "1,15", "*", "1-5", "/usr/bin/find"};
        String[] expected1 = {"0 15 30 45", "0", "1 15", "1 2 3 4 5 6 7 8 9 10 11 12", "1 2 3 4 5", "/usr/bin/find"};
        runTest("Test 1", test1, expected1);

        String[] test2 = {"30", "12", "1,15", "5-7", "1-5", "/usr/bin/task"};
        String[] expected2 = {"30", "12", "1 15", "5 6 7", "1 2 3 4 5", "/usr/bin/task"};
        runTest("Test 2", test2, expected2);

        String[] test3 = {"0", "1", "1W", "*", "*", "/usr/local/bin/run"};
        String[] expected3 = {"0", "1", "weekday nearest to day 1", "1 2 3 4 5 6 7 8 9 10 11 12", "0 1 2 3 4 5 6", "/usr/local/bin/run"};
        runTest("Test 3", test3, expected3);

        String[] test4 = {"*/5", "9-17", "10", "2,4,6", "0", "/bin/bash"};
        String[] expected4 = {"0 5 10 15 20 25 30 35 40 45 50 55", "9 10 11 12 13 14 15 16 17", "10", "2 4 6", "0", "/bin/bash"};
        runTest("Test 4", test4, expected4);

        String[] test5 = {"15", "0", "L", "*", "1", "/usr/bin/schedule"};
        String[] expected5 = {"15", "0", "last day of month", "1 2 3 4 5 6 7 8 9 10 11 12", "1", "/usr/bin/schedule"};
        runTest("Test 5", test5, expected5);

        String[] test6 = {"45", "6", "1C", "3", "1", "/usr/bin/cronjob"};
        String[] expected6 = {"45", "6", "nearest weekday to day 1", "3", "1", "/usr/bin/cronjob"};
        runTest("Test 6", test6, expected6);

        String[] test7 = {"0", "12", "1W", "JAN-DEC", "6", "/usr/bin/dailyjob"};
        String[] expected7 = {"0", "12", "weekday nearest to day 1", "1 2 3 4 5 6 7 8 9 10 11 12", "6", "/usr/bin/dailyjob"};
        runTest("Test 7", test7, expected7);

        String[] test8 = {"*/10", "0", "10-20", "1,2,3", "5", "/usr/bin/cleanup"};
        String[] expected8 = {"0 10 20 30 40 50", "0", "10 11 12 13 14 15 16 17 18 19 20", "1 2 3", "5", "/usr/bin/cleanup"};
        runTest("Test 8", test8, expected8);

        String[] test9 = {"0", "0", "5", "4", "0", "/usr/bin/hourly"};
        String[] expected9 = {"0", "0", "5", "4", "0", "/usr/bin/hourly"};
        runTest("Test 9", test9, expected9);

        String[] test10 = {"*/30", "14", "2,3,4", "JAN", "1-5", "/usr/bin/bimonthly"};
        String[] expected10 = {"0 30", "14", "2 3 4", "1", "1 2 3 4 5", "/usr/bin/bimonthly"};
        runTest("Test 10", test10, expected10);

        String[] test11 = {"5", "3", "1-15", "2-6", "2", "/usr/bin/weeklytask"};
        String[] expected11 = {"5", "3", "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15", "2 3 4 5 6", "2", "/usr/bin/weeklytask"};
        runTest("Test 11", test11, expected11);

        String[] test12 = {"*/45", "5", "*", "1,2,3", "4", "/usr/bin/monthlyjob"};
        String[] expected12 = {"0 45", "5", "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31", "1 2 3", "4", "/usr/bin/monthlyjob"};
        runTest("Test 12", test12, expected12);

        String[] test13 = {"*/20", "1-6", "5", "3,4", "*", "/usr/bin/specialtask"};
        String[] expected13 = {"0 20 40", "1 2 3 4 5 6", "5", "3 4", "0 1 2 3 4 5 6", "/usr/bin/specialtask"};
        runTest("Test 13", test13, expected13);

        String[] test14 = {"10", "4", "15", "*", "1", "/usr/bin/anotherjob"};
        String[] expected14 = {"10", "4", "15", "1 2 3 4 5 6 7 8 9 10 11 12", "1", "/usr/bin/anotherjob"};
        runTest("Test 14", test14, expected14);

        String[] test15 = {"0", "2", "*/5", "JAN", "1", "/usr/bin/testjob"};
        String[] expected15 = {"0", "2", "0 5 10 15 20 25 30", "1", "1", "/usr/bin/testjob"};
        runTest("Test 15", test15, expected15);

        String[] test16 = {"15", "15", "L", "FEB", "0", "/usr/bin/endofmonthtask"};
        String[] expected16 = {"15", "15", "last day of month", "2", "0", "/usr/bin/endofmonthtask"};
        runTest("Test 16", test16, expected16);

        String[] test17 = {"5-10", "5", "1", "3-5", "0", "/usr/bin/midweektask"};
        String[] expected17 = {"5 6 7 8 9 10", "5", "1", "3 4 5", "0", "/usr/bin/midweektask"};
        runTest("Test 17", test17, expected17);

        String[] test18 = {"45", "8", "10-20", "4,5", "2", "/usr/bin/testtask"};
        String[] expected18 = {"45", "8", "10 11 12 13 14 15 16 17 18 19 20", "4 5", "2", "/usr/bin/testtask"};
        runTest("Test 18", test18, expected18);

        String[] test19 = {"0", "0", "1C", "*", "*", "/usr/bin/dailycheck"};
        String[] expected19 = {"0", "0", "nearest weekday to day 1", "1 2 3 4 5 6 7 8 9 10 11 12", "0 1 2 3 4 5 6", "/usr/bin/dailycheck"};
        runTest("Test 19", test19, expected19);

        String[] test20 = {"*/30", "18", "L", "2,4", "1-5", "/usr/bin/eveningtask"};
        String[] expected20 = {"0 30", "18", "last day of month", "2 4", "1 2 3 4 5", "/usr/bin/eveningtask"};
        runTest("Test 20", test20, expected20);

        // Invalid expression tests
        String[] invalidTest21 = {"70", "0", "1,15", "*", "1-5", "/usr/bin/find"}; // Invalid minute value
        String[] invalidExpected21 = {"The expression: 70 is invalid, please provide appropriate expression"};
        runInvalidTest("Invalid Test 21", invalidTest21, invalidExpected21);

        String[] invalidTest22 = {"*/15", "25", "1,15", "*", "1-5", "/usr/bin/task"}; // Invalid hour value
        String[] invalidExpected22 = {"The expression: 25 is invalid, please provide appropriate expression"};
        runInvalidTest("Invalid Test 22", invalidTest22, invalidExpected22);

        String[] invalidTest23 = {"0", "0", "32", "*", "*", "/usr/local/bin/run"}; // Invalid day of month value
        String[] invalidExpected23 = {"The expression: 32 is invalid, please provide appropriate expression"};
        runInvalidTest("Invalid Test 23", invalidTest23, invalidExpected23);

        String[] invalidTest24 = {"0", "0", "1", "13", "0", "/bin/bash"}; // Invalid month value
        String[] invalidExpected24 = {"The expression: 13 is invalid, please provide appropriate expression"};
        runInvalidTest("Invalid Test 24", invalidTest24, invalidExpected24);

        String[] invalidTest25 = {"0", "0", "1", "*", "8", "/usr/bin/schedule"}; // Invalid day of week value
        String[] invalidExpected25 = {"The expression: 8 is invalid, please provide appropriate expression"};
        runInvalidTest("Invalid Test 25", invalidTest25, invalidExpected25);

        String[] invalidTest26 = {"*/5", "0", "1,15", "*", "*", "/usr/bin/find", "extra_arg"}; // Too many fields
        String[] invalidExpected26 = {"Too many fields in expression"};
        runInvalidTest("Invalid Test 26", invalidTest26, invalidExpected26);

        String[] invalidTest27 = {"45", "3", "1W", "INVALID_MONTH", "0", "/usr/bin/testjob"}; // Invalid month name
        String[] invalidExpected27 = {"The expression: INVALID_MONTH is invalid, please provide appropriate expression"};
        runInvalidTest("Invalid Test 27", invalidTest27, invalidExpected27);

        String[] invalidTest28 = {"*/10", "9", "0", "1-5", "C", "/usr/bin/anotherjob"}; // Invalid day of week special character
        String[] invalidExpected28 = {"The expression: 0 is invalid, please provide appropriate expression"};
        runInvalidTest("Invalid Test 28", invalidTest28, invalidExpected28);

        String[] invalidTest29 = {"*", "*", "1", "2", "2", "/usr/bin/testtask", "extra"}; // Too many fields
        String[] invalidExpected29 = {"Too many fields in expression"};
        runInvalidTest("Invalid Test 29", invalidTest29, invalidExpected29);

        String[] invalidTest30 = {"*/5", "15", "1,15", "*", "1-5", "/usr/bin/task", "arg1", "arg2"}; // Too many fields
        String[] invalidExpected30 = {"Too many fields in expression"};
        runInvalidTest("Invalid Test 30", invalidTest30, invalidExpected30);


    }

    private static void runTest(String testName, String[] input, String[] expectedValues) {
        System.out.println(testName + ": Running...");
        System.out.println("Input: " + Arrays.toString(input));
        String[] outputFields = {"minute", "hour", "day of month", "month", "day of week", "command"};
        // Call the CronExpressionParser to parse the input
        CronExpressionParser parser = new CronExpressionParser();
        String[] actualValues = parser.parserCronAsArray(input, outputFields); // Assuming parse() returns an array of fields

        // Check each field against expected values
        boolean passed = true;

        for (int i = 0; i < expectedValues.length; i++) {
            String expectedValue = expectedValues[i].trim();
            String actualValue = actualValues[i].trim();

            // Print values with their lengths for debugging
            System.out.printf("Comparing field %d: Expected '%s' (length: %d), Got '%s' (length: %d)%n",
                    i + 1, expectedValue, expectedValue.length(), actualValue, actualValue.length());

            if (!actualValue.equals(expectedValue)) {
                System.out.printf("Field %d of %s: Failed. Expected '%s', got '%s'%n",
                        i + 1, testName, expectedValue, actualValue);
                passed = false;
            } else {
                System.out.printf("Field %d of %s: Passed.%n", i + 1, testName);
            }
        }

        if (passed) {
            System.out.println(testName + ": All fields passed.");
        } else {
            System.out.println(testName + ": Some fields failed.");
        }

        System.out.println(); // For better readability between tests
    }


    private static void runInvalidTest(String testName, String[] input, String[] expectedErrors) {
        System.out.println(testName + ": Running...");
        System.out.println("Input: " + Arrays.toString(input));
        String[] outputFields = {"minute", "hour", "day of month", "month", "day of week", "command"};

        try {
            CronExpressionParser parser = new CronExpressionParser();
            parser.parserCronAsArray(input, outputFields); // Assuming parse() will throw IllegalArgumentException for invalid cases
            System.out.println(testName + ": Expected IllegalArgumentException, but none was thrown.");
        } catch (IllegalArgumentException e) {
            boolean passed = false;
            for (String expectedError : expectedErrors) {
                if (e.getMessage().contains(expectedError)) {
                    passed = true;
                    break;
                }
            }
            if (passed) {
                System.out.println(testName + ": Passed. Caught expected IllegalArgumentException: " + e.getMessage());
            } else {
                System.out.println(testName + ": Failed. Caught IllegalArgumentException, but message was: " + e.getMessage());
            }
        }

        System.out.println(); // For better readability between tests
    }


}
