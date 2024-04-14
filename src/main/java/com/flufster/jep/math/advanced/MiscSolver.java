package com.flufster.jep.math.advanced;

import com.flufster.jep.math.AdvancedOperationSolver;

import java.util.Optional;

public class MiscSolver implements AdvancedOperationSolver {

    @Override
    public Optional<Double> evaluate(String operation , Double operand) {
        return switch (operation) {
            case "SQRT" -> Optional.of(Math.sqrt(operand));
            default -> Optional.empty();
        };
    }
}
