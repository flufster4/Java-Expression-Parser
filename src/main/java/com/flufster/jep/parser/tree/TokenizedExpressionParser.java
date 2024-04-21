package com.flufster.jep.parser.tree;

import com.flufster.jep.Expression;
import com.flufster.jep.ExpressionFormatException;
import com.flufster.jep.math.AdvancedOperations;
import com.flufster.jep.math.Operation;
import com.flufster.jep.parser.token.ExpressionTokenizer;
import com.flufster.jep.parser.token.NumberToken;
import com.flufster.jep.parser.token.OperationToken;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TokenizedExpressionParser {

    private final List<String> tokenizedExpression;
    private final AdvancedOperations advancedOperations;
    private final HashMap<String, Expression> functionMap;

    public TokenizedExpressionParser(List<String> tokenizedExpression, HashMap<String, Expression> functionMap) {
        this.tokenizedExpression = tokenizedExpression;
        this.functionMap = functionMap;
        this.advancedOperations = new AdvancedOperations((operation, operand) -> {
            for (String key : functionMap.keySet())
                if (key.equals(operation))
                    return Optional.of(functionMap.get(key)
                            .variable('x', operand)
                            .evaluate());
            return Optional.empty();
        });
    }

    public Node parse() {
        Node result = null;

        for (int i = 0; i < tokenizedExpression.size(); i++) {
            String token = tokenizedExpression.get(i);

            if (token.charAt(0) == '(')
                evaluateParentheses(i);

            if (tokenizedExpression.size() == 1)
                return new Node(
                        new OperationToken(Operation.ADDITION),
                        new Node(new NumberToken(Double.valueOf(tokenizedExpression.get(0)))),
                        new Node(new NumberToken(0d))
                );

            if (((Character)token.charAt(0)).toString().matches(ExpressionTokenizer.advancedOperationRegex))
                evaluateAdvancedOperation(i);

            if (tokenizedExpression.size() == 1)
                return new Node(
                        new OperationToken(Operation.ADDITION),
                        new Node(new NumberToken(Double.valueOf(tokenizedExpression.get(0)))),
                        new Node(new NumberToken(0d))
                );

            if (token.matches(ExpressionTokenizer.basicOperationRegex)) {
                Operation operation = findOperation(token);

                try {
                    if (tokenizedExpression.get(i + 1).charAt(0) == '(')
                        evaluateParentheses(i + 1);

                    if (((Character)tokenizedExpression.get(i + 1).charAt(0)).toString().matches(ExpressionTokenizer.advancedOperationRegex))
                        evaluateAdvancedOperation(i + 1);

                    if (tokenizedExpression.get(i + 3).charAt(0) == '(')
                        evaluateParentheses(i + 3);

                    Operation secondOperation = findOperation(tokenizedExpression.get(i + 2));
                    if (secondOperation.priority > operation.priority) {
                        try {
                            if (findOperation(tokenizedExpression.get(i + 4)).priority > secondOperation.priority)
                                solveHigherOperation(i + 4);
                        } catch (IndexOutOfBoundsException ignore) {}
                        solveHigherOperation(i + 2);
                    }
                } catch (IndexOutOfBoundsException ignore) {}

                Double operand1 = Double.valueOf(tokenizedExpression.get(i - 1));
                Double operand2 = Double.valueOf(tokenizedExpression.get(i + 1));

                if (result == null)
                    result = new Node(
                            new OperationToken(operation),
                            new Node(new NumberToken(operand1)),
                            new Node(new NumberToken(operand2))
                    );

                else {
                    Node tempResult = result;
                    result = new Node(
                            new OperationToken(operation),
                            tempResult,
                            new Node(new NumberToken(operand2))
                    );
                }

            }
        }

        return result;
    }

    private void evaluateAdvancedOperation(int index) {
        try {
            if (tokenizedExpression.get(index + 1).charAt(0) == '(')
                evaluateParentheses(index + 1);
            Double result = advancedOperations.evaluateOperation(tokenizedExpression.get(index), Double.valueOf(tokenizedExpression.get(index + 1)));
            tokenizedExpression.remove(index);
            tokenizedExpression.remove(index);
            tokenizedExpression.add(index, result.toString());
        } catch (IndexOutOfBoundsException ignore) {
            throw new ExpressionFormatException("Improper use of function");
        }

    }

    private Operation findOperation(String operation) {
        for (Operation op : Operation.values())
            if (op.operation.equals(operation))
                return op;
        return Operation.ADDITION;
    }

    private Double parseParentheses(int index) {
        String parentheses = tokenizedExpression.get(index);
        if (!(parentheses.length() >= 2))
            throw new ExpressionFormatException("Invalid parentheses.");

        parentheses = parentheses.substring(1);
        parentheses = parentheses.substring(0, parentheses.length() - 1);

        Expression parenthesesExpression = new Expression(parentheses)
                .functionMap(functionMap);
        return parenthesesExpression.evaluate();
    }

    private void evaluateParentheses(int index) {
        Double parenthesesValue = parseParentheses(index);
        tokenizedExpression.remove(index);
        tokenizedExpression.add(index, parenthesesValue.toString());
    }

    private void solveHigherOperation(int operationIndex) {
        Double op1 = Double.valueOf(tokenizedExpression.get(operationIndex - 1));
        Double op2 = Double.valueOf(tokenizedExpression.get(operationIndex + 1));

        Node resultNode = new Node(
                new OperationToken(findOperation(tokenizedExpression.get(operationIndex))),
                new Node(new NumberToken(op1)),
                new Node(new NumberToken(op2))
        );
        resultNode.token.execute(resultNode.left, resultNode.right);

        int op1Location = operationIndex - 1;
        tokenizedExpression.remove(op1Location);
        tokenizedExpression.remove(op1Location);
        tokenizedExpression.remove(op1Location);
        tokenizedExpression.add(op1Location, resultNode.token.value().toString());
    }
}
