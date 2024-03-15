package org.acme;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Voiture {
    private static int positionX;
    private static int positionY;
    private static char direction = 'd'; // Initial direction to the right ('d')
    private static int carburant = 60; // Max carburant
    private static int compteurDeplacements = 0;
    private String couleur; // Nouvel attribut pour la couleur
    private boolean estDetruite = false; // Pour gérer l'état de la voiture
    private static int speed = 0; // Speed management
    private static List<Position> obstacles = new ArrayList<>();
    public static List<Position> boules = new ArrayList<>();

    private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();



    // Assuming you have a Position class defined somewhere
    static {
        // Example obstacles initialization
        obstacles.add(new Position(5, 5));
        obstacles.add(new Position(15, 15));
        boules.add(new Position(15, 15));
        boules.add(new Position(0, 5));
        initBoulesMovement();
    } 

    private static void initBoulesMovement() {
        executorService.scheduleAtFixedRate(() -> {
            deplacerBoulesVersVoiture();
        }, 0, 1, TimeUnit.SECONDS);
    }

    private static void deplacerBoulesVersVoiture() {
        // Vérifier si chaque boule n'est pas déjà à la position de la voiture
        for (Position boule : boules) {
            if (boule.getX() != positionX || boule.getY() != positionY) {
                // Logique pour déplacer la boule en direction de la voiture
                if (boule.getX() < positionX) boule.setX(boule.getX() + 1);
                if (boule.getX() > positionX) boule.setX(boule.getX() - 1);
                if (boule.getY() < positionY) boule.setY(boule.getY() + 1);
                if (boule.getY() > positionY) boule.setY(boule.getY() - 1);
            }
        }
    }


    // Constructeur avec paramètres
    public Voiture(int positionX, int positionY, char direction, int carburant, String couleur) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
        this.carburant = carburant;
        this.couleur = couleur;
    }

    // Getters
    public static int getPositionX() {
        return positionX;
    }

    public static int getPositionY() {
        return positionY;
    }

    public static char getDirection() {
        return direction;
    }

    public static int getCarburant() {
        return carburant;
    }

    // Setters
    public static void setPositionX(int positionX) {
        Voiture.positionX = positionX;
    }

    public static void setPositionY(int positionY) {
        Voiture.positionY = positionY;
    }

    public static void setDirection(char direction) {
        Voiture.direction = direction;
    }

    public static void setCarburant(int carburant) {
        Voiture.carburant = carburant;
    }
    public static void deplacer(char touche) {
        if (carburant <= 0) {
            System.out.println("La voiture est à court de carburant !");
            return;
        }

        switch(touche) {
            case 'h': // haut
                if (direction != 'h') {
                    direction = 'h';
                } else {
                    positionY -= 1;
                    consommerCarburant();
                }
                break;
            case 'b': // bas
                if (direction != 'b') {
                    direction = 'b';
                } else {
                    positionY += 1;
                    consommerCarburant();
                }
                break;
            case 'g': // gauche
                if (direction != 'g') {
                    direction = 'g';
                } else {
                    positionX -= 1;
                    consommerCarburant();
                }
                break;
            case 'd': // droite
                if (direction != 'd') {
                    direction = 'd';
                } else {
                    positionX += 1;
                    consommerCarburant();
                }
                break;
            default:
                System.out.println("Touche non reconnue");
                break;
        }

        boolean hitsObstacle = obstacles.stream().anyMatch(obstacle ->
            obstacle.getX() == positionX && obstacle.getY() == positionY);

        if (hitsObstacle) {
            if (speed > 2) {
                System.out.println("Le véhicule est détruit à cause d'une collision à haute vitesse !");
                // Reset vehicle state or handle destruction
            } else {
                System.out.println("Collision avec un obstacle !");
                // Optionally, reduce speed or prevent movement
            }
            return; // Stop movement to simulate collision
        }
    }

    private static void consommerCarburant() {
        compteurDeplacements++;
        if (compteurDeplacements >= 3) {
            carburant -= 1; // Consomme 1 litre de carburant
            compteurDeplacements = 0; // Réinitialise le compteur après la consommation
        }
    }
    

    public static void rechargerCarburant() {
        carburant = 60; // Recharge le carburant au maximum
    }

    public static void reinitialiser() {
        setPositionX(10); 
        setPositionY(15);
        setCarburant(60);
    }

   
}
