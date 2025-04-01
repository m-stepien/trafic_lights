package org.home.exercise.traffic.lights;

import org.home.exercise.traffic.lights.enums.RoadDirection;

public class Command {
    private String type;
    private String vehicleId;
    private String startRoad;
    private String endRoad;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStartRoad() {
        return startRoad;
    }

    public void setStartRoad(String startRoad) {
        this.startRoad = startRoad;
    }

    public String getEndRoad() {
        return endRoad;
    }

    public void setEndRoad(String endRoad) {
        this.endRoad = endRoad;
    }

    public Vehicle toVehicle() {
        if (vehicleId == null || startRoad == null || endRoad == null) return null;
        return new Vehicle(vehicleId,
                RoadDirection.fromString(startRoad), RoadDirection.fromString(endRoad));
    }

    public boolean isAddVehicle() {
        return "addVehicle".equalsIgnoreCase(type);
    }

    public boolean isStep() {
        return "step".equalsIgnoreCase(type);
    }
}
