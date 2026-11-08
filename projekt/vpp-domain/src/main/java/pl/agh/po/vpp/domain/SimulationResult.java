package pl.agh.po.vpp.domain;

import java.util.List;

/**
 * Paczka wyników całej doby: to, co dostaje panel i scena 3D z POST /api/simulate.
 */
public record SimulationResult(Timeline timeline, OperatorSettings settings, List<StepResult> steps) {

    public SimulationResult {
        if (timeline == null || settings == null) {
            throw new IllegalArgumentException("SimulationResult wymaga osi czasu i ustawień");
        }
        steps = steps == null ? List.of() : List.copyOf(steps);
    }
}
