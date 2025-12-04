/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */

import java.time.LocalDate;
import java.time.Month;

public class Manuel extends Livre{

    private String matiere;
    private String niveauScolaire;

    public Manuel(String description,
                  double prixInitial, int nb_stock,
                  String ISBN, int nbPages,
                  String matiere, String niveauScolaire){
        super(description, prixInitial, nb_stock, ISBN, nbPages);

        this.matiere = matiere;
        this.niveauScolaire = niveauScolaire;
    }

    public void setMatiere(String matiere){
        this.matiere = matiere;
    }

    public void setNiveauScolaire(String niveauScolaire){
        this.niveauScolaire = niveauScolaire;
    }

    public String getMatiere(){
        return matiere;
    }

    public String getNiveauScolaire(){
        return niveauScolaire;
    }

    @Override
    public double calculerPrix(LocalDate periode){
        double prix = this.getPrixInitial();

        if (periode.getMonth() == Month.APRIL){
            prix = prix * 0.5;
        }

        return prix;
    }
    
    @Override
    public String versFichier() {
        String separator = System.lineSeparator();
        
        // Ligne 1: ISBN
        String ligne1 = this.getNumero() + separator; 
        
        // Ligne 2: Description:prixInitial:nbStock:nbPages:matiere:niveauScolaire
        String ligne2 = this.getDescription() + ":" + this.getPrixInitial() + ":" + this.getNbStock() + ":" + this.getNbPages() + ":" + this.getMatiere() + ":" + this.getNiveauScolaire();
        
        return ligne1 + ligne2;
    }

    @Override
    public String toString(){
        return super.toString() +
                ", matière =" + getMatiere() + ", " +
                " niveauScolaire =" + getNiveauScolaire() +" ]";
    }
}