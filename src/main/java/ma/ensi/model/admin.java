package ma.ensi.model;

public class admin extends adminutilisateur {

    // Default constructor
    public admin() {
        super(); // Calls the parent class constructor
    }

    // Parameterized constructor
    public admin(int id_utilisateur, String nom_utilisateur, String email, String mot_de_passe) {
        super(id_utilisateur, nom_utilisateur, email, mot_de_passe); // Calls the parent constructor
    }

    // Getters
    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public String getEmail() {
        return email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    // Setters
    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
}

