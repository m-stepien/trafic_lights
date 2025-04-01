import org.home.exercise.traffic.lights.Lights;
import org.home.exercise.traffic.lights.Vehicle;
import org.home.exercise.traffic.lights.branch.ArrowBranchImpl;
import org.home.exercise.traffic.lights.enums.LightsState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrowBranchImplTest {
    @Test
    void testArrowOverridesRedLight() {
        Lights lights = new Lights(LightsState.RED, false);
        ArrowBranchImpl branch = new ArrowBranchImpl(lights);
        branch.add(new Vehicle("v1", null, null));

        assertFalse(branch.canMove());
        branch.arrowOn();
        assertTrue(branch.canMove());
    }

    @Test
    void testArrowOffDisablesMovementOnRed() {
        Lights lights = new Lights(LightsState.RED, false);
        ArrowBranchImpl branch = new ArrowBranchImpl(lights);
        branch.add(new Vehicle("v1", null, null));

        branch.arrowOn();
        assertTrue(branch.canMove());

        branch.arrowOff();
        assertFalse(branch.canMove());
    }
}
