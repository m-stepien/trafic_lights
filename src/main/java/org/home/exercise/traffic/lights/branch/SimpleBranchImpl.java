package org.home.exercise.traffic.lights.branch;

import org.home.exercise.traffic.lights.Lights;
import org.home.exercise.traffic.lights.enums.LightsState;
import org.home.exercise.traffic.lights.Vehicle;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBranchImpl implements BranchInterface {
    private Queue<Vehicle> vehicleQueue = new LinkedList<>();
    private Lights lights;
    private int waitCount;
    private int countOnGreen;

    public SimpleBranchImpl(Lights lights){
        this.lights = lights;
        this.waitCount = 0;
        this.countOnGreen = 0;
    }

    @Override
    public void add(Vehicle vehicle) {
        vehicleQueue.add(vehicle);
    }

    @Override
    public Vehicle pop() {
        this.countOnGreen++;
        return vehicleQueue.poll();
    }

    @Override
    public Vehicle getFirst() {
        return vehicleQueue.peek();
    }

    @Override
    public int size() {
        return vehicleQueue.size();
    }

    @Override
    public boolean canMove(){
        boolean can = this.lights.getCurrent().equals(LightsState.GREEN);
        if(can){
            this.waitCount = 0;
        }
        else{
            if(this.size()>0){
                this.waitCount+=1;
            }
        }
        return can;
    }

    @Override
    public void nextLight() {
        if(this.lights.getCurrent().equals(LightsState.GREEN)){
            this.countOnGreen = 0;
        }
        this.lights.change();
    }

    @Override
    public int getCountOnGreen(){
        return this.countOnGreen;
    }

    @Override
    public double calcPriorityScore(){
        return this.size()*1.5 + this.waitCount;
    }

    @Override
    public LightsState getCurrentLight(){
        return this.lights.getCurrent();
    }
}
