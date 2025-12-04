
/*
 * TP:     3
 * Class : LigneDepot | Gestion des stocks
 * Author: Stéphane SINGERY / Yoann Rabahi / Teddy Rakotoarivelo
 * Group:  INGE-1-APP-BDML2
 * Date:   2025-11-06
 */

// Import packages
package com.mycompany.gestiondesstocks;

// Declare class
public class LigneDepot {
    
    // ----------------------------- ATTRIBUTE   
    
    protected String numero;
    protected int    nbreExemplaires;
    
    // ----------------------------- CONSTRUCTOR
    
    public LigneDepot (
        String numero,
        int    nbreExemplaires   
    ) {
        this.numero          = numero;
        this.nbreExemplaires = nbreExemplaires;
    }  
    
    // ----------------------------- GETTER
    
    public String getNumero() {
        return this.numero;
    }
    public int    getNbreExemplaires() {
        return this.nbreExemplaires;
    }
    
    // ----------------------------- SETTER
    
    public void setIsbnIssn(String isbnIssn) {
        this.numero = numero;
    }    
    public void setNbreExemplaires(int nbreExemplaires) {
        this.nbreExemplaires = nbreExemplaires;
    }
    
    // ----------------------------- METHOD
    
    /**
     * Affiche les éléments de la ligne de dépôt.
     */
    public void afficherLigneDepot() {
        System.out.printf(
            """
            - ISBN/ISSN article   : %s
            - Nombre d'exemplaires: %s
            """,
            numero,
            nbreExemplaires
        );
    }
}
