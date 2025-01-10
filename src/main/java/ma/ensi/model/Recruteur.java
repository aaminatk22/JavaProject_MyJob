package ma.ensi.model;

import java.util.ArrayList;
import java.util.List;

public class Recruteur {
    private String entreprise; // Nom de l'entreprise
    private String poste;      // Poste occupé dans l'entreprise
    private List<Annonce> annonces = new ArrayList<>(); // Liste des annonces publiées

    // Default constructor
    public Recruteur() {}

    // Parameterized constructor
    public Recruteur(String entreprise, String poste, List<Annonce> annonces) {
        this.entreprise = entreprise;
        this.poste = poste;
        this.annonces = (annonces != null) ? annonces : new ArrayList<>();
    }

    // Getters et Setters
    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public List<Annonce> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(List<Annonce> annonces) {
        this.annonces = (annonces != null) ? annonces : new ArrayList<>();
    }
}
