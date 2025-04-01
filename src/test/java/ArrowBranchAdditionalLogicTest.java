import org.home.exercise.traffic.lights.Lights;
import org.home.exercise.traffic.lights.additional.ArrowBranchAdditionalLogic;
import org.home.exercise.traffic.lights.branch.ArrowBranchImpl;
import org.home.exercise.traffic.lights.branch.BranchInterface;
import org.home.exercise.traffic.lights.enums.LightsState;
import org.home.exercise.traffic.lights.enums.RoadDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrowBranchAdditionalLogicTest {
    private ArrowBranchAdditionalLogic logic;
    private Map<RoadDirection, BranchInterface> intersectionBranches;

    @BeforeEach
    void setUp() {
        logic = new ArrowBranchAdditionalLogic();
        intersectionBranches = new EnumMap<>(RoadDirection.class);
        for (RoadDirection dir : RoadDirection.values()) {
            Lights lights = new Lights(LightsState.RED, false);
            ArrowBranchImpl branch = new ArrowBranchImpl(lights);
            intersectionBranches.put(dir, branch);
        }
    }

    @Test
    void testArrowOnOnlyForAllowedDirections() {
        ArrowBranchImpl northBranch = (ArrowBranchImpl) intersectionBranches.get(RoadDirection.NORTH);
        northBranch.nextLight();
        northBranch.nextLight();
        logic.performLightAdditionalLogic(intersectionBranches);
        assertTrue(intersectionBranches.get(RoadDirection.NORTH).canMove());
        assertTrue((intersectionBranches.get(RoadDirection.EAST)).canMove());
        assertFalse((intersectionBranches.get(RoadDirection.SOUTH)).canMove());
        assertFalse((intersectionBranches.get(RoadDirection.WEST)).canMove());
    }
}
