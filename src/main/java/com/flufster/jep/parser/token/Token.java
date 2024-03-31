package com.flufster.jep.parser.token;

import com.flufster.jep.parser.tree.Node;
import org.jetbrains.annotations.Nullable;

public interface Token {

    void execute(@Nullable Node left, @Nullable Node right);

    Double value();
}
