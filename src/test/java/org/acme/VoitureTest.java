package org.acme;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VoitureTest {
    private Voiture voiture;

    @Before
    public void setUp() {
        voiture = new Voiture();
    }

    @Test
    public void testDeplacerAvecCarburant() {
        int initialX = voiture.getPositionX();
        int initialY = voiture.getPositionY();
        voiture.deplacer(3); // Assuming the car starts facing forward
        assertEquals("Car should move 3 units forward", initialY + 3, voiture.getPositionY());
        assertEquals("X position should not change", initialX, voiture.getPositionX());
    }

    @Test
    public void testDeplacerSansCarburant() {
        voiture.rechargerCarburant(); // Ensure the car has fuel
        for (int i = 0; i < 200; i++) voiture.deplacer(1); // Deplete fuel
        int posXBefore = voiture.getPositionX();
        int posYBefore = voiture.getPositionY();
        voiture.deplacer(1);
        assertEquals("X position should not change without fuel", posXBefore, voiture.getPositionX());
        assertEquals("Y position should not change without fuel", posYBefore, voiture.getPositionY());
    }
}
