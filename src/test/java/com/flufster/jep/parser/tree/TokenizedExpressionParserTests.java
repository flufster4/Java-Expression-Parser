package com.flufster.jep.parser.tree;

import com.flufster.jep.Expression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenizedExpressionParserTests {

    @Test
    void basicOperations() {
        Expression expression = new Expression("2^2*4/4+4-4");
        assertEquals(4, expression.evaluate());
    }

    @Test
    void simpleVariable() {
        Expression expression = new Expression("1+x")
                .variable('x', 1d);
        assertEquals(2, expression.evaluate());
    }

    @Test
    void leftImpliedMultiplicationVariable() {
        Expression expression = new Expression("x2")
                .variable('x', 2d);
        assertEquals(4, expression.evaluate());
    }

    @Test
    void rightImpliedMultiplicationVariable() {
        Expression expression = new Expression("2x")
                .variable('x', 3d);
        assertEquals(6, expression.evaluate());
    }

    @Test
    void simpleCustomFunction() {
        Expression expression = new Expression("ADD(1)")
                .function("ADD", new Expression("10+x"));
        assertEquals(11, expression.evaluate());
    }

    @Test
    void nestedCustomFunction() {
        Expression expression = new Expression("DIV(5^ADD(x))")
                .variable('x', 2d)
                .function("DIV", new Expression("x/2"))
                .function("ADD", new Expression("x+1"));
        assertEquals(62.5, expression.evaluate());
    }


    @Test
    void complexExpression() {
        Expression expression = new Expression("SQRT((1+TAN(45))^6)");
        double delta = 0.0001;
        assertEquals(17.9800, expression.evaluate(), delta);
    }
}
