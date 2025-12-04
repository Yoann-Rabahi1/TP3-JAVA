/*
 * TP:     3
 * Class : Etablissement | Gestion des stocks
 * Author: Stéphane SINGERY
 * Group:  INGE-1-APP-BDML2
 * Date:   2025-11-06
 */

// Import packages
package com.mycompany.gestiondesstocks;
import  java.time.LocalDate;

// Declare class
public class Etablissement {

    // ----------------------------- ATTRIBUTE

    protected String     nomBoutique;
    protected Article[]  tabArticles;
    protected BonDepot[] tabBonsDepot;
    protected int        nbArticles;
    protected int        nbBonsDepot;
    
    // ----------------------------- CONSTRUCTOR
    
    public Etablissement(
        String nomBoutique,
        int    nbArticlesAutorisees,
        int    NbBonsDepotAutorises
    ) {
        this.nomBoutique  = nomBoutique;
        this.tabArticles  = new Article[nbArticlesAutorisees];
        this.tabBonsDepot = new BonDepot[NbBonsDepotAutorises];
        this.nbArticles   = 0;
        this.nbBonsDepot  = 0;
    }

    // ----------------------------- GETTER
    
    public String     getNomBoutique() {
        return this.nomBoutique;
    }

    public Article[]  getTabArticles() {
        return this.tabArticles;
    }

    public BonDepot[] getTabBonsDepot() {
        return this.tabBonsDepot;
    }

    public int        getNbArticles() {
        return this.nbArticles;
    }

    public int        getNbBonsDepot() {
        return        this.nbBonsDepot;
    }

    // ----------------------------- SETTER
    
    public void setNomBoutique(String nomBoutique) {
        this.nomBoutique = nomBoutique;
    }
    
    // ----------------------------- METHOD
    
    // --------------- ARTICLE
    
    /**
     * Ajoute un article en respectant le tri lexicographique.
     */
    public void ajouterArticleTrie(Article a) {
        
        // Vérifie si le nombre d'article autorisés dans la boutique n'est
        // pas atteint.
        if (nbArticles >= tabArticles.length) {
            System.out.println("Le nombre d'articles autorisé a été atteint.");
            return;
        }

        // Trouver la position où insérer l'article dans le tableau,
        // via la function placerApres().
        int pos = 0;
        while (pos < nbArticles && tabArticles[pos].placerApres(a)) {
            pos++;
        }

        // Décale les éléments pour libérer une place.
        for (int i = nbArticles; i > pos; i--) {
            tabArticles[i] = tabArticles[i - 1];
        }

        // Insérer l’article à la place libérée.
        tabArticles[pos] = a;
        nbArticles++;
    }  
    
    /**
     * Ajoute un livre à la boutique.
     */
    public void ajouterLivre(
        String description,
        double prixInitial,
        int    nbreExemplaires,
        String isbn,
        int    nbPages
    ) {
        Livre livre_ = new Livre(
            description,
            prixInitial,
            nbreExemplaires,
            isbn,
            nbPages
        );

        ajouterArticleTrie(livre_);
    }
    
    /**
     * Ajoute un manuel scolaire à la boutique.
     */
    public void ajouterManuel(
        String description,
        double prixInitial,
        int    nbreExemplaires,
        String isbn,
        int    nbPages,
        String matiere,
        String niveauScolaire
    ) {
        Manuel manuel_ = new Manuel(
            description,
            prixInitial,
            nbreExemplaires,
            isbn,
            nbPages,
            matiere,
            niveauScolaire
        );

        ajouterArticleTrie(manuel_);
    }
    
    /**
     * Ajoute un magazine à la boutique.
     */
    public void ajouterMagazine(
        String    description,
        double    prixInitial,
        int       nbreExemplaires,
        String    issn,
        String    periodicite,
        LocalDate datePublication
    ) {
        Magazine magazine_ = new Magazine(
            description,
            prixInitial,
            nbreExemplaires,
            issn,
            periodicite,
            datePublication
        );

        ajouterArticleTrie(magazine_);
    }
    
