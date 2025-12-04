
/*
 * TP:     3
 * Class : Manuel | Gestion des stocks
 * Author: Stéphane SINGERY / Yoann Rabahi / Teddy Rakotoarivelo
 * Group:  INGE-1-APP-BDML2
 * Date:   2025-11-06
 */

// Import packages
package com.mycompany.gestiondesstocks;

// Declare class
class Manuel extends Article {
    
    // ----------------------------- ATTRIBUTE   
    
    protected String isbn;
    protected int    nbPages;
    protected String matiereEnseignee;
    protected String niveauScolaire;
    
    // ----------------------------- CONSTRUCTOR
    
    public Manuel(
        String description,
        double prixInitial,
        int    nbreExemplaires,
        String isbn,
        int    nbPages,
        String matiereEnseignee,
        String niveauScolaire
    ) {
        super(description, prixInitial, nbreExemplaires);
        this.isbn             = isbn;
        this.nbPages          = nbPages;   
        this.matiereEnseignee = matiereEnseignee;
        this.niveauScolaire   = niveauScolaire;
    }   
    
    // ----------------------------- GETTER
    
    
    // Subclass "Manuel"
    public String getNumero() {
        return this.isbn; 
    }   
    public int    getNbPages() {
        return this.nbPages; 
    }
    public String getMatiereEnseignee() {
        return this.matiereEnseignee; 
    }
    public String getNiveauScolaire() {
        return this.niveauScolaire; 
    } 
    
    // ----------------------------- SETTER
    
    
    // Subclass "Manuel"
    public void setNumero(String isbn) {
        this.isbn             = isbn; 
    }  
    public void setNbPages(int nbPages) {
        this.nbPages          = nbPages; 
    }
    public void setMatiereEnseignee(String matiereEnseignee) {
        this.matiereEnseignee = matiereEnseignee; 
    }
    public void setNiveauScolaire(String niveauScolaire) {
        this.niveauScolaire   = niveauScolaire; 
    }
    
    // ----------------------------- METHOD / OVERRIDE
    
    @Override
    /**
     * Recalcule le prix si un discount s'applique.
     */
    public double calculerPrix() {
        // Retourne le numéro du mois d'avril dans l'année.
        int mois = java.time.LocalDate.now().getMonthValue();

        if (mois == 4) {
            return getPrixInitial() * 0.5;
        } else {
            return getPrixInitial();
        }
    }
    
    @Override
    /**
     * Retourne un String composé des attributs du manuel.
     */
    public String toString() {
        return "Manuel :\n"
            + "  ISBN            : " + isbn + "\n"
            + "  Description     : " + getDescription() + "\n"            
            + "  Stock           : " + getNbreExemplaires() + "\n"
            + "  Prix initial    : " + getPrixInitial() + "\n"           
            + "  Pages           : " + nbPages + "\n"
            + "  Matière         : " + matiereEnseignee + "\n"
            + "  Niveau scolaire : " + niveauScolaire;
    }
}
