package org.home.exercise.traffic.lights;

import org.home.exercise.traffic.lights.enums.LightsState;

import java.util.List;

public class Lights {
    private static List<LightsState> stateList = List.of(LightsState.GREEN, LightsState.YELLOW, LightsState.RED);
    private boolean rightDirection;
    private int currentStateIdx;

    public Lights(LightsState initialState, boolean direction) {
        this.currentStateIdx = stateList.indexOf(initialState);
        this.rightDirection = direction;
    }

    public void change() {
        if (rightDirection) {
            currentStateIdx++;
        } else {
            currentStateIdx--;
        }
        if (currentStateIdx == 0) {
            rightDirection = true;
        } else if (currentStateIdx == 2) {
            rightDirection = false;
        }
    }

    public LightsState getCurrent(){
        return stateList.get(currentStateIdx);
    }
}
