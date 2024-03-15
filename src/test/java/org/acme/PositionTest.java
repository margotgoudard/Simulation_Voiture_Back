package org.acme;
import static org.junit.Assert.*;
import org.junit.Test;

public class PositionTest {
    @Test
    public void testEqualityForIdenticalCoordinates() {
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(1, 2);
        assertTrue(pos1.equals(pos2) && pos2.equals(pos1));
        assertEquals(pos1.hashCode(), pos2.hashCode());
    }

    @Test
    public void testInequalityForDifferentCoordinates() {
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(2, 3);
        assertFalse(pos1.equals(pos2));
        // No need to assert hashCode difference as it's not strictly required
    }

    @Test
    public void testComparisonWithNull() {
        Position pos1 = new Position(1, 2);
        assertFalse(pos1.equals(null));
    }

    @Test
    public void testComparisonWithDifferentClassObject() {
        Position pos1 = new Position(1, 2);
        Object obj = new Object();
        assertFalse(pos1.equals(obj));
    }

    @Test
    public void testHashCodeConsistency() {
        Position pos1 = new Position(1, 2);
        int initialHashCode = pos1.hashCode();
        assertEquals(initialHashCode, pos1.hashCode());
    }

    @Test
    public void testEqualityAfterModification() {
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(3, 4);
        assertFalse(pos1.equals(pos2));
        pos2.setX(1);
        pos2.setY(2);
        assertTrue(pos1.equals(pos2));
        assertEquals(pos1.hashCode(), pos2.hashCode());
    }
}