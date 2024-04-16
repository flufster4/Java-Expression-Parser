package com.flufster.jep.math;

public enum Operation {

    ADDITION(0, "+"),
    SUBTRACTION(0, "-"),
    MULTIPLICATION(1, "*"),
    DIVISION(1, "/"),
    EXPONENT(2, "^");

    public final int priority;
    public final String operation;

    Operation(int priority, String operation) {
        this.priority = priority;
        this.operation = operation;
    }
}
