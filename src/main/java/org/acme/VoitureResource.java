package org.acme;

import jakarta.ws.rs.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jboss.resteasy.annotations.SseElementType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;

@Path("/voiture")
public class VoitureResource {

@GET
@Produces(MediaType.SERVER_SENT_EVENTS)
@SseElementType("application/json")
public void positionStream(@Context SseEventSink eventSink, @Context Sse sse) {
    final ObjectMapper mapper = new ObjectMapper();
    final Runnable sendData = () -> {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Map<String, Object> data = new HashMap<>();
                data.put("positionX", Voiture.getPositionX());
                data.put("positionY", Voiture.getPositionY());
                data.put("direction", Voiture.getDirection());
                data.put("carburant", Voiture.getCarburant());

                // Ajouter les positions des boules
                List<Map<String, Integer>> boulesPositions = Voiture.boules.stream()
                        .map(boule -> Map.of("x", boule.getX(), "y", boule.getY()))
                        .collect(Collectors.toList());
                data.put("boules", boulesPositions);

                String json = mapper.writeValueAsString(data);

                eventSink.send(sse.newEvent(json));
                Thread.sleep(1000); // Mise à jour chaque seconde
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    };

    new Thread(sendData).start();
}

@POST
    @Path("/deplacer/{direction}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> deplacerVoiture(@PathParam("direction") String direction) {
        Voiture.deplacer(direction.charAt(0));
        Map<String, Object> response = new HashMap<>();
        response.put("positionX", Voiture.getPositionX());
        response.put("positionY", Voiture.getPositionY());
        response.put("direction", Voiture.getDirection());
        response.put("carburant", Voiture.getCarburant());
        System.out.println("Réponse envoyée: " + response);
        return response;
    }

    @POST
    @Path("/recharger")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> rechargerVoiture() {
        Voiture.rechargerCarburant(); // Appelle la méthode pour recharger le carburant
        Map<String, Object> response = new HashMap<>();
        response.put("positionX", Voiture.getPositionX());
        response.put("positionY", Voiture.getPositionY());
        response.put("direction", Voiture.getDirection());
        response.put("carburant", Voiture.getCarburant());
        System.out.println("Carburant rechargé et réponse envoyée: " + response);
        return response; // Retourne l'état actuel de la voiture après rechargement
    }

    @GET
    @Path("/boules")
    @Produces(MediaType.APPLICATION_JSON)
    
    public List<Map<String, Object>> getBoules() {
        List<Map<String, Object>> boulesList = new ArrayList<>();
    
        // Assuming you have a list or collection of Boule instances
        for (Position boule : Voiture.boules) { 
            System.out.println("boules" + Voiture.boules);
            Map<String, Object> bouleData = new HashMap<>();
            bouleData.put("x", boule.getX());
            bouleData.put("y", boule.getY());
            boulesList.add(bouleData);
        }
    
        return boulesList;
    }

    @POST
    @Path("/reinitialiser")
    public Response reinitialiserJeu() {
        // Réinitialiser la position de la voiture, le carburant, etc.
        Voiture.reinitialiser();


        return Response.ok().build(); // Répondre avec un statut OK après la réinitialisation
}
}
