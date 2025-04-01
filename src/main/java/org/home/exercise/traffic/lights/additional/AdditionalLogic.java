package org.home.exercise.traffic.lights.additional;

import org.home.exercise.traffic.lights.branch.BranchInterface;
import org.home.exercise.traffic.lights.enums.RoadDirection;

import java.util.Map;

@FunctionalInterface
public interface AdditionalLogic {
    void performLightAdditionalLogic(Map<RoadDirection, BranchInterface> intersectionBranches);
}