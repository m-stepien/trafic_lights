import org.home.exercise.traffic.lights.Lights;
import org.home.exercise.traffic.lights.enums.LightsState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LightsTest {
    @Test
    void testLightsCycleForwardAndBackward() {
        Lights lights = new Lights(LightsState.RED, false);
        assertEquals(LightsState.RED, lights.getCurrent());
        lights.change();
        assertEquals(LightsState.YELLOW, lights.getCurrent());
        lights.change();
        assertEquals(LightsState.GREEN, lights.getCurrent());
        lights.change();
        assertEquals(LightsState.YELLOW, lights.getCurrent());
        lights.change();
        assertEquals(LightsState.RED, lights.getCurrent());
    }

    @Test
    void testLightsInitialStateAndDirectionSwitch() {
        Lights lights = new Lights(LightsState.GREEN, true);
        assertEquals(LightsState.GREEN, lights.getCurrent());
        lights.change();
        assertEquals(LightsState.YELLOW, lights.getCurrent());
        lights.change();
        assertEquals(LightsState.RED, lights.getCurrent());
        lights.change();
        assertEquals(LightsState.YELLOW, lights.getCurrent());
        lights.change();
        assertEquals(LightsState.GREEN, lights.getCurrent());
    }
}
