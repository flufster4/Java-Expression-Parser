package com.flufster;

import com.flufster.jep.Expression;
import com.flufster.jep.parser.token.ExpressionTokenizer;
import com.flufster.jep.parser.tree.Node;
import com.flufster.jep.parser.tree.TokenizedExpressionParser;

public class Main {
    public static void main(String[] args) {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer("15/(2+(10-(3*3)))-7");
        System.out.println(tokenizer.tokenize());

        TokenizedExpressionParser parser = new TokenizedExpressionParser(tokenizer.tokenize());
        Node resultTree = parser.parse();
        resultTree.getToken().execute(resultTree.getLeft(), resultTree.getRight());

        System.out.println(resultTree.getToken().value());

//        Expression expression = new Expression("5(3)");
//        System.out.println(expression.evaluate());
    }
}