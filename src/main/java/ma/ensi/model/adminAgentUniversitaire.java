package ma.ensi.model;


public class adminAgentUniversitaire extends adminutilisateur {
    private String nomEtab;
    private String typeEtab;
    private String nomUniversite;

    // Constructeur par défaut
    public adminAgentUniversitaire(String nom_utilisateur, String email, String mot_de_passe, String nom_universite, String nomEtab, String typeEtab) {
        super();
    }

    // Constructeur principal
    public adminAgentUniversitaire(String nom_utilisateur, String email, String mot_de_passe,
                                   String nom, String prenom, String nomUniversite,
                                   String nomEtab, String typeEtab) {
        super(nom_utilisateur, email, mot_de_passe, "agent", nom, prenom); // "agent" comme rôle par défaut
        this.nomUniversite = nomUniversite;
        this.nomEtab = nomEtab;
        this.typeEtab = typeEtab;
    }

    // Constructeur avec ID
    public adminAgentUniversitaire(int id_utilisateur, String nom_utilisateur, String email, String mot_de_passe,
                                   String nom, String prenom, String nomUniversite,
                                   String nomEtab, String typeEtab) {
        this(nom_utilisateur, email, mot_de_passe, nom, prenom, nomUniversite, nomEtab, typeEtab);
        super.setId_utilisateur(id_utilisateur);
    }

    // Getters et Setters
    public String getNomEtab() {
        return nomEtab;
    }

    public void setNomEtab(String nomEtab) {
        this.nomEtab = nomEtab;
    }

    public String getTypeEtab() {
        return typeEtab;
    }

    public void setTypeEtab(String typeEtab) {
        this.typeEtab = typeEtab;
    }

    public String getNomUniversite() {
        return nomUniversite;
    }

    public void setNomUniversite(String nomUniversite) {
        this.nomUniversite = nomUniversite;
    }
}
