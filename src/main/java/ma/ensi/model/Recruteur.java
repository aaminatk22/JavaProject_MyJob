package ma.ensi.model;

import java.util.ArrayList;
import java.util.List;

public class Recruteur extends Utilisateur {
    private String entreprise; // Name of the company
    private String poste;      // Position within the company
    private List<Annonce> annonces = new ArrayList<>(); // List of job postings

    // Default Constructor
    public Recruteur() {
        super(); // Calls the default constructor of Utilisateur
    }

    // Parameterized Constructor
    public Recruteur(int idUtilisateur, String nomUtilisateur, String email, String motDePasse, String role,
                     String nom, String prenom, String tel, String entreprise, String poste, List<Annonce> annonces) {
        super(); // Calls the parent (Utilisateur) constructor
        this.setIdUtilisateur(idUtilisateur);
        this.setNomUtilisateur(nomUtilisateur);
        this.setEmail(email);
        this.setMotDePasse(motDePasse);
        this.setRole(role);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setTel(tel);

        this.entreprise = entreprise;
        this.poste = poste;
        this.annonces = (annonces != null) ? annonces : new ArrayList<>();
    }

    // Getters and Setters
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
