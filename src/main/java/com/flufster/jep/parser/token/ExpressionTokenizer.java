package com.flufster.jep.parser.token;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTokenizer {

    private final String expression;
    private final List<String> result;

    public static final String numberRegex = "[0-9.]";
    public static final String basicOperationRegex = "[+\\-*/^]";
    public static final String advancedOperationRegex = "[A-Z]";

    public ExpressionTokenizer(String expression) {
        this.expression = expression;
        this.result = new ArrayList<>();
    }

    public List<String> tokenize() {
        StringBuilder workingToken = new StringBuilder();

        char[] expressionCharArray = expression.toCharArray();
        List<Character> expressionCharList = new ArrayList<>();
        for ( char character : expressionCharArray )
            expressionCharList.add(character);

        for (int i = 0; i < expressionCharList.size(); i++) {
            Character workingCharacter = expressionCharList.get(i);
            String workingCharecterString = Character.toString(workingCharacter);

            if (workingCharecterString.matches(numberRegex))
                workingToken.append(workingCharacter);

            if (workingCharecterString.matches(advancedOperationRegex)) {
                addImpliedMultiplication(workingToken);
                result.add(parseAdvancedOperation(expressionCharList, i).toString());
                result.add(expressionCharList.get(i).toString());
                expressionCharList.remove(i);
            }

            if (workingCharecterString.matches(basicOperationRegex)) {
                if (!workingToken.isEmpty()) {
                    result.add(workingToken.toString());
                    workingToken.setLength(0);
                }
                result.add(String.valueOf(workingCharacter));
            }

            if (workingCharecterString.equals("(")) {
                addImpliedMultiplication(workingToken);
                StringBuilder parenthesesBuilder = parseParentheses(expressionCharList, i);
                result.add(parenthesesBuilder.toString());
                i--;
            }

        }
        if (!workingToken.isEmpty())
            result.add(workingToken.toString());

        return result;
    }

    private void addImpliedMultiplication(StringBuilder workingToken) {
        if (!workingToken.isEmpty()) {
            result.add(workingToken.toString());
            result.add("*");
            workingToken.setLength(0);
        }
        try {
            if (result.get(result.size() - 1).charAt(0) == '(')
                result.add("*");
        } catch (IndexOutOfBoundsException ignore) {}
    }

    private StringBuilder parseParentheses(List<Character> expressionCharList, int i) {
        Character parenthesesContentCharacter = expressionCharList.get(i);
        StringBuilder parenthesesBuilder = new StringBuilder();

        while (!parenthesesContentCharacter.equals(')')) {
            parenthesesBuilder.append(parenthesesContentCharacter);
            expressionCharList.remove(i);
            parenthesesContentCharacter = expressionCharList.get(i);
        }

        try {
            while (expressionCharList.get(i).equals(')')) {
                parenthesesBuilder.append(')');
                expressionCharList.remove(i);
            }
        } catch (IndexOutOfBoundsException ignore) {}

        return parenthesesBuilder;
    }

    private StringBuilder parseAdvancedOperation(List<Character> expressionCharList, int i) {
        Character workingChar = expressionCharList.get(i);
        StringBuilder operationBuilder = new StringBuilder();

        try {
            while (workingChar.toString().matches(advancedOperationRegex)) {
                operationBuilder.append(workingChar);
                expressionCharList.remove(i);
                workingChar = expressionCharList.get(i);
            }

        } catch (IndexOutOfBoundsException ignore) {}

        return operationBuilder;
    }

}
