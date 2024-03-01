package org.acme;

import java.awt.*;
import java.util.Set;

public class Voiture {
    private int positionX;
    private int positionY;
    private int direction; // 0: avant, 1: droite, 2: arrière, 3: gauche
    private int carburant = 60; // Niveau de carburant initial

    public Voiture() {
        this.positionX = 0;
        this.positionY = 0;
        this.direction = 0; // Commence en se dirigeant vers l'avant
    }

    public void deplacer(int n) {
        if (carburant <= 0) {
            System.out.println("La voiture est à court de carburant!");
            return;
        }

        // Consomme du carburant
        for (int i = 0; i < n; i++) {
            if (i % 3 == 0) carburant -= 1;
            if (carburant <= 0) {
                System.out.println("La voiture est à court de carburant en cours de route!");
                break;
            }

            switch (direction) {
                case 0: positionY += 1; break; // Avance
                case 1: positionX += 1; break; // Droite
                case 2: positionY -= 1; break; // Arrière
                case 3: positionX -= 1; break; // Gauche
            }
        }
    }

    public void tournerDroite() {
        direction = (direction + 1) % 4;
    }

    public void tournerGauche() {
        direction = (direction + 3) % 4;
    }

    public void rechargerCarburant() {
        carburant = 60;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getDirection() {
        return direction;
    }

    public int getCarburant() {
        return carburant;
    }

    public void verifierEtRechargerSiStation(Set<Point> stations) {
        Point positionActuelle = new Point(positionX, positionY);
        if (stations.contains(positionActuelle)) {
            System.out.println("Voiture rechargée à la station-service!");
            rechargerCarburant();
        }
    }

}