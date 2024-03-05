# README - Backend Indépendant pour Jeu de Voiture avec SSE et Quarkus
Ce projet est un backend indépendant écrit en Java, utilisant Quarkus et Server-Sent Events (SSE) pour un jeu de voiture. Il gère la logique du jeu, y compris les déplacements de la voiture, la gestion du carburant, les collisions avec des obstacles, et le mouvement des boules qui tentent de frapper la voiture.

## Fonctionnalités
Déplacement de la Voiture: Répond aux commandes de déplacement envoyées par l'application front-end.
Gestion du Carburant: Réduit le carburant en fonction du déplacement et permet de le recharger.
Gestion des Obstacles et des Boules: Initialise et met à jour la position des obstacles et des boules, en vérifiant les collisions avec la voiture.
SSE (Server-Sent Events): Envoie les mises à jour en temps réel de la position de la voiture, du carburant, et des boules au client.

## Technologies Utilisées

Java: Langage de programmation utilisé pour le développement.
Quarkus: Supersonic Subatomic Java Framework utilisé pour faciliter le développement et l'amélioration des performances.
Gradle: Système de gestion et d'automatisation de builds.

## Lancement

./gradlew quarkusDev

