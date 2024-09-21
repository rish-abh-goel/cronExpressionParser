package org.example.parser;

import java.util.Map;

public interface CronExpressionProcessor {

     String parseExpression(String expression);
     default String validateAndGetValue(String expression, String expressionType, Map<String, Parser> parserStrategies){
          Parser parser = parserStrategies.get(expressionType);
          boolean isValid = parser.validate(expression);
          if(!isValid){
               throw new IllegalArgumentException(String.format("The expression: %s is invalid, please provide appropriate expression", expression));
          }
          return parser.interpret(expression);
     }

}
