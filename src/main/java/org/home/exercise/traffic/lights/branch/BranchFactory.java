package org.home.exercise.traffic.lights.branch;

import org.home.exercise.traffic.lights.additional.AdditionalLogic;

public interface BranchFactory {
    public BranchInterface createBranch();
    public AdditionalLogic getAdditionalLogic();
}
