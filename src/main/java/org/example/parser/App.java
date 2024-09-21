package org.example.parser;

public class App {
    public static void main(String[] args) {

        String[] outputFields = {"minute", "hour", "day of month", "month", "day of week", "command"};
        CronExpressionParser cronExpressionParser = new CronExpressionParser();
        String output = cronExpressionParser.parseCronExpression(args, outputFields);
        System.out.print(output);
    }
}