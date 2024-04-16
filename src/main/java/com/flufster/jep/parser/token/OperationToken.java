package com.flufster.jep.parser.token;

import com.flufster.jep.math.Operation;
import com.flufster.jep.parser.tree.Node;

public class OperationToken implements Token {

    private Double value = 0d;
    private final Operation operation;

    public OperationToken(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void execute(Node left, Node right) {
        if (left == null || right == null) throw new ArithmeticException("Operation requires two operands.");

        //evaluate left and right nodes
        left.token.execute(left.left, left.left);
        right.token.execute(right.left, right.right);
        Double x = left.token.value();
        Double y = right.token.value();

        //calculate result
        switch (operation) {
            case ADDITION:
                value = x + y;
                break;
            case SUBTRACTION:
                value = x - y;
                break;
            case MULTIPLICATION:
                value = x * y;
                break;
            case DIVISION:
                value = x / y;
                break;
            case EXPONENT:
                value = Math.pow(x, y);
                break;
        }
    }

    @Override
    public Double value() {
        return value;
    }
}
