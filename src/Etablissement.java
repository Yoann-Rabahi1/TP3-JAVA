/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */
import java.lang.StringBuilder;
import java.time.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.time.LocalDate;

public class Etablissement {

    private final int max_article = 50;
    private final int max_bon_depot = 50;

    private String nomBoutique;
    private Article[] articles;
    private int nbArticles;
    private BonDepot[] bonDepotEmis;
    private int nbBonDepotEmis;


    // constructeur

    public Etablissement(String nomBoutique){
        this.nomBoutique = nomBoutique;

        this.articles = new Article[max_article];
        this.nbArticles = 0;

        this.bonDepotEmis = new BonDepot[max_bon_depot];
        this.nbBonDepotEmis = 0;
    }

    // setters

    public void setNomBoutique(String nomBoutique){this.nomBoutique = nomBoutique;}
    public void setArticles(Article[] articles){this.articles = articles;}
    public void setNbArticles(int nbArticles){this.nbArticles = nbArticles;}
    public void setBonDepotEmis(BonDepot[] bonDepotEmis){this.bonDepotEmis = bonDepotEmis;}
    public void setNbBonDepotEmis(int nbBonDepotEmis){this.nbBonDepotEmis = nbBonDepotEmis;}

    // getters

    public String getNomBoutique(){return nomBoutique;}
    public Article[] getArticles(){return articles;}
    public int getNbArticles(){return nbArticles;}
    public BonDepot[] getBonDepotEmis(){return bonDepotEmis;}
    public int getNbBonDepotEmis(){return  nbBonDepotEmis;}


    // méthodes pour gérer les articles de l'établissement


    public boolean ajouterArticle(Article nouvelArticle){
        if (this.nbArticles >= this.max_article){
            System.out.println("Erreur : Le stock d'articles est plein.");
            return false;
        }

        int position = 0;

        while(position < this.nbArticles && nouvelArticle.placerApres(this.articles[position])){
            position++;
        }

        for (int i = this.nbArticles; i > position; i--){
            this.articles[i] = this.articles[i - 1];
        }

        this.articles[position] = nouvelArticle;

        this.nbArticles ++;

        return true;
    }

    public boolean ajouter(String description, double prixInitial, int nbStock, String ISBN, int nbPages){
        Livre nouveauLivre = new Livre(description, prixInitial, nbStock, ISBN, nbPages);

        return this.ajouterArticle(nouveauLivre);
    }

    public boolean ajouter(String description, double prixInitial, int nbStock, String ISBN, int nbPages, String matiere, String niveauScolaire){
        Manuel nouveauManuel = new Manuel(description, prixInitial, nbStock, ISBN, nbPages, matiere, niveauScolaire);

        return this.ajouterArticle(nouveauManuel);
    }

    public boolean ajouter(String description, double prixInitial, int nbStock, String ISSN, String periodicite, LocalDate dateDePublication){
        Magazine nouveauMagazine = new Magazine(description, prixInitial, nbStock, ISSN, periodicite, dateDePublication);

        return this.ajouterArticle(nouveauMagazine);
    }

    public Article rechercher(String numero){
        for (int i = 0; i < this.nbArticles; i++){
            Article articlCourant = this.articles[i];
            String numeroCourant = articlCourant.getNumero();

            if (numeroCourant.equals(numero)){
                return articlCourant;
            }

            if(numeroCourant.compareTo(numero)>0){
                break;
            }
        }
        return null;
    }

    public boolean ajouter(String numero, int quantite){

        Article article = this.rechercher(numero);

        if (article != null){
            article.ajouter(quantite);
            System.out.println("Stock de l'article " + numero + " augmenté de " + quantite + ".");
            return true;
        }else{
            return false;
        }
    }

    public boolean retirer(String numero, int quantite) {

        Article article = this.rechercher(numero);

        if (article != null) {
            if (article.retirer(quantite)) {
                System.out.println("Stock de l'article " + numero + " diminué de " + quantite + ".");
                return true;
            } else {
                System.out.println("Erreur: Stock insuffisant pour l'article " + numero +
                        " (disponible: " + article.getNbStock() + ").");
                return false;
            }
        } else {
            System.out.println("Erreur: Article avec le numéro " + numero + " non trouvé.");
            return false;
        }
    }

    public boolean ajouter(String numeroTel){
        if (this.getNbBonDepotEmis() >= max_bon_depot){
            System.out.println("Erreur : le tableau des bons de dépots est plein");
            return false;
        }

        LocalDate dateEmission = LocalDate.now();

        BonDepot nouveauBonDepot = new BonDepot(numeroTel, dateEmission);

        this.bonDepotEmis[this.getNbBonDepotEmis()] = nouveauBonDepot;

        this.nbBonDepotEmis++;

        System.out.println("Bon de dépôt n°" + nouveauBonDepot.getNumeroBon() + " enregistré pour le " + dateEmission + ".");
        return true;
    }

}
