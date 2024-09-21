package org.example.parser;

import java.util.Map;

public class ListParser implements Parser{
    int lowerBound;
    int upperBound;
    Map<String, Integer> symbolicMapper;
    public ListParser(int lowerBound, int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    public ListParser(int lowerBound, int upperBound, Map<String, Integer> symbolicMapper){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.symbolicMapper = symbolicMapper;
    }

    @Override
    public boolean validate(String expression) {
        String[] parts = expression.split(CronConstants.LIST);
        boolean isValid = true;
        for (String part : parts) {
            if (!isValidNumber(part, lowerBound, upperBound)){
                if (symbolicMapper == null || !symbolicMapper.containsKey(part)) {
                    return false;
                }
            }

        }
        return true;
    }
    @Override
    public String interpret(String expression) {
        String[] parts = expression.split(CronConstants.LIST);
        StringBuilder listString = new StringBuilder();
        for(String part: parts){
            if(symbolicMapper != null && symbolicMapper.containsKey(part)){
                listString.append(symbolicMapper.get(part));
            }else{
                listString.append(part);
            }
            listString.append(" ");
        }

        return listString.toString();
    }
}
