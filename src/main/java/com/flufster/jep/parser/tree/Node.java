package com.flufster.jep.parser.tree;

import com.flufster.jep.parser.token.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {

    private Token token;
    private Node left;
    private Node right;

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
