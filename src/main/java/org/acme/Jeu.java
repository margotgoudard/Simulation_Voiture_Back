package org.acme;

public class Jeu {

    // Fonction Title pour afficher le titre du jeu
    public static String Title(String title, int p) {
        // Construction de la chaîne de préfixe avec des '#'
        String prefix = "";
        for (int i = 0; i < p; i++) {
            prefix += "#";
        }

        // Retourner le titre du jeu préfixé
        return prefix + title;
    }


    // Fonction pour animer le titre du jeu
    public static void animateTitle(String title) throws InterruptedException {
        final int maxWidth = 100; // Largeur maximale pour l'animation
        int length = title.length();

        // S'assurer que le titre ne dépasse pas la largeur maximale
        if (length > maxWidth) {
            title = title.substring(0, maxWidth);
            length = maxWidth;
        }

        // Boucle pour animer le titre du jeu
        for (int i = 0; i < maxWidth - length; i++) {
            String animatedTitle = Title(title, i);

            // Utiliser \r pour revenir au début de la ligne et effacer la ligne précédente
            System.out.print("\r" + animatedTitle);

            // Flush pour forcer l'affichage immédiat
            System.out.flush();

            // Pause pour visualiser l'animation
            Thread.sleep(100); // Délai en millisecondes
        }
    }

    public static void main(String[] args) {
        // Exemple d'utilisation de la fonction Title
        String gameTitle = "carmageddon";

        // Afficher le titre avec p = 5
        System.out.println(Title(gameTitle, 5));

        // Afficher le titre avec p = 2
        System.out.println(Title(gameTitle, 2));

        try {
            // Appel de la fonction d'animation avec le titre du jeu
            animateTitle(gameTitle);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
