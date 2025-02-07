package ma.ensi.model;

public class Competence {
    private int idCompetence;
    private int idPortfolio; // Reference to the Portfolio
    private String nom; // Name of the competence
    private String niveau; // Level of the competence (e.g., Beginner, Intermediate, Advanced)

    // Getters and Setters
    public int getIdCompetence() {
        return idCompetence;
    }

    public void setIdCompetence(int idCompetence) {
        this.idCompetence = idCompetence;
    }

    public int getIdPortfolio() {
        return idPortfolio;
    }

    public void setIdPortfolio(int idPortfolio) {
        this.idPortfolio = idPortfolio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}
