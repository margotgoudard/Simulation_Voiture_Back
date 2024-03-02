package org.acme;

import jakarta.ws.rs.*;

import java.util.HashMap;
import java.util.Map;

import org.jboss.resteasy.annotations.SseElementType;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
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
}
