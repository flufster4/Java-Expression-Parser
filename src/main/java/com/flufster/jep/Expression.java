package com.flufster.jep;

import com.flufster.jep.parser.token.ExpressionTokenizer;
import com.flufster.jep.parser.tree.Node;
import com.flufster.jep.parser.tree.TokenizedExpressionParser;

public class Expression {

    private final String expression;

    public Expression(String expression) {
        this.expression = expression;
    }

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
