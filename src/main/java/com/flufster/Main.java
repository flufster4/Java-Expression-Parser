package com.flufster;

import com.flufster.jep.Expression;

public class Main {
    public static void main(String[] args) {
        Expression expression = new Expression("3+7*10+3*2");
        System.out.println(expression.evaluate());
    }
}