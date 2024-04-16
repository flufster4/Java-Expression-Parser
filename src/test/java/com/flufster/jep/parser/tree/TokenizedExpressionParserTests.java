package com.flufster.jep.parser.tree;

import com.flufster.jep.Expression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenizedExpressionParserTests {

    private final Double delta = 0.0001;

    @Test
    void basicOperations() {
        Expression expression = new Expression("2^2*4/4+4-4");
        assertEquals(4, expression.evaluate());
    }

    @Test
    void advancedOperation() {
        Expression expression = new Expression("TAN45");
        assertEquals(1.6197, expression.evaluate(), delta);
    }

    @Test
    void complexExpression() {
        Expression expression = new Expression("SQRT((1+TAN(45))^6)");
        assertEquals(17.9800, expression.evaluate(), delta);
    }
}
