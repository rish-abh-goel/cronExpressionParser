package org.example.parser;

public class CommandExpressionProcessor implements CronExpressionProcessor {

    @Override
    public String parseExpression(String expression) {
        if(expression != null){
            return expression;
        }
        else {
            throw new IllegalArgumentException("The command expression is null");
        }
    }
}
