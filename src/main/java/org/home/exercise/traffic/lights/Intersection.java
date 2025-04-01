package org.home.exercise.traffic.lights;

import org.home.exercise.traffic.lights.additional.AdditionalLogic;
import org.home.exercise.traffic.lights.branch.BranchFactory;
import org.home.exercise.traffic.lights.branch.BranchFactoryProvider;
import org.home.exercise.traffic.lights.branch.BranchInterface;
import org.home.exercise.traffic.lights.enums.LightsState;
import org.home.exercise.traffic.lights.enums.RoadDirection;
import org.home.exercise.traffic.lights.response.StepResult;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Intersection {
    private final Map<RoadDirection, BranchInterface> vehicleBranches = new EnumMap<>(RoadDirection.class);
    private final AdditionalLogic additionalLogic;
    private final static int MIN_GREEN = 3;

    public Intersection() {
        BranchFactory branchFactory = BranchFactoryProvider.getFactory();
        this.additionalLogic = branchFactory.getAdditionalLogic();
        for (RoadDirection dir : RoadDirection.values()) {
            vehicleBranches.put(dir, branchFactory.createBranch());
        }
        vehicleBranches.get(RoadDirection.SOUTH).nextLight();
        vehicleBranches.get(RoadDirection.SOUTH).nextLight();
        this.additionalLogic.performLightAdditionalLogic(vehicleBranches);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleBranches.get(vehicle.startRoad()).add(vehicle);
    }

    public StepResult move() {
        List<Vehicle> leftVehicles = new ArrayList<>();
        for (RoadDirection dir : RoadDirection.values()) {
            if (vehicleBranches.get(dir).canMove()) {
                Vehicle vehicleLeft = vehicleBranches.get(dir).pop();
                if (vehicleLeft != null) {
                    leftVehicles.add(vehicleLeft);
                }
            }
        }
        this.updateLights();
        return new StepResult(leftVehicles);
    }

    private void updateLights() {
        BranchInterface current = this.getCurrentGreen();
        if (current != null) {
            if (getCurrentGreen().getCountOnGreen() > MIN_GREEN || getCurrentGreen().size() == 0) {
                BranchInterface maxBranch = getBranchWithMaxScore();
                    current.nextLight();
                    maxBranch.nextLight();
            }
        } else {
            for (RoadDirection dir : RoadDirection.values()) {
                if (vehicleBranches.get(dir).getCurrentLight().equals(LightsState.YELLOW)) {
                    vehicleBranches.get(dir).nextLight();
                }
            }
            additionalLogic.performLightAdditionalLogic(this.vehicleBranches);
        }
    }

    private BranchInterface getCurrentGreen() {
        BranchInterface currentGreen = null;
        for (RoadDirection dir : RoadDirection.values()) {
            if (vehicleBranches.get(dir).canMove()) {
                currentGreen = vehicleBranches.get(dir);
            }
        }
        return currentGreen;
    }

    private BranchInterface getBranchWithMaxScore() {
        double max = 0;
        BranchInterface branchWithMax = null;
        for (RoadDirection dir : RoadDirection.values()) {
            if (vehicleBranches.get(dir).calcPriorityScore() >= max) {
                max = vehicleBranches.get(dir).calcPriorityScore();
                branchWithMax = vehicleBranches.get(dir);
            }

        }
        return branchWithMax;
    }
}
