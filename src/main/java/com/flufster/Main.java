package com.flufster;

import com.flufster.jep.Expression;

public class Main {
    public static void main(String[] args) {
        Expression expression = new Expression("");
        System.out.println(expression.evaluate());
    }
}