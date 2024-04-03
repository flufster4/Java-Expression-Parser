package com.flufster;

import com.flufster.jep.parser.token.ExpressionTokenizer;
import com.flufster.jep.parser.tree.Node;
import com.flufster.jep.parser.tree.TokenizedExpressionParser;

public class Main {
    public static void main(String[] args) {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer("(2*(2^3))/(0.5*2+1)");
        TokenizedExpressionParser parser = new TokenizedExpressionParser(tokenizer.tokenize());

        Node tree = parser.parse();
        tree.getToken().execute(tree.getLeft(), tree.getRight());
        System.out.println(tree.getToken().value());

    }
}