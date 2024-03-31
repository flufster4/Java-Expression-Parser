package com.flufster.jep.parser.token;

import com.flufster.jep.parser.tree.Node;
import org.jetbrains.annotations.Nullable;

public record NumberToken(Double value) implements Token {
    @Override
    public void execute(@Nullable Node left, @Nullable Node right) {}
}
