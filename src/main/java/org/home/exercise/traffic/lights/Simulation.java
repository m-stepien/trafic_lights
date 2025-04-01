package org.home.exercise.traffic.lights;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.home.exercise.traffic.lights.response.StepResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final Intersection intersection = new Intersection();
    private final List<StepResult> results = new ArrayList<>();


    public void run(File inputFile, File outputFile) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ObjectNode rootNode = (ObjectNode) mapper.readTree(inputFile);
        Command[] commands = mapper.treeToValue(rootNode.get("commands"), Command[].class);
        for (Command command : commands) {
            if (command.isAddVehicle()) {
                intersection.addVehicle(command.toVehicle());
            } else if (command.isStep()) {
                StepResult result = intersection.move();
                if (result != null) {
                    results.add(result);
                }
            }
        }
        ObjectNode output = mapper.createObjectNode();
        output.putPOJO("stepStatuses", results);
        mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, output);
    }
}
