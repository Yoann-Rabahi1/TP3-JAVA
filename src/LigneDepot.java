/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */
public class LigneDepot {

    private String numero;
    private int nbExemplaires;

    public LigneDepot(String numero, int nbExemplaires){
        this.numero = numero;
        this.nbExemplaires = nbExemplaires;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

    public void setNbExemplaires(int nbExemplaires){
        this.nbExemplaires = nbExemplaires;
    }

    public String getNumero(){
        return numero;
    }

    public int getNbExemplaires(){
        return nbExemplaires;
    }
    
    public String versFichier() {
        return this.nbExemplaires + ":" + this.getNumero();
    }

    @Override
    public String toString(){
        return "[ " + getNumero() + " " + getNbExemplaires() +  " ]";
    }

}