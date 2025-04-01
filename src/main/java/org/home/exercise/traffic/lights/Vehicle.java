package org.home.exercise.traffic.lights;

import org.home.exercise.traffic.lights.enums.RoadDirection;

public record Vehicle(String vehicleId,
                      RoadDirection startRoad,
                      RoadDirection endRoad) {
}
