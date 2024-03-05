package org.acme;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VoitureTest {

    @Test
    void testDeplacementEtConsommationCarburant() {
        Voiture voiture = new Voiture(0, 0, 'd', 60, "rouge");
        voiture.deplacer('d'); // Supposons que cela consomme du carburant.
        voiture.deplacer('d'); // Supposons que cela consomme du carburant.
        voiture.deplacer('d'); // Supposons que cela consomme du carburant.

        assertTrue(Voiture.getCarburant() < 60); // Le carburant devrait avoir diminué.

        voiture.setCarburant(0); // Simuler une voiture sans carburant.
        voiture.deplacer('d');
        assertEquals(0, Voiture.getCarburant()); // Aucun carburant, aucun déplacement ne devrait se produire.
    }

    @Test
    void testCollisionAvecObstacles() {
        Voiture.setPositionX(5); // Placer la voiture juste avant un obstacle.
        Voiture.setPositionY(4);
        Voiture.deplacer('h'); // Déplacement vers le haut où un obstacle est présent à (5, 5).
        assertEquals(4, Voiture.getPositionY()); // La position Y ne devrait pas changer en raison de la collision.
    }

    @Test
    void testMouvementDesBoules() throws InterruptedException {
        Voiture.setPositionX(10);
        Voiture.setPositionY(10);
        Position bouleInitiale = new Position(9, 10); // Position initiale de la boule.
        Voiture.boules.add(bouleInitiale);
        Thread.sleep(2000); // Attendre pour permettre au mouvement de s'exécuter.
        
        // Vérifier si la boule s'est déplacée plus près de la voiture.
        Position bouleApresDeplacement = Voiture.boules.get(Voiture.boules.indexOf(bouleInitiale));
        assertTrue(bouleApresDeplacement.getX() == 10 && bouleApresDeplacement.getY() == 10);
}


}