    /**
     * Augmente le nombre d'exemplaires d'un article en donnant son numéro ISBN
     * ou ISSN et la quantité reçue, via l'utilisation de la méthode ajouter()
     * de la classe Article.
     */
    public void ajouter(String isbnIssn, int quantite) {

        Article a = rechercher(isbnIssn);

        if (a != null) {
            a.ajouter(quantite);
        } else {
            System.out.printf(
                """
                🟠 Aucun article enregistré sous le numéro : %s
                """,
                isbnIssn
            );
        }
    }
    
    /**
     * Réduit le nombre d'exemplaires d'un article en donnant son numéro ISBN
     * ou ISSN et la quantité vendue, via l'utilisation de la méthode retirer()
     * de la classe Article.
     */
    public void retirer(String isbnIssn, int quantite) {

        Article a = rechercher(isbnIssn);

        if (a != null) {

            if (quantite <= a.getNbreExemplaires()) {
                a.retirer(quantite);
            } else {
                System.out.println(
                    """
                    🟠 La quantité à retirer est inférieure au nombre d'exemplaires disponibles.
                    """);
            }

        } else {
            System.out.printf(
                """
                🟠 Aucun article enregistré sous le numéro : %s
                """,
                isbnIssn
            );
        }
    }

    
    // -------------- AUTRE METHODES

    /**
     * Ajoute un bon de dépôt.
     */
    public void ajouterBonDepot(BonDepot bon) {
        if (nbBonsDepot < tabBonsDepot.length) {
            tabBonsDepot[nbBonsDepot] = bon;
            nbBonsDepot++;
        } else {
            System.out.println("🟠 Nombre de bons de dépôt autorisés atteint.");
        }
    }
    
    /**
     * Affiche les éléments de la boutique.
     */
    public void afficherEtablissement() {
        System.out.printf(
            """
            - Boutique : %s
            """,
            nomBoutique
        );
        
        System.out.println("\n- Articles disponibles :");
        for (int i = 0; i < nbArticles; i++) {
            System.out.println(tabArticles[i].toString());
            System.out.println();
        }
        
        System.out.println("\n- Bons de dépôt émis :");
        for (int i = 0; i < nbBonsDepot; i++) {
            tabBonsDepot[i].afficherBonDepot();
            System.out.println();
        }
    }
    
    /**
     * Recherche un article grâce à son ISBN/ISSN. 
     * Retourne l'article s'il existe déjà, sinon null.
     */
    public Article rechercher(String isbnIssn) {
        for (int i = 0; i < nbArticles; i++) {
            Article a = tabArticles[i];

            if (a.getNumero().equals(isbnIssn)) {
                return a;
            }
        }
        return null;
    }
    
    /**
     * Une fois que le bon de dépôt a été ajouté/enregiqtré, 
     * traite chaque ligne du bon:
     * - soit l'article est ajouté
     * - soit le nombre d'exemplaires est mis à jour
     */
    public void traiterBonDepot(BonDepot bon) {
        LigneDepot[] lignes = bon.getTabLignesDepot();

        for (int i = 0; i < bon.getNbArticlesDeposes(); i++) {
            LigneDepot ligne = lignes[i];

            // Cherche si l'article existe déjà.
            Article articleExisteDeja = rechercher(ligne.getNumero());

            // Si l'article existe déjà, le nombre d'exemplaires est mis à jour
            // SI L’article n'existe pas, celui-ci est créée.
            if (articleExisteDeja != null) {          
                articleExisteDeja.ajouter(ligne.getNbreExemplaires());
            } else {
                System.out.printf(
                    """
                    L'article numéro %s n'est pas reconnu. Merci de le créer.
                    """,
                    ligne.getNumero()
                );
            }
        }
    }
    
    
    
}
