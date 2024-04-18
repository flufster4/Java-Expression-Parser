package com.flufster.jep;

import com.flufster.jep.parser.token.ExpressionTokenizer;
import com.flufster.jep.parser.tree.Node;
import com.flufster.jep.parser.tree.TokenizedExpressionParser;

/**
 * The {@code Expression} class represents a mathematical expression. The primary
 * method of this call is {@code evaluate}. This method evaluates the expression
 * given on initialization and returns its value as a {@code Double}.
 */
public class Expression implements Comparable<Expression> {

    private final String expression;

    /**
     * Compares one {@code Expression}'s value to another. The rules for this
     * comparison are defined in {@linkplain java.lang.Double#compare(double, double)  Double.compare(this, another)}.
     * @param another the object to be compared.
     * @return the value {@code -1} if the argument's value is larger than this,
     * or the value {@code 1} if it's the other way around.
     */
    @Override
    public int compareTo(Expression another) {
        return Double.compare(evaluate(), another.evaluate());
    }


    public Expression(String expression) {
        this.expression = expression;
    }

    /**
     * Evaluates the expression given at initialization following the order of
     * operations (PEMDAS). Implied multiplication is allowed on both parentheses,
     * functions, and variables.
     * @return the result of the expression as a {@code Double}.
     */
    public Double evaluate() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer(expression);
        try {
            TokenizedExpressionParser parser = new TokenizedExpressionParser(tokenizer.tokenize());
            Node tree = parser.parse();
            tree.token.execute(tree.left, tree.right);
            return tree.token.value();
        } catch (Exception ignore) {
            throw new ExpressionFormatException("Invalid expression!");
        }
    }


}
