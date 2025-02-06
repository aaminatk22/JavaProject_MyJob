package ma.ensi.model;

public class AgentUniversitaire extends Utilisateur {
    private String universite; // Updated to match database schema

    // Default Constructor
    public AgentUniversitaire(String nom_utilisateur, String email, String mot_de_passe, String nom, String prenom, String nom_universite, String nom_etab, String type_etab) {
        super(); // Calls the parent (Utilisateur) default constructor
    }

    // Constructor with parameters
    public AgentUniversitaire(String nom_utilisateur, String email, String mot_de_passe,
                              String nom, String prenom, String universite) {
        super(); // Calls the parent (Utilisateur) constructor
        super.setNom_utilisateur(nom_utilisateur); // Initializing parent class fields
        super.setEmail(email);
        super.setMot_de_passe(mot_de_passe);
        super.setNom(nom);
        super.setPrenom(prenom);
        this.universite = universite;
    }

    // Constructor with id
    public AgentUniversitaire(int id_utilisateur, String nom_utilisateur, String email, String mot_de_passe,
                              String nom, String prenom, String universite) {
        this(nom_utilisateur, email, mot_de_passe, nom, prenom, universite);
        super.setId_utilisateur(id_utilisateur); // Setting the id for the parent class
    }

    // Getter & Setter for universite
    public String getUniversite() {
        return universite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }
}
