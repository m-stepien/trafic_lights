import org.home.exercise.traffic.lights.Intersection;
import org.home.exercise.traffic.lights.Vehicle;
import org.home.exercise.traffic.lights.enums.RoadDirection;
import org.home.exercise.traffic.lights.response.StepResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntersectionTest {
    @Test
    void testVehiclePassesIntersectionInStep() {
        System.setProperty("branch.type", "simple");
        Intersection intersection = new Intersection();
        Vehicle v = new Vehicle("v1", RoadDirection.SOUTH, RoadDirection.NORTH);
        intersection.addVehicle(v);

        StepResult result1 = intersection.move();
        StepResult result2 = intersection.move();

        assertTrue(result1.leftVehicles().contains(v));
        assertTrue(result2.leftVehicles().isEmpty());
    }

    @Test
    void testVehiclePassesIntersectionOnlyOneMove() {
        System.setProperty("branch.type", "simple");
        Intersection intersection = new Intersection();
        Vehicle v = new Vehicle("v1", RoadDirection.SOUTH, RoadDirection.NORTH);
        Vehicle v2 = new Vehicle("v2", RoadDirection.NORTH, RoadDirection.SOUTH);
        Vehicle v3 = new Vehicle("v3", RoadDirection.EAST, RoadDirection.SOUTH);


        intersection.addVehicle(v);
        intersection.addVehicle(v2);
        intersection.addVehicle(v3);

        StepResult result1 = intersection.move();
        StepResult result2 = intersection.move();
        StepResult result3 = intersection.move();
        intersection.move();
        StepResult result4 = intersection.move();
        StepResult result5 = intersection.move();
        assertTrue(result1.leftVehicles().contains(v));
        assertEquals(1, result1.leftVehicles().size());
        assertTrue(result2.leftVehicles().isEmpty());
        assertTrue(result3.leftVehicles().contains(v3));
        assertEquals(1, result3.leftVehicles().size());
        assertTrue(result4.leftVehicles().contains(v2));
        assertEquals(1, result4.leftVehicles().size());
        assertTrue(result5.leftVehicles().isEmpty());
    }

    @Test
    void testVehiclePassesIntersectionNotGreenStartedManyVehiclesInOne() {
        System.setProperty("branch.type", "simple");
        Intersection intersection = new Intersection();
        Vehicle v = new Vehicle("v1", RoadDirection.WEST, RoadDirection.NORTH);
        Vehicle v2 = new Vehicle("v2", RoadDirection.WEST, RoadDirection.EAST);
        Vehicle v3 = new Vehicle("v3", RoadDirection.WEST, RoadDirection.EAST);
        Vehicle v4 = new Vehicle("v4", RoadDirection.EAST, RoadDirection.WEST);

        intersection.addVehicle(v);
        intersection.addVehicle(v2);
        intersection.addVehicle(v3);
        intersection.addVehicle(v4);
        StepResult result1 = intersection.move();
        StepResult result2 = intersection.move();
        StepResult result3 = intersection.move();
        StepResult result4 = intersection.move();
        StepResult result5 = intersection.move();
        StepResult result6 = intersection.move();
        StepResult result7 = intersection.move();
        StepResult result8 = intersection.move();
        assertTrue(result1.leftVehicles().isEmpty());
        assertTrue(result2.leftVehicles().isEmpty());
        assertTrue(result3.leftVehicles().contains(v));
        assertTrue(result4.leftVehicles().contains(v2));
        assertTrue(result5.leftVehicles().contains(v3));
        assertTrue(result6.leftVehicles().isEmpty());
        assertTrue(result7.leftVehicles().contains(v4));
        assertTrue(result8.leftVehicles().isEmpty());
    }

    @Test
    void testVehiclePassesIntersectionWithArrow() {
        System.setProperty("branch.type", "arrow");
        Intersection intersection = new Intersection();
        Vehicle v = new Vehicle("v1", RoadDirection.SOUTH, RoadDirection.WEST);
        Vehicle v2 = new Vehicle("v2", RoadDirection.WEST, RoadDirection.SOUTH);
        Vehicle v3 = new Vehicle("v3", RoadDirection.SOUTH, RoadDirection.WEST);
        Vehicle v4 = new Vehicle("v4", RoadDirection.WEST, RoadDirection.SOUTH);

        intersection.addVehicle(v);
        intersection.addVehicle(v2);
        intersection.addVehicle(v3);
        intersection.addVehicle(v4);
        StepResult result1 = intersection.move();
        StepResult result2 = intersection.move();
        StepResult result3 = intersection.move();
        assertTrue(result1.leftVehicles().contains(v));
        assertTrue(result1.leftVehicles().contains(v2));
        assertEquals(2, result1.leftVehicles().size());
        assertTrue(result2.leftVehicles().contains(v3));
        assertTrue(result2.leftVehicles().contains(v4));
        assertEquals(2, result2.leftVehicles().size());
        assertTrue(result3.leftVehicles().isEmpty());
    }

}