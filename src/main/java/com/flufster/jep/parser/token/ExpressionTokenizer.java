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
        List<String> result = new ArrayList<>();

        String numberRegex = "[0-9.]";
        String operationRegex = "[+\\-*/]";

        char[] expressionCharArray = expression.toCharArray();
        List<Character> expressionCharList = new ArrayList<>();
        for ( char character : expressionCharArray )
            expressionCharList.add(character);

        for (int i = 0; i < expressionCharList.size(); i++) {
            Character workingCharacter = expressionCharList.get(i);
            String workingCharecterString = Character.toString(workingCharacter);

            if (workingCharecterString.matches(numberRegex))
                workingToken.append(workingCharacter);

            if (workingCharecterString.matches(operationRegex)) {
                if (!workingToken.isEmpty()) {
                    result.add(workingToken.toString());
                    workingToken.setLength(0);
                }
                result.add(String.valueOf(workingCharacter));
            }

            if (workingCharecterString.equals("(")) {
                if (!workingToken.isEmpty()) {
                    result.add(workingToken.toString());
                    result.add("*");
                    workingToken.setLength(0);
                }

                Character parenthesesContentCharacter = expressionCharList.get(i);
                StringBuilder parenthesesBuilder = new StringBuilder();

                while (!parenthesesContentCharacter.equals(')')) {
                    parenthesesBuilder.append(parenthesesContentCharacter);
                    expressionCharList.remove(i);
                    parenthesesContentCharacter = expressionCharList.get(i);
                }

                try {
                    while (true) {
                        if (expressionCharList.get(i).equals(')')) {
                            parenthesesBuilder.append(')');
                            expressionCharList.remove(i);
                            continue;
                        }
                        break;
                    }
                } catch (IndexOutOfBoundsException ignore) {}

                result.add(parenthesesBuilder.toString());
                i--;
            }

        }
        if (!workingToken.isEmpty())
            result.add(workingToken.toString());

        return result;
    }
}
