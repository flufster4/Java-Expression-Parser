package com.flufster;

import com.flufster.jep.Expression;

public class Main {
    public static void main(String[] args) {
        Expression expression = new Expression("15/(3+2)-4");
        System.out.println(expression.evaluate());
    }
}