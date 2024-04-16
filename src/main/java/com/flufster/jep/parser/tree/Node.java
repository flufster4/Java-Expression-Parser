package com.flufster.jep.parser.tree;

import com.flufster.jep.parser.token.Token;

public class Node {

    public Token token;
    public Node left;
    public Node right;

    public Node(Token token, Node left, Node right) {
        this.token = token;
        this.left = left;
        this.right = right;
    }

    public Node(Token token) {
        this.token = token;
        this.left = null;
        this.right = null;
    }

}
