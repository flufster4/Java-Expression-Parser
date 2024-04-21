package com.flufster.jep.math;

import com.flufster.jep.math.advanced.MiscSolver;
import com.flufster.jep.math.advanced.TrigSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdvancedOperations {

    private final List<AdvancedOperationSolver> solvers = new ArrayList<>();

    public Double evaluateOperation(String operation, Double operand) {
        return solvers.stream()
                .map(solver -> solver.evaluate(operation, operand))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElse(0d);
    }

    public AdvancedOperations(AdvancedOperationSolver customSolver) {
        solvers.add(customSolver);
        solvers.add(new TrigSolver());
        solvers.add(new MiscSolver());
    }
}
