package org.home.exercise.traffic.lights.branch;

import org.home.exercise.traffic.lights.Lights;
import org.home.exercise.traffic.lights.additional.AdditionalLogic;
import org.home.exercise.traffic.lights.additional.ArrowBranchAdditionalLogic;
import org.home.exercise.traffic.lights.enums.LightsState;

public class ArrowBranchFactory implements BranchFactory{
    @Override
    public BranchInterface createBranch() {
        return new ArrowBranchImpl(new Lights(LightsState.RED, false));
    }

    @Override
    public AdditionalLogic getAdditionalLogic() {
        return new ArrowBranchAdditionalLogic();
    }
}
