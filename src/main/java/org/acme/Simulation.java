package org.acme;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import java.awt.*;
import java.util.Set;

public class Simulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Voiture voiture = new Voiture(); // Utilise la classe Voiture que vous avez fournie
        Set<Point> stations = genererStations();


        while (true) {
            System.out.println("Commandes: avancer (a) [nombre de pas], tourner à droite (d), tourner à gauche (g), recharger (r), quitter (q)");
            System.out.println("Entrez votre commande:");
            String input = scanner.nextLine();
            char action = input.length() > 0 ? input.charAt(0) : ' ';
            int pas = 0;

            if (action == 'a' && input.length() > 1) {
                try {
                    pas = Integer.parseInt(input.substring(1).trim());
                } catch (NumberFormatException e) {
                    System.out.println("Nombre de pas invalide.");
                    continue;
                }
            }

            switch (action) {
                case 'a':
                    voiture.deplacer(pas);
                    voiture.verifierEtRechargerSiStation(stations); 
                    break;
                case 'd':
                    voiture.tournerDroite();
                    break;
                case 'g':
                    voiture.tournerGauche();
                    break;
                case 'r':
                    voiture.rechargerCarburant();
                    break;
                case 'q':
                    System.out.println("Fin de la simulation.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Commande invalide.");
                    break;
            }

            // Affiche l'état actuel de la voiture
            System.out.println("Position: (" + voiture.getPositionX() + ", " + voiture.getPositionY() + ")");
            System.out.println("Direction: " + voiture.getDirection());
            System.out.println("Carburant: " + voiture.getCarburant());


        }
    }

    private static Set<Point> genererStations() {
        Set<Point> stations = new HashSet<>();
        Random random = new Random();
        int nombreStations = 5; // Par exemple, générer 5 stations-service

        for (int i = 0; i < nombreStations; i++) {
            int x = random.nextInt(20); // Supposons un espace de simulation de 20x20
            int y = random.nextInt(20);
            stations.add(new Point(x, y));
        }

        return stations;
    }
}
