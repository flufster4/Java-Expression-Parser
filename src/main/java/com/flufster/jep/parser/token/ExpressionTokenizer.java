package com.flufster.jep.parser.token;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTokenizer {

    private final String expression;

    public ExpressionTokenizer(String expression) {
        this.expression = expression;
    }

    public List<String> tokenize() {
        StringBuilder workingToken = new StringBuilder();
        String numberRegex = "[0-9.]";
        String operationRegex = "[+\\-*/]";
        List<String> result = new ArrayList<>();

        for (char workingCharacter : expression.toCharArray()) {
            String workingCharecterString = Character.toString(workingCharacter);

            if (workingCharecterString.matches(numberRegex))
                workingToken.append(workingCharacter);

            if (workingCharecterString.matches(operationRegex)) {
                result.add(workingToken.toString());
                result.add(String.valueOf(workingCharacter));
                workingToken.setLength(0);
            }

        }
        result.add(workingToken.toString());

        return result;
    }
}
