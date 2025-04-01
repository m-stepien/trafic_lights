package org.home.exercise.traffic.lights.additional;

import org.home.exercise.traffic.lights.branch.ArrowBranchImpl;
import org.home.exercise.traffic.lights.branch.BranchInterface;
import org.home.exercise.traffic.lights.enums.LightsState;
import org.home.exercise.traffic.lights.enums.RoadDirection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrowBranchAdditionalLogic implements AdditionalLogic{

    Map<RoadDirection, List<RoadDirection>> allowedArrow;

    public ArrowBranchAdditionalLogic(){
        allowedArrow = new HashMap<>();
        allowedArrow.put(RoadDirection.NORTH, List.of(RoadDirection.EAST));
        allowedArrow.put(RoadDirection.EAST, List.of(RoadDirection.SOUTH));
        allowedArrow.put(RoadDirection.SOUTH, List.of(RoadDirection.WEST));
        allowedArrow.put(RoadDirection.WEST, List.of(RoadDirection.NORTH));
    }

    @Override
    public void performLightAdditionalLogic(Map<RoadDirection, BranchInterface> intersectionBranches) {
        RoadDirection greenRoad = null;
        for (Map.Entry<RoadDirection, BranchInterface> entry : intersectionBranches.entrySet()) {
            BranchInterface branch = entry.getValue();

            ArrowBranchImpl arrowBranch = (ArrowBranchImpl) branch;
            arrowBranch.arrowOff();
            if (arrowBranch.getCurrentLight().equals(LightsState.GREEN)) {
                greenRoad = entry.getKey();
            }
        }
        if(greenRoad!=null) {
            for (RoadDirection allowed : allowedArrow.get(greenRoad)) {
                ((ArrowBranchImpl) intersectionBranches.get(allowed)).arrowOn();
            }
        }
    }

}
