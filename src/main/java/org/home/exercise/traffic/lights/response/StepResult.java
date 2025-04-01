package org.home.exercise.traffic.lights.response;

import org.home.exercise.traffic.lights.Vehicle;

import java.util.List;

public record StepResult(List<Vehicle> leftVehicles) {
}
