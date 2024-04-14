package com.flufster.jep.math.advanced;

import com.flufster.jep.math.AdvancedOperationSolver;

import java.util.Optional;

public class TrigSolver implements AdvancedOperationSolver {

    @Override
    public Optional<Double> evaluate(String operation, Double operand) {
        return switch (operation) {
            case "COS" -> Optional.of(Math.cos(Math.toRadians(operand)));
            case "SIN" -> Optional.of(Math.sin(Math.toRadians(operand)));
            case "TAN" -> Optional.of(Math.tan(Math.toRadians(operand)));
            case "ARCCOS" -> Optional.of(Math.acos(Math.toRadians(operand)));
            case "ARCSIN" -> Optional.of(Math.asin(Math.toRadians(operand)));
            case "ARCTAN" -> Optional.of(Math.atan(Math.toRadians(operand)));
            default -> Optional.empty();
        };
    }
}
