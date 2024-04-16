package com.flufster.jep.math.advanced;

import com.flufster.jep.math.AdvancedOperationSolver;

import java.util.Optional;

public class TrigSolver implements AdvancedOperationSolver {

    @Override
    public Optional<Double> evaluate(String operation, Double operand) {
        return switch (operation) {
            case "COS" -> Optional.of(Math.cos(operand));
            case "SIN" -> Optional.of(Math.sin(operand));
            case "TAN" -> Optional.of(Math.tan(operand));
            case "ARCCOS" -> Optional.of(Math.acos(operand));
            case "ARCSIN" -> Optional.of(Math.asin(operand));
            case "ARCTAN" -> Optional.of(Math.atan(operand));
            default -> Optional.empty();
        };
    }
}
