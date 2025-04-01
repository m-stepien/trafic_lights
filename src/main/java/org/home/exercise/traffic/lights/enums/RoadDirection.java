package org.home.exercise.traffic.lights.enums;

public enum RoadDirection {
    NORTH, SOUTH, EAST, WEST;
    public static RoadDirection fromString(String input) {
        return RoadDirection.valueOf(input.trim().toUpperCase());
    }
}
