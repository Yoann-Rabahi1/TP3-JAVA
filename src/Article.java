/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */
import java.time.LocalDate;

public abstract class Article {

    private String description;
    private double prixInitial;
    private int nbStock;

    public Article(String description, double prixInitial, int nbStock){
        this.description = description;
        this.prixInitial = prixInitial;
        this.nbStock = nbStock;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setPrixInitial(double prixInitial){
        this.prixInitial = prixInitial;
    }

    public void setNb_stock(int nbStock){
        this.nbStock = nbStock;
    }

    public String getDescription(){
        return description;
    }

    public double getPrixInitial(){
        return prixInitial;
    }

    public int getNbStock(){
        return nbStock;
    }

    public abstract String toString();

    public abstract String getNumero();

    public boolean placerApres(Article autreArticle){
        return this.getNumero().compareTo(autreArticle.getNumero()) > 0;
    }

    public void ajouter(int quantite){
        if (quantite > 0){
            nbStock = nbStock + quantite;
            System.out.println("Stock mis à jour. Nouvelle quantité : " + this.getNbStock());
        }else{
            System.out.println("Erreur la quantité à ajouter doit être positive.");
        }
    }

    public boolean retirer(int quantite){
        if (quantite < 0){
            nbStock = nbStock - quantite;
            System.out.println("Stock mis à jour : " + this.getNbStock());
            return true;
        }else {
            System.out.println("Erreur : La quantité à retirer doit être négative");
            return false;
        }
    }

    public abstract double calculerPrix(LocalDate periode);
    
    public abstract String versFichier();
}