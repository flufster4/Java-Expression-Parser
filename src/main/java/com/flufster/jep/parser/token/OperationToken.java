package com.flufster.jep.parser.token;

import com.flufster.jep.math.Operation;
import com.flufster.jep.parser.tree.Node;
import org.jetbrains.annotations.Nullable;

public class OperationToken implements Token {

    private Double value = 0d;
    private final Operation operation;

    public OperationToken(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void execute(@Nullable Node left, @Nullable Node right) {
        if (left == null || right == null) throw new ArithmeticException("Operation requires two operands.");

        //evaluate left and right nodes
        left.getToken().execute(left.getLeft(), left.getRight());
        right.getToken().execute(right.getLeft(), right.getRight());
        Double x = left.getToken().value();
        Double y = right.getToken().value();

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
        }
    }

    @Override
    public Double value() {
        return value;
    }
}
