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

public class Livre extends Article{

    private String ISBN;
    private int nbPages;

    public Livre(String description, double prixInitial, int nb_stock, String ISBN, int nbPages){
        super(description, prixInitial, nb_stock);
        this.ISBN = ISBN;
        this.nbPages = nbPages;
    }

    public void setNbPages(int nbPages){
        this.nbPages = nbPages;
    }

    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    public String getISBN(){
        return ISBN;
    }

    public int getNbPages(){
        return nbPages;
    }

    @Override
    public String getNumero(){
        return this.ISBN;
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
    public String toString() {
        return "[" +
                "description='" + getDescription() + '\'' +
                ", prixInitial=" + getPrixInitial() +
                ", nbStock=" + getNbStock() +
                ", ISBN=" + getISBN() +
                ", nbPages" + getNbPages() +
                "]";
    }
    
    @Override
    public String versFichier() {
        String separator = System.lineSeparator();
        
        // Ligne 1: ISBN [cite: 111]
        String ligne1 = this.ISBN + separator; 
        
        // Ligne 2: Description:prixInitial:nbStock:nbPages
        String ligne2 = this.getDescription() + ":" + this.getPrixInitial()+ ":" + this.getNbStock() + ":" + this.getNbPages();
        
        return ligne1 + ligne2;
    }
}