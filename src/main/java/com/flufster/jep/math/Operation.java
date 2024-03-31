package com.flufster.jep.math;

import lombok.Getter;

@Getter
public enum Operation {

    ADDITION(0, "+"),
    SUBTRACTION(0, "-"),
    MULTIPLICATION(1, "*"),
    DIVISION(1, "/"),
    EXPONENT(2, "^");

    private final int priority;
    private final String operation;

    Operation(int priority, String operation) {
        this.priority = priority;
        this.operation = operation;
    }
}
