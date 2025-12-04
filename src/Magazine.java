
/*
 * TP:     3
 * Class : Magazine | Gestion des stocks
 * Author: Stéphane SINGERY
 * Group:  INGE-1-APP-BDML2
 * Date:   2025-11-06
 */

// Import packages
package com.mycompany.gestiondesstocks;
import  java.time.LocalDate;

// Declare class
class Magazine extends Article {
    
    // ----------------------------- ATTRIBUTE   
    
    protected String    issn;
    protected String    periodicite;
    protected LocalDate datePublication;
    
    // ----------------------------- CONSTRUCTOR
    
    public Magazine(
        String    description,
        double       prixInitial,
        int       nbreExemplaires,
        String    issn,
        String    periodicite,
        LocalDate datePublication
        
    ) {
        super(description, prixInitial, nbreExemplaires);
        this.issn            = issn;
        this.periodicite     = periodicite;
        this.datePublication = datePublication;
    }
    
    // ----------------------------- GETTER
    
    // Superclass "Article"
    public String getDescription() {
        return super.getDescription(); 
    }
    public double getPrixInitial() {
        return super.getPrixInitial(); 
    }
    public int    getNbreExemplaires() {
        return super.getNbreExemplaires(); 
    }
    
    // Subclass "Magazine"
    public String    getNumero() {
        return this.issn; 
    }   
    public String    getPeriodicite() {
        return this.periodicite; 
    }
    public LocalDate getDatePublication() {
        return this.datePublication; 
    } 
    
    // ----------------------------- SETTER
    
    // Superclass "Article"
    public void setDescription(String description) {
        super.setDescription(description); 
    }
    public void setPrixInitial(double prixInitial) {
        super.setPrixInitial(prixInitial); 
    }
    public void setNbreExemplaires(int getNbreExemplaires) {
        super.setNbreExemplaires(nbreExemplaires); 
    }
    
    // Subclass "Magazine"
    public void setNumero(String issn) {
        this.issn            = issn; 
    }  
    public void setPeriodicite(String periodicite) {
        this.periodicite     = periodicite; 
    }
    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication; 
    }
    
    // ----------------------------- METHOD / OVERRIDE
    
    @Override
    /**
     * Recalcule le prix si un discount s'applique.
     */
    public double calculerPrix() {
        
        LocalDate dateDuJour = LocalDate.now();
        double prix = getPrixInitial();

        // -------------------- Hebdomadaire --------------------
        if (periodicite.equalsIgnoreCase("hebdomadaire")) {

            // Si date du jour >= datePublication + 4 semaines → discount de 75%
            // Si date du jour >= datePublication + 2 semaines → discount de 50%
            if (dateDuJour.isAfter(datePublication.plusWeeks(4))) {
                return prix * 0.25;
            } else if (dateDuJour.isAfter(datePublication.plusWeeks(2))) {
                return prix * 0.50;
            } else {
                return prix;
            }
            
        }

        // -------------------- Mensuel --------------------
        if (periodicite.equalsIgnoreCase("mensuel")) {

            // Si date du jour >= datePublication + 4 mois → discount de 75%
            // Si date du jour >= datePublication + 2 mois → discount de 50%
            if (dateDuJour.isAfter(datePublication.plusMonths(4))) {
                return prix * 0.25;
            } else if (dateDuJour.isAfter(datePublication.plusMonths(2))) {
                return prix * 0.50;
            } else {
                return prix;
            }
            
        }

        // -------------------- Trimestriel --------------------
        if (periodicite.equalsIgnoreCase("trimestriel")) {

            // Si date du jour >= datePublication + 4 trimestres → discount de 75%
            // Si date du jour >= datePublication + 2 trimestres → discount de 50%
            if (dateDuJour.isAfter(datePublication.plusMonths(12))) {   
                return prix * 0.25;
            } else if (dateDuJour.isAfter(datePublication.plusMonths(6))) { 
                return prix * 0.50;
            } else {
                return prix;
            }
            
        }

        return prix;
    }
    
    @Override
    /**
     * Retourne un String composé des attributs du magazine.
     */
    public String toString() {
        return "Magazine :\n"
            + "  ISSN             : " + issn + "\n"
            + "  Description      : " + getDescription() + "\n"            
            + "  Stock            : " + getNbreExemplaires() + "\n"
            + "  Prix initial     : " + getPrixInitial() + "\n"           
            + "  Périodicité      : " + periodicite + "\n"
            + "  Date publication : " + datePublication;
    }
}
