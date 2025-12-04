
/*
 * TP:     3
 * Class : BonDepot | Gestion des stocks
 * Author: Stéphane SINGERY
 * Group:  INGE-1-APP-BDML2
 * Date:   2025-11-06
 */

// Import packages
package com.mycompany.gestiondesstocks;
import  java.time.LocalDate;

// Declare class
public class BonDepot {
    
    // ----------------------------- ATTRIBUTE   
    
    protected String       numeroTelephone;
    protected LocalDate    dateEmission;
    protected int          nbArticlesDeposes;
    protected int          idBonDepot;
    protected int          nbLignesAutorisees;
    protected LigneDepot[] tabLignesDepot;
    
    // Numérotation des bons de manière unique.
    private static int compteurBonDepot = 0;
    
    // ----------------------------- CONSTRUCTOR
    
    public BonDepot(
        String    numeroTelephone,
        int       nbArticlesDeposes,
        int       nbLignesAutorisees
    ) {
        this.numeroTelephone    = numeroTelephone;
        this.dateEmission       = LocalDate.now();
        this.nbArticlesDeposes  = 0;                 // Le bon dépôt est initialisé avec 0 article.
        this.idBonDepot         = ++compteurBonDepot;
        this.nbLignesAutorisees = nbLignesAutorisees;
        this.tabLignesDepot     = new LigneDepot[nbLignesAutorisees];
    }
    
    // ----------------------------- GETTER
    
    public String       getNumeroTelephone() {
        return this.numeroTelephone;
    }

    public LocalDate    getDateEmission() {
        return this.dateEmission;
    }

    public int          getNbArticlesDeposes() {
        return this.nbArticlesDeposes;
    }

    public LigneDepot[] getTabLignesDepot() {
        return this.tabLignesDepot;
    }

    public int          getIdBonDepot() {
        return this.idBonDepot;
    }

    public int          getNbLignesAutorisees() {
        return this.nbLignesAutorisees;
    }

    // ----------------------------- SETTER
    
    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone    = numeroTelephone;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission       = dateEmission;
    }

    public void setNbArticlesDeposes(int nbArticlesDeposes) {
        this.nbArticlesDeposes  = nbArticlesDeposes;
    }

    public void setTabLignesDepot(LigneDepot[] tabLignesDepot) {
        this.tabLignesDepot     = tabLignesDepot;
    }

    public void setIdBonDepot(int idBonDepot) {
        this.idBonDepot         = idBonDepot;
    }

    public void setNbLignesAutorisees(int nbLignesAutorisees) {
        this.nbLignesAutorisees = nbLignesAutorisees;
    }

    
    // ----------------------------- METHOD
    
    /**
     * Afficher les éléments du bon de dépôt.
     */
    public void afficherBonDepot() {
        System.out.printf(
            """
            - Bon de dépôt n°   : %s
            - Téléphone         : %s
            - Date d'émission   : %s
            - Nb articles déposé: %s
            - Lignes autorisées : %s
            - Lignes de dépôt   : %s
            """, 
            idBonDepot,
            numeroTelephone,
            dateEmission,
            nbArticlesDeposes,
            nbLignesAutorisees
        );
             
        for (int i = 0; i < nbArticlesDeposes; i++) {
            tabLignesDepot[i].afficherLigneDepot();
        }
    }
    
    /**
    * Ajoute une ligne de dépôt en donnant le numéro ISBN / ISSN
    * et le nombre d'exemplaires déposés.
    */
    public void ajouterLigne(String isbnIssn, int quantite) {

        if (nbArticlesDeposes < nbLignesAutorisees) {

            LigneDepot ligne = new LigneDepot(isbnIssn, quantite);

            tabLignesDepot[nbArticlesDeposes] = ligne;
            nbArticlesDeposes++;

        } else {
            System.out.println("🟠 Nombre de lignes autorisées atteint.");
        }
    }

  
}


