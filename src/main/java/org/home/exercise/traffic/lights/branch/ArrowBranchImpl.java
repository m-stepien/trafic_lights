package org.home.exercise.traffic.lights.branch;

import org.home.exercise.traffic.lights.Lights;

public class ArrowBranchImpl extends SimpleBranchImpl{
    private boolean arrowOn;
    public ArrowBranchImpl(Lights lights) {
        super(lights);
        arrowOn = false;
    }

    @Override
    public boolean canMove() {
        boolean isGreen = super.canMove();
        return isGreen || this.arrowOn;
    }

    public void arrowOn(){
        this.arrowOn=true;
    }

    public void arrowOff(){
        this.arrowOn=false;
    }
}
