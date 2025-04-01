package org.home.exercise.traffic.lights.branch;

import org.home.exercise.traffic.lights.enums.LightsState;
import org.home.exercise.traffic.lights.Vehicle;

public interface BranchInterface {
    void add(Vehicle vehicle);
    Vehicle pop();
    Vehicle getFirst();
    int size();
    boolean canMove();
    void nextLight();
    int getCountOnGreen();
    double calcPriorityScore();
    LightsState getCurrentLight();
}
