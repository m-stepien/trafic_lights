package org.home.exercise.traffic.lights.branch;

import org.home.exercise.traffic.lights.config.Config;

public class BranchFactoryProvider {
    public static BranchFactory getFactory() {
        String type = Config.get("branch.type");

        return switch (type.toLowerCase()) {
            case "simple" -> new SimpleBranchFactory();
            case "arrow" -> new ArrowBranchFactory();
            default -> throw new IllegalArgumentException("Unsupported branch type: " + type);
        };
    }
}

