package com.flufster.jep.parser.tree;

import com.flufster.jep.math.Operation;
import com.flufster.jep.parser.token.ExpressionTokenizer;
import com.flufster.jep.parser.token.NumberToken;
import com.flufster.jep.parser.token.OperationToken;

import java.util.List;

public class TokenizedExpressionParser {

    private final List<String> tokenizedExpression;

    public TokenizedExpressionParser(List<String> tokenizedExpression) {
        this.tokenizedExpression = tokenizedExpression;
    }

    public Node parse() {
        Node result = null;
        String operationRegex = "[+\\-*/]";

        for (int i = 0; i < tokenizedExpression.size(); i++) {
            String token = tokenizedExpression.get(i);

            if (token.toCharArray()[0] == '(')
                evaluateParentheses(i);

            if (token.matches(operationRegex)) {
                Operation operation;
                operation = findOperation(token);

                try {
                    if (tokenizedExpression.get(i+3).toCharArray()[0] == '(')
                        evaluateParentheses(i + 3);

                    if (tokenizedExpression.get(i+1).toCharArray()[0] == '(')
                        evaluateParentheses(i + 1);


                    if (findOperation(tokenizedExpression.get(i+2)).getPriority() > operation.getPriority()) {
                        Double op1 = Double.valueOf(tokenizedExpression.get(i + 1));
                        Double op2 = Double.valueOf(tokenizedExpression.get(i + 3));

                        Node resultNode = new Node(
                                new OperationToken(findOperation(tokenizedExpression.get(i+2))),
                                new Node(new NumberToken(op1)),
                                new Node(new NumberToken(op2))
                        );
                        resultNode.getToken().execute(resultNode.getLeft(), resultNode.getRight());

                        tokenizedExpression.remove(i + 1);
                        tokenizedExpression.remove(i + 1);
                        tokenizedExpression.remove(i + 1);
                        tokenizedExpression.add(i + 1, resultNode.getToken().value().toString());
                    }
                } catch (IndexOutOfBoundsException e) {
//                    System.out.println(Arrays.toString(e.getStackTrace()));
                }

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

    private Operation findOperation(String operation) {
        for (Operation op : Operation.values())
            if (op.getOperation().equals(operation))
                return op;
        return null;
    }

    private Double parseParentheses(int index) {
        String parentheses = tokenizedExpression.get(index);
        if (parentheses.length() >= 2) {
            parentheses = parentheses.substring(1);
            parentheses = parentheses.substring(0, parentheses.length() - 1);
        }
        ExpressionTokenizer tokenizer = new ExpressionTokenizer(parentheses);
        TokenizedExpressionParser parser = new TokenizedExpressionParser(tokenizer.tokenize());
        Node parenthesesTree = parser.parse();
        parenthesesTree.getToken().execute(parenthesesTree.getLeft(), parenthesesTree.getRight());
        return parenthesesTree.getToken().value();
    }

    private void evaluateParentheses(int index) {
        Double parenthesesValue = parseParentheses(index);
        tokenizedExpression.remove(index);
        tokenizedExpression.add(index, parenthesesValue.toString());
    }
}
