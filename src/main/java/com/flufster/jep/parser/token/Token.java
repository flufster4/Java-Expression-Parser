package com.flufster.jep.parser.token;

import com.flufster.jep.parser.tree.Node;

public interface Token {

    void execute(Node left, Node right);

    Double value();
}
