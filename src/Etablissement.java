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
    
    public void lister(){
        if(this.nbArticles == 0){
            System.out.println("Il n'y a aucun articles");
        }
        
        LocalDate dateCourante = LocalDate.now();
        
        Article[] article_trier = new Article[this.nbArticles];
        
        for(int i = 0; i < this.nbArticles; i ++){
            for(int j = 0; j < this.nbArticles - i - 1; i++){
                if(article_trier[j].getNbStock() > article_trier[j + 1].getNbStock()){
                    
                    Article temp = article_trier[j];
                    article_trier[j] = article_trier[j + 1];
                    article_trier[j + 1] = temp;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== LISTE DES ARTICLES TRIÉS PAR STOCK (Prix au ").append(dateCourante).append(") ===\n");
        sb.append(String.format("%-5s | %-10s | %-10s | %s\n", "N°", "STOCK", "PRIX FINAL", "DESCRIPTION ET ID"));
        sb.append("------------------------------------------------------------------------------------\n");
        
        for(int i = 0; i < this.nbArticles; i++){
            
            Article article = article_trier[i];
            
            double prixFinal = article.calculerPrix(dateCourante);
            
            sb.append(String.format("%-5d | %-10d | %-10.2f | %s\n", 
                                    (i + 1), 
                                    article.getNbStock(),
                                    prixFinal,
                                    article.toString()));
        }
        
        System.out.println(sb.toString());
            
    }
    
    
    public void lister(String numeroTel) {
        if (this.nbBonDepotEmis == 0) {
            System.out.println("Aucun bon de dépôt n'a été émis.");
            return;
        }

        // On utilise un tableau temporaire pour stocker les résultats filtrés
        BonDepot[] bonsFiltres = new BonDepot[this.nbBonDepotEmis];
        int nbBonsFiltres = 0;

        for (int i = 0; i < this.nbBonDepotEmis; i++) {
            BonDepot bon = this.bonDepotEmis[i];
            
            // Nécessite BonDepot.getNumeroTel()
            if (bon.getNumeroTel().equals(numeroTel)) {
                bonsFiltres[nbBonsFiltres] = bon;
                nbBonsFiltres++;
            }
        }
        
        if (nbBonsFiltres == 0) {
            System.out.println("Aucun bon de dépôt trouvé pour le client " + numeroTel + ".");
            return;
        }

        System.out.println("\n=== BONS DE DÉPÔT DU CLIENT " + numeroTel + " (Trié par date d'émission) ===");
        
        // Le résultat est affiché dans l'ordre du tableau bonDepotEmis (le plus récent en dernier)
        for (int i = 0; i < nbBonsFiltres; i++) {
            // Nécessite BonDepot.toString() complet
            System.out.println(bonsFiltres[i].toString()); 
        }
        System.out.println("Nombre total de bons trouvés : " + nbBonsFiltres);
    }
    
    
    public void lister(String numeroArticle, LocalDate debutPeriode, LocalDate finPeriode) {
        if (this.nbBonDepotEmis == 0) {
            System.out.println("Aucun bon de dépôt n'a été émis.");
            return;
        }

        int nbBonsTrouves = 0;
        
        System.out.println("\n=== BONS DE DÉPÔT POUR L'ARTICLE " + numeroArticle + " (Du " + debutPeriode + " au " + finPeriode + ") ===");
        
        for (int i = 0; i < this.nbBonDepotEmis; i++) {
            BonDepot bon = this.bonDepotEmis[i];
            LocalDate dateEmission = bon.getDateEmissionBon();
            boolean articleTrouveDansBon = false;

            // 1. Vérification de la période (date d'émission incluse dans la période)
            // Utilisation de isAfter/isBefore pour la comparaison
            boolean estDansPeriode = (dateEmission.isAfter(debutPeriode) || dateEmission.equals(debutPeriode)) && 
                                     (dateEmission.isBefore(finPeriode) || dateEmission.equals(finPeriode));
                                     
            if (estDansPeriode) {
                // 2. Vérification de la présence de l'article dans les lignes de dépôt du bon
                LigneDepot[] lignes = bon.getLignesDepot(); // Nécessite BonDepot.getLignesDepot()
                
                for (int j = 0; j < bon.getNbLignesDepot(); j++) {
                    // Nécessite LigneDepot.getNumeroArticle()
                    if (lignes[j].getNumero().equals(numeroArticle)) {
                        articleTrouveDansBon = true;
                        break; // Article trouvé, on peut passer au bon suivant
                    }
                }
            }
            
            // 3. Affichage si les deux conditions sont remplies
            if (articleTrouveDansBon) {
                System.out.println(bon.toString());
                nbBonsTrouves++;
            }
        }
        
        if (nbBonsTrouves == 0) {
            System.out.println("Aucun bon de dépôt ne correspond aux critères de recherche.");
        } else {
            System.out.println("Nombre total de bons trouvés : " + nbBonsTrouves);
        }
    }
    
    public boolean versFichierArticles(String nomFichier) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(nomFichier, false); 
            String separator = System.lineSeparator(); 
            
            for (int i = 0; i < this.nbArticles; i++) {
                Article articleCourant = this.articles[i];
                
                String donneesArticle = articleCourant.versFichier();
                
                fw.write(donneesArticle);
                

                if (i < this.nbArticles - 1) {
                    fw.write(separator); 
                }
            }
            System.out.println("Sauvegarde des articles réussie dans " + nomFichier);
            return true;
            
        } catch (IOException e) {
            System.err.println("Erreur de sauvegarde dans le fichier : " + e.getMessage());
            return false;
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.err.println("Erreur à la fermeture du fichier : " + e.getMessage());
                }
            }
        }
    }
    
    
    public boolean depuisFichierArticles(String nomFichier) {
        FileReader fr = null;
        BufferedReader br = null;
        int nbArticlesCharges = 0;

        try {
            fr = new FileReader(nomFichier); 
            br = new BufferedReader(fr); 

            String ligneID; // Ligne contenant l'ISBN ou l'ISSN
            String ligneDonnees; // Ligne contenant les autres informations
            
            while ((ligneID = br.readLine()) != null) {
                
                if (this.nbArticles >= this.max_article) {
                    System.out.println("Stock plein. Arrêt du chargement.");
                    break;
                }
                
                ligneDonnees = br.readLine();
                if (ligneDonnees == null) break; // Fin inattendue du fichier

                String[] parties = ligneDonnees.split(":");
                
                String description = parties[0];
                double prixInitial = Double.parseDouble(parties[1]);
                int nbStock = Integer.parseInt(parties[2]);
                
                Article nouvelArticle = null;
                
                if (ligneID.length() == 13 && parties.length >= 4) { // ISBN (Livre ou Manuel)
                    
                    String ISBN = ligneID;
                    int nbPages = Integer.parseInt(parties[3]);
                    
                    if (parties.length == 6) { // Manuel: 6 parties (Description:prix:stock:pages:matiere:niveau)
                        String matiere = parties[4];
                        String niveauScolaire = parties[5];
                        // On suppose que votre classe s'appelle Manuel
                        nouvelArticle = new Manuel(description, prixInitial, nbStock, ISBN, nbPages, matiere, niveauScolaire);
                        
                    } else if (parties.length == 4) { // Livre: 4 parties (Description:prix:stock:pages)
                        // On suppose que votre classe s'appelle Livre
                        nouvelArticle = new Livre(description, prixInitial, nbStock, ISBN, nbPages);
                    }
                    
                } else if (ligneID.length() == 8 && parties.length == 5) { // ISSN (Magazine)
                    // Magazine: 5 parties (Description:prix:stock:periodicite:date)
                    String ISSN = ligneID;
                    String periodicite = parties[3];
                    // Conversion de la chaîne en LocalDate [cite: 152]
                    LocalDate datePub = LocalDate.parse(parties[4]); 
                    // On suppose que votre classe s'appelle Magazine
                    nouvelArticle = new Magazine(description, prixInitial, nbStock, ISSN, periodicite, datePub);
                }

                // Ajout de l'article au stock (utilise la méthode de tri ajouterArticle)
                if (nouvelArticle != null) {
                    this.ajouterArticle(nouvelArticle);
                    nbArticlesCharges++;
                } else {
                    System.err.println("Avertissement: Format d'article inconnu ou incorrect (ID: " + ligneID + "). Ligne ignorée.");
                }
            }

            System.out.println("Chargement des articles réussi. " + nbArticlesCharges + " articles ajoutés.");
            return true;
            
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.err.println("Erreur de format numérique dans le fichier : " + e.getMessage());
            return false;
        } finally {
            // Fermeture des lecteurs
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException e) {
                System.err.println("Erreur à la fermeture des fichiers : " + e.getMessage());
            }
        }
    }
    
    public boolean versFichierDepots(String nomFichier) {
        FileWriter fw = null;
        try {
            // Création du FileWriter (écrase le fichier existant)
            fw = new FileWriter(nomFichier, false); 
            String separator = System.lineSeparator();

            // On parcourt uniquement les 'nbBonDepotEmis' bons existants
            for (int i = 0; i < this.nbBonDepotEmis; i++) {
                BonDepot bonCourant = this.bonDepotEmis[i];

                // 1. Appel de la méthode versFichier() de BonDepot
                String donneesBon = bonCourant.versFichier();

                // 2. Écriture des données complètes du bon (Numéro, Entête, LignesDepot)
                fw.write(donneesBon);

                // 3. Ajout d'un saut de ligne supplémentaire pour séparer les bons dans le fichier
                if (i < this.nbBonDepotEmis - 1) {
                    fw.write(separator); 
                }
            }
            System.out.println("Sauvegarde des bons de dépôt réussie dans " + nomFichier);
            return true;

        } catch (IOException e) {
            System.err.println("Erreur de sauvegarde des dépôts : " + e.getMessage());
            return false;
        } finally {
            // Fermeture du fichier
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.err.println("Erreur à la fermeture du fichier : " + e.getMessage());
                }
            }
        }
    }
    
    public boolean depuisFichierDepots(String nomFichier) {
        FileReader fr = null;
        BufferedReader br = null;
        int maxNumeroBon = 0; // Pour mettre à jour le compteur statique (si existant)
        int nbBonsCharges = 0;

        try {
            fr = new FileReader(nomFichier); 
            br = new BufferedReader(fr); 

            String ligneNumBon; // Ligne 1: Numéro du bon (Ex: 1)
            String ligneEntete; // Ligne 2: Entête (Ex: 061236547890:2025-10-10:2)

            while ((ligneNumBon = br.readLine()) != null) {

                // Si l'établissement est plein, on arrête le chargement
                if (this.nbBonDepotEmis >= this.max_bon_depot) {
                    System.out.println("Stock de bons de dépôt plein. Arrêt du chargement.");
                    break;
                }

                // 1. Lecture de l'entête
                ligneEntete = br.readLine();
                if (ligneEntete == null) break; // Fin inattendue

                // 2. Parsing du Numéro et de l'Entête
                int numeroBon = Integer.parseInt(ligneNumBon.trim());
                String[] partiesEntete = ligneEntete.split(":");

                // Vérification du format de l'entête (doit contenir Tel, Date, et NbLignes)
                if (partiesEntete.length < 3) {
                    System.err.println("Avertissement: Entête de bon mal formatée. Bon n°" + numeroBon + " ignoré.");
                    continue; 
                }

                String numeroTel = partiesEntete[0];
                LocalDate dateEmission = LocalDate.parse(partiesEntete[1]);
                int nbLignesDepot = Integer.parseInt(partiesEntete[2]);

                // 3. Création du BonDepot
                // NOTE: On suppose un constructeur permettant de définir le numéro et la date pour la recréation
                BonDepot nouveauBon = new BonDepot(numeroBon, numeroTel, dateEmission);

                // 4. Lecture et ajout des LignesDepot
                for (int i = 0; i < nbLignesDepot; i++) {
                    String ligneDepotStr = br.readLine();
                    if (ligneDepotStr == null) throw new IOException("Fin de fichier inattendue lors de la lecture des lignes de dépôt pour le bon n°" + numeroBon);

                    String[] partiesLigne = ligneDepotStr.split(":");

                    // Format de LigneDepot: nbExemplaires:numeroArticle
                    int nbExemplaires = Integer.parseInt(partiesLigne[0]);
                    String numeroArticle = partiesLigne[1];

                    // Utilisation de la méthode déjà définie
                    nouveauBon.ajouterLigne(numeroArticle, nbExemplaires);
                }

                // 5. Ajout du bon à l'établissement
                this.bonDepotEmis[this.nbBonDepotEmis] = nouveauBon;
                this.nbBonDepotEmis++;
                nbBonsCharges++;

                // Mise à jour du numéro max pour le compteur statique
                if (numeroBon > maxNumeroBon) {
                    maxNumeroBon = numeroBon;
                }

                // Consommer le séparateur vide potentiel si ce n'est pas le dernier bon
                br.readLine(); 
            }

            // 6. Mise à jour du compteur statique de la classe BonDepot
            // BonDepot.setCompteurStatique(maxNumeroBon + 1); // Dépend de votre implémentation de BonDepot

            System.out.println("Chargement des bons de dépôt réussi. " + nbBonsCharges + " bons ajoutés.");
            return true;

        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier des dépôts : " + e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.err.println("Erreur de format numérique dans le fichier des dépôts : " + e.getMessage());
            return false;
        } finally {
            // Fermeture des lecteurs
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException e) {
                System.err.println("Erreur à la fermeture des fichiers : " + e.getMessage());
            }
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("=== Établissement : ").append(getNomBoutique()).append(" ===\n");

        String bonsStr = (getNbBonDepotEmis() > 0)
                ? getNbBonDepotEmis() + " bon(s) de dépôt émis."
                : "Aucun bon de dépôt émis.";
        sb.append("Dépôts : ").append(bonsStr).append("\n");

        sb.append("\n--- Liste des ARTICLES en stock (").append(getNbArticles()).append(" / ").append(max_article).append(") ---\n");

        if (getNbArticles() == 0) {
            sb.append("=> Aucun article n'est disponible en stock.\n");
        } else {
            for (int i = 0; i < getNbArticles(); i++) {
                sb.append("  [N°").append(i + 1).append("] : ").append(articles[i].toString()).append("\n");
            }
        }

        sb.append("=========================================\n");

        return sb.toString();
    }

}
