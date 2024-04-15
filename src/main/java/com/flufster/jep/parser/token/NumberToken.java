package com.flufster.jep.parser.token;

import com.flufster.jep.parser.tree.Node;

public record NumberToken(Double value) implements Token {
    @Override
    public void execute(Node left, Node right) {}
}
