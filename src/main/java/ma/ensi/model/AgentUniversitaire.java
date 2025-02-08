package ma.ensi.model;

public class AgentUniversitaire extends Utilisateur {
    private String universite;

    // Constructeur par défaut
    public AgentUniversitaire(int id, String nomUtilisateur, String email, String motDePasse, String nom, String prenom, String nomUniversite) {
        super();
    }

    // Constructeur avec paramètres
    public AgentUniversitaire(String nomUtilisateur, String email, String motDePasse, String nom, String prenom, String universite) {
        super.setNomUtilisateur(nomUtilisateur);
        super.setEmail(email);
        super.setMotDePasse(motDePasse);
        super.setNom(nom);
        super.setPrenom(prenom);
        this.universite = universite;
    }

    // Getters et Setters
    public String getUniversite() {
        return universite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }
}
