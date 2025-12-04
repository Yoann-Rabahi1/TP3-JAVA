/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        // 1. Créer une instance de la classe Etablissement
        Etablissement maLibrairie = new Etablissement("Librairie Campus");
        
        // 2. Appeler les méthodes de chargement des fichiers texte
        System.out.println("--- Démarrage du Programme ---");
        maLibrairie.depuisFichierArticles("articles.txt"); // Charge le stock
        maLibrairie.depuisFichierDepots("depots.txt");     // Charge les bons de dépôt

        // 3. Afficher le menu et gérer les fonctionnalités
        menuPrincipal(maLibrairie);

        // 4. Appeler les méthodes de sauvegarde avant de quitter
        System.out.println("\n--- Fermeture du Programme et Sauvegarde des Données ---");
        maLibrairie.versFichierArticles("articles.txt");
        maLibrairie.versFichierDepots("depots.txt");
        System.out.println("Sauvegarde terminée. Au revoir !");
    }

    /**
     * Gère le menu principal et la boucle du programme.
     */
    private static void menuPrincipal(Etablissement etab) {
        Scanner sc = new Scanner(System.in);
        int choix;
        boolean quitter = false;
        
        do {
            afficherMenu();
            if (sc.hasNextInt()) {
                choix = sc.nextInt();
                sc.nextLine(); // Consommer le retour à la ligne
                
                switch (choix) {
                    case 1: gererStock(etab, sc); break;
                    case 2: gererDepot(etab, sc); break;
                    case 3: gererAffichage(etab, sc); break;
                    case 4: 
                        quitter = true; 
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                sc.nextLine(); // Consommer l'entrée invalide
                choix = 0;
            }
        } while (!quitter);
        sc.close();
    }
    
    // --- Méthode pour afficher le menu ---
    private static void afficherMenu() {
        System.out.println("\n========================================");
        System.out.println(" Menu de Gestion de la Librairie ");
        System.out.println("========================================");
        System.out.println("1. Gérer le Stock d'Articles (Ajouter/Retirer)");
        System.out.println("2. Gérer les Bons de Dépôt");
        System.out.println("3. Lister et Afficher les Données");
        System.out.println("4. Quitter et Sauvegarder");
        System.out.print("Votre choix : ");
    }
    
    // --- (Les méthodes gererStock, gererDepot, et gererAffichage doivent être implémentées ci-dessous) ---
    
    // EXEMPLE SIMPLIFIÉ D'UNE FONCTIONNALITÉ (pour les tests)
    private static void gererStock(Etablissement etab, Scanner sc) {
        System.out.println("\n--- GESTION DU STOCK ---");
        System.out.println("1. Ajouter un article (pour le test, on va le coder directement)");
        System.out.println("2. Retirer une quantité (simuler une vente)");
        System.out.print("Choix : ");
        int choixStock = sc.nextInt();
        sc.nextLine();

        if (choixStock == 2) {
            System.out.print("Numéro ISBN/ISSN de l'article à retirer : ");
            String numero = sc.nextLine();
            System.out.print("Quantité à retirer (vendue) : ");
            int quantite = sc.nextInt();
            sc.nextLine();
            etab.retirer(numero, quantite);
        }
    }
    
    private static void gererDepot(Etablissement etab, Scanner sc) {
        System.out.println("\n--- GESTION DES DÉPÔTS ---");
        System.out.println("1. Enregistrer un nouveau bon de dépôt (avec date et numéro automatiques)");
        System.out.println("2. Ajouter une ligne de dépôt à un bon (non implémenté pour l'exemple)");
        System.out.print("Choix : ");
        int choixDepot = sc.nextInt();
        sc.nextLine();

        if (choixDepot == 1) {
            System.out.print("Numéro de téléphone du client : ");
            String tel = sc.nextLine();
            etab.ajouter(tel); // Appel de la méthode ajouter(String numeroTel)
        }
    }
    
    private static void gererAffichage(Etablissement etab, Scanner sc) {
        System.out.println("\n--- AFFICHAGE ET LISTAGE ---");
        System.out.println("1. Lister tous les articles par stock (avec prix du jour)");
        System.out.println("2. Lister les bons de dépôt par client (téléphone)");
        System.out.println("3. Lister les bons de dépôt par article et période");
        System.out.print("Choix : ");
        int choixAffichage = sc.nextInt();
        sc.nextLine();
        
        switch(choixAffichage) {
            case 1:
                etab.lister(); // Appel de lister() sans paramètre
                break;
            case 2:
                System.out.print("Entrez le numéro de téléphone : ");
                String tel = sc.nextLine();
                etab.lister(tel); // Appel de lister(String numeroTel)
                break;
            case 3:
                System.out.print("Entrez le numéro ISBN/ISSN : ");
                String numero = sc.nextLine();
                // Simulation d'une période pour le test
                LocalDate debut = LocalDate.of(2025, 1, 1);
                LocalDate fin = LocalDate.now(); 
                System.out.println("Recherche entre le " + debut + " et le " + fin + "...");
                etab.lister(numero, debut, fin); // Appel de lister(String, LocalDate, LocalDate)
                break;
        }
    }
}
