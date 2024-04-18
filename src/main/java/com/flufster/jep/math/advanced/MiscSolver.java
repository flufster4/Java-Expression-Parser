package com.flufster.jep.math.advanced;

import com.flufster.jep.math.AdvancedOperationSolver;

import java.util.Optional;

public class MiscSolver implements AdvancedOperationSolver {

    @Override
    public Optional<Double> evaluate(String operation, Double operand) {
        return switch (operation) {
            case "SQRT" -> Optional.of(Math.sqrt(operand));
            case "EXP" -> Optional.of(Math.exp(operand));
            case "LOG" -> Optional.of(Math.log(operand));
            case "LOG10" -> Optional.of(Math.log10(operand));
            case "FACT" -> Optional.of((double) factorial(operand));
            default -> Optional.empty();
        };
    }

    private long factorial(double n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
