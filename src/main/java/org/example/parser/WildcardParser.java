package org.example.parser;

import java.util.ArrayList;
import java.util.List;

public class WildcardParser implements Parser{

    int lowerBound;
    int upperBound;

    public WildcardParser(int lowerBound, int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean validate(String expression) {
        return expression.equals(CronConstants.WILDCARD);
    }

    @Override
    public String interpret(String expression) {
        List<String> result = new ArrayList<>();
        for (int i = lowerBound; i <= upperBound; i++) {
            result.add(String.valueOf(i));
        }
        return String.join(" ", result);
    }



}
