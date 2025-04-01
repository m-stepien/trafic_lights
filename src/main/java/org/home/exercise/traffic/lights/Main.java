package org.home.exercise.traffic.lights;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Required format java -jar simulator.jar <input.json> <output.json>");
            System.exit(1);
        }
        File input = new File(args[0]);
        File output = new File(args[1]);

        Simulation simulation = new Simulation();
        try {
            simulation.run(input, output);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Simulation completed. Results saved to " + args[1]);
    }
}
