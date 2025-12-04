/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.time.*;

public class BonDepot {

    private static int compteur = 0;
    private final int max_lignes = 12;

    private int numeroBon;
    private String numeroTel;
    private LocalDate dateEmissionBon;
    private LigneDepot[] lignesDepot;
    private int nbLignesDepot;

    public BonDepot(String numeroTel, LocalDate dateEmissionBon){
        compteur++;
        this.numeroBon = compteur;

        this.numeroTel = numeroTel;
        this.dateEmissionBon = dateEmissionBon;

        this.lignesDepot = new LigneDepot[max_lignes];
        this.nbLignesDepot = 0;
    }
    
    public BonDepot(int numeroBon, String numeroTel, LocalDate dateEmission) {
        
        // 1. Initialisation des attributs lus (on force le numeroBon)
        this.numeroBon = numeroBon;
        this.numeroTel = numeroTel;
        this.dateEmissionBon = dateEmission;
        
        // 2. Initialisation des attributs internes
        this.lignesDepot = new LigneDepot[max_lignes];
        this.nbLignesDepot = 0;
        
        // 3. Mise à jour du compteur statique
        // Ceci garantit que le prochain bon créé aura un numéro qui suit le plus grand numéro chargé.
        if (numeroBon >= compteur) {
            compteur = numeroBon;
        }
    }

    public void setNumeroBon(int numeroBon){this.numeroBon = numeroBon;}

    public void setNumeroTel(String numeroTel){this.numeroTel = numeroTel;}

    public void setDateEmissionBon(LocalDate dateEmissionBon){this.dateEmissionBon = dateEmissionBon;}

    public void setLignesDepot(LigneDepot[] lignesDepot){this.lignesDepot = lignesDepot;}

    public void setNbLignesDepot(int nbLignesDepot){this.nbLignesDepot = nbLignesDepot;}

    public int getNumeroBon(){return numeroBon;}

    public String getNumeroTel(){return numeroTel;}

    public LocalDate getDateEmissionBon(){return dateEmissionBon;}

    public LigneDepot[] getLignesDepot(){return lignesDepot;}

    public int getNbLignesDepot(){return nbLignesDepot;}

    public boolean ajouterLigne(String numero, int nbExemplaireDepose){
        if(this.nbLignesDepot >= max_lignes){
            System.out.println("Erreur : le bon de dépôt est plein.");
            return false;
        }

        LigneDepot nouvelleLigne = new LigneDepot(numero, nbExemplaireDepose);

        this.lignesDepot[this.nbLignesDepot] = nouvelleLigne;

        this.nbLignesDepot++;
        return true;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        // Entête du bon de dépôt
        sb.append("Bon n° ").append(getNumeroBon());
        sb.append(" - Client : ").append(getNumeroTel());
        sb.append(" - Date : ").append(getDateEmissionBon());
        sb.append(" (").append(getNbLignesDepot()).append(" articles)\n");
        sb.append("Détail des lignes de dépôt :\n");

        // Ajout des lignes de dépôt
        for (int i = 0; i < this.nbLignesDepot; i++) {
            // Utilise le toString() de LigneDepot
            sb.append("  - ").append(lignesDepot[i].toString()).append("\n");
        }

        return sb.toString();
    }

}
