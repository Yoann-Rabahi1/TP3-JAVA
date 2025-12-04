/*
 * TP: 3
 * Class : Article | Gestion des stocks
 * Author: Stéphane SINGERY / Yoann Rabahi / Teddy Rakotoarivelo
 * Group: INGE-1-APP-BDML2
 * Date: 2025-11-06
 */

// Import packages
package com.mycompany.gestiondesstocks;

// Declare class
abstract class Article {
    
    // ----------------------------- ATTRIBUTE
    
    protected String description;
    protected double prixInitial;
    protected int    nbreExemplaires;
    
    // ----------------------------- CONSTRUCTOR
    
    public Article (
        String description,
        double prixInitial,
        int    nbreExemplaires
    ) {
        this.description     = description;
        this.prixInitial     = prixInitial;
        this.nbreExemplaires = nbreExemplaires;
    }
    
    // ----------------------------- GETTER
    
    public String getDescription() {
        return this.description;
    }
    public double getPrixInitial() {
        return this.prixInitial;
    }
    public int    getNbreExemplaires() {
        return this.nbreExemplaires;
    }
    
    // Facilite la recherche d'un article par son identifiant.
    public abstract String getNumero();
    
    // ----------------------------- SETTER
    
    public void setDescription(String description) {
        this.description     = description;
    }   
    public void setPrixInitial(double prixInitial) {
        this.prixInitial     = prixInitial;
    }
    public void setNbreExemplaires(int nbreExemplaires) {
        this.nbreExemplaires = nbreExemplaires;
    }
    
    // ----------------------------- METHOD
    
    /**
    * Augmente le nombre d'exemplaires. 
    */
    public void ajouter(int quantite) {
        if (quantite > 0) {
            this.nbreExemplaires = this.nbreExemplaires + quantite;
        }
    }
    
    /**
    * Réduit le nombre d'exemplaires.
    */
    public void retirer(int quantite) {
        if (quantite > 0 && quantite <= this.nbreExemplaires) {
            this.nbreExemplaires = this.nbreExemplaires - quantite;
        }
    }
    
    /**
     * Retourne true si un article doit être placé après un autre article,
     * false autrement, selon l’ordre lexicographique des identifiants.
     */
    public boolean placerApres(Article autre) {
        return this.getNumero().compareTo(autre.getNumero()) > 0;
    }
    
    public abstract String toString();
    
    public abstract double calculerPrix();
}
