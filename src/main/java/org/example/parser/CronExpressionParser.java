package org.example.parser;

public class CronExpressionParser {

    public String parseCronExpression(String[] expressionFields, String[] outputFields){

        if(outputFields.length < expressionFields.length){
            throw new IllegalArgumentException("Too many fields in expression");
        } else if(outputFields.length > expressionFields.length){
            throw new IllegalArgumentException("Less fields in expression then expected");
        }
        StringBuilder output = new StringBuilder();
        for(int i= 0; i < outputFields.length; i++){
            CronExpressionProcessor cronExpressionProcessor =
                    CronExpressionProcessorFactory.getProcessor(outputFields[i]);
            output.append(formatField(outputFields[i],
                            cronExpressionProcessor.parseExpression(expressionFields[i]))).
                    append("\n");
        }
        return output.toString();
    }
    public String[] parserCronAsArray(String[] expressionFields, String[] outputFields){
        if(outputFields.length < expressionFields.length){
            throw new IllegalArgumentException("Too many fields in expression");
        } else if(outputFields.length > expressionFields.length){
            throw new IllegalArgumentException("Less fields in expression then expected");
        }
        String[] outputExpressions = new String[outputFields.length];
        for(int i= 0; i < outputFields.length; i++){
            CronExpressionProcessor cronExpressionProcessor =
                    CronExpressionProcessorFactory.getProcessor(outputFields[i]);
            outputExpressions[i] = cronExpressionProcessor.parseExpression(expressionFields[i]);
        }
        return outputExpressions;
    }
    private static String formatField(String fieldName, String fieldValues) {
        // Pad fieldName to ensure it takes up 14 columns
        String paddedFieldName = String.format("%-14s", fieldName);
        // Return formatted string
        return paddedFieldName + fieldValues;
    }
}
