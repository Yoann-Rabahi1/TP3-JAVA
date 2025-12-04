/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Magazine extends Article{

    private String ISSN;
    private String periodicite;
    private LocalDate dateDePublication;

    public Magazine(String description, double prixInitial, int nbStock, String ISSN, String periodicite, LocalDate dateDePublication){
        super(description, prixInitial, nbStock);
        this.ISSN = ISSN;
        this.periodicite = periodicite;
        this.dateDePublication = dateDePublication;
    }

    public void setISSN(String ISSN){
        this.ISSN = ISSN;
    }

    public void setPeriodicite(String periodicite){
        this.periodicite = periodicite;
    }

    public void setDateDePublication(LocalDate dateDePublication){
        this.dateDePublication = dateDePublication;
    }

    public String getISSN(){
        return ISSN;
    }

    public String getPeriodicite(){
        return periodicite;
    }

    public LocalDate getDateDePublication(){
        return dateDePublication;
    }

    @Override
    public String getNumero(){
        return this.ISSN;
    }


    @Override
    public double calculerPrix(LocalDate date){
        double prix = this.getPrixInitial();

        long joursEcoules = ChronoUnit.DAYS.between(this.dateDePublication, date);

        int seuil50percent;
        int seuil75percent;

        switch (this.periodicite.toLowerCase()){
            case "hebdomadaire":
                seuil50percent = 14;
                seuil75percent = 28;
                break;

            case "mensuel":
                seuil50percent = 60;
                seuil75percent = 120;
                break;

            case "trimestriel":
                seuil50percent = 180;
                seuil75percent = 360;
                break;

            default:
                return prix;
        }

        if(joursEcoules > seuil75percent){
            prix = this.getPrixInitial() * 0.25;
        }
        else if (joursEcoules > seuil50percent){
            prix = this.getPrixInitial() * 0.5;
        }

        return prix;
    }

    @Override
    public String toString(){
        return "[" + getDescription() + " " + getPrixInitial() + " " + getNbStock() + " " + getISSN() + " " + getPeriodicite() + " " + getDateDePublication() + "]";
    }
}
