package ma.ensi.model;

import java.util.List;

public class Portfolio {
    private int idPortfolio; // Identifiant unique du portfolio
    private int idUtilisateur; // Référence à l'utilisateur (id_utilisateur dans la table utilisateur)
    private String description; // Description du portfolio
    private List<Competence> competences; // Liste des compétences associées
    private List<Projet> projets; // Liste des projets associés
    private List<Experience> experiences; // Liste des expériences associées
    private List<Document> documents; // Liste des documents associés (new field)

    // Constructeur par défaut
    public Portfolio() {}

    // Constructeur avec paramètres
    public Portfolio(int idPortfolio, int idUser, String description, List<Competence> competences, List<Projet> projets, List<Experience> experiences, List<Document> documents) {
        this.idPortfolio = idPortfolio;
        this.idUtilisateur = idUser;
        this.description = description;
        this.competences = competences;
        this.projets = projets;
        this.experiences = experiences;
        this.documents = documents; // Initialize documents
    }

    // Getters et Setters
    public int getIdPortfolio() {
        return idPortfolio;
    }

    public void setIdPortfolio(int idPortfolio) {
        this.idPortfolio = idPortfolio;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Document> getDocuments() { // Getter for documents
        return documents;
    }

    public void setDocuments(List<Document> documents) { // Setter for documents
        this.documents = documents;
    }
}
