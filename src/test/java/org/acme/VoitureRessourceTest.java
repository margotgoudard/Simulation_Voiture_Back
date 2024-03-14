package org.acme;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.core.Response;

// Importez vos classes nécessaires ici

public class VoitureRessourceTest {

    private VoitureResource voitureResource;

    @BeforeEach
    public void setUp() {
        voitureResource = new VoitureResource();
        Voiture.reinitialiser(); // Assurez-vous que l'état de la voiture est réinitialisé avant chaque test
    }

    /*@Test
    public void testDeplacerVoiture() {
        // Test de déplacement vers le haut
        var response = voitureResource.deplacerVoiture("h");
        assertEquals(14, response.get("positionY")); // Supposant que la position initiale Y est 15
        assertEquals('h', response.get("direction"));
        assertTrue((Integer)response.get("carburant") < 60); // Le carburant doit être consommé

        // Répétez pour les autres directions
    }*/

    @Test
    public void testRechargerVoiture() {
        // Consommez d'abord un peu de carburant
        voitureResource.deplacerVoiture("h");
        voitureResource.deplacerVoiture("h");

        var response = voitureResource.rechargerVoiture();
        assertEquals(60, response.get("carburant")); // Carburant rechargé au maximum
    }

    @Test
    public void testGetBoules() {
        var boules = voitureResource.getBoules();
        assertNotNull(boules);
        assertFalse(boules.isEmpty()); // Assurez-vous que la liste n'est pas vide
        // Vérifiez plus de détails sur les boules si nécessaire
    }

    @Test
    public void testReinitialiserJeu() {
        // Changez l'état du jeu d'abord
        voitureResource.deplacerVoiture("h");
        voitureResource.deplacerVoiture("b");

        voitureResource.reinitialiserJeu();
        var response = voitureResource.rechargerVoiture(); // Utilisez cette méthode pour vérifier l'état après réinitialisation

        // Supposons que les positions initiales soient 10 et 15 respectivement
        assertEquals(10, response.get("positionX"));
        assertEquals(15, response.get("positionY"));
        assertEquals(60, response.get("carburant")); // Carburant rechargé au maximum
    }
}
