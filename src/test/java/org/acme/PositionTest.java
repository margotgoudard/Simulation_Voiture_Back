package org.acme;
import static org.junit.Assert.*;
import org.junit.Test;

public class PositionTest {
    @Test
    public void testEqualityForIdenticalCoordinates() {
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(1, 2);
        assertEquals(pos1, pos2); // Asserting direct equality
        assertEquals(pos1.hashCode(), pos2.hashCode());
    }

    @Test
    public void testInequalityForDifferentCoordinates() {
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(2, 3);
        assertNotEquals(pos1, pos2); // Asserting direct inequality
        // No need to assert hashCode difference as it's not strictly required
    }

    @Test
    public void testComparisonWithNull() {
        Position pos1 = new Position(1, 2);
        assertNotEquals(pos1, null); // Direct inequality check with null
    }

    @Test
    public void testComparisonWithDifferentClassObject() {
        Position pos1 = new Position(1, 2);
        Object obj = new Object();
        assertNotEquals(pos1, obj); // Direct inequality check with an object of a different class
    }

    @Test
    public void testHashCodeConsistency() {
        Position pos1 = new Position(1, 2);
        int initialHashCode = pos1.hashCode();
        assertEquals(initialHashCode, pos1.hashCode()); // Consistency check of hashCode
    }

    @Test
    public void testEqualityAfterModification() {
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(3, 4);
        assertNotEquals(pos1, pos2); // Initial inequality check
        pos2.setX(1);
        pos2.setY(2);
        assertEquals(pos1, pos2); // Checking equality after modification
        assertEquals(pos1.hashCode(), pos2.hashCode()); // hashCode equality check
    }
}
