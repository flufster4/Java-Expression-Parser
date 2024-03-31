package com.flufster.jep.parser.tree;

import com.flufster.jep.parser.token.NumberToken;
import com.flufster.jep.parser.token.OperationToken;
import com.flufster.jep.math.Operation;

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

            if (token.matches(operationRegex)) {
                Operation operation;
//                Operation operation2;
                operation = findOperation(token);

                //Order of operations
//                try {
//                    String op2 = tokenizedExpression.get(i + 2);
//                    operation2 = findOperation(op2);
//                    if (operation2.getPriority() > operation.getPriority())
//                        continue;
//                } catch (Exception e) {
//                    System.out.println(Arrays.toString(e.getStackTrace()));
//                }

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
}
