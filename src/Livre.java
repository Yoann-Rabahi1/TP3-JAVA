
/*
 * TP:     3
 * Class : Livre | Gestion des stocks
 * Author: Stéphane SINGERY / Yoann Rabahi / Teddy Rakotoarivelo
 * Group:  INGE-1-APP-BDML2
 * Date:   2025-11-06
 */

// Import packages
package com.mycompany.gestiondesstocks;

// Declare class
class Livre extends Article {
    
    // ----------------------------- ATTRIBUTE   
    
    protected String isbn;
    protected int    nbPages; 
    
    // ----------------------------- CONSTRUCTOR
    
    public Livre(
        String description,
        double prixInitial,
        int    nbreExemplaires,
        String isbn,
        int    nbPages
    ) {
        super(description, prixInitial, nbreExemplaires);
        this.isbn    = isbn;
        this.nbPages = nbPages;        
    }   
    
    // ----------------------------- GETTER
    
    
    // Subclass "Livre"
    public String getNumero() {
        return this.isbn; 
    }   
    public int    getNbPages() {
        return this.nbPages; 
    } 
    
    // ----------------------------- SETTER
    

    
    // Subclass "Livre"
    public void setNumero(String isbn) {
        this.isbn    = isbn; 
    }
    public void setNbPages(int nbPages) {
        this.nbPages = nbPages; 
    }
    
    // ----------------------------- METHOD / OVERRIDE
    
    @Override
    /**
     * Recalcule le prix si discount s'applique.
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
     * Retourne un String composé des attributs du livre.
     */
    public String toString() {
        return "Livre :\n"
            + "  ISBN         : " + isbn + "\n"
            + "  Description  : " + getDescription() + "\n"
            + "  Stock        : " + getNbreExemplaires() + "\n"
            + "  Prix initial : " + getPrixInitial()  + "\n"            
            + "  Pages        : " + nbPages + "\n";
    }
    
}
