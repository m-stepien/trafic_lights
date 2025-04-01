import org.home.exercise.traffic.lights.Lights;
import org.home.exercise.traffic.lights.Vehicle;
import org.home.exercise.traffic.lights.branch.SimpleBranchImpl;
import org.home.exercise.traffic.lights.enums.LightsState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleBranchImplTest {
 private SimpleBranchImpl branch;
private Lights lights;

@BeforeEach
void setUp() {
    lights = new Lights(LightsState.RED, false);
    branch = new SimpleBranchImpl(lights);
}

@Test
void testAddAndSize() {
    branch.add(new Vehicle("v1", null, null));
    branch.add(new Vehicle("v2", null, null));
    assertEquals(2, branch.size());
}

@Test
void testPopAndGetFirst() {
    Vehicle v1 = new Vehicle("v1", null, null);
    Vehicle v2 = new Vehicle("v2", null, null);
    branch.add(v1);
    branch.add(v2);
    assertEquals(v1, branch.getFirst());
    assertEquals(v1, branch.pop());
    assertEquals(v2, branch.getFirst());
}

@Test
void testCanMoveAndWaitCount() {
    branch.add(new Vehicle("v1", null, null));
    branch.canMove();
    branch.canMove();
    assertEquals(3.5, branch.calcPriorityScore());
}

@Test
void testNextLightChangesState() {
    lights = new Lights(LightsState.GREEN, true);
    branch = new SimpleBranchImpl(lights);
    branch.add(new Vehicle("v1", null, null));
    branch.canMove();
    branch.nextLight();
    assertEquals(LightsState.YELLOW, branch.getCurrentLight());
    branch.nextLight();
    assertEquals(LightsState.RED, branch.getCurrentLight());
    branch.nextLight();
    assertEquals(LightsState.YELLOW, branch.getCurrentLight());
}
}
