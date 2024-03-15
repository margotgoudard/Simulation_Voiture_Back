package org.acme;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Map;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;
import jakarta.ws.rs.sse.OutboundSseEvent;

public class VoitureRessourceTest {

    private VoitureResource voitureResource;

    @BeforeEach
    public void setUp() {
        voitureResource = new VoitureResource();
        Voiture.reinitialiser(); // Assurez-vous que l'état de la voiture est réinitialisé avant chaque test
    }

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

    

    /*@Test
    public void testPositionStream() throws Exception {
        // Préparer les objets mock nécessaires
        SseEventSink mockEventSink = mock(SseEventSink.class);
        Sse mockSse = mock(Sse.class);
        VoitureResource resource = new VoitureResource();
    
        // Créer un objet OutboundSseEvent simulé
        OutboundSseEvent mockEvent = mock(OutboundSseEvent.class);
    
        // Simuler un événement avec des données JSON
        String eventData = "{\"positionX\": 10, \"positionY\": 20, \"direction\": \"N\", \"carburant\": 50}";
        Mockito.when(mockEvent.getData()).thenReturn(eventData);
    
        // Simuler le comportement de Sse pour créer un événement
        Mockito.when(mockSse.newEvent(eventData)).thenReturn(mockEvent);
    
        // Appel de la méthode positionStream avec l'événement simulé
        resource.positionStream(mockEventSink, mockSse);
    
        // Simuler un délai pour permettre l'envoi d'événements
        Thread.sleep(2000); // Juste pour l'exemple, ajustez selon le besoin
    
        // Vérifier si l'événement a été envoyé avec les bonnes données
        Mockito.verify(mockEventSink, Mockito.atLeastOnce()).send(mockEvent);
    }
    */
    
}
